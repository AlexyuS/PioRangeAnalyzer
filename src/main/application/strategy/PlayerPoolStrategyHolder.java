package main.application.strategy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;


@Component
public class PlayerPoolStrategyHolder {
	private Map<String,PlayerStrategyHolder> playerPool;
	
	public PlayerPoolStrategyHolder() {
		this.playerPool = new HashMap<>();
	}
	
	public PlayerStrategyHolder getStrategyForPlayer (String player) {
		return playerPool.get(player);
	}
	
	public void putNewPlayerStrategy(PlayerStrategyHolder player) {
		this.playerPool.put(player.getPlayerName(), player);
	}
	
	public Set<String> getAllPlayers(){
		return playerPool.keySet();
	}
	
	public Collection<PlayerStrategyHolder> getAllStrategies(){
		return playerPool.values();
	}
}
