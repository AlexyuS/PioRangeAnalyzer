package application.controller;


import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

@Component
public class MainController extends Controller {
	
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
    

    @FXML
    public void onAddPlayer(ActionEvent event)  {
    	
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

  
}
