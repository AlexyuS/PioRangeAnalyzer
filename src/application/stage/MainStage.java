package application.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.controller.MainController;
import javafx.stage.Stage;

@Component
public class MainStage extends SpringStage<MainController> {
	
	@Autowired
	public MainStage(String path, Stage stage) {
		super(path, stage);
	}

	@Override
	protected void afterInitialize(Stage stage) {
		stage.setTitle("PioRangeAnalyzer");
	}


}
