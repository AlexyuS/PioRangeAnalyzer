package main.application.strategy;

import java.util.ArrayList;
import java.util.List;

public class PlayerStrategyHolder {
    private String playerName;
       
    private final List<StrategyHolder> strategyHolder;

    public PlayerStrategyHolder() {
    	this.strategyHolder = new ArrayList<>();
    }
    
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<StrategyHolder> getStrategyHolder() {
		return strategyHolder;
	}

}
