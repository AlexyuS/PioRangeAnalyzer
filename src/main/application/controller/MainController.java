package main.application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.ui.PlayerInsertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

@Component
public class MainController implements Controller {

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
		
		playerOneController.addPlayerToChoiceList(playerPool.getAllPlayers());
		playerTwoController.addPlayerToChoiceList(playerPool.getAllPlayers());
		playerThreeController.addPlayerToChoiceList(playerPool.getAllPlayers());
		playerFourController.addPlayerToChoiceList(playerPool.getAllPlayers());		
	}
}
