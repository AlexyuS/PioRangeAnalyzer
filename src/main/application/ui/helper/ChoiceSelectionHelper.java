package main.application.ui.helper;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.control.ChoiceBox;

public class ChoiceSelectionHelper {
	public static final String getPlayerForIndex(ChoiceBox<String> choiceBox,Number index) {
		if((Integer)index==-1) {
			return StringUtils.EMPTY;
		}
		return choiceBox.getItems().get((Integer)index);
	}
	
}
