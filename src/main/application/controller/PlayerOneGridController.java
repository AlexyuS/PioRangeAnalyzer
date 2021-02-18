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
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class PlayerOneGridController implements GridController,InitializingBean{
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	@Autowired
	private MainController mainController;
	
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
	public ChoiceBox<PlayerStrategyHolder> choiceBox1;

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
		TextAreaStageHelper.open(textAreaStage, choiceBox1, treeView1);
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		PlayerStrategyHolder playerStrategyHolder= choiceBox1.getValue();
		TreeViewHelper.removeNode(playerStrategyHolder.getPlayerName(),treeView1, treeStorage);
		
		String id = treeView1.getSelectionModel().getSelectedItem().getValue().getId();
		StrategyHelper.clearStrategyFromPool(playerStrategyHolder.getStrategyHolder(), id);
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
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeStorage,treeView1, oldSelection, newSelection);
		mainController.notify(oldSelection,newSelection,PlayerOneGridController.class);
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
		mainController.register(this);
	}


}
