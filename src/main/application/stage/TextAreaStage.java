package main.application.stage;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.controller.TextAreaController;
import javafx.stage.Modality;

@Component
public class TextAreaStage extends SpringStage<TextAreaController>{    
    private String result;
    
	@Autowired
	public TextAreaStage(String path) {
		super(path);
		getStage().initModality(Modality.APPLICATION_MODAL);
	}

	
	
	public void showAndWait() {
		getStage().setTitle("Please insert the range");
		getStage().showAndWait();
	}

	public String getResult() {
		return this.result;
	}
	
	public void closeStage(String input) {
		this.result = input;
		close();
	}
	

}
