package main.application.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import javafx.scene.control.TreeItem;

@Component
public class TreeStorage {

	private Map<String,List<TreeItem<TreeObject>>> treeItemForPlayer;
	
	public TreeStorage() {
		this.treeItemForPlayer = new HashMap<>();
	}
	
	public void addTreeForPlayer(String player,List<TreeItem<TreeObject>> treeItems) {
		List<TreeItem<TreeObject>> clonedItems = clone(treeItems);
		treeItemForPlayer.put(player, clonedItems);
	}
	
	public List<TreeItem<TreeObject>> getTreeForPlayer(String player){
		return treeItemForPlayer.get(player);
	}
	
	public List<TreeItem<TreeObject>> clone(List<TreeItem<TreeObject>> treeItems){
		return treeItems.stream().map(e->new TreeItem<TreeObject>(e.getValue())).collect(Collectors.toList());
	}
}
