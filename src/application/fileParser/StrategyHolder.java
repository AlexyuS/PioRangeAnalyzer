package application.fileParser;

import java.util.HashMap;
import java.util.Map;

import application.cards.CardHand;

public class StrategyHolder {
	private final String strategy;	
	private Map<CardHand, Double> actionFreqForCards;

	public StrategyHolder(String strategy) {
		this.strategy = strategy;
		this.actionFreqForCards = new HashMap<>();
	}

	public void addCards(CardHand card,Double freq) {
		actionFreqForCards.put(card, freq);
	}
	
	public String getStrategy() {
		return this.strategy;
	}
	
}
