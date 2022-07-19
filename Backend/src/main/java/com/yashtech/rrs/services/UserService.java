package com.yashtech.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yashtech.rrs.models.User;
import com.yashtech.rrs.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// getting user by username
	public User getUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	// adding user 
	public User addUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
	}
	// checking if user is registered
	public boolean isRegisteredUser(String username) {
		return this.userRepository.existsByUsername(username);
	}
}
