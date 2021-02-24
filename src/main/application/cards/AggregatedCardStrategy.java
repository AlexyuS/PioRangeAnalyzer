package main.application.cards;

import main.application.helper.MathUtils;
import main.application.strategy.helper.IndexCalcualtionHelper;

public class AggregatedCardStrategy implements CardStrategy,StrategyCardAggregator {
	private final Card highCard;
	private final Card lowCard;
	
	private double occurancePercentage;
	private double absoluteOccurance;
	private double diffAsPercentage;
	private int handCount;

	private HandGroupping handGroupping;
	
	private final int gridIndex;

	public AggregatedCardStrategy(Card high, Card low, HandGroupping handGroupping) {
		this.highCard = high;
		this.lowCard = low;
		this.handGroupping = handGroupping;
		this.gridIndex = calculateGridIndex(high, low, handGroupping);
	}

	public double getOccurancePercentage() {
		return occurancePercentage;
	}

	public void setOccurancePercentage(double occurancePercentage) {
		handCount =1;
		this.occurancePercentage = occurancePercentage;
	}

	public double getAbsoluteOccurance() {
		return absoluteOccurance;
	}

	public void setOccuranceAbsolute(double occuranceAbsolute) {
		this.absoluteOccurance = occuranceAbsolute;
	}

	@Override
	public void increaseOccurancePercentage(double occurancePercentage) {
		this.handCount += 1;
		this.occurancePercentage += occurancePercentage;
		
	}

	public int getGridIndex() {
		return gridIndex;
	}

	public Card getHighCard() {
		return highCard;
	}

	public Card getLowCard() {
		return lowCard;
	}

	public HandGroupping getHandGroupping() {
		return handGroupping;
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

	public void settleUpStrategy() {
		this.absoluteOccurance=MathUtils.round(absoluteOccurance,2);
		this.occurancePercentage= this.occurancePercentage/handCount;
		
	}

	private int calculateGridIndex(Card cardHigh, Card cardLow, HandGroupping handGroupping) {
		if (handGroupping.equals(HandGroupping.PAIRED)) {
			return IndexCalcualtionHelper.getPocketPairIndex(cardHigh.getCardRank());
		}
		if (handGroupping.equals(HandGroupping.SUITED)) {
			return IndexCalcualtionHelper.getSuitedIndex(cardHigh.getCardRank(), cardLow.getCardRank());
		}
		return IndexCalcualtionHelper.getOffsuitedIndex(cardHigh.getCardRank(), cardLow.getCardRank());

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handGroupping == null) ? 0 : handGroupping.hashCode());
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
		if (!handGroupping.equals(other.handGroupping))
			return false;
		if (!highCard.equals(other.highCard))
			return false;
		if (!lowCard.equals(other.lowCard))
			return false;
		return true;
	}

}
