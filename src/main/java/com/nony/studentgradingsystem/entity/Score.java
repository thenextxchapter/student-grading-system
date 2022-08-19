package com.nony.studentgradingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scores")
@Getter
@Setter
@NoArgsConstructor
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(columnDefinition = "integer default 0")
	private Integer CA1;

	@Column(columnDefinition = "integer default 0")
	private Integer CA2;

	@Column(columnDefinition = "integer default 0")
	private Integer CA3;

	@Column(columnDefinition = "integer default 0")
	private Integer CA4;

	@Column(columnDefinition = "integer default 0")
	private Integer Final;

	public Score(Integer CA1, Integer CA2, Integer CA3, Integer CA4, Integer aFinal) {
		this.CA1 = CA1;
		this.CA2 = CA2;
		this.CA3 = CA3;
		this.CA4 = CA4;
		Final = aFinal;
	}

	@Override
	public String toString() {
		return "Score{" +
				"id=" + id +
				", CA1=" + CA1 +
				", CA2=" + CA2 +
				", CA3=" + CA3 +
				", CA4=" + CA4 +
				", Final=" + Final +
				'}';
	}
}
