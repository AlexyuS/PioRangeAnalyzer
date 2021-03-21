package main.application.stage;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javafx.scene.layout.GridPane;


import main.application.cards.IndividualCardStrategy;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SuitedPairGridStage extends AbstractCardDetailStage {

	public SuitedPairGridStage() throws IOException {
		super("resources/suitedPairGrid.fxml");
	}


	public void colorCardGridForIndividualStrategy(List<IndividualCardStrategy> cardStrategy) {
		cardStrategy.forEach(e->colorGridForCard(e));
	}

	@Override
	public GridPane getGridPane() {
		return (GridPane) this.getStage().getScene().lookup("#suitedPairGrid");
	}

}
