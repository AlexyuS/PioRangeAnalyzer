package main.application.observer;


import main.application.controller.GridController;
import main.application.strategy.PlayerStrategyHolder;

public interface SelectionChangedObserver {
	
	public void register(GridController controller) ;

	public void notify(PlayerStrategyHolder oldStrategy,PlayerStrategyHolder newStrategy, Class<?> excludeControler);
	
}
