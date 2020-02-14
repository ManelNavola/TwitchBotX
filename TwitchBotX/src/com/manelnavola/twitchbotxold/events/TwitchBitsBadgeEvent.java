/*
 * Decompiled with CFR 0.137.
 */
package com.manelnavola.twitchbotxold.events;

public class TwitchBitsBadgeEvent extends TwitchEvent {
	private int badgeTierBitAmount = -1;
	private String sourceName = "";

	public TwitchBitsBadgeEvent(UserNoticeEvent event) {
		super(event);
		this.sourceName = (String) this.tags.get("display-name");
		try {
			this.badgeTierBitAmount = Integer.parseInt((String) this.tags.get("msg-param-threshold"));
		} catch (NumberFormatException numberFormatException) {
			// empty catch block
		}
	}

	public int getBadgeTierBitAmount() {
		return this.badgeTierBitAmount;
	}
	
	public String getSourceName() {
		return this.sourceName;
	}
}
