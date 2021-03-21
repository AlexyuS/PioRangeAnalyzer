package test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.application.cards.Card;
import main.application.cards.CardColor;
import main.application.cards.IndividualCardHand;
import main.application.cards.IndividualCardStrategy;
import main.application.fileParser.StrategyReader;
import main.application.strategy.StrategyHolder;

public class PreflopStrategyParser {
	private IndividualCardStrategy AcAh;

	private IndividualCardStrategy As6s;

	private IndividualCardStrategy AhTs;

	private IndividualCardStrategy _7s7h;

	private String rawPreflopStrategy;

	@Before
	public void setUp() {

		AcAh = new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.ACE, CardColor.CLUBS),
				100);

		As6s = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.SIX, CardColor.SPADES), 50);

		_7s7h = new IndividualCardStrategy(
				new IndividualCardHand(Card.SEVEN, CardColor.SPADES, Card.SEVEN, CardColor.HEARTS), 50);

		AhTs = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.TEN, CardColor.SPADES), 100);

		rawPreflopStrategy = "AA,KK,QQ,JJ,TT,99,88,7d7c,7h7c:0.5,7s7c,7h7d:0.5,7s7d,7s7h:0.5,66,55:0.5,44:0.5,AK,AQ,AJ,AT,A9s,A8s,Ac7c,"
				+ "Ad7d,Ah7h,As7s:0.5,Ac6c,Ad6d,Ah6h,As6s:0.5,A3s:0.5,KQ,KJ,KTs,K9s,QJs,QTs,JTs,T9s,98s";

	}

	@Test
	public void testCardsExist() {

		StrategyReader parser = new StrategyReader();
		StrategyHolder result = parser.parsePreflopStrategy(rawPreflopStrategy);

		List<IndividualCardStrategy> results = result.getIndividualCards();

		{
			int indexOf = results.indexOf(AcAh);
			Assert.assertTrue("Card " + AcAh + " not found in database", indexOf != -1);

			double occurancePercentage = results.get(indexOf).getOccurancePercentage();
			Assert.assertTrue("Wrong occurance for card" + AcAh + ". Expected" + AcAh.getOccurancePercentage()
					+ "But found " + occurancePercentage, occurancePercentage == 100);
		}
		{
			int indexOf2 = results.indexOf(As6s);
			Assert.assertTrue("Card " + As6s + " not found in database", indexOf2 != -1);

			double occurancePercentage = results.get(indexOf2).getOccurancePercentage();
			Assert.assertTrue("Wrong occurance for card" + As6s + ". Expected" + As6s.getOccurancePercentage()
					+ "But found " + occurancePercentage, occurancePercentage == 50);
		}

		{
			int indexOf3 = results.indexOf(AhTs);
			Assert.assertTrue("Card " + AhTs + " not found in database", indexOf3 != -1);

			double occurancePercentage = results.get(indexOf3).getOccurancePercentage();
			Assert.assertTrue("Wrong occurance for card" + AhTs + ". Expected" + AhTs.getOccurancePercentage()
					+ "But found " + occurancePercentage, occurancePercentage == 100);

		}
		{
			int indexOf4 = results.indexOf(_7s7h);
			Assert.assertTrue("Card " + _7s7h + " not found in database", indexOf4 != -1);

			double occurancePercentage = results.get(indexOf4).getOccurancePercentage();
			Assert.assertTrue("Wrong occurance for card" + _7s7h + ". Expected" + _7s7h.getOccurancePercentage()
					+ "But found " + occurancePercentage, occurancePercentage == 50);
		}

	}
}
