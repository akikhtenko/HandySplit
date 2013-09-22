package com.akikhtenko.split;

public enum SplitDirection {
	HORIZONTAL(true), VERTICAL(false);
	
	private boolean horizontal;
	
	private SplitDirection(boolean isHorizontal) {
		this.horizontal = isHorizontal;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
}