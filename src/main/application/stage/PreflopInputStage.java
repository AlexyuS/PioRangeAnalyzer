package main.application.stage;


import org.springframework.stereotype.Component;

import javafx.stage.Stage;
import main.application.controller.PreflopStrategyController;

@Component
public class PreflopInputStage extends SpringStage<PreflopStrategyController> {
		
	public PreflopInputStage(String path) {
		super(path);
	}

	
	@Override
	protected void afterInitialize(Stage stage) {
		
	}


}
