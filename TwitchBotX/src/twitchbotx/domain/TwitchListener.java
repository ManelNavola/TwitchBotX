package twitchbotx.domain;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;

import twitchbotx.TwitchBotX;
import twitchbotx.TwitchBotXListener;
import twitchbotx.events.TwitchCheerEvent;
import twitchbotx.events.TwitchMessageEvent;

public class TwitchListener extends ListenerAdapter {

	private TwitchBotX twitchBotX;
	private TwitchBotXListener twitchBotXListener;
	
	public TwitchListener(TwitchBotX twitchBotX) {
		this.twitchBotX = twitchBotX;
	}

	/**
	 * Sets the listener for Twitch events
	 * @param twitchBotXListener The listener to set
	 */
	public void setListener(TwitchBotXListener twitchBotXListener) {
		this.twitchBotXListener = twitchBotXListener;
	}

	/**
	 * Override on connect event to send capabilities
	 */
	@Override
	public void onConnect(ConnectEvent event) throws Exception {
		super.onConnect(event);
		event.getBot().sendRaw().rawLineNow("CAP REQ :twitch.tv/tags twitch.tv/commands");
		twitchBotX.setConnected(true);
	}

	/**
	 * Override message event to fork into normal messages and bit cheers
	 */
	@Override
	public void onMessage(MessageEvent event) throws Exception {
		if (this.twitchBotXListener != null) {
			if (event.getV3Tags().containsKey("bits")) {
				// Bits message
				try {
					int bits = Integer.parseInt(event.getV3Tags().get("bits"));
					this.twitchBotXListener.onTwitchCheer(new TwitchCheerEvent(event.getChannelSource(),
							new TwitchUser(event.getV3Tags()), bits, event.getMessage()));
				} catch (NumberFormatException nfe) {
					
				}
			} else {
				// Normal message
				this.twitchBotXListener.onTwitchMessage(new TwitchMessageEvent(event.getChannelSource(),
						new TwitchUser(event.getV3Tags()), event.getMessage()));
			}
		}
	}
	
	// On user notice event
}
