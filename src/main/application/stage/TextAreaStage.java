package main.application.stage;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.controller.TextAreaController;
import main.application.exception.MismatchStrategyException;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;
import main.application.strategy.calculator.StrategyDiffCalculator;
import main.application.ui.TreeObject;
import main.application.ui.helper.TreeViewHelper;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

@Component
public class TextAreaStage extends SpringStage<TextAreaController>{
    private PlayerStrategyHolder playerStrategyHolder;
    private TreeItem<TreeObject> treeItem;
    
    @Autowired
    private StrategyDiffCalculator calculator;
    
	@Autowired
	public TextAreaStage(String path,StrategyDiffCalculator calculator) {
		super(path);
	}

	@Override
	protected void afterInitialize(Stage stage) {
		stage.setTitle("Please insert the range");
	}
	
	
	public void open(PlayerStrategyHolder strategyHolder,TreeItem<TreeObject> treeItem) {
		super.open();
		this.playerStrategyHolder = strategyHolder;
		this.treeItem = treeItem;
	}

	@Override
	protected void onClose(Stage stage) {
		
	}
	
	public void addStrategyToTree(List<StrategyHolder>  strategies) {
		TreeViewHelper.insertStrategyHolderForPlayer(treeItem, strategies);
	}
	
	public void addStrategyToPlayer(List<StrategyHolder> strategies) {
		playerStrategyHolder.getStrategyHolder().addAll(strategies);	
	}

	public void calculateNewStrategies(String name,List<StrategyHolder> strategiesHolder) throws  MismatchStrategyException {
		calculator.calculateDiffStrategyForPlayer(name, strategiesHolder);
	}
	
	@Override
	protected void refreshUI(Stage stage) {
		
	}
	

}
