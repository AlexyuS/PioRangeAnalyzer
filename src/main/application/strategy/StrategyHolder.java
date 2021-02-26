package main.application.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.IndividualCardStrategy;
import main.application.controller.PlayerOneGridController;

public class StrategyHolder {
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	private final String strategyName;

	private List<AggregatedCardStrategy> aggregatedCardStrategy;
	private List<IndividualCardStrategy> individualCards;
	
	private List<StrategyHolder> childs;

	

	public StrategyHolder(String strategyName) {
		this.strategyName = strategyName;
		this.individualCards = new ArrayList<>();
	}

	public void addCards(IndividualCardStrategy suitedBundleCard) {
		individualCards.add(suitedBundleCard);
	}

	public void setIndividualCard(List<IndividualCardStrategy>individualCards) {
		this.individualCards = individualCards;
	}
	
	public String getStrategy() {
		return this.strategyName;
	}

	public List<IndividualCardStrategy> getIndividualCards() {
		return individualCards;
	}
	
	public void addIndividualCards(List<IndividualCardStrategy> individualCards){
		this.individualCards=individualCards;
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
	
	public void setAggregatedCardStrategy(List<AggregatedCardStrategy> aggregatedCards) {
		this.aggregatedCardStrategy = aggregatedCards;
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
