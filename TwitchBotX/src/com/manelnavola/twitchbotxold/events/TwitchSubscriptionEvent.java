package com.manelnavola.twitchbotxold.events;

import com.manelnavola.twitchbotxold.events.TwitchEvent;
import com.manelnavola.twitchbotxold.events.UserNoticeEvent;

public class TwitchSubscriptionEvent extends TwitchEvent {
	private String gifterName = "";
	private String receiverName = "";
	private boolean gift = false;
	private boolean anon = false;
	private boolean resub = false;
	private short subMonths = (short) -1;
	private short subMonthsStreak = (short) -1;
	private SubPlan subPlan = SubPlan.NONE;
	private String subPlanName = "";

	public TwitchSubscriptionEvent(UserNoticeEvent event) {
		super(event);
		switch ((String) this.tags.get("msg-id")) {
		case "resub": {
			this.resub = true;
		}
		case "sub": {
			this.receiverName = (String) this.tags.get("display-name");
			try {
				this.subMonths = Short.parseShort((String) this.tags.get("msg-param-cumulative-months"));
			} catch (NumberFormatException numberFormatException) {
				// empty catch block
			}
			try {
				if (Boolean.parseBoolean((String) this.tags.get("msg-param-should-share-streak"))) {
					try {
						this.subMonthsStreak = Short.parseShort((String) this.tags.get("msg-param-streak-months"));
					} catch (NumberFormatException numberFormatException) {
					}
				}
			} catch (NumberFormatException numberFormatException) {
				// empty catch block
			}
			this.checkSubPlan();
			return;
		}
		case "subgift": {
			this.gift = true;
			this.gifterName = (String) this.tags.get("display-name");
			try {
				this.subMonths = Short.parseShort((String) this.tags.get("msg-param-months"));
			} catch (NumberFormatException numberFormatException) {
				// empty catch block
			}
			this.receiverName = (String) this.tags.get("msg-param-recipient-display-name");
			this.checkSubPlan();
			return;
		}
		case "anonsubgift": {
			this.gift = true;
			this.anon = true;
			try {
				this.subMonths = Short.parseShort((String) this.tags.get("msg-param-months"));
			} catch (NumberFormatException numberFormatException) {
				// empty catch block
			}
			this.receiverName = (String) this.tags.get("msg-param-recipient-display-name");
			this.checkSubPlan();
			return;
		}
		}
	}

	public String getGifterName() {
		return this.gifterName;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public boolean isGifted() {
		return this.gift;
	}

	public boolean isAnon() {
		return this.anon;
	}

	public boolean isResub() {
		return this.resub;
	}

	public int getSubMonths() {
		return this.subMonths;
	}

	public int getSubMonthsStreak() {
		return this.subMonthsStreak;
	}

	public SubPlan getSubPlan() {
		return this.subPlan;
	}

	public String getSubPlanName() {
		return this.subPlanName;
	}

	private void checkSubPlan() {
		switch ((String) this.tags.get("msg-param-sub-plan")) {
		case "Prime": {
			this.subPlan = SubPlan.PRIME;
			break;
		}
		case "1000": {
			this.subPlan = SubPlan.LEVEL_1;
			break;
		}
		case "2000": {
			this.subPlan = SubPlan.LEVEL_2;
			break;
		}
		case "3000": {
			this.subPlan = SubPlan.LEVEL_3;
		}
		}
		this.subPlanName = (String) this.tags.get("msg-param-sub-plan-name");
	}

	public static enum SubPlan {
		NONE, PRIME, LEVEL_1, LEVEL_2, LEVEL_3;

	}

}
