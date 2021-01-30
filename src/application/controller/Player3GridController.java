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
public class Player3GridController {
	private static final String RED_BACKGROUND = "-fx-background-color: #DE1B1B;";
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";
	
	@Autowired
	@Qualifier("textAreaScene")
	private Scene scene;
	
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
	public void onTreeInsert(ActionEvent e) {
		try {
			openTextAreaScenePlaye3();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void openTextAreaScenePlaye3() throws Exception {
		Scene scene = UILoader.loadScene("resources/textAreaStrategy.fxml");
		Stage stage =  new Stage();
		cardGridEmpty3.get(0).setStyle(RED_BACKGROUND);

		Window window = treeView3.getScene().getWindow();
		stage.initOwner(window);	
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void onTreeValidationPlayer3(ActionEvent e) {

	}

	@FXML
	public void onTreeRemovePlayer3(ActionEvent e) {

	}


}
