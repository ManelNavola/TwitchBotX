package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Class for defining any channel event
 * 
 * @author Manel Navola
 *
 */
public abstract class TwitchChannelEvent {

	private String channelName;
	private Map<String, String> tags;

	/**
	 * TwitchChannelEvent constructor
	 * 
	 * @param channelName The name of the channel where the event was issued
	 * @param tags        The IRC tags
	 */
	public TwitchChannelEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		this.channelName = channelName;
		this.tags = tags;
	}

	/**
	 * Gets the channel name of the event
	 * 
	 * @return The channel name
	 */
	@NonNull
	public String getChannelName() {
		return channelName;
	}

	/**
	 * Gets the IRC tags of the event
	 * 
	 * @return The IRC tags
	 */
	@NonNull
	public Map<String, String> getTags() {
		return tags;
	}

}
