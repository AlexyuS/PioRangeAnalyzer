package main.application.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.cards.IndividualCardStrategy;
import main.application.controller.CardDetailController;
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

	@Override
	public void open(IndividualCardStrategy... params) throws Exception {
		super.open();
		for(IndividualCardStrategy param:params) {
			getController().initializeContent(param);
		}
	}

	@Override
	protected void onClose(Stage stage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refreshUI(Stage stage) {
		// TODO Auto-generated method stub
		
	}

}
