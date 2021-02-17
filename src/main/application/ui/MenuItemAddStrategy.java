package main.application.ui;

import main.application.controller.GridController;
import javafx.scene.control.MenuItem;

public class MenuItemAddStrategy extends MenuItem{
	public MenuItemAddStrategy(GridController controller)  {
		super("Add");
		setOnAction(e->{
			try {
				controller.onTreeInsert(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
}
