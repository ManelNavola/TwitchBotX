package com.manelnavola.twitchbotx.events;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.Event;

import com.manelnavola.twitchbotx.TwitchMessage;
import com.google.common.collect.ImmutableMap;

public class UserNoticeEvent extends Event {
	private final Channel channel;
	private final String channelSource;
	private final UserHostmask userHostmask;
	private final ImmutableMap<String, String> tags;
	private final String rawLine;
	private final TwitchMessage message;

	public UserNoticeEvent(PircBotX bot, Channel channel, String channelSource, UserHostmask userHostmask,
			ImmutableMap<String, String> tags, TwitchMessage tm, String rl) {
		super(bot);
		if (channel == null) {
			throw new NullPointerException("channel");
		}
		if (channelSource == null) {
			throw new NullPointerException("channelSource");
		}
		if (userHostmask == null) {
			throw new NullPointerException("userHostmask");
		}
		this.channel = channel;
		this.channelSource = channelSource;
		this.userHostmask = userHostmask;
		this.tags = tags;
		this.rawLine = rl;
		this.message = tm;
	}

	public void respond(String response) {
		this.getBot().sendRaw().rawLine(response);
	}

	public Channel getChannel() {
		return this.channel;
	}

	public String getChannelSource() {
		return this.channelSource;
	}

	public UserHostmask getUserHostmask() {
		return this.userHostmask;
	}

	public ImmutableMap<String, String> getV3Tags() {
		return this.tags;
	}

	public TwitchMessage getMessage() {
		return this.message;
	}

	public String getRawLine() {
		return this.rawLine;
	}
}
