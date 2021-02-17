package main.application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.fileParser.FileParser;
import main.application.stage.TextAreaStage;
import main.application.strategy.StrategyHolder;
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
			
			textAreaStage.addStrategyToTree(result);
			textAreaStage.addStrategyToPlayer(result);
			textAreaStage.close();
		} catch (Exception e) {
			LOGGER.warning("Exception occured during strategy parser: "+e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	@FXML
	public void clearText(ActionEvent event) {
		strategyTextArea.clear();
	}
}
