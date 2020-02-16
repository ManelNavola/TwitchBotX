package com.manelnavola.twitchbotx.events;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Class for defining a gift upgrade event
 * 
 * @author Manel Navola
 *
 */
public class TwitchGiftUpgradeEvent extends TwitchUserNoticeEvent {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchGiftUpgradeEvent.class);

	private String receiverDisplayName = null;
	private String gifterDisplayName = null;
	private boolean isAnon = false;
	private String promoName = null;
	private int gifterTotalGifts = -1;

	/**
	 * TwitchGiftUpgradeEvent constructor
	 * 
	 * @param channelName The name of the channel where the gift upgrade was issued
	 * @param tags        The IRC tags
	 */
	public TwitchGiftUpgradeEvent(@NonNull String channelName, @NonNull Map<String, String> tags) {
		super(channelName, tags);

		this.receiverDisplayName = tags.get("display-name");
		if ("anongiftpaidupgrade".equals(tags.get("msg-id"))) {
			this.isAnon = true;
		} else {
			this.gifterDisplayName = tags.get("msg-param-sender-name");
		}
		this.promoName = tags.get("msg-param-promo-name");
		String tempString = tags.get("msg-param-promo-gift-total");
		if (tempString != null) {
			try {
				this.gifterTotalGifts = Integer.parseInt(tempString);
			} catch (NumberFormatException numberFormatException) {
				LOG.warn("Could not parse total gift amount!", numberFormatException);
			}
		}
	}

	/**
	 * Gets the display name of the user that gifted the upgrade
	 * 
	 * @return The display name of the gifter
	 */
	@Nullable
	public String getGifterName() {
		return this.gifterDisplayName;
	}

	/**
	 * Gets the display name of the user that received the upgrade
	 * 
	 * @return The display name of the receiver
	 */
	@Nullable
	public String getReceiverName() {
		return this.receiverDisplayName;
	}

	/**
	 * Gets whether the gift was gifted anonymously
	 * 
	 * @return True if the gift was anonymous
	 */
	public boolean isAnon() {
		return this.isAnon;
	}

	/**
	 * Gets the total amount of gifts the gifter has issued
	 * 
	 * @return The total amount of the gifter gifts or -1 if absent
	 */
	public int getGifterTotalGifts() {
		return this.gifterTotalGifts;
	}

	/**
	 * Gets the name of the promo
	 * 
	 * @return The promo's name
	 */
	@Nullable
	public String getPromoName() {
		return this.promoName;
	}

}
