package com.nony.studentgradingsystem.repository;

import java.util.List;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {

	List<State> findByCountryOrderByNameAsc(Country country);

}
