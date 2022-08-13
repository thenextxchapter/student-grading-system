package com.nony.studentgradingsystem.repository;

import com.nony.studentgradingsystem.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
