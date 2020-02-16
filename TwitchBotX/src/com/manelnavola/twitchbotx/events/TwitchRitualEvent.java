package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Class for defining a ritual event
 * 
 * @author Manel Navola
 *
 */
public class TwitchRitualEvent extends TwitchUserNoticeEvent {

	private String ritualName;

	/**
	 * TwitchRitualEvent constructor
	 * 
	 * @param channelName The name of the channel where the ritual was issued
	 * @param tags        The IRC tags
	 */
	public TwitchRitualEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags);

		this.ritualName = tags.get("msg-param-ritual-name");
	}

	/**
	 * Gets the name of the ritual
	 * 
	 * @return The ritual's name
	 */
	@Nullable
	public String getRitualName() {
		return this.ritualName;
	}

}
