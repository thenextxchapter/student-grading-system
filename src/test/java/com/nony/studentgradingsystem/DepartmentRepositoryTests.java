package com.nony.studentgradingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class DepartmentRepositoryTests {

	@Autowired
	DepartmentRepository repo;

	@Test
	public void testCreateNewDepartment() {
		Department dept = new Department("Economics", "ECO", "Economics courses");
		Department savedDept = repo.save(dept);
		assertThat(savedDept.getId()).isGreaterThan(0);
	}
}
