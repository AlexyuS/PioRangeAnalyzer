package application.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import application.stage.SpringStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

@Component
public class PlayerOneGridController extends Controller{
	private final static Logger LOGGER = Logger.getLogger(PlayerOneGridController.class.getName());

	private static final String RED_BACKGROUND = "-fx-background-color: #DE1B1B;";
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";
	
	@Autowired
	public SpringStage<TextAreaController> textAreaStage;
	
	@Autowired
	public SpringStage<CardDetailController> cardDetailStage;
	
	@FXML
	public TreeView<String> treeView0;

	@FXML
	public List<Pane> cardGridEmpty0;

	@FXML
	public List<Pane> cardGridDiff0;

	@FXML
	public List<Pane> cardGridFill0;

	@FXML
	public List<Text> handLabel0;

	@FXML
	public List<Text> handCount0;

	@FXML
	public void onTreeInsertPlayerOne(ActionEvent e) {
		try {
			textAreaStage.open();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void onTreeValidationPlayerOne(ActionEvent e) {

	}

	@FXML
	public void onTreeRemovePlayerOne(ActionEvent e) {

	}
	
	@FXML
	public void onMouseEntered(MouseEvent e) {
	//	LOGGER.info("Hover action detected");
		cardDetailStage.open();
	}
	
	@FXML
	public void onMouseExited(MouseEvent e) {
		LOGGER.info("Hover action detected");
		textAreaStage.open();
	}
	
}
