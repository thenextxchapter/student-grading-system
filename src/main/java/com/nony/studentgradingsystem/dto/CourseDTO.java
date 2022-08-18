package com.nony.studentgradingsystem.dto;

import com.nony.studentgradingsystem.entity.Level;
import com.nony.studentgradingsystem.entity.Semester;

public class CourseDTO {

	private Integer id;
	private Level level;
	private Semester semester;

	public CourseDTO() {
	}

	public CourseDTO(Integer id, Level level, Semester semester) {
		this.id = id;
		this.level = level;
		this.semester = semester;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
}
