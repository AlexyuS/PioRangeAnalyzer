package main.application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import main.application.controller.CardDetailController;
import main.application.controller.MainController;
import main.application.controller.PlayerTwoGridController;
import main.application.controller.TextAreaController;
import main.application.controller.PlayerThreeGridController;
import main.application.controller.PlayerFourGridController;
import main.application.controller.PlayerOneGridController;
import main.application.stage.CardDetailStage;
import main.application.stage.MainStage;
import main.application.stage.SpringStage;
import main.application.stage.TextAreaStage;
import main.application.strategy.calculator.StrategyDiffCalculator;
import javafx.stage.Stage;

@Configuration
@ComponentScan
public class SpringConfiguration {

	@Autowired
	public Stage stage;

	@Autowired
	public StrategyDiffCalculator strategyDiff;
	
	@Autowired
	public MainController mainController;

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
	public TextAreaStage textAreaStage() throws IOException{
		TextAreaStage textAreaStage = new TextAreaStage("resources/textAreaStrategy.fxml",strategyDiff);
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
	
	@Bean
	public TextAreaController textAreaController() throws IOException  {
		return textAreaStage().getController();
	}
	
	
	
}
