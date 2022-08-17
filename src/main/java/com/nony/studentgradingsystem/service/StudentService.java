package com.nony.studentgradingsystem.service;

import javax.transaction.Transactional;

import java.util.List;

import com.nony.studentgradingsystem.entity.Student;
import com.nony.studentgradingsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

	@Autowired
	StudentRepository repo;

	public List<Student> listAll() {
		return (List<Student>) repo.findAll();
	}
}
