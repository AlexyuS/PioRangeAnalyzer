package main.application.exception;

public class CardsCombosLimitExcedeed extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardsCombosLimitExcedeed(Integer expected,String forHand) {
		super(String.format("Insertion limit exceeded for card %d. Expected maximum %s insertions",expected,forHand));
	}
}
