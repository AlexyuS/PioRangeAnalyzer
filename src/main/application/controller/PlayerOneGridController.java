package main.application.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import main.application.stage.SpringStage;
import main.application.stage.TextAreaStage;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.ui.helper.ChoiceSelectionHelper;
import main.application.ui.helper.TextAreaStageHelper;
import main.application.ui.helper.TreeViewHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PlayerOneGridController implements GridController, InitializingBean {
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	@Autowired
	private MainController mainController;

	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<StrategyHolder> treeView1;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox1;

	@FXML
	public GridPane cardGrid1;

	@FXML
	public void onMouseClicked(MouseEvent e) {
		LOGGER.info(e.getPickResult().getIntersectedNode().getId());
	}

	@FXML
	public void onMouseEntered(MouseEvent e) {
		Node parentNode = e.getPickResult().getIntersectedNode().getParent();
		Integer colIndex = GridPane.getColumnIndex(parentNode);
		Integer rowIndex = GridPane.getRowIndex(parentNode);
		// LOGGER.info(String.format("Hover action detected on cell %d %d ", colIndex,
		// rowIndex));
	}

	@FXML
	public void onMouseExited(MouseEvent e) {

	}

	@FXML
	public void onTreeMouseClicked(MouseEvent e) {
		if (e.getButton().compareTo(MouseButton.SECONDARY) == 0) {
			return;
		}
		if(treeView1.getSelectionModel().getSelectedItem()==null) {
			return;
		}
		LOGGER.info("Notify controllers  to recalculate strategy differences");
		mainController.notifyRecalculationRequired();
	}
	@Override
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeView1, oldSelection, newSelection);
	}

	@Override
	public void addPlayerToChoiceList(PlayerStrategyHolder playerPool) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox1, playerPool);
	}

	@Override
	public void removePlayerFromChoiceList(PlayerStrategyHolder player) {
		ChoiceSelectionHelper.removeDirtyPlayer(choiceBox1, player);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.mainController.register(this);
	}

	@Override
	public void onTreeInsert(ActionEvent e) {
		TextAreaStageHelper.open(textAreaStage, choiceBox1, treeView1);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		PlayerStrategyHolder playerStrategyHolder = choiceBox1.getValue();
		TreeViewHelper.removeNode(playerStrategyHolder.getPlayerName(), treeView1);
	}

	@Override
	public void triggerRecalculation() {
		mainController.triggerStrategyCalculation(getRefStrategyFromFirstView(), cardGrid1);
	}

	public StrategyHolder getRefStrategyFromFirstView() {
		return treeView1.getSelectionModel().getSelectedItem().getValue();
	}
}
