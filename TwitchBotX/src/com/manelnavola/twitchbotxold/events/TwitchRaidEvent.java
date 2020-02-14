package com.manelnavola.twitchbotxold.events;

public class TwitchRaidEvent extends TwitchEvent {
	private String raiderName = (String) this.tags.get("msg-param-displayName");
	private int raidSize = -1;
	private boolean raidEnded = false;

	public TwitchRaidEvent(UserNoticeEvent event) {
		super(event);
		if (((String) this.tags.get("msg-id")).equals("raid")) {
			try {
				this.raidSize = Integer.parseInt((String) this.tags.get("msg-param-viewerCount"));
			} catch (NumberFormatException numberFormatException) {
			}
		} else {
			this.raidEnded = true;
		}
	}

	public String getRaiderName() {
		return this.raiderName;
	}

	public int getRaidSize() {
		return this.raidSize;
	}

	public boolean hasRaidEnded() {
		return this.raidEnded;
	}
}
