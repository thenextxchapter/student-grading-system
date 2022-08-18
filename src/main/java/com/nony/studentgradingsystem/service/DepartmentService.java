package com.nony.studentgradingsystem.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.exception.DepartmentNotFoundException;
import com.nony.studentgradingsystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository deptRepo;

	public List<Department> listDepartments(){
		return (List<Department>) deptRepo.findAll();
	}

	public Department get(Integer id) throws DepartmentNotFoundException {
		try {
			return deptRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new DepartmentNotFoundException("Could not find any department with ID " + id);
		}
	}
}
