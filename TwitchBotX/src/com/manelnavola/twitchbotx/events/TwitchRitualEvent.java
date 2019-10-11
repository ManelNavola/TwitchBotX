package com.manelnavola.twitchbotx.events;

public class TwitchRitualEvent extends TwitchEvent {
	private String ritualName = (String) this.tags.get("msg-param-ritual-name");

	public TwitchRitualEvent(UserNoticeEvent event) {
		super(event);
	}

	public String getRitualName() {
		return this.ritualName;
	}
}
