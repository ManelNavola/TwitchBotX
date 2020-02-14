package twitchbotx.events;

public class TwitchChannelEvent {
	
	private String channelName;
	
	public TwitchChannelEvent(String channelName) {
		this.channelName = channelName;
	}
	
	public String getChannelName() {
		return channelName;
	}
	
}
