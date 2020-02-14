package twitchbotx.domain;

import org.pircbotx.Configuration;
import org.pircbotx.InputParser;
import org.pircbotx.PircBotX;

public class TwitchFactory extends Configuration.BotFactory {
	
	/**
	 * Overrides custom input parsing of PircBotX with
	 * added Twitch events
	 */
	@Override
	public InputParser createInputParser(PircBotX bot) {
		return new TwitchParser(bot);
	}
	
}
