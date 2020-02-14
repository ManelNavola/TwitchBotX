package twitchbotx.domain;

import java.io.IOException;
import java.util.List;
import org.pircbotx.InputParser;
import org.pircbotx.PircBotX;
import org.pircbotx.UserHostmask;
import com.google.common.collect.ImmutableMap;

public class TwitchParser extends InputParser {
	
	/**
	 * Default constructor
	 * @param bot The PircBotX instance to parse based on Twitch IRC
	 */
	public TwitchParser(PircBotX bot) {
		super(bot);
	}
	
	/**
	 * Overrides command processing to parse Twitch USERNOTICE events
	 */
	@Override
	public void processCommand(String target, UserHostmask source, String command, String line,
			List<String> parsedLine, ImmutableMap<String, String> tags) throws IOException {
		if (command.equals("USERNOTICE")) {
			/*Channel channel = target.length() != 0
					&& this.bot.getUserChannelDao().containsChannel(target)
					? this.bot.getUserChannelDao().getChannel(target)
					: null;
			this.configuration.getListenerManager()
					.onEvent((Event) new UserNoticeEvent(this.bot,
					channel, target, source, tags, tm, line));*/
		} else {
			super.processCommand(target, source, command, line, parsedLine, tags);
		}
	}
	
	/* Utility method that may be added in the future
	 * private List<Pair<Integer, Integer>> parseEmotes(String emoteList) {
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
	}*/

}
