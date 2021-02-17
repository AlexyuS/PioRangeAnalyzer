package main.application.controller;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.stage.SpringStage;
import main.application.stage.TextAreaStage;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.ui.TreeObject;
import main.application.ui.TreeStorage;
import main.application.ui.helper.ChoiceSelectionHelper;
import main.application.ui.helper.TreeViewHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

@Component
public class PlayerTwoGridController implements GridController {
	// private static final String RED_BACKGROUND = "-fx-background-color:
	// #DE1B1B;";
	// private static final String YELLOW_BACKGROUND = "-fx-background-color:
	// #F9EF1B;";
	private final static Logger LOGGER = Logger.getLogger(PlayerTwoGridController.class.getName());

	@Autowired
	public TreeStorage treeStorage;
	
	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<TreeObject> treeView2;

	@FXML
	public ChoiceBox<String> choiceBox2;

	@FXML
	public List<Pane> cardGridEmpty2;

	@FXML
	public List<Pane> cardGridDiff2;

	@FXML
	public List<Pane> cardGridFill2;

	@FXML
	public List<Text> handLabel2;

	@FXML
	public List<Text> handCount2;


	@Override
	public void onTreeInsert(ActionEvent e) {
		PlayerStrategyHolder player = playerPool.getStrategyForPlayer(choiceBox2.getValue());
		if (player == null) {
			LOGGER.warning("No player found");
			return;
		}
		TreeItem<TreeObject> selectedItem = treeView2.getSelectionModel().getSelectedItem();
		textAreaStage.open(player, selectedItem);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		TreeItem<TreeObject> selectedItem = treeView2.getSelectionModel().getSelectedItem();
		if (selectedItem.getParent() == null) {
			return;
		}
		selectedItem.getParent().getChildren().remove(selectedItem);
	}

	@Override
	public void onSelectionChanged(Number oldSelection, Number newSelection) {
		String oldPlayerSelected = ChoiceSelectionHelper.getPlayerForIndex(choiceBox2, oldSelection);
		if (StringUtils.isNotEmpty(oldPlayerSelected)) {
			treeStorage.addTreeForPlayer(oldPlayerSelected, treeView2.getRoot().getChildren());
			TreeViewHelper.clearTree(treeView2);
		}

		String newPlayerSelected = ChoiceSelectionHelper.getPlayerForIndex(choiceBox2, newSelection);
		if (StringUtils.isEmpty(newPlayerSelected)) {
			return;
		}

		List<TreeItem<TreeObject>> tree = treeStorage.getTreeForPlayer(newPlayerSelected);
		if (tree != null && tree.size()>0) {
			TreeViewHelper.addItemToRoot(treeView2, tree);
		}
		
	}

	@Override
	public void addPlayerToChoiceList(Set<String> players) {
		this.choiceBox2.getItems().clear();
		this.choiceBox2.getItems().addAll(players);
	}

}
