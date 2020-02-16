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
	 * Listener for when the bot fails to connect to Twitch servers
	 */
	public void onConnectFail(Exception e) {
	}

	/**
	 * Listener for when the bot successfully connects to Twitch servers
	 */
	public void onConnectSuccess() {
	}

	/**
	 * Listener for when the bot is disconnected from Twitch servers
	 */
	public void onDisconnect() {
	}

	/**
	 * Listener for a TwitchMessageEvent
	 * 
	 * @param twitchMessageEvent The TwitchMessageEvent triggered
	 */
	public void onTwitchMessage(@NonNull TwitchMessageEvent twitchMessageEvent) {
	}

	/**
	 * Listener for a TwitchCheerEvent
	 * 
	 * @param twitchCheerEvent The TwitchCheerEvent triggered
	 */
	public void onTwitchCheer(@NonNull TwitchCheerEvent twitchCheerEvent) {
	}

	/**
	 * Listener for a TwitchSubscriptionEvent
	 * 
	 * @param twitchSubscriptionEvent The TwitchSubscriptionEvent triggered
	 */
	public void onTwitchSubscription(@NonNull TwitchSubscriptionEvent twitchSubscriptionEvent) {
	}

	/**
	 * Listener for a TwitchMysteryGiftEvent
	 * 
	 * @param twitchMysteryGiftEvent The TwitchMysteryGiftEvent triggered
	 */
	public void onTwitchMysteryGift(@NonNull TwitchMysteryGiftEvent twitchMysteryGiftEvent) {
	}

	/**
	 * Listener for a TwitchGiftUpgradeEvent
	 * 
	 * @param twitchGiftUpgradeEvent The TwitchGiftUpgradeEvent triggered
	 */
	public void onTwitchGiftUpgrade(@NonNull TwitchGiftUpgradeEvent twitchGiftUpgradeEvent) {
	}

	/**
	 * Listener for a TwitchRewardEvent
	 * 
	 * @param twitchRewardEvent The TwitchRewardEvent triggered
	 */
	public void onTwitchReward(@NonNull TwitchRewardEvent twitchRewardEvent) {
	}

	/**
	 * Listener for a TwitchRaidEvent
	 * 
	 * @param twitchRaidEvent The TwitchRaidEvent triggered
	 */
	public void onTwitchRaid(@NonNull TwitchRaidEvent twitchRaidEvent) {
	}

	/**
	 * Listener for a TwitchRitualEvent
	 * 
	 * @param twitchRitualEvent The TwitchRitualEvent triggered
	 */
	public void onTwitchRitual(@NonNull TwitchRitualEvent twitchRitualEvent) {
	}

	/**
	 * Listener for a TwitchBitsBadgeEvent
	 * 
	 * @param twitchBitsBadgeEvent The TwitchBitsBadgeEvent triggered
	 */
	public void onTwitchBitsBadge(@NonNull TwitchBitsBadgeEvent twitchBitsBadgeEvent) {
	}

}
