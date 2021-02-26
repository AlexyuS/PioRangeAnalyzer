package main.application.controller;

import java.util.Arrays;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import main.application.cards.Card;
import main.application.cards.CardColor;
import main.application.cards.IndividualCardHand;
import main.application.cards.IndividualCardStrategy;
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
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PlayerOneGridController implements GridController, InitializingBean {
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	@FXML
	public GridPane cardGrid1;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox1;

	@Autowired
	private MainController mainController;

	@FXML
	public GridPane playerOneHeader;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@Autowired
	private PocketPairGridStage pocketPairWindow;

	@Autowired
	public TextAreaStage textAreaStage;

	@FXML
	public TreeView<StrategyHolder> treeView1;

	@Override
	public void addPlayerToChoiceList(PlayerStrategyHolder playerPool) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox1, playerPool);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.mainController.registerForTreeViewChanged(this);
		IndividualCardHand individualCardHand = new IndividualCardHand(Card.ACE, CardColor.SPADES, Card.KING,
				CardColor.CLUBS);
		IndividualCardStrategy cardStrategy = new IndividualCardStrategy(individualCardHand, 90);
		cardStrategy.setDifferenceFromStrategyRef(30);
		playerOneHeader.add(pocketPairWindow.getGridPane(), 2, 0, 1, 2);
		pocketPairWindow.colorCardGridForIndividualStrategy(Arrays.asList(cardStrategy));

	}

	public StrategyHolder getRefStrategyFromFirstView() {
		return treeView1.getSelectionModel().getSelectedItem().getValue();
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

	}

	@FXML
	public void onMouseExited(MouseEvent e) {

	}

	@Override
	public void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection) {
		TreeViewHelper.handleSelectionChanged(treeView1, oldSelection, newSelection);
		mainController.onParentPlayerSelectionChanged();
	}

	@Override
	public void onTreeDelete(ActionEvent e) {
		StrategyHolder strategyHolder = treeView1.getSelectionModel().getSelectedItem().getValue();

		if (strategyHolder.equals(treeView1.getRoot().getValue())) {
			return;
		}

		PlayerStrategyHolder playerStrategyHolder = choiceBox1.getValue();
		TreeViewHelper.removeNode(playerStrategyHolder.getPlayerName(), treeView1);
		this.mainController.notifyStrategyRemove(playerStrategyHolder.getPlayerName(), strategyHolder);
	}

	@Override
	public void onTreeInsert(ActionEvent e) {
		TextAreaStageHelper.open(textAreaStage, choiceBox1, treeView1);
	}

	@FXML
	public void onTreeMouseClicked(MouseEvent e) {
		if (e.getButton().compareTo(MouseButton.SECONDARY) == 0) {
			return;
		}
		if (treeView1.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		LOGGER.info("Notify controllers  to recalculate strategy differences");
		triggerRecalculation();
		mainController.onParentPlayerSelectionChanged();
	}

	@Override
	public void removeTreeItemForPlayerAndStrategy(String playerName, StrategyHolder strategy) {
		if (!choiceBox1.getValue().getPlayerName().equals(playerName)) {
			return;
		}
		treeView1.getRoot().getChildren().removeIf(e -> e.getValue().getStrategy() == strategy.getStrategy());
	}

	@Override
	public void triggerRecalculation() {
		mainController.fillGridForStrategy(getRefStrategyFromFirstView(), cardGrid1);
	}

}
