package test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.Card;
import main.application.cards.CardColor;
import main.application.cards.IndividualCardHand;
import main.application.cards.IndividualCardStrategy;
import main.application.strategy.StrategyHolder;
import main.application.strategy.helper.StrategyAggregator;

public class AggregateCombosTest {
	StrategyHolder strategyHolder;

	@Before
	public void setUp() {
		strategyHolder = new StrategyHolder("Bet 30");
	}

	@Test
	public void testPairedAggregatedCombos() {
		IndividualCardStrategy AhAd = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.ACE, CardColor.DIAMONDS), 10);
		IndividualCardStrategy AhAs = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.ACE, CardColor.SPADES), 20);
		IndividualCardStrategy AhAc = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.ACE, CardColor.CLUBS), 30);
		IndividualCardStrategy AsAd = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.ACE, CardColor.DIAMONDS), 40);
		IndividualCardStrategy AsAc = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.ACE, CardColor.CLUBS), 50);
		IndividualCardStrategy AdAc = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.DIAMONDS, Card.ACE, CardColor.CLUBS), 60);

		double average = (10 + 20 + 30 + 40 + 50 + 60) / 6;

		strategyHolder.addCards(AhAd);
		strategyHolder.addCards(AhAs);
		strategyHolder.addCards(AsAd);
		strategyHolder.addCards(AhAc);
		strategyHolder.addCards(AsAc);
		strategyHolder.addCards(AdAc);

		List<AggregatedCardStrategy> aggregatedStrategyList = StrategyAggregator.aggregateIndividualCards(strategyHolder.getIndividualCards());
		
		Assert.assertNotNull("There are no aggregated cards",aggregatedStrategyList);
		Assert.assertTrue("Incorrect numberaggregated cards. Expected 1 but found "+aggregatedStrategyList.size(), aggregatedStrategyList.size()==1);
		
		AggregatedCardStrategy aggregatedCard = aggregatedStrategyList.get(0);
		
		double actualOccurance = aggregatedCard.getOccurancePercentage();
		Assert.assertTrue(String.format("Occurence percentage is wrong. Expected %f but found %f", average,actualOccurance),actualOccurance==average);
		
	}

	@Test
	public void testSuitedAggregatedCombos() {
		IndividualCardStrategy AhKh1 = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.HEARTS), 10);
		IndividualCardStrategy AhKh2 = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.HEARTS), 20);
		IndividualCardStrategy AhKh3 = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.HEARTS), 30);
	

		double average = (10 + 20 + 30 ) / 3;

		strategyHolder.addCards(AhKh1);
		strategyHolder.addCards(AhKh2);
		strategyHolder.addCards(AhKh3);

		List<AggregatedCardStrategy> aggregatedStrategyList = StrategyAggregator.aggregateIndividualCards(strategyHolder.getIndividualCards());
		
		Assert.assertNotNull("There are no aggregated cards",aggregatedStrategyList);
		Assert.assertTrue("Incorrect numberaggregated cards. Expected 1 but found "+aggregatedStrategyList.size(), aggregatedStrategyList.size()==1);
		
		AggregatedCardStrategy aggregatedCard = aggregatedStrategyList.get(0);
		
		double actualOccurance = aggregatedCard.getOccurancePercentage();
		Assert.assertTrue(String.format("Occurence percentage is wrong. Expected %f but found %f", average,actualOccurance),actualOccurance==average);
	

	}

	@Test
	public void testOffsuitedAggregatedCombos() {
		IndividualCardStrategy AhKc = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.CLUBS), 10);
		IndividualCardStrategy AhKs = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.SPADES), 20);
		IndividualCardStrategy AhKd = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.HEARTS, Card.KING, CardColor.DIAMONDS), 30);
		IndividualCardStrategy AsKh = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.KING, CardColor.HEARTS), 40);
		IndividualCardStrategy AsKc = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.KING, CardColor.CLUBS), 50);
		IndividualCardStrategy AsKd = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.KING, CardColor.DIAMONDS), 60);
		IndividualCardStrategy AdKs = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.DIAMONDS, Card.KING, CardColor.SPADES), 70);
		IndividualCardStrategy AdKc = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.DIAMONDS, Card.KING, CardColor.CLUBS), 80);
		IndividualCardStrategy AdKh = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.DIAMONDS, Card.KING, CardColor.HEARTS), 90);
		IndividualCardStrategy AcKh = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.HEARTS), 80);
		IndividualCardStrategy AcKs = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.SPADES), 70);
		IndividualCardStrategy AcKd = new IndividualCardStrategy(
				new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.DIAMONDS), 60);

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

		List<AggregatedCardStrategy> aggregatedStrategyList = StrategyAggregator.aggregateIndividualCards(strategyHolder.getIndividualCards());
		
		Assert.assertNotNull("There are no aggregated cards",aggregatedStrategyList);
		Assert.assertTrue("Incorrect numberaggregated cards. Expected 1 but found "+aggregatedStrategyList.size(), aggregatedStrategyList.size()==1);
		
		AggregatedCardStrategy aggregatedCard = aggregatedStrategyList.get(0);
		
		double actualOccurance = aggregatedCard.getOccurancePercentage();
		Assert.assertTrue(String.format("Occurence percentage is wrong. Expected %f but found %f", average,actualOccurance),actualOccurance==average);

		
	}
	
}
