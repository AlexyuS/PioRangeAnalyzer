package main.application.ui;

import main.application.controller.GridController;
import javafx.scene.control.MenuItem;

public class MenuItemRemoveStrategy extends MenuItem{
	public MenuItemRemoveStrategy(GridController controller ) {
		super("Remove");
		setOnAction(e->controller.onTreeDelete(e));
	}
}
