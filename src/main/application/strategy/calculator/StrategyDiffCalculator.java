package main.application.strategy.calculator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.application.cards.SuitedBundle;
import main.application.exception.MismatchStrategyException;
import main.application.strategy.PlayerPoolStrategyHolder;
import main.application.strategy.PlayerStrategyHolder;
import main.application.strategy.StrategyHolder;

@Component
public class StrategyDiffCalculator {
	
	@Autowired
	private PlayerPoolStrategyHolder playerPool;

	public void calculateDiffStrategyForPlayer(String player, List<StrategyHolder> newStrategy)
			throws MismatchStrategyException {
		Set<String> players = playerPool.getAllPlayers().stream().filter(e -> !e.equals(player))
				.collect(Collectors.toSet());

		for (String playerFromPool : players) {
			PlayerStrategyHolder playerPoolStrategyHolder = playerPool.getStrategyForPlayer(playerFromPool);
			calculateDiffAgainstStrategyForPlayer(playerPoolStrategyHolder, newStrategy, player);
		}
	}

	public void calculateDiffAgainstStrategyForPlayer(PlayerStrategyHolder playerStrategy,
			List<StrategyHolder> newStrategy, String player) throws MismatchStrategyException {
		List<StrategyHolder> strategies = playerStrategy.getStrategyHolder();
		String playerFromPool = playerStrategy.getPlayerName();
		for (StrategyHolder strategyHolder : newStrategy) {
			int index = strategies.indexOf(strategyHolder);
			if (index == -1) {

				throw new MismatchStrategyException(player, playerFromPool, strategyHolder.getStrategy());
			}
			StrategyHolder strategyFromPool = strategies.get(index);
			calculateIndividualStrategies(player, strategyHolder, playerFromPool, strategyFromPool);
		}
	}

	public void calculateIndividualStrategies(String player1, StrategyHolder strategy1, String player2,
			StrategyHolder strategy2) {
		List<SuitedBundle> cards1 = strategy1.getIndividualCards();
		List<SuitedBundle> cards2 = strategy2.getIndividualCards();

		calculateCardDifference(player1, cards1, player2, cards2);
		calculateCardDifference(player2, cards2, player1, cards1);
	}

	public void calculateCardDifference(String player1, List<SuitedBundle> cards1, String player2,
			List<SuitedBundle> cards2) {

		for (SuitedBundle sb : cards1) {
			Integer indexOf = cards2.indexOf(sb);
			if (indexOf == -1) {
				sb.setDiffFromPlayer(player2, sb.getOccurance());
			} else {
				SuitedBundle sb2 = cards2.get(indexOf);
				sb.setDiffFromPlayer(player2, sb.getOccurance() - sb2.getOccurance());
			}
		}
	}
}
