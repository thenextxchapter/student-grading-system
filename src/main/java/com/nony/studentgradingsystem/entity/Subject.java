package com.nony.studentgradingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 200, nullable = false, unique = true)
	private String name;

	@Column(length = 4, nullable = false)
	private Integer codeNumber;

	@Column(nullable = false)
	private String code;

	@PreUpdate
	@PrePersist
	public void calc() {
		code = course.getDepartment().getCode() + codeNumber;
	}

	private String description;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	public Subject(String name, Integer codeNumber, String description) {
		this.name = name;
		this.codeNumber = codeNumber;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
