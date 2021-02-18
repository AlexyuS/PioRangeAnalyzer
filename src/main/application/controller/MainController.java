package main.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.observer.SelectionChangedObserver;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.ui.PlayerInsertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

@Component
public class MainController implements Controller, SelectionChangedObserver {

	@Autowired
	private PlayerPoolStrategyHolder playerPool;

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

	private List<GridController> controllerList;

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

	@FXML
	public void onAddPlayer(ActionEvent event) {
		PlayerInsertDialog dialog = new PlayerInsertDialog();
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			playerPool.putNewPlayerStrategy(result.get());
		}
		insertNewPlayer(playerPool.getStrategyForPlayer(result.get()));
	}

	public void insertNewPlayer(PlayerStrategyHolder playerPool) {
		playerOneController.addPlayerToChoiceList(playerPool);
		playerTwoController.addPlayerToChoiceList(playerPool);
		playerThreeController.addPlayerToChoiceList(playerPool);
		playerFourController.addPlayerToChoiceList(playerPool);
	}

	@Override
	public void register(GridController controller) {
		if (controllerList == null) {
			this.controllerList = new ArrayList<>();
		}
		controllerList.add(controller);
	}

	@Override
	public void notify(PlayerStrategyHolder oldStrategy, PlayerStrategyHolder newStrategy, Class<?> excludeControler) {
		controllerList.forEach(e -> {
			if (!e.getClass().equals(excludeControler)) {
				e.addPlayerToChoiceList(oldStrategy);
				e.removePlayerFromChoiceList(newStrategy);
			}
		});
	}
	
}
