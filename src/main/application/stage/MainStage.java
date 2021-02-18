package main.application.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.controller.GridController;
import main.application.controller.MainController;
import main.application.strategy.PlayerStrategyHolder;
import main.application.ui.MenuItemAddStrategy;
import main.application.ui.MenuItemRemoveStrategy;
import main.application.ui.TreeObject;
import main.application.ui.helper.TreeViewHelper;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
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
			TreeView<TreeObject> treeView = (TreeView<TreeObject>) stage.getScene().lookup("#treeView1");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
			addRootNodeForTree(treeView);
		}

		{
			GridController controller = getController().getPlayerTwoController();
			TreeView<TreeObject> treeView = (TreeView<TreeObject>) stage.getScene().lookup("#treeView2");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
			addRootNodeForTree(treeView);
		}

		{
			GridController controller = getController().getPlayerThreeController();
			TreeView<TreeObject> treeView = (TreeView<TreeObject>) stage.getScene().lookup("#treeView3");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
			addRootNodeForTree(treeView);
		}
		{
			GridController controller = getController().getPlayerFourController();
			TreeView<TreeObject> treeView = (TreeView<TreeObject>) stage.getScene().lookup("#treeView4");
			treeView.setContextMenu(
					new ContextMenu(new MenuItemAddStrategy(controller), new MenuItemRemoveStrategy(controller)));
			addRootNodeForTree(treeView);
		}
		
		initializeListView(stage);

	}

	@Override
	protected void onClose(Stage stage) {
		// TODO Auto-generated method stub

	}

	private void addRootNodeForTree(TreeView<TreeObject> treeView) {
		TreeItem<TreeObject> rootNode = TreeViewHelper.createRootNode();
		treeView.setRoot(rootNode);
	}

	@Override
	protected void refreshUI(Stage stage) {
		
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
