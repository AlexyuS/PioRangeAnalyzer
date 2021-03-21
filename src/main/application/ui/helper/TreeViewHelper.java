package main.application.ui.helper;

import java.io.File;
import java.util.List;

import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.ui.events.TreeItemClickedEvent;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TreeViewHelper {
	public static void insertStrategyHolderForPlayer(TreeItem<StrategyHolder> parent,
			List<StrategyHolder> strategyHolder) {
		for (StrategyHolder strategy : strategyHolder) {
			TreeItem<StrategyHolder> item = createTreeItem(strategy);

			if (parent.getChildren() == null || parent.getChildren().size() == 0) {
				parent.getChildren().add(item);
			} else {
				boolean notFound = parent.getChildren().stream().noneMatch(e -> e.getValue().equals(strategy));
				if (notFound) {
					parent.getChildren().add(item);
				}
			}
		}
	}

	private static TreeItem<StrategyHolder> createTreeItem(StrategyHolder strategy) {
		TreeItem<StrategyHolder> treeItem = new TreeItem<>(strategy);
		treeItem.addEventHandler(MouseEvent.MOUSE_CLICKED, new TreeItemClickedEvent(treeItem));
		return treeItem;
	}

	public static final void handleSelectionChanged(TreeView<StrategyHolder> treeView,
			PlayerStrategyHolder oldPlayerSelected, PlayerStrategyHolder newPlayerSelected) {

		if (oldPlayerSelected != null) {
			clearTree(treeView);
		}

		if (newPlayerSelected != null) {
			treeView.setRoot(createTreeItem(newPlayerSelected.getStrategyHolder()));
			populateTreeView(treeView.getRoot(), newPlayerSelected.getStrategyHolder().getChilds());
		}
	}

	public static Node createTreeExpandedIcon() {
		return new ImageView(new Image(new File("resources/arrow_down_16x16.png").toURI().toString()));
	}

	public static Node createTreeNonExpandedIcon() {
		return new ImageView(new Image(new File("resources/arrow_right_16x16.png").toURI().toString()));
	}

	public static boolean hasChild(TreeView<StrategyHolder> treeView, StrategyHolder strategy) {
		return true;
	}

	public static void expandTree(TreeItem<StrategyHolder> treeItem) {
		treeItem.setExpanded(true);
		treeItem.setGraphic(createTreeExpandedIcon());
	}

	public static void minimizeTree(TreeItem<StrategyHolder> treeItem) {
		treeItem.setExpanded(false);
		treeItem.setGraphic(createTreeNonExpandedIcon());
	}

	public static final void refreshTreeView(StrategyHolder rootStrategy,TreeView<StrategyHolder> treeView) {
		clearTree(treeView);
		addItemToRoot(treeView, rootStrategy);
		populateTreeView(treeView.getRoot(), rootStrategy.getChilds());
		
	}
	public static final void clearTree(TreeView<StrategyHolder> treeView) {
		treeView.setRoot(null);
	}

	public static final void addItemToRoot(TreeView<StrategyHolder> treeView,
			StrategyHolder strategy) {
		treeView.setRoot(createTreeItem(strategy));
	}

	public static final void removeNode(TreeView<StrategyHolder> treeView, StrategyHolder deletedStrategy) {
		treeView.getRoot().getChildren().removeIf(e -> e.getValue().getStrategy() == deletedStrategy.getStrategy());
	}

	public static final void populateTreeView(TreeItem<StrategyHolder> rootItem, List<StrategyHolder> strategies) {

		if (strategies == null) {
			return;
		}

		for (StrategyHolder strategyHolder : strategies) {
			TreeItem<StrategyHolder> treeItem = createTreeItem(strategyHolder);
			rootItem.getChildren().add(treeItem);
			populateTreeView(treeItem, strategyHolder.getChilds());
		}
	}

	public static boolean currentSelectionIsRootNode(TreeView<StrategyHolder> treeView) {
		StrategyHolder strategyHolder = treeView.getSelectionModel().getSelectedItem().getValue();
		if (strategyHolder.equals(treeView.getRoot().getValue())) {
			return true;
		}
		return false;
	}
}
