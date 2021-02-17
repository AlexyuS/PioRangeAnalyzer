package main.application.cards;

public enum CardColor {

	SPADES(0), HEARTS(1), DIAMONDS(2), CLUBS(3);

	private Integer color;

	CardColor(Integer color) {
		this.color = color;
	}

	public Integer getCardRank() {
		return color;
	}
	
	public static CardColor createFrom(String color) throws Exception{
		switch(color) {
		case("s"):return SPADES;
		case("d"):return DIAMONDS;
		case("c"):return CLUBS;
		case("h"):return HEARTS;
		default:
			throw new Exception("Raw color could not be converted to a type");
		}
	}
}
