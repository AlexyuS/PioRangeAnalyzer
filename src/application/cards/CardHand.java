package application.cards;

public class CardHand {

	private final Card highCard;
	private final Color highCardColor;

	private final Card lowCard;
	private final Color lowCardColor;

	public CardHand(Card highCard, Color highCardColor, Card lowCard, Color lowCardColor) {
		this.highCard = highCard;
		this.highCardColor = highCardColor;
		this.lowCard = lowCard;
		this.lowCardColor = lowCardColor;
	}

	public Card getHighCard() {
		return highCard;
	}

	public Color getHighCardColor() {
		return highCardColor;
	}

	public Card getLowCard() {
		return lowCard;
	}

	public Color getLowCardColor() {
		return lowCardColor;
	}

}
