package application.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.controller.TextAreaController;
import javafx.stage.Stage;

@Component
public class TextAreaStage extends SpringStage<TextAreaController>{

	@Autowired
	public TextAreaStage(String path) {
		super(path);
	}

	@Override
	protected void afterInitialize(Stage stage) {
		stage.setTitle("Please insert the range");
	}

}
