package com.manelnavola.twitchbotx.events;

import com.manelnavola.twitchbotx.TwitchUser;

public class TwitchMessageEvent {
	private TwitchUser user;
	private String message;
	private String channel;

	public TwitchMessageEvent(TwitchUser tu, String msg, String ch) {
		this.user = tu;
		this.message = msg;
		this.channel = ch;
	}

	public TwitchUser getUser() {
		return this.user;
	}

	public String getContents() {
		return this.message;
	}

	public String getChannelName() {
		return this.channel;
	}
}
