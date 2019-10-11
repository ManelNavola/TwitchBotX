package com.manelnavola.twitchbotx;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;

import com.manelnavola.twitchbotx.events.UserNoticeEvent;

public class TwitchListenerAdapter extends ListenerAdapter {
	public void onEvent(Event event) throws Exception {
		if (event instanceof UserNoticeEvent) {
			this.onUserNotice((UserNoticeEvent) event);
		} else {
			super.onEvent(event);
		}
	}

	public void onUserNotice(UserNoticeEvent event) throws Exception {
	}
}
