package com.nony.studentgradingsystem.service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import com.nony.studentgradingsystem.entity.Subject;
import com.nony.studentgradingsystem.exception.SubjectNotFoundException;
import com.nony.studentgradingsystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepo;

	public List<Subject> listAll() {
		return (List<Subject>) subjectRepo.findAll();
	}

	public Subject save(Subject subject) {
		return subjectRepo.save(subject);
	}

	public String checkUnique(Integer id, String name, String code) {
		boolean isCreatingNew = (id == null || id == 0);

		Subject subjectByName = subjectRepo.findByName(name);
		Subject subjectByCode = subjectRepo.findByCode(code);

		if (isCreatingNew) {
			if (subjectByName != null || subjectByCode != null) return "DuplicateName";
		} else {
			if (subjectByName != null && subjectByName.getId() != id) {
				return "DuplicateName";
			} else if (subjectByCode != null && subjectByCode.getId() != id) {
				return "DuplicateName";
			}
		}

		return "OK";
	}

	public Subject get(Integer id) throws SubjectNotFoundException {
		try {
			return subjectRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new SubjectNotFoundException("Could not find any subject with ID " + id);
		}
	}

	public void delete(Integer id) throws SubjectNotFoundException {
		Long countById = subjectRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new SubjectNotFoundException("Could not find any subject with ID " + id);
		}

		subjectRepo.deleteById(id);
	}
}
