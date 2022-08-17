package com.nony.studentgradingsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 30, nullable = false)
	private String name;

	@Column(length = 4, nullable = false)
	private String code;

	@Column(length = 512, nullable = false)
	private String description;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Student> details = new ArrayList<>();

	public Department(String name, String code, String description) {
		this.name = name;
		this.code = code;
		this.description = description;
	}
}
