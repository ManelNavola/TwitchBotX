package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Class for defining a reward event
 * 
 * @author Manel Navola
 *
 */
public class TwitchRewardEvent extends TwitchUserNoticeEvent {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchRewardEvent.class);

	private String sourceDisplayName = null;
	private int bitsAmount = -1;
	private String domain = null;
	private int minCheerAmount = -1;
	private int selectedCount = -1;

	/**
	 * TwitchRewardEvent constructor
	 * 
	 * @param channelName The name of the channel where the reward was issued
	 * @param tags        The IRC tags
	 */
	public TwitchRewardEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags);

		this.sourceDisplayName = tags.get("display-name");
		this.domain = tags.get("msg-param-domain");

		String tempString = tags.get("msg-param-bits-amount");
		if (tags.get("msg-param-bits-amount") != null) {
			try {
				this.bitsAmount = Integer.parseInt(tempString);
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse bits amount!", numberFormatException);
			}
		}
		tempString = tags.get("msg-param-min-cheer-amount");
		if (tempString != null) {
			try {
				this.minCheerAmount = Integer.parseInt(tempString);
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse min cheer amount!", numberFormatException);
			}
		}
		tempString = tags.get("msg-param-selected-count");
		if (tempString != null) {
			try {
				this.selectedCount = Integer.parseInt(tempString);
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse selected count!", numberFormatException);
			}
		}
	}

	/**
	 * Gets the display name of the source user
	 * 
	 * @return The source user's display name
	 */
	@Nullable
	public String getSourceDisplayName() {
		return this.sourceDisplayName;
	}

	/**
	 * Gets the amount of bits cheered
	 * 
	 * @return The bits amount or -1 if absent
	 */
	public int getBitsAmount() {
		return this.bitsAmount;
	}

	/**
	 * Gets the domain
	 * 
	 * @return The domain
	 */
	@Nullable
	public String getDomain() {
		return this.domain;
	}

	/**
	 * Gets the minimum cheer amount
	 * 
	 * @return The minimum cheer amount or -1 if absent
	 */
	public int getMinCheerAmount() {
		return this.minCheerAmount;
	}

	/**
	 * Gets the selected count
	 * 
	 * @return The selected count or -1 if absent
	 */
	public int getSelectedCount() {
		return this.selectedCount;
	}

}
