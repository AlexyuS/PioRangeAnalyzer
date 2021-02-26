package main.application.observer;


import main.application.controller.GridController;

public interface SelectionChangedObserver {
	
	public void register(GridController controller) ;
	
}
