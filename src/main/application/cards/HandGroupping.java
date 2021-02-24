package main.application.cards;

public enum HandGroupping {
	SUITED("s"), 
	OFFSUITED("o"), 
	PAIRED("");
	
	private String handTypeLabel;
	
	HandGroupping(String handTypeLabel) {
		this.handTypeLabel=handTypeLabel;
	}
	
	public String getHandTypeLabel() {
		return handTypeLabel;
	}
}
