package main.application.strategy.helper;


public class IndexCalcualtionHelper {
	public static final int getPocketPairIndex(int rank) {
		return 14 * (14 - rank);
	}

	public static final int getOffsuitedIndex(int highCard, int lowCard) {
		return 196 - highCard - 13 * lowCard;
	}

	public static final int getSuitedIndex(int highCard, int lowCard) {
		return 13 * (14 - highCard) + 14 - lowCard;
	}
}
