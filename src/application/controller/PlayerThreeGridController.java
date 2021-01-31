package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import application.stage.SpringStage;
import application.utils.UILoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

@Component
public class PlayerThreeGridController extends Controller{
	private static final String RED_BACKGROUND = "-fx-background-color: #DE1B1B;";
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";
	
	@Autowired
	public SpringStage<TextAreaController> textAreaStage;
	
	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;
	
	@FXML
	public TreeView<String> treeView3;

	@FXML
	public List<Pane> cardGridEmpty3;

	@FXML
	public List<Pane> cardGridDiff3;

	@FXML
	public List<Pane> cardGridFill3;

	@FXML
	public List<Text> handLabel3;

	@FXML
	public List<Text> handCount3;

	@FXML
	public void onTreeInsertPlayerThree(ActionEvent e) {
		
	}

	@FXML
	public void onTreeValidationPlayerThree(ActionEvent e) {

	}

	@FXML
	public void onTreeRemovePlayerThree(ActionEvent e) {

	}


}
