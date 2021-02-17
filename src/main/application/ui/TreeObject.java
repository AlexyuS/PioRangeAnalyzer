package main.application.ui;


public class TreeObject {
	
	
	private final String id;
	private final String label;

	public TreeObject(String label, String id) {
		this.id = id;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
