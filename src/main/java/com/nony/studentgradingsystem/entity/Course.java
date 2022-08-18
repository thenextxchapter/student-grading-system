package com.nony.studentgradingsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	private Level level;

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	private Semester semester;

	@Column(name = "start_year", nullable = false)
	private Integer startYear;

	@Column(name = "end_year", nullable = false)
	private Integer endYear;

	@Column(nullable = false)
	private String session;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Subject> courseSubjects = new ArrayList<>();

	@PreUpdate
	@PrePersist
	public void calc() {
		session = startYear + "/" + endYear;
	}

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public Course(Level level, Semester semester, Integer startYear, Integer endYear) {
		this.level = level;
		this.semester = semester;
		this.startYear = startYear;
		this.endYear = endYear;
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", level=" + level +
				", semester=" + semester +
				", session='" + session + '\'' +
				'}';
	}
}
