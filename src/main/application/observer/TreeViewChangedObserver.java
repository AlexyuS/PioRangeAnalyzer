package main.application.observer;


import main.application.controller.GridController;
import main.application.strategy.StrategyHolder;

public interface TreeViewChangedObserver {
	
	/**
	 * Registers for any changes on the tree view from any grid
	 * @param controller
	 */
	public void registerForTreeViewChanged(GridController controller) ;
	
	/**
	 * Called whenever a node is deleted on any of the tree view;
	 * @param playerName
	 * @param source
	 */
	public void notifyStrategyRemove(String playerName,StrategyHolder strategy);
	
}
