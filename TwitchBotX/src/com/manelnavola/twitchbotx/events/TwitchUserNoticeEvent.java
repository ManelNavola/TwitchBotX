package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.manelnavola.twitchbotx.domain.TwitchUser;

/**
 * Class for defining any user notice event
 * 
 * @author Manel Navola
 *
 */
public class TwitchUserNoticeEvent extends TwitchChannelEvent {

	private String message;
	private String systemMessage;
	private TwitchUser twitchUser;

	/**
	 * TwitchUserNoticeEvent constructor
	 * 
	 * @param channelName The name of the channel where the event was issued
	 * @param tags        The IRC tags
	 */
	public TwitchUserNoticeEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags);

		this.twitchUser = new TwitchUser(tags);
		this.message = tags.get("message");
		this.systemMessage = tags.get("system-msg");
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

	/**
	 * Gets the message that has been shown in the Twitch chat on this event
	 * 
	 * @return The system message shown
	 */
	@Nullable
	public String getSystemMessage() {
		return systemMessage;
	}

}
