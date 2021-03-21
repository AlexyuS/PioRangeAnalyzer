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

public class PlayerOneGridController implements ChangeListener<PlayerStrategyHolder>, InitializingBean {
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	@FXML
	public GridPane playerOneHeader;

	@Autowired
	public PlayerPoolStrategyHolder playerPool;

	@Autowired
	public CardDetailLoader cardDetailLoader;

	@Autowired
	public TextAreaStage textAreaStage;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@FXML
	public TreeView<StrategyHolder> treeView1;

	@FXML
	public GridPane cardGrid1;

	@FXML
	public ChoiceBox<PlayerStrategyHolder> choiceBox1;

	private List<GridPane> cardsGrid;

	@Override
	public void afterPropertiesSet() throws Exception {
		cardsGrid = cardGrid1.getChildren().stream().filter(e -> e instanceof GridPane).map(g -> (GridPane) g)
				.collect(Collectors.toList());
	}
	@Override
	public void changed(ObservableValue<? extends PlayerStrategyHolder> observable, PlayerStrategyHolder oldValue,
			PlayerStrategyHolder newValue) {
		newValue.setReferencePlayer(true);
		if (oldValue != null) {
			oldValue.setReferencePlayer(false);
		}
		GridHelper.clearGrid(cardsGrid);
		applicationEventPublisher.publishEvent(newValue);

		TreeViewHelper.handleSelectionChanged(treeView1, oldValue, newValue);

	}

	@EventListener
	public void onNewPlayerInserted(PlayerStrategyHolder playerStrategy) {
		ChoiceSelectionHelper.populateChoiceList(choiceBox1, playerStrategy);
	}
	@EventListener
	public void onStrategyReloadEvent(StrategyReloadEvent event) {
		if(choiceBox1.getValue()==null) {
			return;
		}
		if(!choiceBox1.getValue().getPlayerName().equals(event.getPlayerName())){
			return;
		}
		
		StrategyHolder rootStrategy = choiceBox1.getValue().getStrategyHolder();
		TreeViewHelper.refreshTreeView(rootStrategy, treeView1);
	}
	
	@EventListener
	public void onStrategyInsertionEvent(OnStrategyInsertEvent event) {
		if(event.getPlayerIndex()!=1) {
			return;
		}
		TreeItem<StrategyHolder> selectedItem = treeView1.getSelectionModel().getSelectedItem();
		if(selectedItem==null || selectedItem.getValue()==null) {
			return;
		}
		event.getStrategies().forEach(e->e.setAggregatedCardStrategy(StrategyAggregator.aggregateIndividualCards(e.getIndividualCards())));
		StrategyHelper.addStrategiesToParrent(selectedItem.getValue(), event.getStrategies());
		applicationEventPublisher.publishEvent(new StrategyReloadEvent(choiceBox1.getValue().getPlayerName()));

	}
	
	@FXML
	public void onMouseClicked(MouseEvent e) {
		LOGGER.info(e.getPickResult().getIntersectedNode().getId());
	}

	@FXML
	public void onMouseEntered(MouseEvent event) {
		Node parentNode = event.getPickResult().getIntersectedNode().getParent();
		Integer colIndex = GridPane.getColumnIndex(parentNode);
		Integer rowIndex = GridPane.getRowIndex(parentNode);

		TreeItem<StrategyHolder> selectedItem = treeView1.getSelectionModel().getSelectedItem();

		cardDetailLoader.displayDetailedViewForSelection(selectedItem, colIndex, rowIndex, playerOneHeader);
	}

	@FXML
	public void onTreeMouseClicked(MouseEvent e) {
		if (e.getButton().compareTo(MouseButton.SECONDARY) == 0) {
			return;
		}
		
		TreeItem<StrategyHolder> selectedItem = treeView1.getSelectionModel().getSelectedItem();
		if(selectedItem==null || selectedItem.getValue()==null) {
			return;
		}
		StrategyHolder strategyRefChangedEvent = selectedItem.getValue();

		if(strategyRefChangedEvent==null ) {
			return;
		}
		colorGrid(strategyRefChangedEvent, strategyRefChangedEvent);
		applicationEventPublisher.publishEvent(strategyRefChangedEvent);

	}

	private void colorGrid(StrategyHolder refStrategy, StrategyHolder ownStrategy) {
		StrategyDiffCalculator strategyDiffCalculator = new StrategyDiffCalculator(refStrategy);
		strategyDiffCalculator.calculateStrategies(ownStrategy);

		GridHelper gridhelper = new GridHelper();
		gridhelper.fillGridForStrategy(ownStrategy, cardGrid1);
	}

}
