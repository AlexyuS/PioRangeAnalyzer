package main.application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.cards.AggregatedCardStrategy;
import main.application.fileParser.FileParser;
import main.application.stage.TextAreaStage;
import main.application.strategy.StrategyHolder;
import main.application.strategy.helper.StrategyAggregator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

@Component
public class TextAreaController implements Controller{
	private final static Logger LOGGER = Logger.getLogger(TextAreaController.class.getName());

	@FXML
	public TextArea strategyTextArea;

	@FXML
	public Button buttonApply;

	@FXML
	public Button buttonClear;
	
	@Autowired
	public TextAreaStage textAreaStage;
	

	@FXML
	public void applyStrategy(ActionEvent event) {
		FileParser parser =  new FileParser();
		String rawStrategy = strategyTextArea.getText();
		try {
			List<StrategyHolder> result = parser.getStrategies(rawStrategy);
			aggregatedStrategy(result);
			
			textAreaStage.addStrategyToTree(result);
			textAreaStage.addStrategyToPlayer(result);
			textAreaStage.close();
		} catch (Exception e) {
			LOGGER.warning("Exception occured during strategy parser: "+e.getMessage());
			e.printStackTrace();
		}
	}

	private void aggregatedStrategy(List<StrategyHolder> strategies) {
		strategies.forEach(e->{
			List<AggregatedCardStrategy> aggregatedCards = StrategyAggregator.aggregateIndividualCards(e.getIndividualCards());
			e.setAggregatedCardStrategy(aggregatedCards);
		});
	}
	
	@FXML
	public void clearText(ActionEvent event) {
		strategyTextArea.clear();
	}
}
