package twitchbotx;

import twitchbotx.events.TwitchCheerEvent;
import twitchbotx.events.TwitchMessageEvent;

public interface TwitchBotXListener {

	void onTwitchMessage(TwitchMessageEvent twitchMessageEvent);

	void onTwitchCheer(TwitchCheerEvent twitchCheerEvent);

}
