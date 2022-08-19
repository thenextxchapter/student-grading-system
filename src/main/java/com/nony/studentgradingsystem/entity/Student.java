package com.nony.studentgradingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;

	@Column(name = "entry_year", nullable = false)
	private Integer yearOfEntry;

	@Column(name = "matric_no", nullable = false)
	private String matricNumber;

	@PostUpdate
	@PrePersist
	@Transient
	public void calc() {
		matricNumber = "VUG/" + department.getCode() + "/" + yearOfEntry + "/" + id;
	}

	@Column(length = 128, nullable = false, unique = true)
	private String email;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "middle_name", length = 45)
	private String middleName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Gender gender;

	@Column(name = "phone_number", length = 15, nullable = false)
	private String phoneNumber;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob", nullable = false)
	private LocalDate dateOfBirth;

	@Column(nullable = false)
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private Religion religion;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country nationality;

	private boolean enabled;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToMany
	@JoinTable(
			name = "student_subjects",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "subject_id")
	)
	private List<Subject> subjects = new ArrayList<>();

	public Student(Department department, Integer yearOfEntry, String email, String firstName, String middleName,
			String lastName,
			Gender gender, String phoneNumber, LocalDate dateOfBirth, String address, Religion religion, Country nationality, Course course) {
		this.department = department;
		this.yearOfEntry = yearOfEntry;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.religion = religion;
		this.nationality = nationality;
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student{" +
				"matricNumber='" + matricNumber + '\'' +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", middleName='" + middleName + '\'' +
				", lastName='" + lastName + '\'' +
				", gender=" + gender +
				", phoneNumber='" + phoneNumber + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", address='" + address + '\'' +
				", religion=" + religion +
				'}';
	}
}
