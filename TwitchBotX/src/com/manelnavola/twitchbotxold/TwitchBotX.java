package com.manelnavola.twitchbotxold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.net.ssl.SSLSocketFactory;

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.manelnavola.twitchbotxold.events.*;

public class TwitchBotX extends ListenerAdapter {
	
	private TwitchBotXInterface tbxi;
	private PircBotX bot = new PircBotX(new Configuration.Builder().setAutoNickChange(false).setOnJoinWhoEnabled(false)
			.setName("justinfan12345").addServer("irc.twitch.tv", 6697).setSocketFactory(SSLSocketFactory.getDefault())
			.addListener(this).setBotFactory((Configuration.BotFactory) new TwitchBotXFactory()).buildConfiguration());
	private AtomicBoolean botCrashed = new AtomicBoolean(false);
	private AtomicBoolean botStarted = new AtomicBoolean(false);
	private Thread botStartThread = new Thread() {
		@Override
		public void run() {
			try {
				TwitchBotX.this.bot.startBot();
			} catch (Exception e) {
				if (!botStarted.get()) {
					e.printStackTrace();
				}
			} finally {
				TwitchBotX.this.botCrashed.set(true);
			}
		}
	};
	private Map<String, TwitchUser> userMap = new HashMap<String, TwitchUser>();
	
	public TwitchBotX(TwitchBotXInterface inter) {
		tbxi = inter;
		this.botStartThread.start();
	}
	
	public void onEvent(Event event) throws Exception {
		if (event instanceof UserNoticeEvent) {
			this.onUserNotice((UserNoticeEvent) event);
		} else {
			super.onEvent(event);
		}
	}

	public PircBotX getPircBotX() {
		return this.bot;
	}

	public void joinChannel(String ch) {
		this.bot.send().joinChannel(ch);
	}

	public void leaveChannel(String ch) {
		this.bot.getUserChannelDao().getChannel(ch).send().part();
	}

	public List<String> getConnectedChannels() {
		ArrayList<String> cc = new ArrayList<String>();
		for (Channel c : this.bot.getUserChannelDao().getAllChannels()) {
			cc.add(c.getName());
		}
		return cc;
	}

	public boolean isConnectedTo(String ch) {
		return this.bot.getUserChannelDao().containsChannel(ch);
	}

	public boolean waitForStartup() {
		while (!this.botCrashed.get()) {
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!this.bot.isConnected()) return false;
			this.bot.sendRaw().rawLineNow("CAP REQ :twitch.tv/tags twitch.tv/commands");
			botStarted.set(true);
			return true;
		}
		return false;
	}

	public void onMessage(MessageEvent event) throws Exception {
		if (tbxi == null) {
			System.err.println("This twitch bot does not have an interface!");
			return;
		}
		TwitchUser tu = this.getTwitchUser((Map<String, String>) event.getV3Tags());
		if (tu != null) {
			if (event.getV3Tags().containsKey("bits")) {
				try {
					int bits = Integer.parseInt(event.getV3Tags().get("bits"));
					tbxi.onTwitchMessage(new TwitchMessageEvent(tu, event.getMessage(), event.getChannelSource(),
							bits));
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
				
			} else {
				tbxi.onTwitchMessage(new TwitchMessageEvent(tu, event.getMessage(), event.getChannelSource()));
			}
		}
	}

	public void onUserNotice(UserNoticeEvent event) throws Exception {
		String msgId = (String) event.getV3Tags().get((Object) "msg-id");
		if (tbxi == null) {
			System.err.println("This twitch bot does not have an interface!");
			return;
		}
		if (msgId != null) {
			switch (msgId) {
			case "sub":
			case "resub":
			case "subgift":
			case "anonsubgift": {
				tbxi.onTwitchSubscription(new TwitchSubscriptionEvent(event));
				return;
			}
			case "submysterygift": {
				tbxi.onTwitchMysteryGift(new TwitchMysteryGiftEvent(event));
				return;
			}
			case "giftpaidupgrade":
			case "anongiftpaidupgrade": {
				tbxi.onTwitchGiftUpgrade(new TwitchGiftUpgradeEvent(event));
				return;
			}
			case "rewardgift": {
				tbxi.onTwitchReward(new TwitchRewardEvent(event));
				return;
			}
			case "raid":
			case "unraid": {
				tbxi.onTwitchRaid(new TwitchRaidEvent(event));
				return;
			}
			case "ritual": {
				tbxi.onTwitchRitual(new TwitchRitualEvent(event));
				return;
			}
			case "bitsbadgetier": {
				tbxi.onTwitchBitsBadge(new TwitchBitsBadgeEvent(event));
				return;
			}
			}
		}
	}

	public TwitchUser getTwitchUser(Map<String, String> tags) {
		String tuuid = tags.get("user-id");
		if (tuuid == null) {
			return null;
		}
		TwitchUser tu = this.userMap.get(tuuid);
		if (tu == null) {
			tu = new TwitchUser(tags);
			this.userMap.put(tuuid, tu);
		} else {
			tu.checkTags(tags);
		}
		return tu;
	}

	public void dispose() {
		if (this.bot.isConnected()) {
			this.bot.stopBotReconnect();
			this.bot.sendIRC().quitServer();
			this.bot.close();
		}
		try {
			this.botStartThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
