package main.application.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.CardHand;
import main.application.cards.CardStrategy;
import main.application.cards.IndividualCardStrategy;
import main.application.controller.PlayerOneGridController;
import main.application.cards.HandGroupping;

public class StrategyHolder {
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	private final String strategyName;

	private List<CardStrategy> individualCards;
	private List<StrategyHolder> childs;

	private List<AggregatedCardStrategy> aggregatedCardStrategy;

	public StrategyHolder(String strategyName) {
		this.strategyName = strategyName;
		this.individualCards = new ArrayList<>();
		this.aggregatedCardStrategy = new ArrayList<>();
	}

	public void addCards(IndividualCardStrategy suitedBundleCard) {
		individualCards.add(suitedBundleCard);
		aggregateCombos(suitedBundleCard, aggregatedCardStrategy);
	}

	public String getStrategy() {
		return this.strategyName;
	}

	public List<CardStrategy> getIndividualCards() {
		return individualCards;
	}

	public List<StrategyHolder> getChilds() {
		return childs;
	}

	public void addChilds(List<StrategyHolder> childs) {
		if (this.childs == null) {
			this.childs = new ArrayList<>();
		}

		for (StrategyHolder strat : childs) {
			if (!this.childs.contains(strat)) {
				this.childs.add(strat);
			}
		}

	}

	public List<AggregatedCardStrategy> getAggregatedCardStrategy() {
		return aggregatedCardStrategy;
	}

	private void aggregateCombos(IndividualCardStrategy cardStrategy, List<AggregatedCardStrategy> aggrgatedCards) {
		CardHand cardHand = cardStrategy.getCardHand();
		HandGroupping handGroupping = determineHandGrouping(cardHand);
		AggregatedCardStrategy aggregatedCard = new AggregatedCardStrategy(cardHand.getHighCard(),
				cardHand.getLowCard(), handGroupping);
		int index = aggrgatedCards.indexOf(aggregatedCard);
		if (index == -1) {
			LOGGER.info("New card hand "+cardHand+" is created in the list for strategy"+strategyName);
			LOGGER.info("--------with occurance :"+cardStrategy.getOccurancePercentage());
			LOGGER.info("--------and absolute occurance:"+cardStrategy.getAbsoluteOccurance());
			aggregatedCard.setOccurancePercentage(cardStrategy.getOccurancePercentage());
			aggregatedCard.setOccuranceAbsolute(cardStrategy.getAbsoluteOccurance());
			aggrgatedCards.add(aggregatedCard);
		} else {
			LOGGER.info("New card hand"+cardHand+" is aggregated in the list for strategy "+strategyName);
			LOGGER.info("--------with occurance :"+cardStrategy.getOccurancePercentage());
			LOGGER.info("--------and absolute occurance:"+cardStrategy.getAbsoluteOccurance());
			AggregatedCardStrategy exsitingAggregatedCard = aggrgatedCards.get(index);
			exsitingAggregatedCard.increaseOccurancePercentage(cardStrategy.getOccurancePercentage());
			exsitingAggregatedCard.increaseOccuranceAbsolute(cardStrategy.getAbsoluteOccurance());
		}
	}

	private HandGroupping determineHandGrouping(CardHand cardHand) {
		if (cardHand.getHighCard() == cardHand.getLowCard()) {
			return HandGroupping.PAIRED;
		}
		if (cardHand.getHighCardColor() == cardHand.getLowCardColor()) {
			return HandGroupping.SUITED;
		}
		return HandGroupping.OFFSUITED;
	}

	@Override
	public String toString() {
		return strategyName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((strategyName == null) ? 0 : strategyName.hashCode());
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
		StrategyHolder other = (StrategyHolder) obj;
		if (strategyName == null) {
			if (other.strategyName != null)
				return false;
		} else if (!strategyName.equals(other.strategyName))
			return false;
		return true;
	}

}
