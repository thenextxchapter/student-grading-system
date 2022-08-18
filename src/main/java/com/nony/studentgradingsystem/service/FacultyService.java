package com.nony.studentgradingsystem.service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.studentgradingsystem.entity.Department;
import com.nony.studentgradingsystem.entity.Faculty;
import com.nony.studentgradingsystem.exception.FacultyNotFoundException;
import com.nony.studentgradingsystem.repository.DepartmentRepository;
import com.nony.studentgradingsystem.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FacultyService {

	@Autowired
	private FacultyRepository facultyRepo;

	@Autowired
	private DepartmentRepository departmentRepo;

	public List<Faculty> listAll() {
		return (List<Faculty>) facultyRepo.findAll();
	}

	public List<Department> listDepartments() {
		return (List<Department>) departmentRepo.findAll();
	}

	public Faculty save(Faculty faculty) {
		return facultyRepo.save(faculty);
	}

	public String checkUnique(Integer id, String email) {
		boolean isCreatingNew = (id == null || id == 0);

		Faculty facultyByEmail = facultyRepo.findByEmail(email);

		if (isCreatingNew) {
			if (facultyByEmail != null) return "DuplicateEmail";
		} else {
			if (facultyByEmail != null && facultyByEmail.getId() != id) return "DuplicateEmail";
		}
		return "OK";
	}

	public Faculty get(Integer id) throws FacultyNotFoundException {
		try {
			return facultyRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new FacultyNotFoundException("Could not find any lecturer with ID " + id);
		}
	}

	public void delete(Integer id) throws FacultyNotFoundException {
		Long countById = facultyRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new FacultyNotFoundException("Could not find any lecturer with ID " + id);
		}

		facultyRepo.deleteById(id);
	}

	public void updateFacultyEnabledStatus(Integer id, boolean enabled) {
		facultyRepo.updateEnabledStatus(id, enabled);
	}
}
