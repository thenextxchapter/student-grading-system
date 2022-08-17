package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
