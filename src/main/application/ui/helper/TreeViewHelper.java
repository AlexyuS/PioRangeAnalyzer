package main.application.ui.helper;

import java.io.File;
import java.util.List;

import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.ui.TreeObject;
import main.application.ui.TreeStorage;
import main.application.ui.events.TreeItemClickedEvent;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TreeViewHelper {
	public static void insertStrategyHolderForPlayer(TreeItem<TreeObject> parent, List<StrategyHolder> strategyHolder) {
		for (StrategyHolder strategy : strategyHolder) {
			TreeItem<TreeObject> item = createTreeItem(strategy);
			parent.getChildren().add(item);
		}
	}

	public static TreeItem<TreeObject> createRootNode() {
		TreeObject treeObject = new TreeObject("Strategy", "Strategy");
		TreeItem<TreeObject> treeRoot = new TreeItem<>(treeObject);
		treeRoot.addEventHandler(MouseEvent.MOUSE_PRESSED,new TreeItemClickedEvent(treeRoot));
		return treeRoot;
	}

	private static TreeItem<TreeObject> createTreeItem(StrategyHolder strategy) {
		TreeObject treeObject = new TreeObject(strategy.getStrategy(), strategy.getUUID());
		TreeItem<TreeObject> treeItem = new TreeItem<>(treeObject);
		treeItem.addEventHandler(MouseEvent.MOUSE_CLICKED,new TreeItemClickedEvent(treeItem));
		return treeItem;
	}
	
	public static final void handleSelectionChanged(TreeStorage treeStorage,TreeView<TreeObject> treeView, PlayerStrategyHolder oldPlayerSelected,
			PlayerStrategyHolder newPlayerSelected) {
	
		if (oldPlayerSelected != null) {
			treeStorage.addTreeForPlayer(oldPlayerSelected.getPlayerName(), treeView.getRoot().getChildren());
			clearTree(treeView);
		}

		if (newPlayerSelected != null) {
			List<TreeItem<TreeObject>> tree = treeStorage.getTreeForPlayer(newPlayerSelected.getPlayerName());
			if (tree != null && tree.size() > 0) {
				addItemToRoot(treeView, tree);
			}
		}
	}
	
	public static Node createTreeExpandedIcon() {	
		return new ImageView(new Image(new File("resources/arrow_down_16x16.png").toURI().toString()));
	}
	
	public static Node createTreeNonExpandedIcon() {
		return new ImageView(new Image(new File("resources/arrow_right_16x16.png").toURI().toString()));
	}
	
	public static void expandTree(TreeItem<TreeObject>treeItem) {
		treeItem.setExpanded(true);
		treeItem.setGraphic(createTreeExpandedIcon());
	}
	
	public static void minimizeTree(TreeItem<TreeObject>treeItem) {
		treeItem.setExpanded(false);
		treeItem.setGraphic(createTreeNonExpandedIcon());
	}
	
	public static final void clearTree(TreeView<TreeObject>treeView) {
		treeView.getRoot().getChildren().clear();
	}
	
	public static final void addItemToRoot(TreeView<TreeObject>treeView,List<TreeItem<TreeObject>>childrens) {
		treeView.getRoot().getChildren().addAll(childrens);
	}
	
	public static final void removeNode(String player,TreeView<TreeObject>treeView,TreeStorage treeStorage) {
		TreeItem<TreeObject> selectedItem = treeView.getSelectionModel().getSelectedItem();
		if (selectedItem.getParent() == null) {
			return;
		}
		selectedItem.getParent().getChildren().remove(selectedItem);
		
		if(treeStorage.getTreeForPlayer(player).contains(selectedItem)) {
			treeStorage.getTreeForPlayer(player).remove(selectedItem);
		}
		
	}
}
