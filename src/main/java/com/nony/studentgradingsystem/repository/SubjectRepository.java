package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Course;
import com.nony.studentgradingsystem.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

	Long countById(Integer id);

	Subject findByName(String name);

	Subject findByCode(String code);

	Iterable<Subject> findSubjectByCourse(Course course);
}
