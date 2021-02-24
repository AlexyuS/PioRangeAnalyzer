package main.application.stage;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.controller.TextAreaController;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.ui.helper.TreeViewHelper;
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

	@Override
	protected void onClose(Stage stage) {
		
	}
	
	public void addStrategyToTree(List<StrategyHolder>  strategies) {
		TreeViewHelper.insertStrategyHolderForPlayer(treeItem, strategies);
	}
	
	public void addStrategyToPlayer(List<StrategyHolder> strategies) {
		if(playerStrategyHolder.getStrategyHolder().size()==0) {
			playerStrategyHolder.setStrategyHolder(strategies);
		}else {
			treeItem.getValue().addChilds(strategies);
		}
	}

	@Override
	protected void refreshUI(Stage stage) {
		
	}
	

}
