package com.manelnavola.twitchbotx.domain;

import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.pircbotx.InputParser;
import org.pircbotx.PircBotX;
import org.pircbotx.UserHostmask;
import com.google.common.collect.ImmutableMap;
import com.manelnavola.twitchbotx.TwitchBotX;
import com.manelnavola.twitchbotx.TwitchBotXListenerAdapter;
import com.manelnavola.twitchbotx.events.*;

/**
 * Class for parsing raw IRC messages into Twitch events
 * 
 * @author Manel Navola
 *
 */
public class TwitchParser extends InputParser {

	private TwitchBotX twitchBotX;

	/**
	 * TwitchParser constructor
	 * 
	 * @param twitchBotX The TwitchBotX instance to call events on
	 * @param pircBotX   The PircBotX instance to parse
	 */
	public TwitchParser(@NonNull TwitchBotX twitchBotX, PircBotX pircBotX) {
		super(pircBotX);
		this.twitchBotX = twitchBotX;
	}

	/**
	 * Overrides command processing to parse Twitch USERNOTICE events
	 */
	@Override
	public void processCommand(String target, UserHostmask source, String command, String line, List<String> parsedLine,
			ImmutableMap<String, String> tags) throws IOException {
		if (command.equals("USERNOTICE")) {
			TwitchBotXListenerAdapter adapter = twitchBotX.getListenerAdapter();
			if (adapter == null)
				return;
			String msdId = tags.get("msg-id");
			if (msdId == null)
				return;
			String channel = target.length() != 0 && this.bot.getUserChannelDao().containsChannel(target)
					? this.bot.getUserChannelDao().getChannel(target).getName()
					: null;
			if (channel == null)
				return;
			channel = channel.substring(1);
			switch (msdId) {
			case "sub":
			case "resub":
			case "subgift":
			case "anonsubgift":
				adapter.onTwitchSubscription(new TwitchSubscriptionEvent(channel, tags));
				return;
			case "submysterygift":
				adapter.onTwitchMysteryGift(new TwitchMysteryGiftEvent(channel, tags));
				return;
			case "giftpaidupgrade":
			case "anongiftpaidupgrade":
				adapter.onTwitchGiftUpgrade(new TwitchGiftUpgradeEvent(channel, tags));
				return;
			case "rewardgift":
				adapter.onTwitchReward(new TwitchRewardEvent(channel, tags));
				return;
			case "raid":
			case "unraid":
				adapter.onTwitchRaid(new TwitchRaidEvent(channel, tags));
				return;
			case "ritual":
				adapter.onTwitchRitual(new TwitchRitualEvent(channel, tags));
				return;
			case "bitsbadgetier":
				adapter.onTwitchBitsBadge(new TwitchBitsBadgeEvent(channel, tags));
				return;
			}
			;
		} else {
			super.processCommand(target, source, command, line, parsedLine, tags);
		}
	}

	/*
	 * Utility method that may be added in the future private List<Pair<Integer,
	 * Integer>> parseEmotes(String emoteList) { List<Pair<Integer, Integer>> tr =
	 * new ArrayList<>();
	 * 
	 * StringTokenizer st = new StringTokenizer(emoteList, "/"); while
	 * (st.hasMoreTokens()) { String emoteString = st.nextToken(); int
	 * emoteStringIndex = emoteString.indexOf(':'); String indicesList =
	 * emoteString.substring(emoteStringIndex+1); StringTokenizer st2 = new
	 * StringTokenizer(indicesList, ","); while (st2.hasMoreTokens()) { String
	 * emotePair = st2.nextToken(); int index = emotePair.indexOf('-'); int l =
	 * Integer.parseInt(emotePair.substring(0,index)); int r =
	 * Integer.parseInt(emotePair.substring(index+1));
	 * 
	 * tr.add(Pair.of(l, r)); }
	 * 
	 * } return tr; }
	 */

}
