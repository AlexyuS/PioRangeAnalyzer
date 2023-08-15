package main.application.stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javafx.scene.layout.GridPane;


import main.application.cards.IndividualCardStrategy;
import main.application.ui.helper.GridHelper;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PocketPairGridStage extends AbstractCardDetailStage {

	public PocketPairGridStage() throws IOException {
		super("resources/pocketPairGrid.fxml");
	}

	public void colorCardGridForIndividualStrategy(List<IndividualCardStrategy> cardStrategy) {
		clearCardGrid();
		cardStrategy.forEach(e->colorGridForCard(e));
	}

	@Override
	public GridPane getGridPane() {
		return (GridPane) this.getStage().getScene().lookup("#pocketPairGrid");
	}

	@Override
	protected void clearCardGrid() {
	List <GridPane> gridListToClean = new ArrayList<>();
		
		gridListToClean.add((GridPane)this.getStage().getScene().lookup("#sc"));
		gridListToClean.add((GridPane)this.getStage().getScene().lookup("#sd"));
		gridListToClean.add((GridPane)this.getStage().getScene().lookup("#sh"));
		gridListToClean.add((GridPane)this.getStage().getScene().lookup("#hc"));
		gridListToClean.add((GridPane)this.getStage().getScene().lookup("#hd"));
		gridListToClean.add((GridPane)this.getStage().getScene().lookup("#dc"));
	
		
		GridHelper.clearGrid(gridListToClean);

	}

}
