package com.nony.studentgradingsystem.controller;

import java.util.List;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryRestController {

	@Autowired
	CountryRepository repo;

	@GetMapping("/countries")
	public List<Country> listAll() {
		return repo.findAllByOrderByNameAsc();
	}
}
