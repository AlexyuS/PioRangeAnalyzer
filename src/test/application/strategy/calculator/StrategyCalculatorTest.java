package test.application.strategy.calculator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import main.application.cards.Card;
import main.application.cards.CardColor;
import main.application.cards.CardHand;
import main.application.cards.CardStrategy;
import main.application.strategy.calculator.StrategyDiffCalculator;

public class StrategyCalculatorTest {

	private List<CardStrategy> sb1;
	private List<CardStrategy> sb2;

	private String player1 = "Jhon";
	private String player2 = "Mike";

	private CardStrategy AcAd1;
	private CardStrategy KcKd1;
	private CardStrategy QcQd1;
	private CardStrategy JcJd;

	private CardStrategy AcAd2;
	private CardStrategy KcKd2;
	private CardStrategy QcQd2;
	private CardStrategy TcTd;

	@Before
	public void before() {
		sb1 = new ArrayList<>();
		sb2 = new ArrayList<>();

		AcAd1 = new CardStrategy(new CardHand(Card.ACE, CardColor.CLUBS, Card.ACE, CardColor.DIAMONDS), (double) 20);
		KcKd1 = new CardStrategy(new CardHand(Card.KING, CardColor.CLUBS, Card.KING, CardColor.DIAMONDS), (double) 20);
		QcQd1 = new CardStrategy(new CardHand(Card.KING, CardColor.CLUBS, Card.KING, CardColor.DIAMONDS), (double) 20);
		JcJd = new CardStrategy(new CardHand(Card.JACK, CardColor.CLUBS, Card.JACK, CardColor.DIAMONDS), (double) 20);
		sb1.add(AcAd1);
		sb1.add(KcKd1);
		sb1.add(QcQd1);
		sb1.add(JcJd);

		AcAd2 = new CardStrategy(new CardHand(Card.ACE, CardColor.CLUBS, Card.ACE, CardColor.DIAMONDS), (double) 10);
		KcKd2 = new CardStrategy(new CardHand(Card.KING, CardColor.CLUBS, Card.KING, CardColor.DIAMONDS), (double) 20);
		QcQd2 = new CardStrategy(new CardHand(Card.QUEEN, CardColor.CLUBS, Card.QUEEN, CardColor.DIAMONDS),
				(double) 40);
		TcTd = new CardStrategy(new CardHand(Card.TEN, CardColor.CLUBS, Card.TEN, CardColor.DIAMONDS), (double) 20);

		sb2.add(AcAd2);
		sb2.add(KcKd2);
		sb2.add(QcQd2);
		sb2.add(TcTd);

	}

	@Test
	public void testStrategyCalculator() {
		StrategyDiffCalculator calculator = new StrategyDiffCalculator();

		calculator.calculateCardDifference(player1, sb1, player2, sb2);
		calculator.calculateCardDifference(player2, sb2, player1, sb1);

		Assert.isTrue(AcAd1.getOccurance()-AcAd2.getOccurance()==AcAd1.getDiffFromPlayer(player2),"Wrong calculations for AcAd and player"+player1);
		Assert.isTrue(AcAd2.getOccurance()-AcAd1.getOccurance()==AcAd2.getDiffFromPlayer(player1),"Wrong calculations for AcAd and player"+player2);

		Assert.isTrue(KcKd1.getOccurance()-KcKd2.getOccurance()==KcKd1.getDiffFromPlayer(player2),"Wrong calculations for AcAd and player"+player1);
		Assert.isTrue(KcKd1.getOccurance()-KcKd2.getOccurance()==KcKd1.getDiffFromPlayer(player1),"Wrong calculations for AcAd and player"+player2);

		Assert.isTrue(JcJd.getOccurance()==JcJd.getDiffFromPlayer(player2),"Wrong calculations for AcAd and player"+player1);
		Assert.isTrue(TcTd.getOccurance()==TcTd.getDiffFromPlayer(player1),"Wrong calculations for AcAd and player"+player2);

	}
}
