package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Class for defining a raid/unraid event
 * 
 * @author Manel Navola
 *
 */
public class TwitchRaidEvent extends TwitchUserNoticeEvent {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchRaidEvent.class);

	private String raiderDisplayName = null;
	private int raidSize = -1;
	private boolean raidEnded = false;

	/**
	 * TwitchRaidEvent constructor
	 * 
	 * @param channelName The name of the channel where the raid/unraid was issued
	 * @param tags        The IRC tags
	 */
	public TwitchRaidEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags);

		raiderDisplayName = tags.get("msg-param-displayName");

		if ("raid".equals(tags.get("msg-id"))) {
			this.raidEnded = false;
			try {
				this.raidSize = Integer.parseInt(tags.get("msg-param-viewerCount"));
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse raid size!", numberFormatException);
			}
		} else if ("unraid".equals(tags.get("msg-id"))) {
			this.raidEnded = true;
		}
	}

	/**
	 * Gets the display name of the raider
	 * 
	 * @return The raider's display name
	 */
	@Nullable
	public String getRaiderDisplayName() {
		return this.raiderDisplayName;
	}

	/**
	 * Gets the raid size
	 * 
	 * @return The size of the raid or -1 if absent
	 */
	public int getRaidSize() {
		return this.raidSize;
	}

	/**
	 * Gets whether the raid has ended
	 * 
	 * @return If the raid has ended
	 */
	@Nullable
	public boolean hasRaidEnded() {
		return this.raidEnded;
	}

}
