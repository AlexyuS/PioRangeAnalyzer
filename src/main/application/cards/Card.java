package main.application.cards;

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

	public String getCardAsString() {
		switch (cardRank) {
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";

		case 5:
			return "5";

		case 6:
			return "6";

		case 7:
			return "7";

		case 8:
			return "8";

		case 9:
			return "9";

		case 10:
			return "T";

		case 11:
			return "J";

		case 12:
			return "Q";

		case 13:
			return "K";

		case 14:
			return "A";

		default:
			throw new IllegalArgumentException("Unknown card found");
		}
	}
}
