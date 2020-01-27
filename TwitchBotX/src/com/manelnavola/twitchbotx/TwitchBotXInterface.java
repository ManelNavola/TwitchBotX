package com.manelnavola.twitchbotx;

import com.manelnavola.twitchbotx.events.TwitchBitsBadgeEvent;
import com.manelnavola.twitchbotx.events.TwitchGiftUpgradeEvent;
import com.manelnavola.twitchbotx.events.TwitchMessageEvent;
import com.manelnavola.twitchbotx.events.TwitchMysteryGiftEvent;
import com.manelnavola.twitchbotx.events.TwitchRaidEvent;
import com.manelnavola.twitchbotx.events.TwitchRewardEvent;
import com.manelnavola.twitchbotx.events.TwitchRitualEvent;
import com.manelnavola.twitchbotx.events.TwitchSubscriptionEvent;

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
