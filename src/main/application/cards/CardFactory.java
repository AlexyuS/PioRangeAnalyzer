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
	
	public CardHand pairedFrom(int colIndex,int rowIndex) {
		
		try {
			Card card = Card.createFrom(14-colIndex);
			return new PairedCardHand(card);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	
	}
	
	public CardHand suitedFrom(int colIndex,int rowIndex) {
		
		try {
			Card highCard = Card.createFrom(14-rowIndex);
			Card lowCard = Card.createFrom(14-colIndex);
			
			return new SuitedCardHand(highCard, lowCard);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public CardHand offsuitedFrom(int colIndex,int rowIndex) {
		try {
			Card highCard = Card.createFrom(14-colIndex);
			Card lowCard = Card.createFrom(14-rowIndex);
			
			return new OffsuitedCardHand(highCard, lowCard);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
