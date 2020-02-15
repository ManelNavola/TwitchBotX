package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Class for defining a mystery gift event
 * 
 * @author Manel Navola
 *
 */
public class TwitchMysteryGiftEvent extends TwitchUserNoticeEvent {

	/**
	 * TwitchMysteryGiftEvent constructor
	 * 
	 * @param channelName The name of the channel where the mystery gift was issued
	 * @param tags        The IRC tags
	 */
	public TwitchMysteryGiftEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags);
	}

}
