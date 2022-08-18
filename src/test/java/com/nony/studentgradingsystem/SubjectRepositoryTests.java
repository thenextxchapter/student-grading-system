package com.nony.studentgradingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.Course;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Gender;
import com.nony.studentgradingsystem.entity.Religion;
import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.entity.Subject;
import com.nony.studentgradingsystem.repository.CourseRepository;
import com.nony.studentgradingsystem.repository.StudentRepository;
import com.nony.studentgradingsystem.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class SubjectRepositoryTests {

	@Autowired
	SubjectRepository repo;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testGetSubjectsByCourse() {
		Course course = courseRepo.findById(24).get();

		Iterable<Subject> listSubjects = repo.findSubjectByCourse(course);
		listSubjects.forEach(System.out::println);

	}

}
