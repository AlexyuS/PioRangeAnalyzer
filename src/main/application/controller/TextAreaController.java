package main.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import main.application.stage.TextAreaStage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

@Component
public class TextAreaController implements Controller{
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
		textAreaStage.closeStage(strategyTextArea.getText());
	}

	@FXML
	public void clearText(ActionEvent event) {
		strategyTextArea.clear();
	}
}
