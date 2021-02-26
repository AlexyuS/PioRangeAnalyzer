package main.application.ui.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import main.application.cards.AggregatedCardStrategy;
import main.application.strategy.StrategyHolder;

public class GridHelper {

	public void fillGridForStrategy(StrategyHolder strategy, GridPane grid) {
		List<AggregatedCardStrategy> aggregatedCards = strategy.getAggregatedCardStrategy();
		List<GridPane> gridList = getChildGridsForGridParent(grid);

		clearGrid(gridList);
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

		if (difference > 0) {
			occurance -= difference;
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
			fillGridMoreThanRef(grid, occurance, difference);
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

		setBackgroundToGrid(grid, totalOccurance, "red", diffOccurance);
	}

	private void fillGridMoreThanRef(GridPane grid, double totalOccurance, double diffOccurance) {
		setBackgroundToGrid(grid, totalOccurance, "green", diffOccurance);
	}

	private void setBackgroundToGrid(GridPane gridPane, double totOccurance, String colorCode, double diffOccurance) {

		if (totOccurance + diffOccurance > 100) {
			throw new IllegalArgumentException("Total occurance percentage cannot exceed 100%");
		}

		List<Node> existingPane = gridPane.getChildren().stream().filter(e -> e instanceof Pane)
				.collect(Collectors.toList());
		gridPane.getChildren().removeAll(existingPane);

		double noOccurance = 100 - totOccurance - diffOccurance;

		colorGrid(gridPane, noOccurance, "", 0);
		colorGrid(gridPane, diffOccurance, colorCode, 1);
		colorGrid(gridPane, totOccurance, "yellow", 2);
		
	}

	public static void colorGrid(GridPane gridPane, double percentage, String colorCode, int rowIndex) {
		RowConstraints row = gridPane.getRowConstraints().get(rowIndex);
		row.setMinHeight(percentage / 100 * gridPane.getPrefHeight());
		row.setMaxHeight(percentage / 100 * gridPane.getPrefHeight());
		row.setVgrow(Priority.NEVER);

		Pane backgroundPane = new Pane();
		if (StringUtils.isNotBlank(colorCode)) {
			backgroundPane.setStyle("-fx-background-color:" + colorCode);
		}

		gridPane.add(backgroundPane, 0, rowIndex, 2, 1);
		backgroundPane.toBack();
	}

	private static void clearGrid(List<GridPane> grid) {
		grid.forEach(e -> e.getChildren().removeIf(p -> p instanceof Pane));
		grid.forEach(e -> e.getChildren().forEach(c -> {
			if (c instanceof Text) {
				((Text) c).setText("");
			}
		}));
	}

}
