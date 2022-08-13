package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
