package main.application.ui.helper.model;

import java.util.List;

import javafx.scene.layout.Pane;

public class GridElementsBundle {
	private final List<Pane> cardGridEmpty;

	private final List<Pane> cardGridDiff;

	private final List<Pane> cardGridFill;

	public GridElementsBundle(List<Pane> cardGridEmpty, List<Pane> cardGridDiff, List<Pane> cardGridFill) {
		this.cardGridEmpty = cardGridEmpty;
		this.cardGridDiff = cardGridDiff;
		this.cardGridFill = cardGridFill;
	}

	public List<Pane> getCardGridEmpty() {
		return cardGridEmpty;
	}

	public List<Pane> getCardGridDiff() {
		return cardGridDiff;
	}

	public List<Pane> getCardGridFill() {
		return cardGridFill;
	}
	
}
