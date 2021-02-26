package main.application.observer;


import main.application.controller.GridController;

public interface SelectionChangedObserver {
	
	/**
	 * Registers for any changes on any selection changed grid
	 * @param controller
	 */
	public void registerForAnyPlayerSelectionChanged(GridController controller) ;
	
	/**
	 * Executes when any change on any player selection changed
	 * @param source
	 */
	public void onParentPlayerSelectionChanged();
	
}
