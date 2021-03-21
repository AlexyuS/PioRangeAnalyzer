package main.application.stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.InitializingBean;

import main.application.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SpringStage<E extends Controller> implements InitializingBean{
	private final String path;
	private final Stage stage;
	
	private FXMLLoader loader;
	
	public SpringStage(String path,Stage stage) {
		this.path = path;
		this.stage = stage;
	}
	
	public SpringStage(String path) {
		this.path = path;
		this.stage = new Stage();
	}
	
	@Override
	public void afterPropertiesSet() throws IOException {
		URL resource = new File(path).toURI().toURL();
		
		loader = new FXMLLoader();
		loader.setLocation(resource);	
		Parent panel = loader.load();
		
		Scene scene  =  new Scene(panel);
		stage.setScene(scene);

	}
	
	public void open() {
		stage.show();
	}
	
	public void close() {
		stage.close();
	}
	public E getController() {
		return loader.getController();
	}
		
	public Stage getStage(){
		return stage;
	}
}
