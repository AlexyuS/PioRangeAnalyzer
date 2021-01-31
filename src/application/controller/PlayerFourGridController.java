package application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.stage.SpringStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

@Component
public class PlayerFourGridController extends Controller{
	private final static Logger LOGGER = Logger.getLogger(PlayerFourGridController.class.getName());
	
	private static final String RED_BACKGROUND = "-fx-background-color: #DE1B1B;";
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";

	@Autowired
	public SpringStage<TextAreaController> textAreaStage;
	
	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;
	
	@FXML
	public TreeView<String> treeView4;

	@FXML
	public List<Pane> cardGridEmpty4;

	@FXML
	public List<Pane> cardGridDiff4;

	@FXML
	public List<Pane> cardGridFill4;

	@FXML
	public List<Text> handLabel4;

	@FXML
	public List<Text> handCount4;

	@FXML
	public void onTreeInsertPlayerFour(ActionEvent e) {
		
	}

	@FXML
	public void onTreeValidationPlayerFour(ActionEvent e) {

	}

	@FXML
	public void onTreeRemovePlayerFour(ActionEvent e) {

	}


}
