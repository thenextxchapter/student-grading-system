package com.nony.studentgradingsystem.entity;

public enum Religion {
	CHRISTIANITY("Christianity"),
	ISLAM("Islam"),
	JUDAISM("Judaism"),
	TRADITIONAL_WORSHIPPER("Traditional Worshipper"),
	BUDDHISM("Buddhism"),
	NONE("None");

	private final String displayValue;

	Religion(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
