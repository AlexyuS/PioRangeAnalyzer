package main.application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.application.cards.AggregatedCardStrategy;
import main.application.fileParser.StrategyReader;
import main.application.stage.PreflopInputStage;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.strategy.helper.StrategyAggregator;


public class PreflopStrategyController implements Controller{
	private final static Logger LOGGER = Logger.getLogger(PreflopStrategyController.class.getName());
	
	@Autowired
	private PreflopInputStage preflopInputStage;
	
	@Autowired
	private PlayerPoolStrategyHolder playerPool;
	
	@Autowired
	private MainController mainController;
	
	@FXML
	public TextArea strategyTextArea;

	@FXML
	public Button buttonApply;

	@FXML
	public Button buttonClear;
	
	@FXML
	public TextField playerTextField;
	
	

	@FXML
	public void applyStrategy(ActionEvent event) {
		StrategyReader fileParser = new StrategyReader();
		StrategyHolder preflopStrategy = fileParser.parsePreflopStrategy(strategyTextArea.getText());
		
		List<AggregatedCardStrategy> aggregatedCards = StrategyAggregator.aggregateIndividualCards(preflopStrategy.getIndividualCards());
		preflopStrategy.setAggregatedCardStrategy(aggregatedCards);
		
		String newPlayer =  playerTextField.getText();
		addNewPreflopStrategy(newPlayer, preflopStrategy);
		preflopInputStage.close();
	}

	
	@FXML
	public void clearText(ActionEvent event) {
		strategyTextArea.clear();
	}
	
	private void addNewPreflopStrategy(String player,StrategyHolder strategy) {
		LOGGER.info(String.format("New player %s has been added to the player pool",player));
		PlayerStrategyHolder strategyHolder=  new PlayerStrategyHolder(player);
		strategyHolder.setStrategyHolder(strategy);
		
		playerPool.putNewPlayerStrategy(strategyHolder);
		mainController.insertNewPlayer(strategyHolder);
	}
}
