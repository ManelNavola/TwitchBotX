package com.manelnavola.twitchbotx;

import org.pircbotx.Configuration;
import org.pircbotx.InputParser;
import org.pircbotx.PircBotX;

import com.manelnavola.twitchbotx.TwitchInputParser;

public class TwitchBotXFactory extends Configuration.BotFactory {
	public InputParser createInputParser(PircBotX bot) {
		return new TwitchInputParser(bot);
	}
}
