package com.nony.studentgradingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "states")
@Getter
@Setter
@NoArgsConstructor
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 45, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public State(String name, Country country) {
		this.name = name;
		this.country = country;
	}

	@Override
	public String toString() {
		return "State{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
