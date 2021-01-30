package application.controller;


import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;

@Component
public class MainController {
	
    @FXML
    public MenuItem addPlayerMenuItem;

    @FXML
    public MenuItem closeMenuItem;

    @FXML
    public GridPane playerGrid0;

    @FXML
    public GridPane headerGrid0;

    @FXML
    public TreeView<?> treeView0;

    @FXML
    public ChoiceBox<?> choiceBox0;

    @FXML
    public GridPane bodyGrid0;
    

    @FXML
    public void onAddPlayer(ActionEvent event)  {
    	
    }
  
}
