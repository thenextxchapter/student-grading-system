package com.nony.studentgradingsystem.service;

import java.util.List;

import com.nony.studentgradingsystem.entity.Role;
import com.nony.studentgradingsystem.entity.User;
import com.nony.studentgradingsystem.repository.RoleRepository;
import com.nony.studentgradingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	public List<User> listAll() {
		return (List<User>) userRepo.findAll();
	}

	public List<Role> listRoles(){
		return (List<Role>) roleRepo.findAll();
	}
}
