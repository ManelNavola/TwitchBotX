package com.manelnavola.twitchbotx.domain;

import org.eclipse.jdt.annotation.NonNull;
import org.pircbotx.Configuration;
import org.pircbotx.InputParser;
import org.pircbotx.PircBotX;

import com.manelnavola.twitchbotx.TwitchBotX;

/**
 * Twitch factory to inject the Twitch Input Parser into bot events
 * 
 * @author Manel Navola
 *
 */
public class TwitchFactory extends Configuration.BotFactory {

	private TwitchBotX twitchBotX;

	/**
	 * TwitchFactory constructor
	 * 
	 * @param twitchBotX The TwitchBotX bot to parse events for
	 */
	public TwitchFactory(@NonNull TwitchBotX twitchBotX) {
		this.twitchBotX = twitchBotX;
	}

	/**
	 * Overrides custom input parsing of PircBotX with added Twitch events
	 */
	@Override
	public InputParser createInputParser(PircBotX pircBotX) {
		return new TwitchParser(twitchBotX, pircBotX);
	}

}
