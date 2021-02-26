package main.application.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.controller.GridController;
import main.application.controller.MainController;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.ui.MenuItemAddStrategy;
import main.application.ui.MenuItemRemoveStrategy;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

@Component
public class MainStage extends SpringStage<MainController> {
	
	@Autowired
	public MainStage(String path, Stage stage) {
		super(path, stage);
	}

	@Override
	protected void afterInitialize(Stage stage) {
		stage.setTitle("PioRangeAnalyzer");

		{
			GridController controller = getController().getPlayerOneController();
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) stage.getScene().lookup("#treeView1");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
		}

		{
			GridController controller = getController().getPlayerTwoController();
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) stage.getScene().lookup("#treeView2");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
		}

		{
			GridController controller = getController().getPlayerThreeController();
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) stage.getScene().lookup("#treeView3");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
		}
		{
			GridController controller = getController().getPlayerFourController();
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) stage.getScene().lookup("#treeView4");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
		}
		
		initializeListView(stage);

	}

	private void initializeListView(Stage stage) {
		{
			GridController controller = getController().getPlayerOneController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene().lookup("#choiceBox1");
			choiceBox.setValue(new PlayerStrategyHolder("Select"));
			choiceBox.getSelectionModel().selectedItemProperty().addListener((e,m,t)->controller.onSelectionChanged(m,t));
		}

		{
			GridController controller = getController().getPlayerTwoController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene().lookup("#choiceBox2");
			choiceBox.setValue(new PlayerStrategyHolder("Select"));
			choiceBox.getSelectionModel().selectedItemProperty().addListener((e,m,t)->controller.onSelectionChanged(m,t));
		}

		{
			GridController controller = getController().getPlayerThreeController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene().lookup("#choiceBox3");
			choiceBox.setValue(new PlayerStrategyHolder("Select"));
			choiceBox.getSelectionModel().selectedItemProperty().addListener((e,m,t)->controller.onSelectionChanged(m,t));
		
		}

		{
			GridController controller = getController().getPlayerFourController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene().lookup("#choiceBox4");
			choiceBox.setValue(new PlayerStrategyHolder("Select"));
			choiceBox.getSelectionModel().selectedItemProperty().addListener((e,m,t)->controller.onSelectionChanged(m,t));
		}
	}
	
}
