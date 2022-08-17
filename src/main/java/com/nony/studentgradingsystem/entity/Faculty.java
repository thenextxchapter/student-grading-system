package com.nony.studentgradingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "faculties")
@Getter
@Setter
@NoArgsConstructor
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 128, nullable = false, unique = true)
	private String email;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "middle_name", length = 45)
	private String middleName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private CivilStatus civilStatus;

	@Column(name = "phone_number", length = 15, nullable = false)
	private String phoneNumber;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "lecturers_departments",
			joinColumns = @JoinColumn(name = "faculty_id"),
			inverseJoinColumns = @JoinColumn(name = "department_id")
	)
	private Set<Department> departments = new HashSet<>();

	public Faculty(String email, String firstName, String middleName, String lastName, Gender gender,
			CivilStatus civilStatus,
			String phoneNumber) {
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.civilStatus = civilStatus;
		this.phoneNumber = phoneNumber;
	}

	public void addDepartment(Department department) {
		this.departments.add(department);
	}

	@Override
	public String toString() {
		return "Faculty{" +
				"id=" + id +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", middleName='" + middleName + '\'' +
				", lastName='" + lastName + '\'' +
				", gender=" + gender +
				", civilStatus=" + civilStatus +
				", phoneNumber='" + phoneNumber + '\'' +
				", enabled=" + enabled +
				'}';
	}
}
