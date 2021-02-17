package main.application.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import main.application.cards.SuitedBundle;

public class StrategyHolder  {
	public List<SuitedBundle> getIndividualCards() {
		return individualCards;
	}

	public void setIndividualCards(List<SuitedBundle> individualCards) {
		this.individualCards = individualCards;
	}

	private final String uuid;
	private final String strategyName;	
	

	private List<SuitedBundle> individualCards;
    
	public StrategyHolder(String strategyName) {
		this.strategyName = strategyName;
		this.individualCards = new ArrayList<>();
		this.uuid = UUID.randomUUID().toString();
	}

	public void addCards(SuitedBundle suitedBundleCard) {
		individualCards.add(suitedBundleCard);
	}
	
	public String getStrategy() {
		return this.strategyName;
	}
	
	public String getUUID() {
		return this.uuid;
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
