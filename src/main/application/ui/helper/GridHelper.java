package main.application.ui.helper;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import main.application.cards.AggregatedCardStrategy;
import main.application.strategy.StrategyHolder;

public class GridHelper {

	public void fillGridForStrategy(StrategyHolder strategy, GridPane grid) {
		List<AggregatedCardStrategy> aggregatedCards = strategy.getAggregatedCardStrategy();
		List<GridPane> gridList = getChildGridsForGridParent(grid);

		aggregatedCards.forEach(e -> colorGridForAggregatedCrad(gridList, e));
	}

	private void colorGridForAggregatedCrad(List<GridPane> gridPaneList, AggregatedCardStrategy cardStrategy) {

		int index = cardStrategy.getCardHand().getCardIndex();
		double difference = cardStrategy.getDiffFromStrategyRef();
		double occurance = cardStrategy.getOccurancePercentage();
		GridPane grid = gridPaneList.get(index);

		double absoluteOccurance = cardStrategy.getAbsoluteOccurance();
		setCardLabelOccurance(grid, absoluteOccurance);
		setCardLabel(grid, cardStrategy);

		if(difference>0) {
			occurance-=difference;
		}
		colorGridForIndex(grid, index, difference, occurance);

	}

	private void setCardLabelOccurance(GridPane grid, double absolutOccurance) {
		Text occuranceTextField = getAbsoluteOccuranceTextField(grid);
		occuranceTextField.setText(absolutOccurance + "");
	}

	private void setCardLabel(GridPane grid, AggregatedCardStrategy cardStrategy) {
		Text occuranceTextField = getCardLabelTextField(grid);
		String highCard = cardStrategy.getCardHand().getHighCard().getLabel();
		String lowCard = cardStrategy.getCardHand().getLowCard().getLabel();
		char handTypeLabel = cardStrategy.getCardHand().getGroupShortcut();
		occuranceTextField.setText(highCard + lowCard + handTypeLabel);
	}

	private void colorGridForIndex(GridPane grid, int index, double difference, double occurance) {
		if (difference < 0) {
			fillGridLessThanReff(grid, occurance, -1 * difference);
		} else {
			fillGridMoreThanRef(grid, occurance,difference);
		}
	}

	private Text getAbsoluteOccuranceTextField(GridPane grid) {
		return grid.getChildren().stream().filter(e -> e instanceof Text).map(e -> (Text) e)
				.collect(Collectors.toList()).get(1);
	}

	private Text getCardLabelTextField(GridPane grid) {
		return grid.getChildren().stream().filter(e -> e instanceof Text).map(e -> (Text) e)
				.collect(Collectors.toList()).get(0);
	}

	private List<GridPane> getChildGridsForGridParent(GridPane grid) {
		return grid.getChildren().stream().filter(e -> e instanceof GridPane).map(e -> (GridPane) e)
				.collect(Collectors.toList());
	}

	private void fillGridLessThanReff(GridPane grid, double totalOccurance, double diffOccurance) {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:transparent,red");

		setBackgroundToGrid(grid, totalOccurance, pane, diffOccurance);
	}

	private void fillGridMoreThanRef(GridPane grid, double totalOccurance, double diffOccurance) {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:transparent,green");

		setBackgroundToGrid(grid, totalOccurance, pane, diffOccurance);
	}

	private void setBackgroundToGrid(GridPane gridPane, double totOccurance, Pane diffOccuranceBackground,
			double diffOccurance) {
		
		
		if (totOccurance + diffOccurance > 100) {
			throw new IllegalArgumentException("Total occurance percentage cannot exceed 100%");
		}
	
		List<Node> existingPane = gridPane.getChildren().stream().filter(e-> e instanceof Pane).collect(Collectors.toList());
		gridPane.getChildren().removeAll(existingPane);
		
		double noOccurance = 100-totOccurance-diffOccurance;
		double totHeight = gridPane.getHeight()-3;
		RowConstraints row1 = gridPane.getRowConstraints().get(0);
		row1.setMinHeight(noOccurance / 100 * totHeight);
		row1.setMaxHeight(noOccurance / 100 * totHeight);

		RowConstraints row2 =  gridPane.getRowConstraints().get(1);
		row2.setMinHeight(diffOccurance / 100 * totHeight);
		row2.setMaxHeight(diffOccurance / 100 * totHeight);


		RowConstraints row3 =  gridPane.getRowConstraints().get(2);
		row3.setMinHeight(totOccurance / 100 * totHeight);
		row3.setMaxHeight(totOccurance / 100 * totHeight);

		Pane baseOccuranceBackground = new Pane();
		baseOccuranceBackground.setStyle("-fx-background-color:transparent,yellow");

		Pane noOccurancePane = new Pane();
		
		gridPane.add(noOccurancePane, 0, 0, 2, 1);
		gridPane.add(diffOccuranceBackground, 0, 1, 2, 1);
		gridPane.add(baseOccuranceBackground, 0, 2, 2, 1);

		noOccurancePane.toBack();
		diffOccuranceBackground.toBack();
		baseOccuranceBackground.toBack();
	}
}
