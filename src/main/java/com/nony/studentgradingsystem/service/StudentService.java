package com.nony.studentgradingsystem.service;

import javax.transaction.Transactional;

import java.util.List;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.repository.CountryRepository;
import com.nony.studentgradingsystem.repository.DepartmentRepository;
import com.nony.studentgradingsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

	@Autowired
	StudentRepository repo;

	@Autowired
	DepartmentRepository deptRepo;

	@Autowired
	CountryRepository countryRepo;

	public List<Student> listAll() {
		return (List<Student>) repo.findAll();
	}

	public List<Country> listCountries(){
		return (List<Country>) countryRepo.findAll();
	}

	public List<Department> listDepartments(){
		return (List<Department>) deptRepo.findAll();
	}

	public Student save(Student student) {
		return repo.save(student);
	}

	public String checkUnique(Integer id, String email) {
		boolean isCreatingNew = (id == null || id == 0);

		Student studentByEmail = repo.findByEmail(email);

		if (isCreatingNew) {
			if (studentByEmail != null) return "DuplicateName";
		} else {
			if (studentByEmail != null && studentByEmail.getId() != id) return "DuplicateName";
		}
		return "OK";
	}
}
