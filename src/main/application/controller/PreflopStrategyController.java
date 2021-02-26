package main.application.controller;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class PreflopStrategyController implements Controller{
	private final static Logger LOGGER = Logger.getLogger(PreflopStrategyController.class.getName());

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
		
	}

	
	@FXML
	public void clearText(ActionEvent event) {
		strategyTextArea.clear();
	}
}
