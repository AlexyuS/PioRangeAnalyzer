package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.Card;
import main.application.cards.CardColor;
import main.application.cards.CardHand;
import main.application.cards.CardStrategy;
import main.application.cards.HandGroupping;
import main.application.cards.IndividualCardStrategy;
import main.application.strategy.StrategyHolder;

public class AggregateCombosTest {
	StrategyHolder strategyHolder;

	@Before
	public void setUp() {
		strategyHolder = new StrategyHolder("Bet 30");
	}

	@Test
	public void testPairedAggregatedCombos() {
		IndividualCardStrategy AhAd = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.ACE, CardColor.DIAMONDS), 10);
		IndividualCardStrategy AhAs = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.ACE, CardColor.SPADES), 20);
		IndividualCardStrategy AhAc = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.ACE, CardColor.CLUBS), 30);
		IndividualCardStrategy AsAd = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.SPADES, Card.ACE, CardColor.DIAMONDS), 40);
		IndividualCardStrategy AsAc = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.SPADES, Card.ACE, CardColor.CLUBS), 50);
		IndividualCardStrategy AdAc = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.DIAMONDS, Card.ACE, CardColor.CLUBS), 60);

		double average = (10 + 20 + 30 + 40 + 50 + 60) / 6;

		strategyHolder.addCards(AhAd);
		strategyHolder.addCards(AhAs);
		strategyHolder.addCards(AsAd);
		strategyHolder.addCards(AhAc);
		strategyHolder.addCards(AsAc);
		strategyHolder.addCards(AdAc);

		AggregatedCardStrategy aggregatedStrategy = new AggregatedCardStrategy(Card.ACE, Card.ACE,
				HandGroupping.PAIRED);

		Assert.isTrue(strategyHolder.getAggregatedCardStrategy().size() == 1,
				"Aggregated cards strategy size is not correct");
		strategyHolder.getAggregatedCardStrategy().forEach(e -> e.settleUpStrategy());

		int index = strategyHolder.getAggregatedCardStrategy().indexOf(aggregatedStrategy);
		Assert.isTrue(index != -1, "Card strategy was not found in aggregated list");

		CardStrategy cards = strategyHolder.getAggregatedCardStrategy().get(index);

		Assert.isTrue(cards.getOccurancePercentage() == average, String.format(
				"The occurance percentage of the cards is not corectlly calculated." + " Expected %f but was found %f",
				average, cards.getOccurancePercentage()));

		double absoluteValue = average / 100 * 6;

		Assert.isTrue(absoluteValue == cards.getAbsoluteOccurance(),
				String.format("Absolute occurance of hand group is not correct. Expected %f, but was found %f",
						absoluteValue, cards.getAbsoluteOccurance()));

	}

	@Test
	public void testSuitedAggregatedCombos() {
		IndividualCardStrategy AhKh1 = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.HEARTS), 10);
		IndividualCardStrategy AhKh2 = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.HEARTS), 20);
		IndividualCardStrategy AhKh3 = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.HEARTS), 30);
		IndividualCardStrategy AhKh4 = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.HEARTS), 40);

		double average = (10 + 20 + 30 + 40) / 4;

		strategyHolder.addCards(AhKh1);
		strategyHolder.addCards(AhKh2);
		strategyHolder.addCards(AhKh3);
		strategyHolder.addCards(AhKh4);

		AggregatedCardStrategy aggregatedStrategy = new AggregatedCardStrategy(Card.ACE, Card.KING,
				HandGroupping.SUITED);

		Assert.isTrue(strategyHolder.getAggregatedCardStrategy().size() == 1,
				"Aggregated cards strategy size is not correct");
		strategyHolder.getAggregatedCardStrategy().forEach(e -> e.settleUpStrategy());

		int index = strategyHolder.getAggregatedCardStrategy().indexOf(aggregatedStrategy);
		Assert.isTrue(index != -1, "Card strategy was not found in aggregated list");

		CardStrategy cards = strategyHolder.getAggregatedCardStrategy().get(index);

		Assert.isTrue(cards.getOccurancePercentage() == average, String.format(
				"The occurance percentage of the cards is not corectlly calculated." + " Expected %f but was found %f",
				average, cards.getOccurancePercentage()));

		double absoluteValue = average / 100 * 4;

		Assert.isTrue(absoluteValue == cards.getAbsoluteOccurance(),
				String.format("Absolute occurance of hand group is not correct. Expected %f, but was found %f",
						absoluteValue, cards.getAbsoluteOccurance()));

	}

	@Test
	public void testOffsuitedAggregatedCombos() {
		IndividualCardStrategy AhKc = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.CLUBS), 10);
		IndividualCardStrategy AhKs = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.SPADES), 20);
		IndividualCardStrategy AhKd = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.DIAMONDS), 30);
		IndividualCardStrategy AsKh = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.SPADES, Card.KING, CardColor.HEARTS), 40);
		IndividualCardStrategy AsKc = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.SPADES, Card.KING, CardColor.CLUBS), 50);
		IndividualCardStrategy AsKd = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.SPADES, Card.KING, CardColor.DIAMONDS), 60);
		IndividualCardStrategy AdKs = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.DIAMONDS, Card.KING, CardColor.SPADES), 70);
		IndividualCardStrategy AdKc = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.DIAMONDS, Card.KING, CardColor.CLUBS), 80);
		IndividualCardStrategy AdKh = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.DIAMONDS, Card.KING, CardColor.HEARTS), 90);
		IndividualCardStrategy AcKh = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.HEARTS), 80);
		IndividualCardStrategy AcKs = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.SPADES), 70);
		IndividualCardStrategy AcKd = new IndividualCardStrategy(
				new CardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.DIAMONDS), 60);

		double average = (10 + 20 + 30 + 40 + 50 + 60 + 70 + 80 + 90 + 80 + 70 + 60) / 12;

		strategyHolder.addCards(AhKc);
		strategyHolder.addCards(AhKs);
		strategyHolder.addCards(AhKd);
		strategyHolder.addCards(AsKh);
		strategyHolder.addCards(AsKc);
		strategyHolder.addCards(AsKd);
		strategyHolder.addCards(AdKc);
		strategyHolder.addCards(AdKh);
		strategyHolder.addCards(AdKs);
		strategyHolder.addCards(AcKh);
		strategyHolder.addCards(AcKs);
		strategyHolder.addCards(AcKd);

		AggregatedCardStrategy aggregatedStrategy = new AggregatedCardStrategy(Card.ACE, Card.KING,
				HandGroupping.OFFSUITED);

		Assert.isTrue(strategyHolder.getAggregatedCardStrategy().size() == 1,
				"Aggregated cards strategy size is not correct");
		strategyHolder.getAggregatedCardStrategy().forEach(e -> e.settleUpStrategy());

		int index = strategyHolder.getAggregatedCardStrategy().indexOf(aggregatedStrategy);
		Assert.isTrue(index != -1, "Card strategy was not found in aggregated list");

		CardStrategy cards = strategyHolder.getAggregatedCardStrategy().get(index);

		Assert.isTrue(cards.getOccurancePercentage() == average, String.format(
				"The occurance percentage of the cards is not corectlly calculated." + " Expected %f but was found %f",
				average, cards.getOccurancePercentage()));

		double absoluteValue = average / 100 * 12;

		Assert.isTrue(absoluteValue == cards.getAbsoluteOccurance(),
				String.format("Absolute occurance of hand group is not correct. Expected %f, but was found %f",
						absoluteValue, cards.getAbsoluteOccurance()));

	}
}
