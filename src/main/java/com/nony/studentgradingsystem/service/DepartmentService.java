package com.nony.studentgradingsystem.service;

import java.util.List;

import com.nony.studentgradingsystem.entity.Department;
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
}
