package com.nony.studentgradingsystem.entity;

public enum CivilStatus {
	SINGLE("Single"),
	MARRIED("Married");

	private final String displayValue;

	CivilStatus(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
