package main.application.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.observer.SelectionChangedObserver;
import main.application.observer.TreeViewChangedObserver;
import main.application.stage.PreflopInputStage;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.strategy.calculator.StrategyDiffCalculator;
import main.application.ui.helper.GridHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

@Component
public class MainController implements Controller, SelectionChangedObserver,TreeViewChangedObserver {
	
	@Autowired
	private PreflopInputStage preflopStage;
	
	@FXML
	public MenuItem addPlayerMenuItem;

	@FXML
	public MenuItem closeMenuItem;

	@FXML
	public PlayerOneGridController playerOneController;

	@FXML
	public PlayerTwoGridController playerTwoController;

	@FXML
	public PlayerThreeGridController playerThreeController;

	@FXML
	public PlayerFourGridController playerFourController;

	private List<GridController> parentPlayerChangedListener;
	
	private List<GridController> treeDeletionListener;

	@Override
	public void registerForTreeViewChanged(GridController controller) {
		if (treeDeletionListener == null) {
			this.treeDeletionListener = new ArrayList<>();
		}
		treeDeletionListener.add(controller);
	}


	@Override
	public void registerForAnyPlayerSelectionChanged(GridController controller) {
		if (parentPlayerChangedListener == null) {
			this.parentPlayerChangedListener = new ArrayList<>();
		}
		parentPlayerChangedListener.add(controller);
	}
	
	@Override
	public void notifyStrategyRemove(String playerName, StrategyHolder strategy) {
		treeDeletionListener.forEach(e->e.removeTreeItemForPlayerAndStrategy(playerName, strategy));
	}

	@Override
	public void onParentPlayerSelectionChanged() {
		parentPlayerChangedListener.forEach(e->e.triggerRecalculation());
	}
	
	@FXML
	public void onAddPlayer(ActionEvent event) {
		preflopStage.open();
	}

	
	public PlayerOneGridController getPlayerOneController() {
		return playerOneController;
	}

	public PlayerTwoGridController getPlayerTwoController() {
		return playerTwoController;
	}

	public PlayerThreeGridController getPlayerThreeController() {
		return playerThreeController;
	}

	public PlayerFourGridController getPlayerFourController() {
		return playerFourController;
	}

	
	public void insertNewPlayer(PlayerStrategyHolder playerPool) {
		playerOneController.addPlayerToChoiceList(playerPool);
		playerTwoController.addPlayerToChoiceList(playerPool);
		playerThreeController.addPlayerToChoiceList(playerPool);
		playerFourController.addPlayerToChoiceList(playerPool);
	}


	public void fillGridForStrategy(StrategyHolder strategyHolder,GridPane parentGrid) {
		if(strategyHolder==null) {
			return;
		}
		StrategyHolder strategy = playerOneController.getRefStrategyFromFirstView();
		
		StrategyDiffCalculator strategyDifCalculator =  new StrategyDiffCalculator(strategy);
		strategyDifCalculator.calculateStrategies(strategyHolder);
		
		GridHelper gridhelper =  new GridHelper();
		gridhelper.fillGridForStrategy(strategyHolder, parentGrid);
		
	}
	

	
	
}
