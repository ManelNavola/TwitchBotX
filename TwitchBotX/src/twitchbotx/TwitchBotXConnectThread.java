package twitchbotx;

import org.pircbotx.PircBotX;

public class TwitchBotXConnectThread extends Thread {
	
	private PircBotX pircBotX;
	private Exception exitException;
	
	public TwitchBotXConnectThread(PircBotX pircBotX) {
		this.pircBotX = pircBotX;
	}
	
	public Exception getExitException() {
		return exitException;
	}
	
	@Override
	public void run() {
		try {
			this.pircBotX.startBot();
		} catch (Exception e) {
			exitException = e;
		}
		System.out.println("Thread done");
	}
	
}
