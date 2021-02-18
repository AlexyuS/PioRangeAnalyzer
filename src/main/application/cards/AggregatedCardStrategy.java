package main.application.cards;

public class AggregatedCardStrategy {
	private final Card highCard;
	private final Card lowCard;

	public double getOccurancePercentage() {
		return occurancePercentage;
	}

	public void setOccurancePercentage(double occurancePercentage) {
		this.occurancePercentage = occurancePercentage;
	}

	public double getOccuranceAbsolute() {
		return occuranceAbsolute;
	}

	public void setOccuranceAbsolute(double occuranceAbsolute) {
		this.occuranceAbsolute = occuranceAbsolute;
	}

	public Card getHighCard() {
		return highCard;
	}

	public Card getLowCard() {
		return lowCard;
	}

	private double occurancePercentage;
	private double occuranceAbsolute;

	public AggregatedCardStrategy(Card high, Card low) {
		this.highCard = high;
		this.lowCard = low;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((highCard == null) ? 0 : highCard.hashCode());
		result = prime * result + ((lowCard == null) ? 0 : lowCard.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AggregatedCardStrategy other = (AggregatedCardStrategy) obj;
		if (highCard != other.highCard)
			return false;
		if (lowCard != other.lowCard)
			return false;
		return true;
	}

}
