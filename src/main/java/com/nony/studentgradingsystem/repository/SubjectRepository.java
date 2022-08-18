package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Faculty;
import com.nony.studentgradingsystem.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

	@Query("SELECT s FROM Subject s WHERE s.code = :code")
	Faculty getSubjectByCode(@Param("code") String code);

	@Query("SELECT s FROM Subject s WHERE s.name = :code")
	Faculty getSubjectByName(@Param("name") String name);

	Long countById(Integer id);

	Subject findByName(String name);

	Subject findByCode(String code);
}
