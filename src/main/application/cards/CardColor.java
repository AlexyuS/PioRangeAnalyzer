package main.application.cards;

public enum CardColor {

	SPADES(0,'s'), HEARTS(1,'h'), DIAMONDS(2,'d'), CLUBS(3,'c');

	private Integer color;
	private char label;
	
	CardColor(Integer color,char label) {
		this.color = color;
		this.label = label;
	}

	public Integer getCardRank() {
		return color;
	}
	
	public char getLabel() {
		return this.label;
	}
	
	public static CardColor createFrom(char color) throws Exception{
		switch(color) {
		case('s'):return SPADES;
		case('d'):return DIAMONDS;
		case('c'):return CLUBS;
		case('h'):return HEARTS;
		default:
			throw new Exception("Raw color could not be converted to a type");
		}
	}
	
	
}
