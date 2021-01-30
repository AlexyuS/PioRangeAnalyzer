package application.cards;

public enum Card {
	ACE(14), KING(13), QUEEN(12), JACK(11), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3),
	TWO(2);

	private Integer cardRank;

	Card(Integer string) {
		this.cardRank = string;
	}

	public Integer getCardRank() {
		return cardRank;
	}

	public static Card createFrom(String card) throws Exception {
		switch (card) {
		case "A":
			return ACE;
		case "K":
			return KING;
		case "Q":
			return QUEEN;
		case "J":
			return JACK;
		case "T":
			return TEN;
		case "9":
			return NINE;
		case "8":
			return EIGHT;
		case "7":
			return SEVEN;
		case "6":
			return SIX;
		case "5":
			return FIVE;
		case "4":
			return FOUR;
		case "3":
			return THREE;
		case "2":
			return TWO;
		default:
			throw new Exception("Raw card could not be converted to a type");
		}
	}
}
