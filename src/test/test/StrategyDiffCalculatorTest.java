package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.Card;
import main.application.cards.CardColor;
import main.application.cards.IndividualCardHand;
import main.application.cards.IndividualCardStrategy;
import main.application.cards.OffsuitedCardHand;
import main.application.cards.PairedCardHand;
import main.application.cards.SuitedCardHand;
import main.application.strategy.StrategyHolder;
import main.application.strategy.calculator.StrategyDiffCalculator;

public class StrategyDiffCalculatorTest {
	private StrategyHolder strategyHolderRef;
	
	@Before
	public void setUp() {
		strategyHolderRef =  new StrategyHolder("BET30",null);
		strategyHolderRef.addCards(new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.CLUBS),50));
		strategyHolderRef.addCards(new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.QUEEN, CardColor.CLUBS),50));
		strategyHolderRef.addCards(new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.JACK, CardColor.CLUBS),50));
		strategyHolderRef.addCards(new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.TEN, CardColor.CLUBS),50));

		AggregatedCardStrategy aggregatedCardStrategy =  new AggregatedCardStrategy(new SuitedCardHand(Card.ACE,Card.KING));
		aggregatedCardStrategy.setOccurancePercentage(30);
		AggregatedCardStrategy aggregatedCardStrategy2 =  new AggregatedCardStrategy(new OffsuitedCardHand(Card.ACE,Card.QUEEN));
		aggregatedCardStrategy2.setOccurancePercentage(30);
		AggregatedCardStrategy aggregatedCardStrategy3 =  new AggregatedCardStrategy(new PairedCardHand(Card.JACK));
		aggregatedCardStrategy3.setOccurancePercentage(30);
		
		List<AggregatedCardStrategy> cardStrategy =  new ArrayList<>();
		cardStrategy.add(aggregatedCardStrategy3);
		cardStrategy.add(aggregatedCardStrategy2);
		cardStrategy.add(aggregatedCardStrategy);
		
		strategyHolderRef.setAggregatedCardStrategy(cardStrategy);
	}
	
	@Test
	public void testIndividualCardStrategyDiff() {
		 StrategyDiffCalculator strategyDiffCalculator =  new StrategyDiffCalculator(strategyHolderRef);
		 
		 StrategyHolder strategyToCalculate = new StrategyHolder("BET40",null);
		 
		 //does exist
		 IndividualCardStrategy indivStrat1 = new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.KING, CardColor.CLUBS),75);
		 IndividualCardStrategy indivStrat2 = new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.KING, CardColor.CLUBS),30);
		 IndividualCardStrategy indivStrat3 = new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.QUEEN, CardColor.SPADES),36);
		 IndividualCardStrategy indivStrat4 = new IndividualCardStrategy(new IndividualCardHand(Card.KING, CardColor.CLUBS, Card.QUEEN, CardColor.SPADES),41);
		 IndividualCardStrategy indivStrat5 = new IndividualCardStrategy(new IndividualCardHand(Card.ACE, CardColor.CLUBS, Card.NINE, CardColor.SPADES),66);

		strategyToCalculate.addCards(indivStrat5);
		strategyToCalculate.addCards(indivStrat4);
		strategyToCalculate.addCards(indivStrat3);
		strategyToCalculate.addCards(indivStrat2);
		strategyToCalculate.addCards(indivStrat1);
		
		AggregatedCardStrategy suitedCardHand1 =  new AggregatedCardStrategy(new SuitedCardHand(Card.ACE, Card.KING));
		suitedCardHand1.setOccurancePercentage(60);
		
		AggregatedCardStrategy suitedCardHand2 =  new AggregatedCardStrategy(new SuitedCardHand(Card.ACE, Card.JACK));
		suitedCardHand2.setOccurancePercentage(20);
		
		AggregatedCardStrategy offsuitedCardHand1 =  new AggregatedCardStrategy(new OffsuitedCardHand(Card.ACE, Card.KING));
		offsuitedCardHand1.setOccurancePercentage(30);
		
		AggregatedCardStrategy offsuitedCardHand2 =  new AggregatedCardStrategy(new OffsuitedCardHand(Card.ACE, Card.QUEEN));
		offsuitedCardHand2.setOccurancePercentage(70);

		AggregatedCardStrategy offsuitedCardHand3 =  new AggregatedCardStrategy(new OffsuitedCardHand(Card.ACE, Card.NINE));
		offsuitedCardHand3.setOccurancePercentage(30);

		
		AggregatedCardStrategy pairedCardHand1=  new AggregatedCardStrategy(new PairedCardHand(Card.ACE));
		pairedCardHand1.setOccurancePercentage(30);

		AggregatedCardStrategy pairedCardHand2 =  new AggregatedCardStrategy(new PairedCardHand(Card.JACK));
		pairedCardHand2.setOccurancePercentage(40);
		
		List<AggregatedCardStrategy> aggreatedCardStrategy =  new ArrayList<>();
		aggreatedCardStrategy.add(pairedCardHand1);
		aggreatedCardStrategy.add(pairedCardHand2);
		aggreatedCardStrategy.add(suitedCardHand1);
		aggreatedCardStrategy.add(suitedCardHand2);
		aggreatedCardStrategy.add(offsuitedCardHand1);
		aggreatedCardStrategy.add(offsuitedCardHand2);
		aggreatedCardStrategy.add(offsuitedCardHand3);

		
		strategyToCalculate.setAggregatedCardStrategy(aggreatedCardStrategy);
		
		strategyDiffCalculator.calculateStrategies(strategyToCalculate);
		
		double diffFromStrategyRef = indivStrat1.getDiffFromStrategyRef();
		Assert.assertTrue("Expected -25 but found"+diffFromStrategyRef,diffFromStrategyRef==-25);
		
		diffFromStrategyRef = indivStrat2.getDiffFromStrategyRef();
		Assert.assertTrue("Expected 30 but found"+diffFromStrategyRef,diffFromStrategyRef==30);
		Assert.assertTrue(indivStrat3.getDiffFromStrategyRef()==36);
		Assert.assertTrue(indivStrat4.getDiffFromStrategyRef()==41);
		Assert.assertTrue(indivStrat5.getDiffFromStrategyRef()==66);
		Assert.assertTrue(suitedCardHand1.getDiffFromStrategyRef()==-30);
		Assert.assertTrue(suitedCardHand2.getDiffFromStrategyRef()==20);
		Assert.assertTrue(offsuitedCardHand1.getDiffFromStrategyRef()==30);
		
		diffFromStrategyRef = offsuitedCardHand2.getDiffFromStrategyRef();
		Assert.assertTrue("Expected -40 but found"+diffFromStrategyRef,diffFromStrategyRef==-40);
		Assert.assertTrue(offsuitedCardHand3.getDiffFromStrategyRef()==30);
		Assert.assertTrue(pairedCardHand1.getDiffFromStrategyRef()==30);
		Assert.assertTrue(pairedCardHand2.getDiffFromStrategyRef()==-10);

		 
		 
		 
	}
}
