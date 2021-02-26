package main.application.exception;

import main.application.cards.IndividualCardStrategy;

public class StrategyChainMismatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StrategyChainMismatchException(IndividualCardStrategy cardStrategy,String parrentStrategy) {
		super(String.format("Incorrect strategy attached to parrent. Card %s not found in parrent %s",cardStrategy.getCardHand(),parrentStrategy));
	}
}
