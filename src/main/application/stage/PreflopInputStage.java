package main.application.stage;


import org.springframework.stereotype.Component;

import javafx.stage.Modality;
import main.application.controller.PreflopStrategyController;

@Component
public class PreflopInputStage extends SpringStage<PreflopStrategyController> {
	private PreflopInputResult result;
	
	public PreflopInputStage(String path) {
		super(path);
		getStage().initModality(Modality.APPLICATION_MODAL);
	}

	
	@Override
	public void open() {
		getStage().showAndWait();
	}
	
	public void close(PreflopInputResult result) {
		this.result=result;
		close();
	}
	
	public PreflopInputResult getResult() {
		return this.result;
	}
	
	public static class PreflopInputResult {
		String playerName;
		String strategy;
		
		public String getPlayerName() {
			return playerName;
		}
		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}
		public String getStrategy() {
			return strategy;
		}
		public void setStrategy(String strategy) {
			this.strategy = strategy;
		}
	}
}
