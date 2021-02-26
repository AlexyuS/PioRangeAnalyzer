package main.application.cards;

public class PairedCardHand extends CardHand {

	public PairedCardHand(Card highCard) {
		super(highCard, null, highCard, null);
		
	}

	@Override
	protected int calculateGridPosition() {
		return 14 * (14 - getHighCard().getCardRank());
	}

	@Override
	protected int getStandardCardOccurance() {
		return 6;
	}

	@Override
	public char getGroupShortcut() {
		return ' ';
	}
	@Override
	public String toString() {
		return getHighCard().getLabel()+getLowCard().getLabel();
	}
	
}
