package main.application.controller;



import javafx.event.ActionEvent;
import main.application.strategy.PlayerStrategyHolder;

public interface GridController extends Controller {

	void onTreeInsert(ActionEvent e) throws Exception;

	void onTreeDelete(ActionEvent e);

	void onSelectionChanged(PlayerStrategyHolder oldSelection, PlayerStrategyHolder newSelection);

	public void addPlayerToChoiceList(PlayerStrategyHolder players);
	
	public void removePlayerFromChoiceList(PlayerStrategyHolder player);
	
	public void triggerRecalculation();
}
