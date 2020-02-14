package twitchbotx.events;

import twitchbotx.domain.TwitchUser;

public class TwitchMessageEvent extends TwitchChannelEvent {
	
	private TwitchUser twitchUser;
	private String message;
	
	public TwitchMessageEvent(String channelName, TwitchUser twitchUser, String message) {
		super(channelName);
		
		this.twitchUser = twitchUser;
		this.message = message;
	}
	
	public TwitchUser getTwitchUser() {
		return twitchUser;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return this.twitchUser.getDisplayName() + ": " + this.message;
	}
	
}
