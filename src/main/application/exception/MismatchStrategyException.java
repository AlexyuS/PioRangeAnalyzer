package main.application.exception;


public class MismatchStrategyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MismatchStrategyException(String newPlayer,String newStrategy,String withPlayer) {
		super(String.format("Strategy %s for player %s does not match with any strategy from player %s",newPlayer,newStrategy,withPlayer));
	}

}
