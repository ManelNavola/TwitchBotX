package twitchbotx.domain;

import java.util.Map;
import java.util.StringTokenizer;

public class TwitchUser {
	
	public enum Badge {
		TURBO((byte) 0), SUBSCRIBER((byte) 1),
		MODERATOR((byte) 2), BROADCASTER((byte) 3),
		STAFF((byte) 4), GLOBAL_MOD((byte) 5),
		ADMIN((byte) 6), BITS((byte) 7);
		
		private byte value;
		
		private Badge(byte value) {
			this.value = value;
		}
		
		public byte getValue() {
			return this.value;
		}
	}
	
	private Short subscriptionMonths;
	private boolean[] badges;
	private final Map<String, String> tags;
	
	public TwitchUser(final Map<String, String> tags) {
		this.tags = tags;
	}
	
	/**
	 * Gets all user tags,
	 * used for exceptional cases
	 * @return The user tags
	 */
	public Map<String, String> getTags() {
		return tags;
	}
	
	/**
	 * Gets the user id of the user
	 * @return The user id
	 */
	public String getUserId() {
		return tags.get("user-id");
	}
	
	/**
	 * Gets the display name of the user
	 * @return The display name
	 */
	public String getDisplayName() {
		return tags.get("display-name");
	}
	
	/**
	 * Gets the subscription months of the user,
	 * @return The number of subscription months or -1 if absent
	 */
	public short getSubscribedMonths() {
		if (this.subscriptionMonths != null) return this.subscriptionMonths;
		String badgeInfo;
		if ((badgeInfo = tags.get("badge-info")) != null) {
			StringTokenizer st = new StringTokenizer(badgeInfo, ",");
			while (st.hasMoreTokens()) {
				String[] badge = st.nextToken().split("/", 2);
				switch (badge[0]) {
				case "subscriber":
					try {
						this.subscriptionMonths = Short.parseShort(badge[1]);
						return this.subscriptionMonths;
					} catch (NumberFormatException nfe) {
						;
					}
					break;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Checks if the user belongs to a certain role
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
					case "admin":
						this.badges[0] = true;
						break;
					case "broadcaster":
						this.badges[1] = true;
						break;
					case "global_mod":
						this.badges[2] = true;
						break;
					case "moderator":
						this.badges[3] = true;
						break;
					case "subscriber":
						this.badges[4] = true;
						break;
					case "staff":
						this.badges[5] = true;
						break;
					case "turbo":
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
