package main.application.stage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.controller.MainController;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.ui.MenuItemAddStrategy;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

@Component
public class MainStage extends SpringStage<MainController> {
	@Autowired
	private MenuItemAddStrategy addStrategyP1;
	
	@Autowired
	private MenuItemAddStrategy addStrategyP2;
	
	@Autowired
	private MenuItemAddStrategy addStrategyP3;
	
	@Autowired
	private MenuItemAddStrategy addStrategyP4;

	@Autowired
	public MainStage(String path, Stage stage) {
		super(path, stage);
	}

	@Override
	public void afterPropertiesSet() throws IOException{
		super.afterPropertiesSet();
		
		Stage mainStage = getStage();
		mainStage.setTitle("PioRangeAnalyzer");

		initializeTreeView(mainStage);
		initializeListView(mainStage);

	}

	private void initializeTreeView(Stage mainStage) {
		{
			addStrategyP1.setPlayerIndex(1);
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) mainStage.getScene().lookup("#treeView1");
			treeView.setContextMenu(new ContextMenu(addStrategyP1));
		}

		{
			addStrategyP2.setPlayerIndex(2);
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) mainStage.getScene().lookup("#treeView2");
			treeView.setContextMenu(new ContextMenu(addStrategyP2));
		}

		{
			addStrategyP3.setPlayerIndex(3);
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) mainStage.getScene().lookup("#treeView3");
			treeView.setContextMenu(new ContextMenu(addStrategyP3));
		}
		{
			addStrategyP4.setPlayerIndex(4);
			TreeView<StrategyHolder> treeView = (TreeView<StrategyHolder>) mainStage.getScene().lookup("#treeView4");
			treeView.setContextMenu(new ContextMenu(addStrategyP4));
		}
	}

	private void initializeListView(Stage stage) {
		{
			ChangeListener<PlayerStrategyHolder> listener = getController().getPlayerOneController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene()
					.lookup("#choiceBox1");
			choiceBox.getSelectionModel().selectedItemProperty().addListener(listener);
		}

		{
			ChangeListener<PlayerStrategyHolder> listener = getController().getPlayerTwoController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene()
					.lookup("#choiceBox2");
			choiceBox.getSelectionModel().selectedItemProperty().addListener(listener);
		}

		{
			ChangeListener<PlayerStrategyHolder> listener = getController().getPlayerThreeController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene()
					.lookup("#choiceBox3");
			choiceBox.getSelectionModel().selectedItemProperty().addListener(listener);

		}

		{
			ChangeListener<PlayerStrategyHolder> listener = getController().getPlayerFourController();
			ChoiceBox<PlayerStrategyHolder> choiceBox = (ChoiceBox<PlayerStrategyHolder>) stage.getScene()
					.lookup("#choiceBox4");
			choiceBox.getSelectionModel().selectedItemProperty().addListener(listener);
		}
	}


}
