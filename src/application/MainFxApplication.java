package application;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainFxApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();
			beanFactory.registerSingleton("primaryStage", primaryStage);
			ctx.register(SpringConfiguration.class);
			ctx.refresh();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
