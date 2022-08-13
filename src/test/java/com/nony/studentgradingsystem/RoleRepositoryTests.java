package com.nony.studentgradingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import com.nony.studentgradingsystem.entity.Role;
import com.nony.studentgradingsystem.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;

	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Admin", "Manages Everything");
		Role savedRole = repo.save(roleAdmin);

		assertThat(savedRole.getId()).isGreaterThan(0);
	}
}
