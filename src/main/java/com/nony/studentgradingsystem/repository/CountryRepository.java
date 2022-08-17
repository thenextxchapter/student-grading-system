package com.nony.studentgradingsystem.repository;

import java.util.List;

import com.nony.studentgradingsystem.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {

	List<Country> findAllByOrderByNameAsc();
}
