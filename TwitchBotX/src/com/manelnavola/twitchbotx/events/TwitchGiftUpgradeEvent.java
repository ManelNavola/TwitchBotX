package com.manelnavola.twitchbotx.events;

public class TwitchGiftUpgradeEvent extends TwitchEvent {
	private String gifterName;
	private String receiverName;
	private boolean anon;
	private int gifterTotalGifts;
	private String promoName;

	public TwitchGiftUpgradeEvent(UserNoticeEvent event) {
		super(event);
		String msgId = (String) this.tags.get("msg-id");
		this.receiverName = (String) this.tags.get("display-name");
		if (msgId.equals("anongiftpaidupgrade")) {
			this.anon = true;
		} else {
			this.gifterName = (String) this.tags.get("msg-param-sender-name");
		}
		this.promoName = (String) this.tags.get("msg-param-promo-name");
		try {
			this.gifterTotalGifts = Integer.parseInt((String) this.tags.get("msg-param-promo-gift-total"));
		} catch (NumberFormatException numberFormatException) {
			// empty catch block
		}
	}

	public String getGifterName() {
		return this.gifterName;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public boolean isAnon() {
		return this.anon;
	}

	public int getGifterTotalGifts() {
		return this.gifterTotalGifts;
	}

	public String getPromoName() {
		return this.promoName;
	}
}
