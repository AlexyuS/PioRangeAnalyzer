package application.utils;

import java.io.File;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class UILoader {
	public static Scene loadScene(String path)  throws Exception{
		FXMLLoader loader = new FXMLLoader();
		URL resource = new File(path).toURI().toURL();
		
		loader.setLocation(resource);
		Parent panel = loader.load();
		
		return new Scene(panel);
		
	}
}
