package main.application.ui.events;

import java.util.logging.Logger;

import main.application.ui.TreeObject;
import main.application.ui.helper.TreeViewHelper;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

public class TreeItemClickedEvent implements EventHandler<MouseEvent>{
	private final static Logger LOGGER = Logger.getLogger(TreeItemClickedEvent.class.getName());

	private final TreeItem<TreeObject> treeItem;
	
	public TreeItemClickedEvent(TreeItem<TreeObject> treeItem) {
		this.treeItem = treeItem;
		
	}
	@Override
	public void handle(MouseEvent event) {
		LOGGER.info("Mouse clicked on tree item:"+treeItem.getValue().getLabel());
		if(this.treeItem.isExpanded()) {
			TreeViewHelper.minimizeTree(treeItem);
		}else {
			TreeViewHelper.expandTree(treeItem);
		}
	}

}
