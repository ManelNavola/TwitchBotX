package com.manelnavola.twitchbotxold;

import java.util.Map;
import java.util.StringTokenizer;

public class TwitchUser {
	private String tuuid;
	private String nickname;
	private boolean hasBadge = false;
	private boolean[] userType = new boolean[7];
	private short subbedMonths = (short) -1;

	public TwitchUser(Map<String, String> tags) {
		this.checkTags(tags);
	}

	public void checkTags(Map<String, String> tags) {
		String badgeInfo;
		String badges;
		String nn;
		this.subbedMonths = (short) -1;
		this.hasBadge = false;
		String l_tuuid = tags.get("user-id");
		if (l_tuuid != null) {
			this.tuuid = l_tuuid;
		}
		if ((nn = tags.get("display-name")) != null) {
			this.nickname = nn;
		}
		if ((badgeInfo = tags.get("badge-info")) != null) {
			StringTokenizer st = new StringTokenizer(badgeInfo, ",");
			while (st.hasMoreTokens()) {
				String[] badge = st.nextToken().split("/", 2);
				switch (badge[0]) {
				case "subscriber": {
					try {
						this.subbedMonths = Short.parseShort(badge[1]);
						break;
					} catch (NumberFormatException nfe) {
						nfe.printStackTrace();
					}
				}
				}
			}
		}
		if ((badges = tags.get("badges")) != null) {
			StringTokenizer st = new StringTokenizer(badges, ",");
			while (st.hasMoreTokens()) {
				String[] badge = st.nextToken().split("/", 2);
				switch (badge[0]) {
				case "admin": {
					this.userType[0] = true;
					this.hasBadge = true;
					break;
				}
				case "broadcaster": {
					this.userType[1] = true;
					this.hasBadge = true;
					break;
				}
				case "global_mod": {
					this.userType[2] = true;
					this.hasBadge = true;
					break;
				}
				case "moderator": {
					this.userType[3] = true;
					this.hasBadge = true;
					break;
				}
				case "subscriber": {
					this.userType[4] = true;
					this.hasBadge = true;
					break;
				}
				case "staff": {
					this.userType[5] = true;
					this.hasBadge = true;
					break;
				}
				case "turbo": {
					this.userType[6] = true;
					this.hasBadge = true;
				}
				}
			}
		}
	}

	public String getUUID() {
		return this.tuuid;
	}

	public String getNickname() {
		return this.nickname;
	}

	public int getSubbedMonths() {
		return this.subbedMonths;
	}

	public boolean hasBadge() {
		return this.hasBadge;
	}

	public boolean isAdmin() {
		return this.userType[0];
	}

	public boolean isBroadcaster() {
		return this.userType[1];
	}

	public boolean isGlobalMod() {
		return this.userType[2];
	}

	public boolean isModerator() {
		return this.userType[3];
	}

	public boolean isSubscriber() {
		return this.userType[4];
	}

	public boolean isStaff() {
		return this.userType[5];
	}

	public boolean isTurbo() {
		return this.userType[6];
	}
}
