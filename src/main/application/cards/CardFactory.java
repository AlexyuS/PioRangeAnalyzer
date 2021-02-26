package main.application.cards;

public class CardFactory {
	public CardHand pairedFrom(IndividualCardHand cardHand) {
		return new PairedCardHand(cardHand.getHighCard());
	}
	
	public CardHand suitedFrom(IndividualCardHand cardHand) {
		return new SuitedCardHand(cardHand.getHighCard(), cardHand.getLowCard());
	}
	
	public CardHand offsuitedFrom(IndividualCardHand cardHand) {
		return new OffsuitedCardHand(cardHand.getHighCard(), cardHand.getLowCard());
	}
}
