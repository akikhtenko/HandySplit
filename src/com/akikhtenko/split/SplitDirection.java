package com.akikhtenko.split;

public enum SplitDirection {
	HORIZONTAL(true), VERTICAL(false);
	
	private boolean horizontal;
	
	public static SplitDirection fromString(String abbreviation) {
		if (abbreviation.equalsIgnoreCase("V")) {
			return VERTICAL;
		}
		return HORIZONTAL;
	}
	
	private SplitDirection(boolean isHorizontal) {
		this.horizontal = isHorizontal;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
}