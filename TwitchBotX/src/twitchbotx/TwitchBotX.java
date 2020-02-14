package twitchbotx;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.net.ssl.SSLSocketFactory;

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.UserChannelDao;

import twitchbotx.domain.TwitchFactory;
import twitchbotx.domain.TwitchListener;

public class TwitchBotX {
	
	private final PircBotX pircBotX;
	private final TwitchListener twitchListener;
	private final TwitchFactory twitchBotXFactory;
	
	private TwitchBotXConnectThread botThread;
	private boolean isConnected;
	
	/**
	 * Creates a TwitchBotX
	 * @param autoReconnect Whether the bot should try reconnecting automatically
	 * @param autoReconnectDelay Delay in milliseconds between connection retries
	 */
	public TwitchBotX(boolean autoReconnect, int maxReconnectTries, int autoReconnectDelay) {
		System.setProperty("file.encoding", "UTF-8");
		twitchListener = new TwitchListener(this);
		twitchBotXFactory = new TwitchFactory();
		
		pircBotX = new PircBotX(new Configuration.Builder()
				.setAutoNickChange(false)
				.setOnJoinWhoEnabled(false)
				.setEncoding(Charset.forName("UTF-8"))
				.setName("justinfan12345")
				.addServer("irc.twitch.tv", 6697)
				.setSocketFactory(SSLSocketFactory.getDefault())
				.addListener(twitchListener)
				.setAutoReconnect(autoReconnect)
				.setAutoReconnectDelay(autoReconnectDelay)
				.setBotFactory(twitchBotXFactory)
				.buildConfiguration());
	}
	
	/**
	 * Creates a TwitchBotX
	 * @param autoReconnect Whether the bot should try reconnecting automatically
	 */
	public TwitchBotX(boolean autoReconnect) {
		this(autoReconnect, 5, 1000);
	}
	
	/**
	 * Set the listener for Twitch events
	 * @param twitchBotXListener The listener to bind
	 */
	public void setListener(TwitchBotXListener twitchBotXListener) {
		twitchListener.setListener(twitchBotXListener);
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	/**
	 * Attempts to connect to Twitch servers
	 * @throws Exception If the bot is already connected or failed to connect
	 */
	public void connect() throws Exception {
		if (botThread != null) {
			throw new IOException("The bot is already connected!");
		} else {
			botThread = new TwitchBotXConnectThread(pircBotX);
			botThread.start();
		}
		
		while (botThread.isAlive()) {
			Thread.sleep(100);
			if (isConnected()) return;
		}
		
		throw botThread.getExitException();
	}
	
	/**
	 * Disconnects from Twitch servers
	 */
	public void disconnect() {
		if (pircBotX.isConnected()) {
			pircBotX.send().quitServer();
		}
		pircBotX.stopBotReconnect();
		setConnected(false);
		botThread = null;
	}
	
	/**
	 * Joins a channel
	 * @param channelName The name of the channel in lowercase
	 * @throws IOException If the bot is already connected to the channel
	 */
	public void joinChannel(String channelName) throws IOException {
		if (isConnected()) {
			if (pircBotX.getUserChannelDao().containsChannel(channelName)) {
				throw new IOException("The bot is already connected to " + channelName + "!");
			} else {
				System.out.println("Sent connect to #" + channelName);
				pircBotX.send().joinChannel("#" + channelName);
			}
		}
	}
	
	/**
	 * Parts from a channel
	 * @param channelName The name of the channel in lowercase
	 * @throws IOException If the bot is not connected to the channel
	 */
	public void leaveChannel(String channelName) throws IOException {
		if (isConnected()) {
			UserChannelDao<User, Channel> userChannelDao = pircBotX.getUserChannelDao();
			if (userChannelDao.containsChannel(channelName)) {
				userChannelDao.getChannel(channelName).send().part();
			} else {
				throw new IOException("The bot is not connected to " + channelName + "!");
			}
		}
	}
	
	/**
	 * Sets whether the bot is considered connected
	 * Must be used only internally by the library
	 * @param b True if the bot can be considered connected
	 */
	public void setConnected(boolean b) {
		isConnected = b;
	}
	
}
