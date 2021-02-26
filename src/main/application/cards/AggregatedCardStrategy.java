package main.application.cards;

import main.application.helper.MathUtils;

public class AggregatedCardStrategy implements CardStrategy,StrategyCardAggregator {
	
	private final CardHand cardhand;
	
	private double occurancePercentage;
	private double absoluteOccurance;
	private double diffAsPercentage;
	private int handCount;

	public AggregatedCardStrategy(CardHand cardHand) {
		this.cardhand = cardHand;
	}
	@Override
	public CardHand getCardHand() {
		return this.cardhand;
	}

	@Override
	public double getDiffFromStrategyRef() {
		return diffAsPercentage;
	}

	@Override
	public void setDifferenceFromStrategyRef(double diff) {
		this.diffAsPercentage = diff;
	}

	@Override
	public void increaseOccuranceAbsolute(double absoluteOccurance) {
		this.absoluteOccurance +=absoluteOccurance;
	}
	
	@Override
	public void increaseOccurancePercentage(double occurancePercentage) {
		this.handCount+=1;
		this.occurancePercentage += occurancePercentage;
	}

	public double getOccurancePercentage() {
		return occurancePercentage;
	}

	public void setOccurancePercentage(double occurancePercentage) {
		this.handCount=1;
		this.occurancePercentage = occurancePercentage;
	}

	public double getAbsoluteOccurance() {
		return absoluteOccurance;
	}

	public void setOccuranceAbsolute(double occuranceAbsolute) {
		this.absoluteOccurance = occuranceAbsolute;
	}


	public void settleUpStrategy() {
		this.absoluteOccurance=MathUtils.round(absoluteOccurance,2);
		this.occurancePercentage= this.occurancePercentage/handCount;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardhand == null) ? 0 : cardhand.hashCode());
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
		if (cardhand == null) {
			if (other.cardhand != null)
				return false;
		} else if (!cardhand.equals(other.cardhand))
			return false;
		return true;
	}
}
