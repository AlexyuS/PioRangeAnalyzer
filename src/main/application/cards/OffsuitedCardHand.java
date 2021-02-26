package main.application.cards;


public class OffsuitedCardHand extends CardHand{

	public OffsuitedCardHand(Card highCard, Card lowCard) {
		super(highCard, null, lowCard, null);
	}

	@Override
	protected int calculateGridPosition() {
		return 196 - getHighCard().getCardRank() - 13 * getLowCard().getCardRank();
	}

	@Override
	protected int getStandardCardOccurance() {
		return 12;
	}

	@Override
	public char getGroupShortcut() {
		return 'o';
	}
	@Override
	public String toString() {
		return getHighCard().getLabel()+getLowCard().getLabel()+"o";
	}
}
