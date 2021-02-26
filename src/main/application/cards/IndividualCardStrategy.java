package main.application.cards;

public class IndividualCardStrategy implements CardStrategy {

	private final IndividualCardHand cardHand;
	private double differenceFromRefStrategy;
	private double occurancePercentage;
	private double absoluteOccurence;

	public IndividualCardStrategy(IndividualCardHand cardHand, double occurance) {
		this.cardHand = cardHand;
		this.occurancePercentage = occurance;
		this.absoluteOccurence = this.occurancePercentage / 100;
	}

	@Override
	public String toString() {
		return "IndividualCardStrategy [cardHand=" + cardHand + ", occurancePercentage=" + occurancePercentage + "]";
	}

	@Override
	public double getOccurancePercentage() {
		return occurancePercentage;
	}

	@Override
	public void setOccurancePercentage(double occurancePercentage) {
		this.occurancePercentage = occurancePercentage;

	}

	@Override
	public double getAbsoluteOccurance() {
		return absoluteOccurence;
	}

	@Override
	public void setOccuranceAbsolute(double absoluteOccurence) {
		this.absoluteOccurence = absoluteOccurence;
	}

	@Override
	public double getDiffFromStrategyRef() {
		return differenceFromRefStrategy;
	}

	@Override
	public void setDifferenceFromStrategyRef(double diff) {
		this.differenceFromRefStrategy = diff;
	}

	public IndividualCardHand getCardHand() {
		return this.cardHand;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndividualCardStrategy other = (IndividualCardStrategy) obj;
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
