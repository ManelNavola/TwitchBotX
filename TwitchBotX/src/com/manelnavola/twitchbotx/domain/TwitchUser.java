package com.manelnavola.twitchbotx.domain;

import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Utility class for representing a Twitch User
 * 
 * @author Manel Navola
 *
 */
public class TwitchUser {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchUser.class);

	/**
	 * Different badges a TwitchUser can have
	 *
	 */
	public enum Badge {
		TURBO((byte) 0), SUBSCRIBER((byte) 1), MODERATOR((byte) 2), BROADCASTER((byte) 3), STAFF((byte) 4),
		GLOBAL_MOD((byte) 5), ADMIN((byte) 6), BITS((byte) 7);

		private byte value;

		/**
		 * Enum constructor
		 * 
		 * @param value The numerical value assigned to this enum in byte form
		 */
		private Badge(byte value) {
			this.value = value;
		}

		/**
		 * Gets the numerical value assigned to this enum
		 * 
		 * @return Numerical value in byte form
		 */
		public byte getValue() {
			return this.value;
		}
	}

	private Short subscriptionMonths;
	private boolean[] badges;
	private final Map<String, String> tags;

	/**
	 * TwitchUser constructor
	 * 
	 * @param tags The tags to parse the TwitchUser with
	 */
	public TwitchUser(@NonNull final Map<String, String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets all user tags, used for exceptional cases
	 * 
	 * @return The user tags
	 */
	@NonNull
	public Map<String, String> getTags() {
		return tags;
	}

	/**
	 * Gets the user id of the user
	 * 
	 * @return The user id
	 */
	@Nullable
	public String getUserId() {
		return tags.get("user-id");
	}

	/**
	 * Gets the display name of the user
	 * 
	 * @return The display name
	 */
	@Nullable
	public String getDisplayName() {
		return tags.get("display-name");
	}

	/**
	 * Gets the subscription months of the user,
	 * 
	 * @return The number of subscription months or -1 if absent
	 */
	public short getSubscribedMonths() {
		if (this.subscriptionMonths != null)
			return this.subscriptionMonths;
		String badgeInfo;
		if ((badgeInfo = tags.get("badge-info")) != null) {
			StringTokenizer st = new StringTokenizer(badgeInfo, ",");
			while (st.hasMoreTokens()) {
				String[] badge = st.nextToken().split("/", 2);
				switch (badge[0]) {
				case "subscriber":
				case "founder":
					try {
						this.subscriptionMonths = Short.parseShort(badge[1]);
						return this.subscriptionMonths;
					} catch (NumberFormatException nfe) {
						LOG.error("Error parsing subscription months", nfe);
					}
					break;
				}
			}
		}
		return -1;
	}

	/**
	 * Checks if the user contains a certain badge
	 * 
	 * @param badge The badge to check
	 * @return True if the user belongs to that role
	 */
	public boolean hasBadge(Badge badge) {
		if (this.badges == null) {
			String badgeChain;
			if ((badgeChain = tags.get("badges")) != null) {
				this.badges = new boolean[8];
				StringTokenizer st = new StringTokenizer(badgeChain, ",");
				while (st.hasMoreTokens()) {
					switch (st.nextToken().split("/", 2)[0]) {
					case "turbo":
						this.badges[0] = true;
						break;
					case "subscriber":
					case "founder":
						this.badges[1] = true;
						break;
					case "moderator":
						this.badges[2] = true;
						break;
					case "broadcaster":
						this.badges[3] = true;
						break;
					case "staff":
						this.badges[4] = true;
						break;
					case "global_mod":
						this.badges[5] = true;
						break;
					case "admin":
						this.badges[6] = true;
						break;
					case "bits":
						this.badges[7] = true;
					}
				}
			}
		}
		return this.badges[badge.getValue()];
	}

}
