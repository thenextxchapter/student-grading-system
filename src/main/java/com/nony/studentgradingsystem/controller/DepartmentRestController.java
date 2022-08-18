package com.nony.studentgradingsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.nony.studentgradingsystem.dto.CourseDTO;
import com.nony.studentgradingsystem.entity.Course;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.exception.DepartmentNotFoundException;
import com.nony.studentgradingsystem.exception.DepartmentNotFoundRestException;
import com.nony.studentgradingsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentRestController {

	@Autowired
	DepartmentService service;

	@GetMapping("/departments/{id}/courses")
	public List<CourseDTO> listCoursesByDepartments(@PathVariable(name = "id") Integer departmentId)
			throws DepartmentNotFoundRestException {
		List<CourseDTO> listCourses = new ArrayList<>();

		try {
			Department department = service.get(departmentId);
			List<Course> courses = department.getCourses();

			for (Course course : courses) {
				CourseDTO dto = new CourseDTO(course.getId(), course.getLevel(), course.getSemester());
				listCourses.add(dto);
			}

			return listCourses;
		} catch (DepartmentNotFoundException e) {
			throw new DepartmentNotFoundRestException();
		}
	}
}
