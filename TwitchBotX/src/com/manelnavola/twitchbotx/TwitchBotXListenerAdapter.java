package com.manelnavola.twitchbotx;

import org.eclipse.jdt.annotation.NonNull;

import com.manelnavola.twitchbotx.events.*;

/**
 * Listener for user implementation of various Twitch related events
 * 
 * @author Manel Navola
 *
 */
public abstract class TwitchBotXListenerAdapter {

	/**
	 * Listener for a TwitchMessageEvent
	 * 
	 * @param twitchMessageEvent The TwitchMessageEvent triggered
	 */
	public abstract void onTwitchMessage(@NonNull TwitchMessageEvent twitchMessageEvent);

	/**
	 * Listener for a TwitchCheerEvent
	 * 
	 * @param twitchCheerEvent The TwitchCheerEvent triggered
	 */
	public abstract void onTwitchCheer(@NonNull TwitchCheerEvent twitchCheerEvent);

	/**
	 * Listener for a TwitchSubscriptionEvent
	 * 
	 * @param twitchSubscriptionEvent The TwitchSubscriptionEvent triggered
	 */
	public abstract void onTwitchSubscription(@NonNull TwitchSubscriptionEvent twitchSubscriptionEvent);

	/**
	 * Listener for a TwitchMysteryGiftEvent
	 * 
	 * @param twitchMysteryGiftEvent The TwitchMysteryGiftEvent triggered
	 */
	public abstract void onTwitchMysteryGift(@NonNull TwitchMysteryGiftEvent twitchMysteryGiftEvent);

	/**
	 * Listener for a TwitchGiftUpgradeEvent
	 * 
	 * @param twitchGiftUpgradeEvent The TwitchGiftUpgradeEvent triggered
	 */
	public abstract void onTwitchGiftUpgrade(@NonNull TwitchGiftUpgradeEvent twitchGiftUpgradeEvent);

	/**
	 * Listener for a TwitchRewardEvent
	 * 
	 * @param twitchRewardEvent The TwitchRewardEvent triggered
	 */
	public abstract void onTwitchReward(@NonNull TwitchRewardEvent twitchRewardEvent);

	/**
	 * Listener for a TwitchRaidEvent
	 * 
	 * @param twitchRaidEvent The TwitchRaidEvent triggered
	 */
	public abstract void onTwitchRaid(@NonNull TwitchRaidEvent twitchRaidEvent);

	/**
	 * Listener for a TwitchRitualEvent
	 * 
	 * @param twitchRitualEvent The TwitchRitualEvent triggered
	 */
	public abstract void onTwitchRitual(@NonNull TwitchRitualEvent twitchRitualEvent);

	/**
	 * Listener for a TwitchBitsBadgeEvent
	 * 
	 * @param twitchBitsBadgeEvent The TwitchBitsBadgeEvent triggered
	 */
	public abstract void onTwitchBitsBadge(@NonNull TwitchBitsBadgeEvent twitchBitsBadgeEvent);

}
