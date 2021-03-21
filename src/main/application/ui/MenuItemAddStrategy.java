package main.application.ui;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import main.application.fileParser.StrategyReader;
import main.application.stage.MainStage;
import main.application.stage.TextAreaStage;
import main.application.strategy.StrategyHolder;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MenuItemAddStrategy extends MenuItem{
	
	@Autowired
	TextAreaStage textAreaStage;
	
	@Autowired
	MainStage stage;
		
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	private Integer playerIndex;
	
	public MenuItemAddStrategy()  {
		super("Add");
		super.setOnAction(new OnTreeInsertEvent());
	}
	
	public void setPlayerIndex(Integer index) {
		this.playerIndex = index;
	}
	
	private class OnTreeInsertEvent implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			textAreaStage.showAndWait();
			String result = textAreaStage.getResult();
		
			StrategyReader strategyReader = new StrategyReader();
			
			try {
				List<StrategyHolder> strategy = strategyReader.getStrategies(result);
				
				OnStrategyInsertEvent strategyInsertEvent = new OnStrategyInsertEvent();
				strategyInsertEvent.setStrategies(strategy);
				strategyInsertEvent.setPlayerIndex(playerIndex);
				applicationEventPublisher.publishEvent(strategyInsertEvent);
			} catch (Exception e) {
				System.out.println("Could not parse strategies");
				e.printStackTrace();
			}
			
		}
	}
	
	public static class OnStrategyInsertEvent{
		
		List<StrategyHolder> strategies;
		Integer playerIndex;
		
		public Integer getPlayerIndex() {
			return playerIndex;
		}
		public void setPlayerIndex(Integer playerIndex) {
			this.playerIndex = playerIndex;
		}
		public List<StrategyHolder> getStrategies() {
			return strategies;
		}
		public void setStrategies(List<StrategyHolder> strategies) {
			this.strategies = strategies;
		}
		
	
		
	}
}
