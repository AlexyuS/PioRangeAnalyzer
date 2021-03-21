package main.application.ui.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;
import main.application.cards.IndividualCardStrategy;
import main.application.stage.OffsuitedPairGridStage;
import main.application.stage.PocketPairGridStage;
import main.application.stage.SuitedPairGridStage;
import main.application.strategy.StrategyHolder;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CardDetailLoader {
	
	@Autowired
	private PocketPairGridStage pocketPairWindow;

	@Autowired
	private SuitedPairGridStage suitedPairWindow;

	@Autowired
	private OffsuitedPairGridStage offsuitedPairWindow;
	

	public void displayDetailedViewForSelection(TreeItem<StrategyHolder> strategy, Integer colIndex, Integer rowIndex,
			GridPane header) {

		if (strategy == null || strategy.getValue() == null) {
			return;
		}

		if (colIndex == null) {
			colIndex = 0;
		}
		if (rowIndex == null) {
			rowIndex = 0;
		}

		header.getChildren().removeIf(e -> e.getId().equals("pocketPairGrid"));
		header.getChildren().removeIf(e -> e.getId().equals("suitedPairGrid"));
		header.getChildren().removeIf(e -> e.getId().equals("offsuitedPairGrid"));

		List<IndividualCardStrategy> selectedGroup = GridHelper
				.getSelectedIndividualSubgroupFromGrid(strategy.getValue(), colIndex, rowIndex);

		if (colIndex == rowIndex) {
			header.add(pocketPairWindow.getGridPane(), 2, 0, 1, 2);
			pocketPairWindow.colorCardGridForIndividualStrategy(selectedGroup);
			return;
		}
		if (colIndex > rowIndex) {
			header.add(suitedPairWindow.getGridPane(), 2, 0, 1, 2);
			suitedPairWindow.colorCardGridForIndividualStrategy(selectedGroup);
			return;
		}
		if (colIndex < rowIndex) {
			header.add(offsuitedPairWindow.getGridPane(), 2, 0, 1, 2);
			offsuitedPairWindow.colorCardGridForIndividualStrategy(selectedGroup);
		}
	}
}
