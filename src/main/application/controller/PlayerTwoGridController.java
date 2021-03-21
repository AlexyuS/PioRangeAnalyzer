package main.application.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import main.application.stage.TextAreaStage;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.strategy.calculator.StrategyDiffCalculator;
import main.application.strategy.helper.StrategyAggregator;
import main.application.strategy.helper.StrategyHelper;
import main.application.ui.MenuItemAddStrategy.OnStrategyInsertEvent;
import main.application.ui.events.StrategyReloadEvent;
import main.application.ui.helper.CardDetailLoader;
import main.application.ui.helper.ChoiceSelectionHelper;
import main.application.ui.helper.GridHelper;
import main.application.ui.helper.TreeViewHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PlayerTwoGridController implements InitializingBean, ChangeListener<PlayerStrategyHolder> {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(PlayerTwoGridController.class.getName());

	@Autowired
	public MainController mainController;

	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	private CardDetailLoader cardDetailLoader;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@FXML
	public TreeView<StrategyHolder> treeView2;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox2;

	@FXML
	public GridPane cardGrid2;

	@FXML
	public GridPane playerTwoHeader;

	private List<GridPane> cardsGrid;

	private StrategyHolder refStrategy;

	@Override
	public void afterPropertiesSet() throws Exception {
		cardsGrid = cardGrid2.getChildren().stream().filter(e -> e instanceof GridPane).map(g -> (GridPane) g)
				.collect(Collectors.toList());

	}

	@Override
	public void changed(ObservableValue<? extends PlayerStrategyHolder> observable, PlayerStrategyHolder oldValue,
			PlayerStrategyHolder newValue) {
		TreeViewHelper.handleSelectionChanged(treeView2, oldValue, newValue);
		GridHelper.clearGrid(cardsGrid);
	}

	@FXML
	public void onTreeMouseClicked(MouseEvent e) {
		if (e.getButton().compareTo(MouseButton.SECONDARY) == 0) {
			return;
		}
		TreeItem<StrategyHolder> selectedItem = treeView2.getSelectionModel().getSelectedItem();
		if (selectedItem == null || selectedItem.getValue() == null) {
			return;
		}
		colorGrid(refStrategy, selectedItem.getValue());
	}

	@FXML
	public void onMouseEntered(MouseEvent event) {
		Node parentNode = event.getPickResult().getIntersectedNode().getParent();
		Integer colIndex = GridPane.getColumnIndex(parentNode);
		Integer rowIndex = GridPane.getRowIndex(parentNode);

		TreeItem<StrategyHolder> selectedItem = treeView2.getSelectionModel().getSelectedItem();

		cardDetailLoader.displayDetailedViewForSelection(selectedItem, colIndex, rowIndex, playerTwoHeader);

	}

	@EventListener
	public void onStrategyReloadEvent(StrategyReloadEvent event) {
		if (choiceBox2.getValue() == null) {
			return;
		}
		if (!choiceBox2.getValue().getPlayerName().equals(event.getPlayerName())) {
			return;
		}

		StrategyHolder rootStrategy = choiceBox2.getValue().getStrategyHolder();
		TreeViewHelper.refreshTreeView(rootStrategy, treeView2);
	}

	@EventListener
	public void addPlayerToChoiceList(PlayerStrategyHolder playerStrategy) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox2, playerStrategy);
	}

	@EventListener(condition = "#newPlayer.isReferencePlayer")
	public void onReferenceSelectionChanged(PlayerStrategyHolder newPlayer) {
		if (treeView2.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		GridHelper.clearGrid(cardsGrid);
	}

	@EventListener
	public void onStrategyInsertionEvent(OnStrategyInsertEvent event) {
		if (event.getPlayerIndex() != 2) {
			return;
		}
		TreeItem<StrategyHolder> selectedItem = treeView2.getSelectionModel().getSelectedItem();
		if(selectedItem==null || selectedItem.getValue()==null) {
			return;
		}
		
		event.getStrategies().forEach(e->e.setAggregatedCardStrategy(StrategyAggregator.aggregateIndividualCards(e.getIndividualCards())));
		event.getStrategies().forEach(e->StrategyAggregator.aggregateIndividualCards(e.getIndividualCards()));

		applicationEventPublisher.publishEvent(new StrategyReloadEvent(choiceBox2.getValue().getPlayerName()));
	}

	@EventListener
	public void onRefStrategyChanged(StrategyHolder refStrategy) {
		this.refStrategy = refStrategy;
		if (treeView2.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		StrategyHolder ownStrategy = treeView2.getSelectionModel().getSelectedItem().getValue();
		colorGrid(refStrategy, ownStrategy);

	}

	private void colorGrid(StrategyHolder refStrategy, StrategyHolder ownStrategy) {
		StrategyDiffCalculator strategyDiffCalculator = new StrategyDiffCalculator(refStrategy);
		strategyDiffCalculator.calculateStrategies(ownStrategy);

		GridHelper gridhelper = new GridHelper();
		gridhelper.fillGridForStrategy(ownStrategy, cardGrid2);
	}

}
