package com.manelnavola.twitchbotxold;

import org.pircbotx.Configuration;
import org.pircbotx.InputParser;
import org.pircbotx.PircBotX;

import com.manelnavola.twitchbotxold.TwitchInputParser;

public class TwitchBotXFactory extends Configuration.BotFactory {
	public InputParser createInputParser(PircBotX bot) {
		return new TwitchInputParser(bot);
	}
}
