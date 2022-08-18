package com.nony.studentgradingsystem.controller;

import java.util.List;

import com.nony.studentgradingsystem.entity.Course;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Gender;
import com.nony.studentgradingsystem.entity.Level;
import com.nony.studentgradingsystem.entity.Semester;
import com.nony.studentgradingsystem.exception.CourseNotFoundException;
import com.nony.studentgradingsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {

	@Autowired
	private CourseService service;

	@GetMapping("/courses")
	public String listAllCourses(Model model) {
		List<Course> courseList = service.listAll();

		model.addAttribute("courseList", courseList);
		return "courses/courses";
	}

	@GetMapping("/courses/new")
	public String newCourse(Model model) {
		List<Department> listDepartments = service.listDepartments();

		Course course = new Course();

		model.addAttribute("course", course);
		model.addAttribute("level", Level.values());
		model.addAttribute("semester", Semester.values());
		model.addAttribute("listDepartments", listDepartments);
		model.addAttribute("pageTitle", "Create New Course");
		model.addAttribute("headerTitle", "New Course");

		return "courses/course_form";
	}

	@PostMapping("/courses/save")
	public String saveCourse(
			Course course,
			RedirectAttributes redirectAttributes
	) {
		service.save(course);
		redirectAttributes.addFlashAttribute("message", "The course has been saved successfully");

		return "redirect:/courses";
	}

	@GetMapping("/courses/edit/{id}")
	public String editCourse(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes,
			Model model
	) {
		try {
			Course course = service.get(id);
			List<Department> listDepartments = service.listDepartments();

			model.addAttribute("course", course);
			model.addAttribute("level", Level.values());
			model.addAttribute("semester", Semester.values());
			model.addAttribute("listDepartments", listDepartments);
			model.addAttribute("pageTitle", "Edit Course (ID: " + id + ")");
			model.addAttribute("headerTitle", "Edit Course");

			return "courses/course_form";
		} catch (CourseNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
			return "redirect:/courses";
		}
	}

	@GetMapping("/courses/delete/{id}")
	public String deleteCourse(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes
	) {
		try {
			service.delete(id);;
			redirectAttributes.addFlashAttribute("message", "The course with ID " + id + " has been deleted successfully");
		} catch (CourseNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
		}

		return "redirect:/courses";
	}
}
