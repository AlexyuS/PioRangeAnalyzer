package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
public class Player2GridController {
	private static final String RED_BACKGROUND = "-fx-background-color: #DE1B1B;";
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";
	
	@Autowired
	@Qualifier("textAreaScene")
	private Scene scene;
	
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
	public void onTreeInsertPlayer2(ActionEvent e) {
		try {
			openTextAreaScene();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void openTextAreaScene() throws Exception {
		Scene scene = UILoader.loadScene("resources/textAreaStrategy.fxml");
		Stage stage =  new Stage();
		cardGridEmpty2.get(0).setStyle(RED_BACKGROUND);
		Window window = treeView2.getScene().getWindow();
		stage.initOwner(window);	
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void onTreeValidationPlayer2(ActionEvent e) {

	}

	@FXML
	public void onTreeRemovePlayer2(ActionEvent e) {

	}


}
