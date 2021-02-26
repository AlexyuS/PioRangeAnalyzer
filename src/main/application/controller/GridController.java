package main.application.controller;



import javafx.event.ActionEvent;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;

public interface GridController extends Controller {

	void onTreeInsert(ActionEvent e) throws Exception;

	void onTreeDelete(ActionEvent e);

	void removeTreeItemForPlayerAndStrategy(String playerName,StrategyHolder strategy);
	
	void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection);

	public void addPlayerToChoiceList(PlayerStrategyHolder players);
		
	public void triggerRecalculation();
	
}
