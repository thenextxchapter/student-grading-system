package com.nony.studentgradingsystem.entity;

public enum Semester {
	FIRST_SEMESTER("1st Semester"),
	SECOND_SEMESTER("2nd Semester");

	private final String displayValue;

	Semester(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
