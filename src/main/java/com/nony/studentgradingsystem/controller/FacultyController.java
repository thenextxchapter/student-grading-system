package com.nony.studentgradingsystem.controller;

import java.util.List;

import com.nony.studentgradingsystem.entity.CivilStatus;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Faculty;
import com.nony.studentgradingsystem.entity.Gender;
import com.nony.studentgradingsystem.exception.FacultyNotFoundException;
import com.nony.studentgradingsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FacultyController {

	@Autowired
	private FacultyService service;

	@GetMapping("/faculties")
	public String listAllFaculties(Model model) {
		List<Faculty> facultyList = service.listAll();

		model.addAttribute("facultyList", facultyList);
		return "faculties/faculties";
	}

	@GetMapping("/faculties/new")
	public String newFaculty(Model model) {
		List<Department> listDepartments = service.listDepartments();
		Faculty faculty = new Faculty();
		faculty.setEnabled(true);

		model.addAttribute("faculty", faculty);
		model.addAttribute("gender", Gender.values());
		model.addAttribute("status", CivilStatus.values());
		model.addAttribute("listDepartments", listDepartments);
		model.addAttribute("pageTitle", "Create New Lecturer");
		model.addAttribute("headerTitle", "New Lecturer");

		return "faculties/faculty_form";
	}

	@PostMapping("/faculties/save")
	public String saveFaculty(
			Faculty faculty,
			RedirectAttributes redirectAttributes
	) {
		service.save(faculty);
		redirectAttributes.addFlashAttribute("message", "The lecturer has been saved successfully");

		return "redirect:/faculties";
	}

	@GetMapping("/faculties/edit/{id}")
	public String editFaculty(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes,
			Model model
	) {
		try {
			Faculty faculty = service.get(id);
			List<Department> listDepartments = service.listDepartments();

			model.addAttribute("faculty", faculty);
			model.addAttribute("listDepartments", listDepartments);
			model.addAttribute("gender", Gender.values());
			model.addAttribute("status", CivilStatus.values());
			model.addAttribute("pageTitle", "Edit Lecturer (ID: " + id + ")");
			model.addAttribute("headerTitle", "Edit Lecturer");

			return "faculties/faculty_form";
		} catch (FacultyNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
			return "redirect:/faculties";
		}
	}

	@GetMapping("/faculties/delete/{id}")
	public String deleteFaculty(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes
	) {
		try {
			service.delete(id);
			redirectAttributes.addFlashAttribute("message",
					"The lecturer with ID " + id + " has been deleted successfully");
		} catch (FacultyNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
		}
		return "redirect:/faculties";
	}

	@GetMapping("/faculties/{id}/enabled/{status}")
	public String updateFacultyEnabledStatus(
			@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes
	) {
		service.updateFacultyEnabledStatus(id, enabled);
		String status = enabled ? "enabled" : "disabled";
		String message = "The lecturer with ID " + id + " has been " + status;
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/faculties";
	}
}
