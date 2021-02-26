package main.application.cards;

public class IndividualCardHand extends CardHand{

	public IndividualCardHand(Card highCard, CardColor highCardColor, Card lowCard, CardColor lowCardColor) {
		super(highCard, highCardColor, lowCard, lowCardColor);
	}

	@Override
	protected int calculateGridPosition() {
		return -1;
	}

	@Override
	protected int getStandardCardOccurance() {
		return 1;
	}

	@Override
	public char getGroupShortcut() {
		return ' ';
	}

}
