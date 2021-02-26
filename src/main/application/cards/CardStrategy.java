package main.application.cards;

public interface CardStrategy {

	public void setOccurancePercentage(double occurancePercentage);

	public void setOccuranceAbsolute(double absoluteOccurence);

	public double getAbsoluteOccurance();

	public double getOccurancePercentage();

	public CardHand getCardHand();

	public double getDiffFromStrategyRef();

	public void setDifferenceFromStrategyRef(double diff);
	
}
