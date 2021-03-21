package main.application.cards;

public class SuitedCardHand extends CardHand{

	public SuitedCardHand(Card highCard, Card lowCard) {
		super(highCard, null, lowCard, null);
	}

	@Override
	protected int calculateGridPosition() {
		return 13 * (14 - getHighCard().getCardRank()) + 14 - getLowCard().getCardRank();
	}

	@Override
	protected int getStandardCardOccurance() {
		return 4;
	}

	@Override
	public char getGroupShortcut() {
		return 's';
	}
	@Override
	public String toString() {
		return getHighCard().getLabel()+getLowCard().getLabel()+getGroupShortcut();
	}
}
