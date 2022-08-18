package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

	Long countById(Integer id);
}
