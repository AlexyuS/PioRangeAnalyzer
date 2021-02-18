package main.application.ui.helper;

import java.util.logging.Logger;

import javafx.scene.control.ChoiceBox;
import main.application.strategy.PlayerStrategyHolder;

public class ChoiceSelectionHelper {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(ChoiceSelectionHelper.class.getName());

	public static final PlayerStrategyHolder getPlayerForIndex(ChoiceBox<PlayerStrategyHolder> choiceBox,
			Number index) {
		if ((Integer) index == -1) {
			return null;
		}
		if (choiceBox.getItems().size() == 0) {
			return null;
		}
		return choiceBox.getItems().get(index.intValue());
	}

	public static final void populateChoiceList(ChoiceBox<PlayerStrategyHolder> choiceBox,
			PlayerStrategyHolder player) {
		if (player.getStrategyHolder() == null) {
			return;
		}
		choiceBox.getItems().add(player);
	}

	public static final void removeDirtyPlayer(ChoiceBox<PlayerStrategyHolder> choiceBox, PlayerStrategyHolder player) {
		if (player == null) {
			return;
		}
		if (choiceBox.getValue().getPlayerName().equals(player.getPlayerName())) {
			return;
		}

		if (choiceBox.getItems().contains(player)) {
			choiceBox.getItems().remove(player);
		}

	}

}
