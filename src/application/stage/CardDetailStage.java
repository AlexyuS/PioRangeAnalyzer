package application.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.controller.CardDetailController;
import javafx.stage.Stage;

@Component
public class CardDetailStage extends SpringStage<CardDetailController> {

	@Autowired
	public CardDetailStage(String path) {
		super(path);
	}

	@Override
	protected void afterInitialize(Stage stage) {
		
	}

}
