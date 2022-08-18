package com.nony.studentgradingsystem.controller;

import java.util.List;

import com.nony.studentgradingsystem.entity.Course;
import com.nony.studentgradingsystem.entity.Subject;
import com.nony.studentgradingsystem.exception.SubjectNotFoundException;
import com.nony.studentgradingsystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SubjectController {

	@Autowired
	SubjectService service;

	@GetMapping("/subjects")
	public String listAllSubjects(Model model) {
		List<Subject> subjectList = service.listAll();

		model.addAttribute("subjectList", subjectList);
		return "subjects/subjects";
	}

	@GetMapping("/subjects/new")
	public String newSubject(Model model) {
		List<Course> listCourses = service.listCourses();
		Subject subject = new Subject();

		model.addAttribute("subject", subject);
		model.addAttribute("listCourses", listCourses);
		model.addAttribute("pageTitle", "Create New Subject");
		model.addAttribute("headerTitle", "New Subject");

		return "subjects/subject_form";
	}

	@PostMapping("/subjects/save")
	public String saveSubject(
			Subject subject,
			RedirectAttributes redirectAttributes
	) {
		service.save(subject);
		redirectAttributes.addFlashAttribute("message", "The subject has been saved successfully");

		return "redirect:/subjects";
	}

	@GetMapping("/subjects/edit/{id}")
	public String editSubject(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes,
			Model model
	) {
		try {
			Subject subject = service.get(id);
			List<Course> listCourses = service.listCourses();

			model.addAttribute("subject", subject);
			model.addAttribute("listCourses", listCourses);
			model.addAttribute("pageTitle", "Edit Subject (ID: " + id + ")");
			model.addAttribute("headerTitle", "Edit Subject");

			return "subjects/subject_form";
		} catch (SubjectNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
			return "redirect:/subjects";
		}
	}

	@GetMapping("/subjects/delete/{id}")
	public String deleteSubject(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes
	) {
		try {
			service.delete(id);
			redirectAttributes.addFlashAttribute("message",
					"The subject with ID " + id + " has been deleted successfully");
		} catch (SubjectNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
		}
		return "redirect:/subjects";
	}
}
