package com.nony.studentgradingsystem.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.nony.studentgradingsystem.entity.Role;
import com.nony.studentgradingsystem.entity.User;
import com.nony.studentgradingsystem.exception.UserNotFoundException;
import com.nony.studentgradingsystem.export.UserPDFExporter;
import com.nony.studentgradingsystem.service.UserService;
import com.nony.studentgradingsystem.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String listAllUsers(Model model) {
		List<User> usersList = service.listAll();

		model.addAttribute("userList", usersList);
		return "users/users";
	}

	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = service.listRoles();
		User user = new User();
		user.setEnabled(true);

		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Create New User");
		model.addAttribute("headerTitle", "New User");

		return "users/user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(
			User user,
			RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile
	) throws IOException {
		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhoto(fileName);
			User savedUser = service.save(user);

			String uploadDir = "../user-photos/" + savedUser.getId();

			FileUploadUtil.cleanDir(uploadDir);

			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} else {
			if (user.getPhoto().isEmpty()) user.setPhoto(null);
			service.save(user);
		}

		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");

		return "redirect:/users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes,
			Model model
	) {
		try {
			User user = service.get(id);
			List<Role> listRoles = service.listRoles();

			model.addAttribute("user", user);
			model.addAttribute("listRoles", listRoles);
			model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
			model.addAttribute("headerTitle", "Edit User");

			return "users/user_form";
		} catch (UserNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
			return "redirect:/users";
		}
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes
	) {
		try {
			service.delete(id);
			redirectAttributes.addFlashAttribute("message",
					"The user ID " + id + " has been deleted successfully");

		} catch (UserNotFoundException exception) {
			redirectAttributes.addFlashAttribute("message", exception.getMessage());
		}
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/enabled/{status}")
	public String updateUserEnabledStatus(
			@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes
	)  {
		service.updateUserEnabledStatus(id, enabled);
		String status = enabled ? "enabled" : "disabled";
		String message = "The user ID " + id + " has been " + status;
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/users";
	}

	@GetMapping("/users/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		List<User> listUsers = service.listAll();
		UserPDFExporter exporter = new UserPDFExporter();
		exporter.export(listUsers, response);
	}
}
