package com.nony.studentgradingsystem.service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.studentgradingsystem.entity.Course;
import com.nony.studentgradingsystem.exception.CourseNotFoundException;
import com.nony.studentgradingsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CourseRepository courseRepo;

	public List<Course> listAll() {
		return (List<Course>) courseRepo.findAll();
	}

	public Course save(Course course) {
		return courseRepo.save(course);
	}

	public Course get(Integer id) throws CourseNotFoundException {
		try {
			return courseRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new CourseNotFoundException("Could not find any course with ID " + id);
		}
	}

	public void delete(Integer id) throws CourseNotFoundException {
		Long countById = courseRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new CourseNotFoundException("Could not find any course with ID " + id);
		}

		courseRepo.deleteById(id);
	}
}
