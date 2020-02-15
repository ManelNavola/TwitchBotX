package com.manelnavola.twitchbotx;

import java.nio.charset.Charset;

import javax.net.ssl.SSLSocketFactory;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import com.manelnavola.twitchbotx.domain.TwitchBotXConnectThread;
import com.manelnavola.twitchbotx.domain.TwitchFactory;
import com.manelnavola.twitchbotx.domain.TwitchListener;

/**
 * TwitchBotX is an extension of the IRC Java framework PircBotX.
 * <p>
 * It provides an event-driven architecture to handle the most commonly used
 * Twitch events such as subscription, message, cheers, raids and more.
 * <p>
 * It is easily extendable with an event interface as well as quality of life
 * additions such as methods for obtaining the PircBotX directly and parsing
 * Twitch user messages manually.
 * <p>
 * To perform actions when a Twitch event is triggered, you must implement the
 * TwitchBotXListener interface and add your own code in any event you want to
 * react upon (see {@link TwitchBotXListenerAdapter}).
 *
 * @author Originally by: <a href="http://www.jibble.org/">Paul James Mutton</a>
 *         for <a href="http://www.jibble.org/pircbot.php">PircBot</a>
 *         <p>
 *         Forked and Maintained by Leon Blakey in
 *         <a href="http://github.com/thelq/pircbotx">PircBotX</a>
 *         <p>
 *         Extended for Twitch methods by Manel Navola in
 *         <a href="https://github.com/ManelNavola/TwitchBotX">TwitchBotX</a>
 */
public class TwitchBotX {

	private final PircBotX pircBotX;
	private final TwitchListener twitchListener;
	private final TwitchFactory twitchFactory;

	private TwitchBotXConnectThread botThread;
	private boolean isConnected;

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchBotX.class);

	/**
	 * TwitchBotX constructor
	 * 
	 * @param autoReconnect      Whether the bot should try reconnecting
	 *                           automatically
	 * @param maxReconnectTries  The maximum amount of retries to perform
	 * @param autoReconnectDelay Delay in milliseconds between connection retries
	 */
	public TwitchBotX(boolean autoReconnect, int maxReconnectTries, int autoReconnectDelay) {
		twitchListener = new TwitchListener(this);
		twitchFactory = new TwitchFactory(this);

		pircBotX = new PircBotX(new Configuration.Builder().setAutoNickChange(false).setOnJoinWhoEnabled(false)
				.setEncoding(Charset.forName("UTF-8")).setName("justinfan12345").addServer("irc.twitch.tv", 6697)
				.setSocketFactory(SSLSocketFactory.getDefault()).addListener(twitchListener)
				.setAutoReconnect(autoReconnect).setAutoReconnectDelay(autoReconnectDelay).setBotFactory(twitchFactory)
				.buildConfiguration());
	}

	/**
	 * Another TwitchBotX constructor
	 * 
	 * @param autoReconnect Whether the bot should try reconnecting automatically
	 */
	public TwitchBotX(boolean autoReconnect) {
		this(autoReconnect, 5, 1000);
	}

	/**
	 * Set the listener for Twitch events
	 * 
	 * @param twitchBotXListener The listener to bind
	 */
	public void setListenerAdapter(@Nullable TwitchBotXListenerAdapter twitchBotXListener) {
		twitchListener.setListener(twitchBotXListener);
	}

	/**
	 * Get the listener for Twitch events
	 * 
	 * @return The listener
	 */
	@Nullable
	public TwitchBotXListenerAdapter getListenerAdapter() {
		return twitchListener.getAdapter();
	}

	/**
	 * Starts running the bot and attempts to connect to Twitch servers, locking
	 * until it either fails or succeeds
	 * 
	 * @throws Exception If the bot is already connected or failed to connect
	 */
	public synchronized void start() throws Exception {
		if (botThread != null) {
			Exception e = new Exception("The bot is already running!");
			LOG.error("Tried starting a running bot!", e);
			throw e;
		} else {
			botThread = new TwitchBotXConnectThread(pircBotX);
			botThread.start();
		}

		while (botThread.isAlive()) {
			Thread.sleep(100);
			if (this.isConnected)
				return;
		}

		throw botThread.getExitException();
	}

	/**
	 * Disconnects from Twitch servers and stops the bot
	 */
	public synchronized void stop() {
		if (pircBotX.isConnected()) {
			pircBotX.send().quitServer();
		} else {
			LOG.info("Stopping a disconnected bot...");
			botThread.interrupt();
		}
		pircBotX.stopBotReconnect();
		setIsConnectedInternalUseOnly(false);
		try {
			botThread.join();
		} catch (InterruptedException e) {
			LOG.error("Exception found when joining bot thread", e);
			e.printStackTrace();
		}
		botThread = null;
	}

	/**
	 * Joins a channel
	 * 
	 * @param channelName The name of the channel in lowercase
	 */
	public void joinChannel(@NonNull String channelName) {
		if (!this.isConnected) {
			LOG.error("Cannot join a channel if the bot is not connected!");
			return;
		}
		if (isConnectedTo(channelName)) {
			LOG.info("The bot has already joined channel " + channelName + "!");
			return;
		}
		pircBotX.send().joinChannel("#" + channelName);
	}

	/**
	 * Parts from a Twitch channel
	 * 
	 * @param channelName The name of the Twitch channel in lowercase
	 */
	public void partChannel(@NonNull String channelName) {
		if (!this.isConnected) {
			LOG.error("Cannot join a channel if the bot is not connected!");
			return;
		}
		if (!isConnectedTo(channelName)) {
			LOG.info("The bot is not connected to channel " + channelName + "!");
			return;
		}
		this.pircBotX.getUserChannelDao().getChannel("#" + channelName).send().part();
	}

	/**
	 * Returns whether the bot is connected to a Twitch channel
	 * 
	 * @param channelName The name of the channel in lowercase
	 * @return If the bot is connected to the channel
	 */
	public boolean isConnectedTo(@NonNull String channelName) {
		return this.pircBotX.getUserChannelDao().containsChannel("#" + channelName);
	}

	/**
	 * Sets whether the bot is considered connected Must be used only internally by
	 * the library
	 * 
	 * @param b True if the bot can be considered connected
	 */
	public void setIsConnectedInternalUseOnly(boolean b) {
		isConnected = b;
	}

	/**
	 * Gets the PircBotX assigned to this TwitchBotX
	 * 
	 * @return The PircBotX instance
	 */
	public PircBotX getPircBotX() {
		return pircBotX;
	}

}
