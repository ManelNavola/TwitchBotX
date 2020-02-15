package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Class for defining a subscription event
 * 
 * @author Manel Navola
 *
 */
public class TwitchSubscriptionEvent extends TwitchUserNoticeEvent {

	public static enum SubPlan {
		NONE, PRIME, LEVEL_1, LEVEL_2, LEVEL_3;
	}

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchSubscriptionEvent.class);

	private SubPlan subPlan = null;
	private String subPlanName = null;
	private boolean isResub = false;
	private boolean isGift = false;
	private boolean isAnon = false;
	private String gifterDisplayName = null;
	private String receiverDisplayName = null;
	private short subMonths = -1;
	private short subMonthsStreak = -1;

	/**
	 * TwitchSubscriptionEvent constructor
	 * 
	 * @param channelName The name of the channel where the subscription was issued
	 * @param tags        The IRC tags
	 */
	public TwitchSubscriptionEvent(String channelName, Map<String, String> tags) {
		super(channelName, tags);

		String switchString = tags.get("msg-id");
		if (switchString == null) {
			LOG.warn("Subscription event did not contain a msg-id!");
		}

		switch (switchString) {
		case "resub":
			this.isResub = true;
		case "sub":
			this.receiverDisplayName = tags.get("display-name");
			try {
				this.subMonths = Short.parseShort((String) tags.get("msg-param-cumulative-months"));
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse sub months!", numberFormatException);
			}
			try {
				if (Boolean.parseBoolean(tags.get("msg-param-should-share-streak"))) {
					try {
						this.subMonthsStreak = Short.parseShort(tags.get("msg-param-streak-months"));
					} catch (NumberFormatException numberFormatException) {
					}
				}
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse sub month streak!", numberFormatException);
			}
			break;
		case "subgift":
			this.isGift = true;
			this.gifterDisplayName = tags.get("display-name");
			try {
				this.subMonths = Short.parseShort(tags.get("msg-param-months"));
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse sub months!", numberFormatException);
			}
			this.receiverDisplayName = tags.get("msg-param-recipient-display-name");
			break;
		case "anonsubgift":
			this.isGift = true;
			this.isAnon = true;
			try {
				this.subMonths = Short.parseShort(tags.get("msg-param-months"));
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse sub months!", numberFormatException);
			}
			this.receiverDisplayName = tags.get("msg-param-recipient-display-name");
			break;
		}

		switchString = tags.get("msg-param-sub-plan");
		if (switchString == null) {
			LOG.warn("Subscription event did not contain a msg-param-sub-plan!");
		}

		switch (switchString) {
		case "Prime": {
			this.subPlan = SubPlan.PRIME;
			break;
		}
		case "1000": {
			this.subPlan = SubPlan.LEVEL_1;
			break;
		}
		case "2000": {
			this.subPlan = SubPlan.LEVEL_2;
			break;
		}
		case "3000": {
			this.subPlan = SubPlan.LEVEL_3;
		}
		}
		this.subPlanName = tags.get("msg-param-sub-plan-name");
	}

	/**
	 * Gets the subscription plan
	 * 
	 * @return The sub plan
	 */
	@Nullable
	public SubPlan getSubPlan() {
		return subPlan;
	}

	/**
	 * Gets the name of the subscription plan
	 * 
	 * @return The name of the sub plan
	 */
	@Nullable
	public String getSubPlanName() {
		return subPlanName;
	}

	/**
	 * Gets whether the subscription is a resub
	 * 
	 * @return True if the subscription is a resub
	 */
	public boolean isResub() {
		return isResub;
	}

	/**
	 * Gets whether the subscription is a gift
	 * 
	 * @return True if the subscription is gifted
	 */
	public boolean isGift() {
		return isGift;
	}

	/**
	 * Gets whether the subscription is anonymous
	 * 
	 * @return True if the subscription is anonymous
	 */
	public boolean isAnonymous() {
		return isAnon;
	}

	/**
	 * Gets the display name of the gifter
	 * 
	 * @return The gifter's display name
	 */
	@Nullable
	public String getGifterDisplayName() {
		return gifterDisplayName;
	}

	/**
	 * Gets the display name of the receiver
	 * 
	 * @return The receiver's display name
	 */
	@Nullable
	public String getReceiverDisplayName() {
		return receiverDisplayName;
	}

	/**
	 * Gets the number of months the user has subscribed for
	 * 
	 * @return The sub month amount or -1 if absent
	 */
	public short getSubMonths() {
		return subMonths;
	}

	/**
	 * Gets the subscription months streak
	 * 
	 * @return The sub months streak or -1 if absent
	 */
	public short getSubMonthsStreak() {
		return subMonthsStreak;
	}

}
