package com.manelnavola.twitchbotxold;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class TwitchMessage {
	private String text;
	private List<Pair<Integer, Integer>> emoteIndices;

	public TwitchMessage(String txt, List<Pair<Integer, Integer>> emi) {
		this.text = txt;
		this.emoteIndices = emi;
	}
 
	public String getText() {
		return this.text;
	}

	public List<Pair<Integer, Integer>> getEmoteIndices() {
		return this.emoteIndices;
	}
}
