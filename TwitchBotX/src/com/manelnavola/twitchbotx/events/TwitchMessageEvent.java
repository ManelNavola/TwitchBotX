package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.manelnavola.twitchbotx.domain.TwitchUser;

/**
 * Class for defining any channel message event
 * 
 * @author Manel Navola
 *
 */
public class TwitchMessageEvent extends TwitchChannelEvent {

	private TwitchUser twitchUser;
	private String message;

	/**
	 * TwitchMessageEvent constructor
	 * 
	 * @param channelName The name of the channel where the message was issued
	 * @param tags        The IRC tags
	 * @param message     The chat message issued
	 */
	public TwitchMessageEvent(@NonNull String channelName, @NonNull Map<String, String> tags,
			@Nullable String message) {
		super(channelName, tags);

		this.twitchUser = new TwitchUser(tags);
		this.message = message;
	}

	/**
	 * Gets the TwitchUser related to this event
	 * 
	 * @return The TwitchUser
	 */
	@NonNull
	public TwitchUser getTwitchUser() {
		return twitchUser;
	}

	/**
	 * Gets the message of this event
	 * 
	 * @return The message
	 */
	@Nullable
	public String getMessage() {
		return message;
	}

}
