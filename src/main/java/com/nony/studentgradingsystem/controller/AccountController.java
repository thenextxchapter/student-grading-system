package com.nony.studentgradingsystem.controller;

import java.io.IOException;

import com.nony.studentgradingsystem.entity.User;
import com.nony.studentgradingsystem.security.SGSUserDetails;
import com.nony.studentgradingsystem.service.UserService;
import com.nony.studentgradingsystem.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

	@Autowired
	private UserService service;

	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal SGSUserDetails loggedUser,
			Model model) {
		/* In this handler method, we need to add the user object
		 * that currently represents the logged in user
		 * Spring security will return an instance of ShopDownUserDetails */
		String email = loggedUser.getUsername();
		User user = service.getByEmail(email);
		model.addAttribute("user", user);

		return "users/account_form";
	}

	@PostMapping("/account/update")
	public String saveDetails(
			User user,
			RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal SGSUserDetails loggedUser,
			@RequestParam("image") MultipartFile multipartFile
	) throws IOException {
		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhoto(fileName);
			User savedUser = service.updateAccount(user);

			String uploadDir = "user-photos/" + savedUser.getId();

			FileUploadUtil.cleanDir(uploadDir);

			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} else {
			if (user.getPhoto().isEmpty()) user.setPhoto(null);
			service.updateAccount(user);
		}

		loggedUser.setFirstName(user.getFirstName());

		redirectAttributes.addFlashAttribute("message", "Account details have been updated");

		return "redirect:/account";
	}
}
