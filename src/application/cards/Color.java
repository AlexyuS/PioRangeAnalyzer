package application.cards;

public enum Color {

	SPADES("s"), DIAMONDS("d"), HEARTS("h"), CLUBS("c");

	private String color;

	Color(String string) {
		this.color = string;
	}

	public String getCardRank() {
		return color;
	}
	
	public static Color createFrom(String color) throws Exception{
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
