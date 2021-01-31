package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.stage.SpringStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


@Component
public class PlayerTwoGridController extends Controller {
	private static final String RED_BACKGROUND = "-fx-background-color: #DE1B1B;";
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";
	@Autowired
	public SpringStage<TextAreaController> textAreaStage;

	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;
	
	@FXML
	public TreeView<String> treeView2;

	@FXML
	public List<Pane> cardGridEmpty2;

	@FXML
	public List<Pane> cardGridDiff2;

	@FXML
	public List<Pane> cardGridFill2;

	@FXML
	public List<Text> handLabel2;

	@FXML
	public List<Text> handCount2;

	@FXML
	public void onTreeInsertPlayerTwo(ActionEvent e) {
		
	}

	@FXML
	public void onTreeValidationPlayerTwo(ActionEvent e) {

	}

	@FXML
	public void onTreeRemovePlayerTwo(ActionEvent e) {

	}

}
