package main.application.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import main.application.stage.PocketPairGridStage;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PlayerThreeGridController implements GridController, InitializingBean {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(PlayerThreeGridController.class.getName());

	@Autowired
	public MainController mainController;

	@Autowired
	private PocketPairGridStage pocketPairWindow;
	
	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<StrategyHolder> treeView3;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox3;

	@FXML
	public GridPane cardGrid3;

	@FXML
	public GridPane playerThreeHeader;
	
	@FXML
	public void onTreeMouseClicked(MouseEvent e) {
		if (e.getButton().compareTo(MouseButton.SECONDARY) == 0) {
			return;
		}
		sendRecalculationEventToMainControler();
	}

	private void sendRecalculationEventToMainControler() {
		if (treeView3.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		StrategyHolder strategy = treeView3.getSelectionModel().getSelectedItem().getValue();
		mainController.fillGridForStrategy(strategy, cardGrid3);
	}

	@Override
	public void onTreeInsert(ActionEvent e) {
		TextAreaStageHelper.open(textAreaStage, choiceBox3, treeView3);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		StrategyHolder strategyHolder = treeView3.getSelectionModel().getSelectedItem().getValue();

		if(strategyHolder.getStrategy().equals(treeView3.getRoot().getValue().getStrategy())) {
			return;
		}
		PlayerStrategyHolder playerStrategyHolder = choiceBox3.getValue();
		TreeViewHelper.removeNode(playerStrategyHolder.getPlayerName(), treeView3);
		this.mainController.notifyStrategyRemove(playerStrategyHolder.getPlayerName(), strategyHolder);

	}

	@Override
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeView3, oldSelection, newSelection);
	}

	@Override
	public void addPlayerToChoiceList(PlayerStrategyHolder players) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox3, players);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.mainController.registerForAnyPlayerSelectionChanged(this);
		this.mainController.registerForTreeViewChanged(this);
	}

	@Override
	public void triggerRecalculation() {
		sendRecalculationEventToMainControler();
	}

	@Override
	public void removeTreeItemForPlayerAndStrategy(String playerName, StrategyHolder strategy) {
		if(!choiceBox3.getValue().getPlayerName().equals(playerName)) {
			return;
		}
		treeView3.getRoot().getChildren().removeIf(e -> e.getValue().getStrategy() == strategy.getStrategy());

	}

}
