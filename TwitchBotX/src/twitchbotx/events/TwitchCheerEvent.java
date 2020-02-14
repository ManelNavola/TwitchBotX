package twitchbotx.events;

import twitchbotx.domain.TwitchUser;

public class TwitchCheerEvent extends TwitchMessageEvent {
	
	private int bits;
	
	public TwitchCheerEvent(String channelSource, TwitchUser twitchUser, int bits, String message) {
		super(channelSource, twitchUser, message);
		
		this.bits = bits;
	}
	
	public int getBits() {
		return bits;
	}
	
	@Override
	public String toString() {
		return getTwitchUser().getDisplayName() + "cheered with " + this.bits + "bits: " + getMessage();
	}
	
}
