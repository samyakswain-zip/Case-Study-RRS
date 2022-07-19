package com.yashtech.rrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashtech.rrs.models.User;
import com.yashtech.rrs.services.UserService;

@RestController
public class TestController {

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring!";
	}

	@GetMapping("/profile")
	public ResponseEntity<?> profile() {
		// SecurityContextHolder : a helper class, which provide access to the security context.
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = this.userService.getUserByUsername(username);
		return ResponseEntity.ok(user);

	}
}
