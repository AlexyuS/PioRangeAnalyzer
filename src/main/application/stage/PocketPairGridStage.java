package main.application.stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.application.cards.Card;
import main.application.cards.CardColor;
import main.application.cards.CardHand;
import main.application.cards.IndividualCardHand;
import main.application.cards.IndividualCardStrategy;
import main.application.controller.Controller;
import main.application.exception.InvalidCardHandFormat;
import main.application.ui.helper.GridHelper;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PocketPairGridStage extends AbstractCardDetailStage {

	public PocketPairGridStage() throws IOException {
		super("resources/pocketPairGrid.fxml");
		super.initialize();
	}

	@Override
	protected void afterInitialize(Stage stage) {
	}

	public void colorCardGridForIndividualStrategy(List<IndividualCardStrategy> cardStrategy) {
		cardStrategy.forEach(e->colorGridForCard(e));
	}

	@Override
	public GridPane getGridPane() {
		return (GridPane) this.getStage().getScene().lookup("#pocketPairGrid");
	}

}
