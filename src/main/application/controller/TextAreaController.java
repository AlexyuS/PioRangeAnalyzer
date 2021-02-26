package main.application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.cards.AggregatedCardStrategy;
import main.application.fileParser.StrategyReader;
import main.application.stage.TextAreaStage;
import main.application.strategy.StrategyHolder;
import main.application.strategy.helper.StrategyAggregator;
import main.application.ui.helper.TreeViewHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;

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
		
		String rawStrategy = strategyTextArea.getText();
		try {
			TreeItem<StrategyHolder> treeItem = textAreaStage.getTreeItem();
			StrategyReader parser =  new StrategyReader(treeItem.getValue());
			List<StrategyHolder> result = parser.getStrategies(rawStrategy);
			treeItem.getValue().addChilds(result);
			TreeViewHelper.insertStrategyHolderForPlayer(treeItem, result);
			
			aggregatedStrategy(result);
			
			
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
