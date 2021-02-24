package main.application.cards;



public class IndividualCardStrategy implements CardStrategy {

	private final CardHand cardHand;
	private double diff;
	private double occurancePercentage;
	private double absoluteOccurence;
	
	public IndividualCardStrategy(CardHand cardHand, double occurance) {
		this.cardHand = cardHand;
		this.occurancePercentage = occurance;
		this.absoluteOccurence = this.occurancePercentage/100;
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
		this.absoluteOccurence =absoluteOccurence;
	}

	@Override
	public Card getHighCard() {
		return this.cardHand.getHighCard();
	}

	@Override
	public Card getLowCard() {
		return this.cardHand.getLowCard();
	}
	
	@Override
	public double getDiffFromStrategyRef() {
		return diff;
	}

	@Override
	public void setDifferenceFromStrategyRef(double diff) {
		this.diff = diff;
	}

	public CardColor getLowCardColor() {
		return this.cardHand.getLowCardColor();
	}
	
	public CardColor getHighCardColor() {
		return this.cardHand.getHighCardColor();
	}
	
	public CardHand getCardHand() {
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
