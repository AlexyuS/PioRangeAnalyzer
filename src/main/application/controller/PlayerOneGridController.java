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
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

@Component
public class PlayerOneGridController implements GridController {
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;

	@Autowired
	public TreeStorage treeStorage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<TreeObject> treeView1;

	@FXML
	public ChoiceBox<String> choiceBox1;

	@FXML
	public List<Pane> cardGridEmpty1;

	@FXML
	public List<Pane> cardGridDiff0;

	@FXML
	public List<Pane> cardGridFill0;

	@FXML
	public List<Text> handLabel0;

	@FXML
	public List<Text> handCount0;


	@Override
	public void onTreeInsert(ActionEvent e) {
		PlayerStrategyHolder player = playerPool.getStrategyForPlayer(choiceBox1.getValue());
		if (player == null) {
			LOGGER.warning("No player found");
			return;
		}
		TreeItem<TreeObject> selectedItem = treeView1.getSelectionModel().getSelectedItem();
		textAreaStage.open(player, selectedItem);

	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		TreeItem<TreeObject> selectedItem = treeView1.getSelectionModel().getSelectedItem();
		if (selectedItem.getParent() == null) {
			return;
		}
		selectedItem.getParent().getChildren().remove(selectedItem);
	}

	@FXML
	public void onMouseClicked(MouseEvent e) {
		LOGGER.info(e.getPickResult().getIntersectedNode().getId());
	}

	@FXML
	public void onMouseEntered(MouseEvent e) {

		Node parentNode = e.getPickResult().getIntersectedNode().getParent();
		Integer colIndex = GridPane.getColumnIndex(parentNode);
		Integer rowIndex = GridPane.getRowIndex(parentNode);
		LOGGER.info(String.format("Hover action detected on cell %d %d ", colIndex, rowIndex));
	}

	@FXML
	public void onMouseExited(MouseEvent e) {

	}

	@Override
	public void onSelectionChanged(Number oldSelection, Number newSelection) {
		String oldPlayerSelected = ChoiceSelectionHelper.getPlayerForIndex(choiceBox1, oldSelection);
		if (StringUtils.isNotEmpty(oldPlayerSelected)) {
			treeStorage.addTreeForPlayer(oldPlayerSelected, treeView1.getRoot().getChildren());
			TreeViewHelper.clearTree(treeView1);
		}

		String newPlayerSelected = ChoiceSelectionHelper.getPlayerForIndex(choiceBox1, newSelection);
		if (StringUtils.isEmpty(newPlayerSelected)) {
			return;
		}

		List<TreeItem<TreeObject>> tree = treeStorage.getTreeForPlayer(newPlayerSelected);
		if (tree != null && tree.size()>0) {
			TreeViewHelper.addItemToRoot(treeView1, tree);
		}
	}

	@Override
	public void addPlayerToChoiceList(Set<String> players) {
		this.choiceBox1.getItems().clear();
		this.choiceBox1.getItems().addAll(players);
	}

}
