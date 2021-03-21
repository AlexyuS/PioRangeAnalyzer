package main.application.cards;

public enum Card {
	ACE(14,"A"), KING(13,"K"), QUEEN(12,"Q"), JACK(11,"J"), TEN(10,"T"), NINE(9,"9"), EIGHT(8,"8"), SEVEN(7,"7"), SIX(6,"6"), FIVE(5,"5"), FOUR(4,"4"), THREE(3,"3"),
	TWO(2,"2");

	private int cardRank;
	private String label;

	Card(Integer cardRank,String label) {
		this.cardRank = cardRank;
		this.label = label;
	}

	public int getCardRank() {
		return cardRank;
	}

	public static Card createFrom(char card) throws Exception {
		switch (card) {
		case 'A':
			return ACE;
		case 'K':
			return KING;
		case 'Q':
			return QUEEN;
		case 'J':
			return JACK;
		case 'T':
			return TEN;
		case '9':
			return NINE;
		case '8':
			return EIGHT;
		case '7':
			return SEVEN;
		case '6':
			return SIX;
		case '5':
			return FIVE;
		case '4':
			return FOUR;
		case '3':
			return THREE;
		case '2':
			return TWO;
		default:
			throw new Exception("Raw card could not be converted to a type");
		}
	}

	public static Card createFrom(int cardRank) throws Exception{
		switch(cardRank) {
		case 14:
			return ACE;
		case 13:
			return KING;
		case 12:
			return QUEEN;
		case 11:
			return JACK;
		case 10:
			return TEN;
		case 9:
			return NINE;
		case 8:
			return EIGHT;
		case 7:
			return SEVEN;
		case 6:
			return SIX;
		case 5:
			return FIVE;
		case 4:
			return FOUR;
		case 3:
			return THREE;
		case 2:
			return TWO;
		default:
			throw new Exception("Raw card could not be converted to a type");
		}
	}
	
	public String getLabel() {
		return this.label;
	}
}
