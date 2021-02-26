package main.application.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import main.application.stage.PocketPairGridStage;
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



public class PlayerFourGridController implements GridController,InitializingBean {

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(PlayerFourGridController.class.getName());

	@Autowired
	public MainController mainController;
	
	@Autowired
	private PocketPairGridStage pocketPairWindow;
	
	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@FXML
	public TreeView<StrategyHolder> treeView4;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox4;

	@FXML
	public GridPane cardGrid4;

	@FXML
	public GridPane playerFourHeader;
	
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
		StrategyHolder strategy = treeView4.getSelectionModel().getSelectedItem().getValue();
		if(strategy.getStrategy().equals(treeView4.getRoot().getValue().getStrategy())){
			return;
		}
		PlayerStrategyHolder playerStrategyHolder = choiceBox4.getValue();
		TreeViewHelper.removeNode(playerStrategyHolder.getPlayerName(), treeView4);
		this.mainController.notifyStrategyRemove(playerStrategyHolder.getPlayerName(),strategy);
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
	public void afterPropertiesSet() throws Exception {
		this.mainController.registerForAnyPlayerSelectionChanged(this);
		this.mainController.registerForTreeViewChanged(this);
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
		mainController.fillGridForStrategy(strategy, cardGrid4);
	}

	@Override
	public void removeTreeItemForPlayerAndStrategy(String playerName, StrategyHolder strategy) {
		if(!choiceBox4.getValue().getPlayerName().equals(playerName)) {
			return;
		}
		
		treeView4.getRoot().getChildren().removeIf(e->e.getValue().getStrategy()==strategy.getStrategy());
	}




}
