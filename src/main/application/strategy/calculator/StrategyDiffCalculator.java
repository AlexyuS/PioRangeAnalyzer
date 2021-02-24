package main.application.strategy.calculator;

import java.util.List;
import java.util.logging.Logger;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.CardStrategy;
import main.application.strategy.StrategyHolder;

public class StrategyDiffCalculator {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(StrategyDiffCalculator.class.getName());
	private final StrategyHolder refStrategy;
	
	public StrategyDiffCalculator(StrategyHolder baseStrategy) {
		this.refStrategy = baseStrategy;
	}

	public void calculateIndividualStrategies(StrategyHolder strategy1) {
		calculateCardDifferenceForIndividualCards(strategy1.getIndividualCards());
		calculateCardDifferenceForAggregatedCards(strategy1.getAggregatedCardStrategy());
	}

	private void calculateCardDifferenceForIndividualCards(
			List<CardStrategy> cards2) {

		for (CardStrategy refCard : refStrategy.getIndividualCards()) {
			Integer indexOf = cards2.indexOf(refCard);
			if (indexOf == -1) {
				refCard.setDifferenceFromStrategyRef(refCard.getOccurancePercentage());
			} else {
				CardStrategy cardToBeCalculated = cards2.get(indexOf);
				cardToBeCalculated.setDifferenceFromStrategyRef(cardToBeCalculated.getOccurancePercentage()-refCard.getOccurancePercentage());
			}
		}
	}
	
	private void calculateCardDifferenceForAggregatedCards(
			List<AggregatedCardStrategy> cards2) {

		for (CardStrategy refCard : refStrategy.getAggregatedCardStrategy()) {
			Integer indexOf = cards2.indexOf(refCard);
			if (indexOf == -1) {
				refCard.setDifferenceFromStrategyRef(refCard.getOccurancePercentage());
			} else {
				CardStrategy cardToBeCalculated = cards2.get(indexOf);
				cardToBeCalculated.setDifferenceFromStrategyRef(cardToBeCalculated.getOccurancePercentage()-refCard.getOccurancePercentage());
			}
		}
	}
}
