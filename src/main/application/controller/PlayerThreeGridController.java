package main.application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import main.application.stage.SpringStage;
import main.application.stage.TextAreaStage;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.helper.StrategyHelper;
import main.application.ui.TreeObject;
import main.application.ui.TreeStorage;
import main.application.ui.helper.ChoiceSelectionHelper;
import main.application.ui.helper.TextAreaStageHelper;
import main.application.ui.helper.TreeViewHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PlayerThreeGridController implements GridController,InitializingBean {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(PlayerThreeGridController.class.getName());

	@Autowired
	public MainController mainController;

	@Autowired
	public TreeStorage treeStorage;

	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<TreeObject> treeView3;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox3;

	@FXML
	public List<Pane> cardGridEmpty3;

	@FXML
	public List<Pane> cardGridDiff3;

	@FXML
	public List<Pane> cardGridFill3;

	@FXML
	public List<Text> handLabel3;

	@FXML
	public List<Text> handCount3;

	
	@Override
	public void onTreeInsert(ActionEvent e) {
		TextAreaStageHelper.open(textAreaStage, choiceBox3, treeView3);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		PlayerStrategyHolder playerStrategyHolder= choiceBox3.getValue();
		TreeViewHelper.removeNode(playerStrategyHolder.getPlayerName(),treeView3, treeStorage);
		
		String id = treeView3.getSelectionModel().getSelectedItem().getValue().getId();
		StrategyHelper.clearStrategyFromPool(playerStrategyHolder.getStrategyHolder(), id);
	}

	@Override
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeStorage, treeView3, oldSelection, newSelection);
		mainController.notify(oldSelection,newSelection,PlayerThreeGridController.class);

	}

	@Override
	public void addPlayerToChoiceList(PlayerStrategyHolder players) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox3, players);
	}

	@Override
	public void removePlayerFromChoiceList(PlayerStrategyHolder player) {
		ChoiceSelectionHelper.removeDirtyPlayer(choiceBox3, player);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.mainController.register(this);
	}
}
