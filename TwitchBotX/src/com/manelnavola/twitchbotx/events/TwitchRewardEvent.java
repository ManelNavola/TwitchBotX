package com.manelnavola.twitchbotx.events;

public class TwitchRewardEvent extends TwitchEvent {
	private String sourceName = (String) this.tags.get("display-name");
	private int bitsAmount = -1;
	private String domain = (String) this.tags.get("msg-param-domain");
	private int minCheerAmount = -1;
	private int selectedCount = -1;

	public TwitchRewardEvent(UserNoticeEvent event) {
		super(event);
		try {
			this.bitsAmount = Integer.parseInt((String) this.tags.get("msg-param-bits-amount"));
		} catch (NumberFormatException numberFormatException) {
			// empty catch block
		}
		try {
			this.minCheerAmount = Integer.parseInt((String) this.tags.get("msg-param-min-cheer-amount"));
		} catch (NumberFormatException numberFormatException) {
			// empty catch block
		}
		try {
			this.selectedCount = Integer.parseInt((String) this.tags.get("msg-param-selected-count"));
		} catch (NumberFormatException numberFormatException) {
			// empty catch block
		}
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public int getBitsAmount() {
		return this.bitsAmount;
	}

	public String getDomain() {
		return this.domain;
	}

	public int getMinCheerAmount() {
		return this.minCheerAmount;
	}

	public int getSelectedCount() {
		return this.selectedCount;
	}
}
