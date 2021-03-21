package main.application.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import main.application.cards.AggregatedCardStrategy;
import main.application.fileParser.StrategyReader;
import main.application.stage.PreflopInputStage;
import main.application.stage.PreflopInputStage.PreflopInputResult;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.strategy.helper.StrategyAggregator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

@Component
public class MainController implements Controller {
	
	@Autowired
	private PreflopInputStage preflopStage;
	
	@FXML
	public MenuItem addPlayerMenuItem;

	@FXML
	public MenuItem closeMenuItem;

	@FXML
	public PlayerOneGridController playerOneController;

	@FXML
	public PlayerTwoGridController playerTwoController;

	@FXML
	public PlayerThreeGridController playerThreeController;

	@FXML
	public PlayerFourGridController playerFourController;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private PlayerPoolStrategyHolder playerPool;
	
	@FXML
	public void onAddPlayer(ActionEvent event) {
		preflopStage.open();
		PreflopInputResult result = preflopStage.getResult();
		
		StrategyReader fileParser = new StrategyReader();
		StrategyHolder preflopStrategy = fileParser.parsePreflopStrategy(result.getStrategy());
				
		List<AggregatedCardStrategy> aggregatedCards = StrategyAggregator.aggregateIndividualCards(preflopStrategy.getIndividualCards());
		preflopStrategy.setAggregatedCardStrategy(aggregatedCards);
		
		PlayerStrategyHolder playerStrategyHolder =  new PlayerStrategyHolder(preflopStage.getResult().getPlayerName());		
		playerStrategyHolder.setStrategyHolder(preflopStrategy);
		
		playerPool.putNewPlayerStrategy(playerStrategyHolder);
		
		applicationEventPublisher.publishEvent(playerStrategyHolder);
	}

	
	public PlayerOneGridController getPlayerOneController() {
		return playerOneController;
	}

	public PlayerTwoGridController getPlayerTwoController() {
		return playerTwoController;
	}

	public PlayerThreeGridController getPlayerThreeController() {
		return playerThreeController;
	}

	public PlayerFourGridController getPlayerFourController() {
		return playerFourController;
	}

	
	public void fillGridForStrategy(StrategyHolder strategyHolder,GridPane parentGrid) {
		if(strategyHolder==null) {
			return;
		}
		
		/*
		 * StrategyDiffCalculator strategyDifCalculator = new
		 * StrategyDiffCalculator(strategy);
		 * strategyDifCalculator.calculateStrategies(strategyHolder);
		 * 
		 * GridHelper gridhelper = new GridHelper();
		 * gridhelper.fillGridForStrategy(strategyHolder, parentGrid);
		 */
	}
}
