package com.nony.studentgradingsystem.service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.studentgradingsystem.entity.Country;
import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.exception.StudentNotFoundException;
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
			if (studentByEmail != null) return "DuplicateEmail";
		} else {
			if (studentByEmail != null && studentByEmail.getId() != id) return "DuplicateEmail";
		}
		return "OK";
	}

	public Student get(Integer id) throws StudentNotFoundException {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new StudentNotFoundException("Could not find any student with ID " + id);
		}
	}

	public void delete(Integer id) throws StudentNotFoundException {
		Long countById = repo.countById(id);
		if (countById == null || countById == 0) {
			throw new StudentNotFoundException("Could not find any student with ID " + id);
		}

		repo.deleteById(id);
	}

	public void updateStudentEnabledStatus(Integer id, boolean enabled) {
		repo.updateEnabledStatus(id, enabled);
	}
}
