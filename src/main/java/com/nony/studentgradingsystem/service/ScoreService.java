package com.nony.studentgradingsystem.service;

import javax.transaction.Transactional;

import java.util.List;

import com.nony.studentgradingsystem.entity.Score;
import com.nony.studentgradingsystem.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ScoreService {

	@Autowired
	ScoreRepository repo;

	public List<Score> listAll() {
		return (List<Score>) repo.findAll();
	}
}
