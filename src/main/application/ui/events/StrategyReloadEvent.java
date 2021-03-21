package main.application.ui.events;

public class StrategyReloadEvent {
	private String playerName;

	public StrategyReloadEvent(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
}
