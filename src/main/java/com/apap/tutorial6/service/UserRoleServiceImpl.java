package com.apap.tutorial6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		// TODO Auto-generated method stub
		if (user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
			String pass = encrypt(user.getPassword());
			user.setPassword(pass);
			return userDb.save(user);
		} else return null;
	}

	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
	@Override
	public UserRoleModel getUserByUsername(String username) {
		return userDb.findByUsername(username);
	}
	
	@Override
	public UserRoleModel getUserById(long id) {
		return userDb.findById(id).get();
	}

}
