package main.application.ui.helper;

import java.util.logging.Logger;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.application.stage.TextAreaStage;
import main.application.strategy.PlayerStrategyHolder;
import main.application.ui.TreeObject;

public class TextAreaStageHelper {
	private final static Logger LOGGER = Logger.getLogger(TextAreaStageHelper.class.getName());

	public static final void open(TextAreaStage textAreaStage, ChoiceBox<PlayerStrategyHolder> choiceBox,
			TreeView<TreeObject> treeView) {
		PlayerStrategyHolder player = choiceBox.getValue();

		if (player == null || player.getPlayerName().equals("Select")) {
			LOGGER.warning("No player found");
			return;
		}
		TreeItem<TreeObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
		textAreaStage.open(player, selectedItem);
	}
}
