package com.nony.studentgradingsystem.entity;

public enum Level {
	FIRST_YEAR("100 Level"),
	SECOND_YEAR("200 Level"),
	THIRD_YEAR("300 Level"),
	FOURTH_YEAR("400 Level"),
	FIFTH_YEAR("500 Level"),
	SIXTH_YEAR("600 Level");

	private final String displayValue;

	Level(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
