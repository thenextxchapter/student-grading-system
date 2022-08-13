package com.nony.studentgradingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import com.nony.studentgradingsystem.entity.Role;
import com.nony.studentgradingsystem.entity.User;
import com.nony.studentgradingsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

	@Autowired
	UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userTest = new User("test@test.com", "test", "Test", "test");
		userTest.addRole(roleAdmin);

		User savedUser = repo.save(userTest);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testGetUserById() {
		User userNony = repo.findById(1).get();
		System.out.println(userNony);
		assertThat(userNony).isNotNull();
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(System.out::println);
	}
}
