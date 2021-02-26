package main.application.strategy;

import java.util.ArrayList;
import java.util.List;

public class PlayerStrategyHolder {

	private final String playerName;
	private List<StrategyHolder> strategyHolder;
	
	public PlayerStrategyHolder(String name) {
		this.playerName = name;
		this.strategyHolder = new ArrayList<>();
	}

	public String getPlayerName() {
		return playerName;
	}

	public List<StrategyHolder> getStrategyHolder() {
		return strategyHolder;
	}

	public void setStrategyHolder(List<StrategyHolder> strategyHolder) {
		this.strategyHolder = strategyHolder;
	}

	@Override
	public String toString() {
		return playerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
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
		PlayerStrategyHolder other = (PlayerStrategyHolder) obj;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}

}
