package com.manelnavola.twitchbotx.domain;

import org.eclipse.jdt.annotation.NonNull;
import org.pircbotx.PircBotX;

/**
 * The thread in charge of maintaining a thread for the PircBotX connection
 * start process
 * 
 * @author Manel Navola
 *
 */
public class TwitchBotXConnectThread extends Thread {

	private PircBotX pircBotX;
	private Exception exitException;

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitchBotXConnectThread.class);

	/**
	 * TwitchBotXConnectThread constructor
	 * 
	 * @param pircBotX The bot to start
	 */
	public TwitchBotXConnectThread(@NonNull PircBotX pircBotX) {
		this.pircBotX = pircBotX;
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
			this.pircBotX.startBot();
		} catch (Exception e) {
			LOG.info("Exited from bot thread", e);
			exitException = e;
		}
	}

}
