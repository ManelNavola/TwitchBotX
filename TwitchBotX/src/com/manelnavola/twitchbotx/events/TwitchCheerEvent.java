package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Class for defining a cheer event
 * 
 * @author Manel Navola
 *
 */
public class TwitchCheerEvent extends TwitchMessageEvent {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchCheerEvent.class);

	private int bits = -1;

	/**
	 * TwitchCheerEvent constructor
	 * 
	 * @param channelName The name of the channel where the cheer was issued
	 * @param tags        The IRC tags
	 */
	public TwitchCheerEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags, tags.get("message"));

		try {
			this.bits = Integer.parseInt(tags.get("bits"));
		} catch (NumberFormatException numberFormatException) {
			LOG.warn("Could not parse bit amount!", numberFormatException);
		}
	}

	/**
	 * Gets the amount of bits cheered
	 * 
	 * @return The bits amount or -1 if absent
	 */
	public int getBits() {
		return bits;
	}

}
