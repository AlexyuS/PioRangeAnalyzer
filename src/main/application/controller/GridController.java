package main.application.controller;

import java.util.Set;

import javafx.event.ActionEvent;

public interface GridController extends Controller {

	void onTreeInsert(ActionEvent e) throws Exception;

	void onTreeDelete(ActionEvent e);

	void onSelectionChanged(Number oldSelection, Number newSelection);

	public void addPlayerToChoiceList(Set<String> players);
}
