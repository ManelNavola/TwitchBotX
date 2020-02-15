package com.manelnavola.twitchbotx.domain;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.MessageEvent;

import com.manelnavola.twitchbotx.TwitchBotX;
import com.manelnavola.twitchbotx.TwitchBotXListenerAdapter;
import com.manelnavola.twitchbotx.events.TwitchCheerEvent;
import com.manelnavola.twitchbotx.events.TwitchMessageEvent;

/**
 * Class for handling added Twitch events
 * 
 * @author Manel Navola
 *
 */
public class TwitchListener extends ListenerAdapter {

	private TwitchBotX twitchBotX;
	private TwitchBotXListenerAdapter twitchBotXListenerAdapter;

	/**
	 * TwitchListener constructor
	 * 
	 * @param twitchBotX The TwitchBotX to use for the listener
	 */
	public TwitchListener(@NonNull TwitchBotX twitchBotX) {
		this.twitchBotX = twitchBotX;
	}

	/**
	 * Sets the listener for Twitch events
	 * 
	 * @param twitchBotXListener The listener to set
	 */
	public void setListener(@Nullable TwitchBotXListenerAdapter twitchBotXListener) {
		this.twitchBotXListenerAdapter = twitchBotXListener;
	}

	/**
	 * Override onConnect event to send capabilities once being connected to Twitch
	 * IRC
	 */
	@Override
	public void onConnect(ConnectEvent event) throws Exception {
		super.onConnect(event);
		event.getBot().sendRaw().rawLineNow("CAP REQ :twitch.tv/tags twitch.tv/commands");
		twitchBotX.setIsConnectedInternalUseOnly(true);
	}

	/**
	 * Override onDisconnect event to catch failing to login in Twitch Servers
	 */
	@Override
	public void onDisconnect(DisconnectEvent event) throws Exception {
		super.onDisconnect(event);
		twitchBotX.stop();
	}

	/**
	 * Override onMessage event to fork into normal messages and bit cheers
	 */
	@Override
	public void onMessage(MessageEvent event) throws Exception {
		if (this.twitchBotXListenerAdapter != null) {
			if (event.getV3Tags().containsKey("bits")) {
				// Bits message
				this.twitchBotXListenerAdapter
						.onTwitchCheer(new TwitchCheerEvent(event.getChannelSource(), event.getV3Tags()));
			} else {
				// Normal message
				this.twitchBotXListenerAdapter.onTwitchMessage(
						new TwitchMessageEvent(event.getChannelSource(), event.getV3Tags(), event.getMessage()));
			}
		}
	}

	/**
	 * Gets the listener adapter of this listener
	 * 
	 * @return The listener adapter
	 */
	@Nullable
	public TwitchBotXListenerAdapter getAdapter() {
		return twitchBotXListenerAdapter;
	}
}
