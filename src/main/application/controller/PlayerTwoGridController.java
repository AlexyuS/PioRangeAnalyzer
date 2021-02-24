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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PlayerTwoGridController implements GridController, InitializingBean {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(PlayerTwoGridController.class.getName());

	@Autowired
	public MainController mainController;

	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<StrategyHolder> treeView2;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox2;

	@FXML
	public GridPane cardGrid2;
	

	@FXML
	public void onTreeMouseClicked(MouseEvent e) {
		if (e.getButton().compareTo(MouseButton.SECONDARY) == 0) {
			return;
		}
		LOGGER.info("recalculation for "+PlayerTwoGridController.class.getName()+" was triggered");
		sendRecalculationEventToMainControler();
	}

	private void sendRecalculationEventToMainControler() {
		if (treeView2.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		StrategyHolder strategy = treeView2.getSelectionModel().getSelectedItem().getValue();
		mainController.triggerStrategyCalculation(strategy,cardGrid2);
	}

	
	@Override
	public void onTreeInsert(ActionEvent e) {
		TextAreaStageHelper.open(textAreaStage, choiceBox2, treeView2);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		PlayerStrategyHolder playerStrategyHolder = choiceBox2.getValue();
		TreeViewHelper.removeNode(playerStrategyHolder.getPlayerName(), treeView2);
	}

	@Override
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeView2, oldSelection, newSelection);
	}

	@Override
	public void addPlayerToChoiceList(PlayerStrategyHolder player) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox2, player);
	}

	@Override
	public void removePlayerFromChoiceList(PlayerStrategyHolder player) {
		ChoiceSelectionHelper.removeDirtyPlayer(choiceBox2, player);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		mainController.register(this);
	}

	@Override
	public void triggerRecalculation() {
		sendRecalculationEventToMainControler();
	}
}
