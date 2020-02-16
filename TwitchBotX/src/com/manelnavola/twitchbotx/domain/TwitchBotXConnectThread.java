package com.manelnavola.twitchbotx.domain;

import org.eclipse.jdt.annotation.NonNull;

import com.manelnavola.twitchbotx.TwitchBotX;

/**
 * The thread in charge of maintaining a thread for the PircBotX connection
 * start process
 * 
 * @author Manel Navola
 *
 */
public class TwitchBotXConnectThread extends Thread {

	private boolean hasShutdown = false;
	private TwitchBotX twitchBotX = null;
	private Exception exitException = null;

	/**
	 * TwitchBotXConnectThread constructor
	 * 
	 * @param twitchBotX The bot to start
	 */
	public TwitchBotXConnectThread(@NonNull TwitchBotX twitchBotX) {
		this.twitchBotX = twitchBotX;
	}

	/**
	 * Fetches the exception that forced the bot to end
	 * 
	 * @return An exception
	 */
	@NonNull
	public Exception getExitException() {
		return exitException;
	}

	/**
	 * Overriden run method to start the PircBotX
	 */
	@Override
	public void run() {
		try {
			this.twitchBotX.getPircBotX().startBot();
		} catch (Exception e) {
			exitException = e;
		}

		if (!this.hasShutdown)
			this.twitchBotX.disconnect();
	}

	/**
	 * Forces the thread to stop, without generating a disconnect event
	 */
	public void shutdown() {
		this.hasShutdown = true;
		this.interrupt();
	}

}
