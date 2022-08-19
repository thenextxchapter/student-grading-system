package com.nony.studentgradingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.nony.studentgradingsystem.entity.Course;
import com.nony.studentgradingsystem.entity.Score;
import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.entity.Subject;
import com.nony.studentgradingsystem.repository.CourseRepository;
import com.nony.studentgradingsystem.repository.ScoreRepository;
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
public class ScoreRepositoryTests {

	@Autowired
	SubjectRepository repo;

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	ScoreRepository scoreRepo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateResult() {
		Student student = entityManager.find(Student.class, 30);
		Optional<Subject> subject = repo.findById(27);

		Score test = new Score(10, 10, 10, 10, 60);
		Score savedTest = scoreRepo.save(test);

		assertThat(savedTest.getId()).isGreaterThan(0);

	}

}
