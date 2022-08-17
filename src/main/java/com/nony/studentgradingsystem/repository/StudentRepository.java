package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	Student findByEmail(String email);

	Long countById(Integer id);

	@Query("UPDATE Student s SET s.enabled = ?2 WHERE s.id = ?1")
	@Modifying
	void updateEnabledStatus(Integer id, boolean enabled);
}
