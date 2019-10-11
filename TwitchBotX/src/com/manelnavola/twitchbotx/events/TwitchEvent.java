package com.manelnavola.twitchbotx.events;

import java.util.Map;

import com.manelnavola.twitchbotx.TwitchMessage;

public class TwitchEvent {
	protected String channel;
	protected Map<String, String> tags;
	protected TwitchMessage message;
	protected String rawLine;

	public TwitchEvent(UserNoticeEvent event) {
		this.channel = event.getChannelSource();
		this.tags = event.getV3Tags();
		this.message = event.getMessage();
		this.rawLine = event.getRawLine();
	}

	public String getChannel() {
		return this.channel;
	}

	public Map<String, String> getV3Tags() {
		return this.tags;
	}

	public TwitchMessage getMessage() {
		return this.message;
	}

	public String getRawLine() {
		return this.rawLine;
	}
}
