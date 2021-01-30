package application.controller;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

@Component
public class TextAreaController {
	@FXML
	public TextArea strategyTextArea;

	@FXML
	public Button buttonApply;

	@FXML
	public Button buttonClear;

	@FXML
	public void applyStrategy(ActionEvent event) {

	}

	@FXML
	public void clearText(ActionEvent event) {

	}
}
