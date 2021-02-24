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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;



public class PlayerFourGridController implements GridController,InitializingBean {

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(PlayerFourGridController.class.getName());

	@Autowired
	public MainController mainController;
	
	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<StrategyHolder> treeView4;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox4;

	@FXML
	public GridPane cardGrid4;

	@FXML
	public void onTreeMouseClicked(MouseEvent e){
		if(e.getButton().compareTo(MouseButton.SECONDARY)==0) {
			return;
		}
		sendRecalculationEventToMainControler();
	}

	@Override
	public void onTreeInsert(ActionEvent e) throws Exception {
		TextAreaStageHelper.open(textAreaStage, choiceBox4, treeView4);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		TreeItem<StrategyHolder> selectedItem = treeView4.getSelectionModel().getSelectedItem();
		if (selectedItem.getParent() == null) {
			return;
		}
		selectedItem.getParent().getChildren().remove(selectedItem);
	}

	@Override
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeView4, oldSelection, newSelection);
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

	@Override
	public void triggerRecalculation() {
		sendRecalculationEventToMainControler();
	}

	private void sendRecalculationEventToMainControler() {
		if(treeView4.getSelectionModel().getSelectedItem()==null){
			return;
		}
		StrategyHolder strategy = treeView4.getSelectionModel().getSelectedItem().getValue();
		mainController.triggerStrategyCalculation(strategy, cardGrid4);
	}


	

}
