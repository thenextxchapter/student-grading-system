package com.nony.studentgradingsystem.controller;

import java.util.ArrayList;
import java.util.List;

import com.nony.studentgradingsystem.dto.StateDTO;
import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.State;
import com.nony.studentgradingsystem.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateRestController {

	@Autowired
	StateRepository repo;

	@GetMapping("states/list-by-country/{id}")
	public List<StateDTO> listByCountry(@PathVariable("id") Integer countryId) {
		List<State> listStates = repo.findByCountryOrderByNameAsc(new Country(countryId));
		List<StateDTO> result = new ArrayList<>();

		for (State state : listStates) {
			result.add(new StateDTO(state.getId(), state.getName()));
		}

		return result;
	}
}
