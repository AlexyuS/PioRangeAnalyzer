package main.application.stage;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.controller.TextAreaController;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

@Component
public class TextAreaStage extends SpringStage<TextAreaController>{
    
    private PlayerStrategyHolder playerStrategyHolder;
    
    private TreeItem<StrategyHolder> treeItem;
    
    
	@Autowired
	public TextAreaStage(String path) {
		super(path);
	}

	@Override
	protected void afterInitialize(Stage stage) {
		stage.setTitle("Please insert the range");
	}
	
	
	public void open(TreeItem<StrategyHolder> treeItem,PlayerStrategyHolder playerName) {
		super.open();
		this.treeItem = treeItem;
		this.playerStrategyHolder = playerName;
	}

	
	
	public TreeItem<StrategyHolder> getTreeItem(){
		return this.treeItem;
	}
	
	public PlayerStrategyHolder getPlayerStrategy() {
		return this.playerStrategyHolder;
	}

}
