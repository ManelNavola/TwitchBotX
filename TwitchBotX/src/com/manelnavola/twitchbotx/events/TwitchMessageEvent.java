package com.manelnavola.twitchbotx.events;

import com.manelnavola.twitchbotx.TwitchUser;

public class TwitchMessageEvent {
	private TwitchUser user;
	private String message;
	private String channel;
	private int bits = -1;
	private boolean hasBits = false;

	public TwitchMessageEvent(TwitchUser tu, String msg, String ch) {
		this.user = tu;
		this.message = msg;
		this.channel = ch;
	}

	public TwitchMessageEvent(TwitchUser tu, String msg, String ch, int bits) {
		this(tu, msg, ch);
		this.bits = bits;
		this.hasBits = true;
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
	
	public boolean hasBits() {
		return this.hasBits;
	}
	
	public int getBits() {
		return this.bits;
	}
}
