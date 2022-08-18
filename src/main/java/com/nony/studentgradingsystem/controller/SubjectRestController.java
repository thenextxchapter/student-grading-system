package com.nony.studentgradingsystem.controller;

import com.nony.studentgradingsystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectRestController {

	@Autowired
	private SubjectService service;

	@PostMapping("/subjects/check_unique")
	public String checkUnique(
			@Param("id") Integer id,
			@Param("email") String email,
			@Param("code") String code
	) {
		return service.checkUnique(id, email, code);
	}
}
