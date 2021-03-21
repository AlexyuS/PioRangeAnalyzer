package main.application.controller;


import org.springframework.beans.factory.annotation.Autowired;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.application.stage.PreflopInputStage;
import main.application.stage.PreflopInputStage.PreflopInputResult;



public class PreflopStrategyController implements Controller{
	
	@Autowired
	private PreflopInputStage preflopInputStage;
	

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
		PreflopInputResult inputResult = new PreflopInputResult();
		inputResult.setPlayerName(playerTextField.getText());
		inputResult.setStrategy(strategyTextArea.getText());
		preflopInputStage.close(inputResult);
	}

	
	@FXML
	public void clearText(ActionEvent event) {
		strategyTextArea.clear();
	}
	
}
