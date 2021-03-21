package main.application.strategy.calculator;

import java.util.List;
import java.util.logging.Logger;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.CardStrategy;
import main.application.cards.IndividualCardStrategy;
import main.application.strategy.StrategyHolder;

public class StrategyDiffCalculator {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(StrategyDiffCalculator.class.getName());
	private final StrategyHolder refStrategy;
	
	public StrategyDiffCalculator(StrategyHolder baseStrategy) {
		this.refStrategy = baseStrategy;
	}

	public void calculateStrategies(StrategyHolder strategy1) {
		if(refStrategy==null || strategy1==null) {
			return;
		}
		calculateCardDifferenceForIndividualCards(strategy1.getIndividualCards());
		calculateCardDifferenceForAggregatedCards(strategy1.getAggregatedCardStrategy());
	}

	private void calculateCardDifferenceForIndividualCards(
			List<IndividualCardStrategy> calculatableCards) {
		
		List<IndividualCardStrategy> refCards = this.refStrategy.getIndividualCards();
		for (CardStrategy calculatableCard : calculatableCards) {
			Integer indexOf = refCards.indexOf(calculatableCard);
			if (indexOf == -1) {
				calculatableCard.setDifferenceFromStrategyRef(calculatableCard.getOccurancePercentage());
			} else {
				CardStrategy refCard = refCards.get(indexOf);
				calculatableCard.setDifferenceFromStrategyRef(calculatableCard.getOccurancePercentage()-refCard.getOccurancePercentage());
			}
		}
	}
	
	private void calculateCardDifferenceForAggregatedCards(
			List<AggregatedCardStrategy> calculatableCards) {
		
		List<AggregatedCardStrategy> refCards = this.refStrategy.getAggregatedCardStrategy();
		for (CardStrategy calculatableCard : calculatableCards) {
			Integer indexOf = refCards.indexOf(calculatableCard);
			if (indexOf == -1) {
				calculatableCard.setDifferenceFromStrategyRef(calculatableCard.getOccurancePercentage());
			} else {
				CardStrategy refCard = refCards.get(indexOf);
				calculatableCard.setDifferenceFromStrategyRef(calculatableCard.getOccurancePercentage()-refCard.getOccurancePercentage());
			}
		}
	}
	
	
}
