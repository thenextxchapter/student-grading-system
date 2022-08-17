package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Faculty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {

	@Query("SELECT f FROM Faculty f WHERE f.email = :email")
	Faculty getFacultyByEmail(@Param("email") String email);

	Long countById(Integer id);

	@Query("UPDATE Faculty f SET f.enabled = ?2 WHERE f.id = ?1")
	@Modifying
	void updateEnabledStatus(Integer id, boolean enabled);

	Faculty findByEmail(String email);
}
