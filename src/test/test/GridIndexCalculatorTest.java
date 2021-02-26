package test;

import org.junit.Assert;
import org.junit.Test;

import main.application.cards.Card;
import main.application.cards.CardHand;
import main.application.cards.OffsuitedCardHand;
import main.application.cards.PairedCardHand;
import main.application.cards.SuitedCardHand;

public class GridIndexCalculatorTest {

	@Test
	public void testOffSuitedGridCalculator() {

		{
			// AKo has index 13
			CardHand cardHand = new OffsuitedCardHand(Card.ACE, Card.KING);
			int cardPosIngrid = cardHand.getCardIndex();
			Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 13, cardPosIngrid),
					cardPosIngrid == 13);
		}

		{
			// K8o has index 79
			CardHand cardHand = new OffsuitedCardHand(Card.KING, Card.EIGHT);
			int index = cardHand.getCardIndex();
			Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 79, index),
					index == 79);
		}

		{
			// 43o has index 153
			CardHand cardHand = new OffsuitedCardHand(Card.FOUR, Card.THREE);
			int index = cardHand.getCardIndex();
			Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 153, index),
					index == 153);
		}
	}

	@Test
	public void testSuitedGridCalculator() {

		{// AKs has index 1
			CardHand suitedCard = new SuitedCardHand(Card.ACE, Card.KING);
			int index = suitedCard.getCardIndex();
			Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 1, index), index == 1);
		}
		{
			// 95s has index 74
			CardHand cardHand = new SuitedCardHand(Card.NINE, Card.FIVE);
			int index = cardHand.getCardIndex();
			Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 74, index),
					index == 74);
		}
	}

	@Test
	public void testPairedGridCalculator() {
		{   //KK has index 14
			CardHand suitedCard = new PairedCardHand(Card.KING);
			int index = suitedCard.getCardIndex();
			Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 14, index), index == 14);
		}
		{
			//70 has index
			CardHand cardHand = new PairedCardHand(Card.NINE);
			int index = cardHand.getCardIndex();
			Assert.assertTrue(String.format("Wrong index calculations, Expected %d, but was %d", 70, index),
					index == 70);
		}
	}
}
