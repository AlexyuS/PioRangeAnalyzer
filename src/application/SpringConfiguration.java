package application;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.controller.MainController;
import application.controller.TextAreaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Configuration
public class SpringConfiguration {

	@Autowired
	public Stage stage;
	
	@Autowired
	@Qualifier("mainScene")
	public Scene scene;
	
	@Autowired
	@Qualifier("textAreaScene")
	public Scene textAreaScene;
	
	@Autowired
	public MainController controller;
	
	private FXMLLoader textAreaLoader;
	private FXMLLoader mainStageLoader;
	
	@Bean(name = "mainScene")
	public Scene mainScene() {
		try {
			URL resource = new File("resources/diagram.fxml").toURI().toURL();
			mainStageLoader = new FXMLLoader();
			mainStageLoader.setLocation(resource);
			
			Parent panel = mainStageLoader.load();
			Scene scene  =  new Scene(panel);
			
			stage.setTitle("PioInterpreter");
			stage.setScene(scene);
			stage.show();
			return scene;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Bean(name = "textAreaScene")
	public Scene textAreaScene() {
		try {
			textAreaLoader =  new FXMLLoader();
			URL resource = new File("resources/textAreaStrategy.fxml").toURI().toURL();
			textAreaLoader.setLocation(resource);
			
			Parent panel = textAreaLoader.load();
			
			Scene scene  =  new Scene(panel);
			Stage stage =  new Stage();

			stage.initOwner(this.stage);	
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			
			return scene;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Bean
	public FXMLLoader fxmlLoader() {
		return new FXMLLoader();
	}
	@Bean
	public MainController createMainController() {
		return mainStageLoader.getController();
	}
	
	@Bean
	public TextAreaController createJavaFxController() {
		return textAreaLoader.getController();
	}
}
