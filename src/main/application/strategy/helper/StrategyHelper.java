package main.application.strategy.helper;

import java.util.List;

import main.application.strategy.StrategyHolder;

public class StrategyHelper {
	public static final void addStrategiesToParrent(StrategyHolder parent, List<StrategyHolder> childs) {
		parent.addChilds(childs);
		childs.forEach(e->e.setParent(parent));
	}
}
