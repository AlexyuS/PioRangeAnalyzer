package main.application.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import main.application.cards.IndividualCardStrategy;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

@Component
public class CardDetailController implements Controller{
	
	@FXML
	public List<Pane> cardDetailsGridEmpty;

	@FXML
	public List<Pane> cardDetailsGridDiff;

	@FXML
	public List<Pane> cardDetailsGridFill;

	@FXML
	public List<Text> cardDetailsGridLabel;
	
	@FXML
	public List<Text> suitedHighCard;
	
	@FXML
	public List<Text> suitedLowCard;
	
	public void initializeContent(IndividualCardStrategy bundle)throws Exception {
		
	}
	
	
	
}
