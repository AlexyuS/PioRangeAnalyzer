package main.application.cards;

import java.util.HashMap;
import java.util.Map;

public class SuitedBundle {

	private final CardHand cardHand;

	private double occurance;

	private final Map<String, Double> diffFromPlayer;

	public SuitedBundle(CardHand cardHand, double occurance) {
		this.cardHand = cardHand;
		this.occurance = occurance;
		this.diffFromPlayer = new HashMap<>();
	}

	public CardHand getCardHand() {
		return cardHand;
	}

	public double getOccurance() {
		return occurance;
	}

	public double getDiffFromPlayer(String player) {
		Double diff = diffFromPlayer.get(player);
		if (diff == null) {
			return 0;
		}
		return diff;
	}

	public void setOccurance(Double occurance) throws Exception {
		if (occurance < 0 || occurance > 100) {
			throw new IllegalArgumentException("occurance should between 0 and 100, but was " + occurance);
		}
		this.occurance = occurance;
	}

	public void setDiffFromPlayer(String player, Double diff) {
		diffFromPlayer.put(player, diff);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuitedBundle other = (SuitedBundle) obj;
		if (cardHand == null) {
			if (other.cardHand != null)
				return false;
		} else if (!cardHand.equals(other.cardHand))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardHand == null) ? 0 : cardHand.hashCode());
		return result;
	}

}
