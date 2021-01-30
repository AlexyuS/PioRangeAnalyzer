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
public class Player4GridController {
	private static final String RED_BACKGROUND = "-fx-background-color: #DE1B1B;";
	private static final String YELLOW_BACKGROUND = "-fx-background-color: #F9EF1B;";
	
	@Autowired
	@Qualifier("textAreaScene")
	private Scene scene;
	
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
	public void onTreeInsert(ActionEvent e) {
		try {
			openTextAreaScenePlayer4();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void openTextAreaScenePlayer4() throws Exception {
		Scene scene = UILoader.loadScene("resources/textAreaStrategy.fxml");
		Stage stage =  new Stage();
		cardGridEmpty4.get(0).setStyle(RED_BACKGROUND);

		Window window = treeView4.getScene().getWindow();
		stage.initOwner(window);	
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void onTreeValidationPlayer4(ActionEvent e) {

	}

	@FXML
	public void onTreeRemovePlayer4(ActionEvent e) {

	}


}
