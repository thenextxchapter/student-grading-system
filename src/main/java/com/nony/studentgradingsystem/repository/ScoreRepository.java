package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Integer> {

}
