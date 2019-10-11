package com.manelnavola.twitchbotx;

import com.manelnavola.twitchbotx.TwitchMessage;
import com.manelnavola.twitchbotx.events.UserNoticeEvent;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.tuple.Pair;
import org.pircbotx.Channel;
import org.pircbotx.InputParser;
import org.pircbotx.PircBotX;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.Event;

public class TwitchInputParser extends InputParser {
	public TwitchInputParser(PircBotX bot) {
		super(bot);
	}

	public void processCommand(String target, UserHostmask source, String command, String line, List<String> parsedLine,
			ImmutableMap<String, String> tags) throws IOException {
		Channel channel = target.length() != 0 && this.bot.getUserChannelDao().containsChannel(target)
				? this.bot.getUserChannelDao().getChannel(target)
				: null;
		if (command.equals("USERNOTICE")) {
			TwitchMessage tm = new TwitchMessage("", parseEmotes(tags.get("emotes")));
			this.configuration.getListenerManager()
					.onEvent((Event) new UserNoticeEvent(this.bot, channel, target, source, tags, tm, line));
		} else {
			super.processCommand(target, source, command, line, parsedLine, tags);
		}
	}
	
	// Quick custom parser
	private List<Pair<Integer, Integer>> parseEmotes(String emoteList) {
		List<Pair<Integer, Integer>> tr = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(emoteList, "/");
		while (st.hasMoreTokens()) {
			String emoteString = st.nextToken();
			int emoteStringIndex = emoteString.indexOf(':');
			String indicesList = emoteString.substring(emoteStringIndex+1);
			StringTokenizer st2 = new StringTokenizer(indicesList, ",");
			while (st2.hasMoreTokens()) {
				String emotePair = st2.nextToken();
				int index = emotePair.indexOf('-');
		        int l = Integer.parseInt(emotePair.substring(0,index));
		        int r = Integer.parseInt(emotePair.substring(index+1));
		        
		        tr.add(Pair.of(l, r));
			}
			
		}
		return tr;
	}
}
