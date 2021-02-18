package main.application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import main.application.stage.SpringStage;
import main.application.stage.TextAreaStage;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.ui.TreeObject;
import main.application.ui.TreeStorage;
import main.application.ui.helper.ChoiceSelectionHelper;
import main.application.ui.helper.TextAreaStageHelper;
import main.application.ui.helper.TreeViewHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class PlayerFourGridController implements GridController,InitializingBean {

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(PlayerFourGridController.class.getName());

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
	public TreeView<TreeObject> treeView4;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox4;

	@FXML
	public List<Pane> cardGridEmpty4;

	@FXML
	public List<Pane> cardGridDiff4;

	@FXML
	public List<Pane> cardGridFill4;

	@FXML
	public List<Text> handLabel4;

	@FXML
	public List<Text> handCount4;


	@Override
	public void onTreeInsert(ActionEvent e) throws Exception {
		TextAreaStageHelper.open(textAreaStage, choiceBox4, treeView4);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		TreeItem<TreeObject> selectedItem = treeView4.getSelectionModel().getSelectedItem();
		if (selectedItem.getParent() == null) {
			return;
		}
		selectedItem.getParent().getChildren().remove(selectedItem);
	}

	@Override
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeStorage, treeView4, oldSelection, newSelection);
		mainController.notify(oldSelection,newSelection,PlayerFourGridController.class);
	}

	@Override
	public void addPlayerToChoiceList(PlayerStrategyHolder players) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox4, players);
	}

	@Override
	public void removePlayerFromChoiceList(PlayerStrategyHolder player) {
		ChoiceSelectionHelper.removeDirtyPlayer(choiceBox4, player);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.mainController.register(this);
	}


}
