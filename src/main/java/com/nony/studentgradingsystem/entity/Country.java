package com.nony.studentgradingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@NoArgsConstructor
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 45, nullable = false)
	private String name;

	@Column(length = 5, nullable = false)
	private String code;

	@OneToMany(mappedBy = "country")
	private Set<State> states;

	public Country(Integer id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Country(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Country(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Country{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				'}';
	}
}
