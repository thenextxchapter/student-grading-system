package com.nony.studentgradingsystem.entity;

public enum Gender {
	MALE("Male"),
	FEMALE("Female"),
	NON_BINARY("Non-Binary");

	private final String displayValue;

	Gender(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
