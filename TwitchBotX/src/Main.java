import java.io.IOException;

import org.pircbotx.exception.IrcException;

import twitchbotx.TwitchBotX;
import twitchbotx.TwitchBotXListener;
import twitchbotx.events.TwitchCheerEvent;
import twitchbotx.events.TwitchMessageEvent;

public class Main {
	
	public static void main(String[] args) throws IOException, IrcException {
		TwitchBotX tbx = new TwitchBotX(true);
		tbx.setListener(new TwitchBotXListener() {

			@Override
			public void onTwitchMessage(TwitchMessageEvent twitchMessageEvent) {
				System.out.println(twitchMessageEvent);
			}

			@Override
			public void onTwitchCheer(TwitchCheerEvent twitchCheerEvent) {
				System.out.println(twitchCheerEvent);
			}
			
		});
		try {
			tbx.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Attempt join");
		tbx.joinChannel("myth");
		System.out.println("Joined channel");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tbx.disconnect();
		System.out.println("Disconnected");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connect again");
		try {
			tbx.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
