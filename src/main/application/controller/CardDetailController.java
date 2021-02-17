package main.application.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import main.application.cards.CardHand;
import main.application.cards.SuitedBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

@Component
public class CardDetailController implements Controller{
	private static final String RED_BACKGROUND ="-fx-background-color: #DE1B1B;"; 
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";
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
	
	public void initializeContent(SuitedBundle bundle)throws Exception {
		CardHand cardHand = bundle.getCardHand();
		int index = getIndexForCard(cardHand);
		
		cardDetailsGridDiff.get(index).setStyle(RED_BACKGROUND);
		cardDetailsGridFill.get(index).setStyle(YELLOW_BACKGROUND);
		cardDetailsGridLabel.get(index).setText(bundle.getOccurance()+"");
		suitedHighCard.get(index).setText(cardHand.getHighCard().getCardAsString());
		suitedLowCard.get(index).setText(cardHand.getLowCard().getCardAsString());
	}
	
	private int getIndexForCard(CardHand cardHand) throws Exception{
		if(cardHand.getHighCardColor()==cardHand.getLowCardColor()) {
			throw new Exception("Offsuited hands are not allowed here");
		}
		return cardHand.getHighCardColor().getCardRank();
	}
	
}
