package com.nony.studentgradingsystem.controller;

import java.util.List;

import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping("/students")
	public String listAllStudents(Model model) {
		List<Student> studentsList = service.listAll();

		model.addAttribute("studentList", studentsList);
		return "students/students";
	}
}
