package com.nony.studentgradingsystem.controller;

import com.nony.studentgradingsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {

	@Autowired
	StudentService service;

	@PostMapping("/students/check_unique")
	public String checkUnique(
			@Param("id") Integer id,
			@Param("email") String email
	) {
		return service.checkUnique(id, email);
	}
}
