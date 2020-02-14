package com.manelnavola.twitchbotxold;

import com.manelnavola.twitchbotxold.events.TwitchBitsBadgeEvent;
import com.manelnavola.twitchbotxold.events.TwitchGiftUpgradeEvent;
import com.manelnavola.twitchbotxold.events.TwitchMessageEvent;
import com.manelnavola.twitchbotxold.events.TwitchMysteryGiftEvent;
import com.manelnavola.twitchbotxold.events.TwitchRaidEvent;
import com.manelnavola.twitchbotxold.events.TwitchRewardEvent;
import com.manelnavola.twitchbotxold.events.TwitchRitualEvent;
import com.manelnavola.twitchbotxold.events.TwitchSubscriptionEvent;

public interface TwitchBotXInterface {
	
	public void onTwitchSubscription(TwitchSubscriptionEvent tsee);
	public void onTwitchMysteryGift(TwitchMysteryGiftEvent te);
	public void onTwitchGiftUpgrade(TwitchGiftUpgradeEvent tue);
	public void onTwitchReward(TwitchRewardEvent tre);
	public void onTwitchRaid(TwitchRaidEvent tre);
	public void onTwitchRitual(TwitchRitualEvent tre);
	public void onTwitchBitsBadge(TwitchBitsBadgeEvent tbbe);
	public void onTwitchMessage(TwitchMessageEvent tm);
	
}
