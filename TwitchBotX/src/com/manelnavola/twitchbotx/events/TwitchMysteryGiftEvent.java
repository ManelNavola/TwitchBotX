package com.manelnavola.twitchbotx.events;

public class TwitchMysteryGiftEvent extends TwitchEvent {
	public String sourceName;

	public TwitchMysteryGiftEvent(UserNoticeEvent event) {
		super(event);
		this.sourceName = (String) this.tags.get("display-name");
	}

	public String getSourceName() {
		return this.sourceName;
	}
}
