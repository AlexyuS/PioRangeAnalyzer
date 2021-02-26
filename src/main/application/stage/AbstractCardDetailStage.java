package main.application.stage;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.application.cards.CardColor;
import main.application.cards.CardHand;
import main.application.cards.IndividualCardHand;
import main.application.cards.IndividualCardStrategy;
import main.application.controller.Controller;
import main.application.exception.InvalidCardHandFormat;
import main.application.ui.helper.GridHelper;

public abstract class AbstractCardDetailStage extends SpringStage<Controller> {

	public AbstractCardDetailStage(String path) {
		super(path);
	}

	public abstract GridPane getGridPane();
	
	protected Text determineTextLabelForCardHand(CardHand cardHand) {
		CardColor highCardColor = cardHand.getHighCardColor();
		CardColor lowCardColor = cardHand.getLowCardColor();
		
		Text textElement = (Text) this.getStage().getScene().lookup("#"+highCardColor.getLabel()+lowCardColor.getLabel()+"Label");
		
		if(textElement==null) {
			throw new InvalidCardHandFormat(cardHand+ " could not be found in the grid");
		}
		
		return textElement;
	}
	
	
	protected GridPane determinGridForCardHand(CardHand cardHand) {
		CardColor highCardColor = cardHand.getHighCardColor();
		CardColor lowCardColor = cardHand.getLowCardColor();


		GridPane sc = (GridPane) this.getStage().getScene().lookup("#"+highCardColor.getLabel()+lowCardColor.getLabel());
		
		if(sc==null) {
			throw new InvalidCardHandFormat(cardHand+ " could not be found in the grid");
		}
		
		return sc;
	}
	
	protected void colorGridForCard(IndividualCardStrategy strategy) {

		IndividualCardHand cardHand = strategy.getCardHand();
		Text textLabel = determineTextLabelForCardHand(cardHand);
		textLabel.setText(cardHand.getHighCard().getLabel() + cardHand.getLowCard().getLabel());

		GridPane gridPane = determinGridForCardHand(cardHand);
		List<Node> deletableNodes = gridPane.getChildren().stream().filter(e -> e instanceof Pane)
				.collect(Collectors.toList());
		gridPane.getChildren().removeAll(deletableNodes);

		double difference = strategy.getDiffFromStrategyRef();
		double totalOccurance = strategy.getOccurancePercentage();
		double noOccurance = 100 - totalOccurance - difference;

		GridHelper.colorGrid(gridPane, noOccurance, null, 0);
		if (difference < 0) {
			GridHelper.colorGrid(gridPane, difference, "red", 1);
		} else {
			GridHelper.colorGrid(gridPane, difference, "green", 1);
		}
		
	}
}
