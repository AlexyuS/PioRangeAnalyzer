package application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.controller.CardDetailController;
import application.controller.MainController;
import application.controller.PlayerTwoGridController;
import application.controller.PlayerThreeGridController;
import application.controller.PlayerFourGridController;
import application.controller.PlayerOneGridController;
import application.controller.TextAreaController;
import application.stage.CardDetailStage;
import application.stage.MainStage;
import application.stage.SpringStage;
import application.stage.TextAreaStage;
import javafx.stage.Stage;

@Configuration
public class SpringConfiguration {

	@Autowired
	public Stage stage;

	@Bean
	public SpringStage<MainController> mainStage() throws IOException {
		SpringStage<MainController> mainStage = new MainStage("resources/diagram.fxml", stage);
		mainStage.initialize();
		mainStage.open();
		return mainStage;
	}

	@Bean
	public SpringStage<CardDetailController> cardDetailStage() throws IOException{
		CardDetailStage detailStage = new CardDetailStage("resources/cardDetailGrid.fxml");
		detailStage.initialize();
		return detailStage;
	}

	@Bean
	public SpringStage<TextAreaController> textAreaStage() throws IOException{
		TextAreaStage textAreaStage = new TextAreaStage("resources/textAreaStrategy.fxml");
		textAreaStage.initialize();
		return textAreaStage;
	}

	@Bean
	public MainController mainController() throws IOException {
		return mainStage().getController();
	}

	@Bean
	public CardDetailController cardDetailController() throws IOException{
		return cardDetailStage().getController();
	}

	@Bean
	public PlayerOneGridController playerGridController() throws IOException {
		return mainController().getPlayerOneController();
	}

	@Bean
	public PlayerTwoGridController playerTwoGridController() throws IOException {
		return mainController().getPlayerTwoController();
	}

	@Bean
	public PlayerThreeGridController playerThreeGridController() throws IOException {
		return mainController().getPlayerThreeController();
	}

	@Bean
	public PlayerFourGridController playerFourGridController() throws IOException  {
		return mainController().getPlayerFourController();
	}

}
