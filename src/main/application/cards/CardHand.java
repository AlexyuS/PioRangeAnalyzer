package main.application.cards;

public class CardHand {

	
	private final Card highCard;
	private final CardColor highCardColor;

	private final Card lowCard;
	private final CardColor lowCardColor;
	

	public CardHand(Card highCard, CardColor highCardColor, Card lowCard, CardColor lowCardColor) {
		this.highCard = highCard;
		this.highCardColor = highCardColor;
		this.lowCard = lowCard;
		this.lowCardColor = lowCardColor;
	}

	public Card getHighCard() {
		return highCard;
	}

	public CardColor getHighCardColor() {
		return highCardColor;
	}

	public Card getLowCard() {
		return lowCard;
	}

	public CardColor getLowCardColor() {
		return lowCardColor;
	}

	@Override
	public String toString() {
		return "CardHand [highCard=" + highCard + ", highCardColor=" + highCardColor + ", lowCard=" + lowCard
				+ ", lowCardColor=" + lowCardColor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((highCard == null) ? 0 : highCard.hashCode());
		result = prime * result + ((highCardColor == null) ? 0 : highCardColor.hashCode());
		result = prime * result + ((lowCard == null) ? 0 : lowCard.hashCode());
		result = prime * result + ((lowCardColor == null) ? 0 : lowCardColor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardHand other = (CardHand) obj;
		if (highCard != other.highCard)
			return false;
		if (highCardColor != other.highCardColor)
			return false;
		if (lowCard != other.lowCard)
			return false;
		if (lowCardColor != other.lowCardColor)
			return false;
		return true;
	}

}
