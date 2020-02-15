package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Class for defining a bits badge event
 * 
 * @author Manel Navola
 *
 */
public class TwitchBitsBadgeEvent extends TwitchUserNoticeEvent {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchBitsBadgeEvent.class);

	private String sourceDisplayName;
	private int badgeTierBitsAmount = -1;

	/**
	 * TwitchBitsBadgeEvent constructor
	 * 
	 * @param channelName The name of the channel where the event was issued
	 * @param tags        The IRC tags
	 */
	public TwitchBitsBadgeEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags);

		this.sourceDisplayName = tags.get("display-name");
		try {
			this.badgeTierBitsAmount = Integer.parseInt(tags.get("msg-param-threshold"));
		} catch (NumberFormatException numberFormatException) {
			LOG.warn("Could not parse badge tier bits amount!", numberFormatException);
		}
	}

	/**
	 * Gets the display name of the user that obtain the bits badge
	 * 
	 * @return The user's display name
	 */
	@Nullable
	public String getSourceDisplayName() {
		return sourceDisplayName;
	}

	/**
	 * Gets the amount of bits required for the obtained badge
	 * 
	 * @return The bits amount or -1 if absent
	 */
	public int getBadgeTierBitsAmount() {
		return badgeTierBitsAmount;
	}

}
