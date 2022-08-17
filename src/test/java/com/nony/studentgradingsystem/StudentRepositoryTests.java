package com.nony.studentgradingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Date;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Gender;
import com.nony.studentgradingsystem.entity.Religion;
import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.entity.User;
import com.nony.studentgradingsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTests {

	@Autowired
	StudentRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewStudent() {
		Department department = entityManager.find(Department.class, 7);
		Country nigeria = entityManager.find(Country.class, 164);

		Student student = new Student(
				department, 2017, "mary.abechi@gmail.com", "Enuwa", "Mary"
				,"Abechi", Gender.FEMALE, "08112345678", LocalDate.of(1999, Month.MAY, 9),
				"10, Aguiyi Ironsi Street, Airport Road, Lugbe, Abuja", Religion.CHRISTIANITY, nigeria
		);
		Student savedStudent = repo.save(student);
		System.out.println(student);
		assertThat(savedStudent.getId()).isGreaterThan(0);
	}

	@Test
	public void testGetStudentById() {
		Student student = repo.findById(14).get();
		System.out.println(student);
		assertThat(student).isNotNull();
	}

	@Test
	public void testListAllStudents() {
		Iterable<Student> listStudents = repo.findAll();
		listStudents.forEach(System.out::println);
	}

}
