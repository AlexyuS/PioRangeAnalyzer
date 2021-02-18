package main.application.strategy.helper;

import java.util.List;
import java.util.stream.Collectors;

import main.application.strategy.StrategyHolder;

public class StrategyHelper {
	public static final void clearStrategyFromPool(List<StrategyHolder> strategyHolder,String uuid) {
		List<StrategyHolder> toBeRemoved = strategyHolder.stream().filter(e->e.getUUID().equals(uuid)).collect(Collectors.toList());
		strategyHolder.removeAll(toBeRemoved);
	}
}
