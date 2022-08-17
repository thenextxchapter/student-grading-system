package com.nony.studentgradingsystem.controller;

import java.util.List;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Gender;
import com.nony.studentgradingsystem.entity.Religion;
import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.service.DepartmentService;
import com.nony.studentgradingsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;

	@Autowired
	private DepartmentService deptService;

	@GetMapping("/students")
	public String listAllStudents(Model model) {
		List<Student> studentsList = service.listAll();

		model.addAttribute("studentList", studentsList);
		return "students/students";
	}

	@GetMapping("/students/new")
	public String newStudent(Model model) {
		List<Department> listDepartments = deptService.listDepartments();
		List<Country> listCountries = service.listCountries();

		Student student = new Student();
		student.setEnabled(true);

		model.addAttribute("student", student);
		model.addAttribute("gender", Gender.values());
		model.addAttribute("religion", Religion.values());
		model.addAttribute("departments", listDepartments);
		model.addAttribute("countries", listCountries);
		model.addAttribute("pageTitle", "Create New Student");
		model.addAttribute("headerTitle", "New Student");

		return "students/student_form";
	}

	@PostMapping("/students/save")
	public String saveUser(
			Student student,
			RedirectAttributes redirectAttributes
	) {
		service.save(student);
		redirectAttributes.addFlashAttribute("message", "The student has been saved successfully");

		return "redirect:/students";
	}
}
