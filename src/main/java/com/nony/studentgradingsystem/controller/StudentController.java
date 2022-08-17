package com.nony.studentgradingsystem.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Gender;
import com.nony.studentgradingsystem.entity.Religion;
import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.exception.StudentNotFoundException;
import com.nony.studentgradingsystem.export.StudentPDFExporter;
import com.nony.studentgradingsystem.service.DepartmentService;
import com.nony.studentgradingsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String saveStudent(
			Student student,
			RedirectAttributes redirectAttributes
	) {
		service.save(student);
		redirectAttributes.addFlashAttribute("message", "The student has been saved successfully");

		return "redirect:/students";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudent(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes,
			Model model
	) {
		try {
			Student student = service.get(id);

			List<Department> listDepartments = deptService.listDepartments();
			List<Country> listCountries = service.listCountries();

			model.addAttribute("student", student);
			model.addAttribute("gender", Gender.values());
			model.addAttribute("religion", Religion.values());
			model.addAttribute("departments", listDepartments);
			model.addAttribute("countries", listCountries);
			model.addAttribute("pageTitle", "Edit Student (ID: " + id + ")");
			model.addAttribute("headerTitle", "Edit Student");

			return "students/student_form";
		} catch (StudentNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
			return "redirect:/students";
		}
	}

	@GetMapping("/students/delete/{id}")
	public String deleteStudent(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes
	) {
		try {
			service.delete(id);
			redirectAttributes.addFlashAttribute("message",
					"The Student with ID " + id + " has been deleted successfully");
		} catch (StudentNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception);
		}

		return "redirect:/students";
	}

	@GetMapping("/students/{id}/enabled/{status}")
	public String updateStudentEnabledStatus(
			@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes
	)  {
		service.updateStudentEnabledStatus(id, enabled);
		String status = enabled ? "enabled" : "disabled";
		String message = "The Student with ID " + id + " has been " + status;
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/students";
	}

	@GetMapping("/students/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<Student> listStudents = service.listAll();
		StudentPDFExporter exporter = new StudentPDFExporter();
		exporter.export(listStudents, response);
	}

}
