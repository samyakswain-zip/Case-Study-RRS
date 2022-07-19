package com.yashtech.rrs.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yashtech.rrs.models.AuthenticationRequest;
import com.yashtech.rrs.models.User;
import com.yashtech.rrs.services.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//SLF4J stands for Simple Logging Facade for Java
@Slf4j
public class AuthController {

	@Autowired
	// container for authentication providers, giving a consistent interface to them
	// all
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	// represents an HTTP response, including headers, body, and status
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			log.error("Invalid Login Credentials: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid login credentials", e);
		}

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("mylittlesecret".getBytes());
		// JWT : JWT is a token format
		String accessToken = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000)).withIssuer("RRS")
				.withClaim("roles",
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);

		Map<String, Object> data = new HashMap<>();
		data.put("token", accessToken);

		return ResponseEntity.ok(data);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		User savedUser = null;

		try {

			System.out.println("user = " + user);
			if (this.userService.isRegisteredUser(user.getUsername())) {
				System.out.println("User already registered in the system");
			} else {
				user.setUserRole("ROLE_CUSTOMER");
				savedUser = this.userService.addUser(user);
			}
			log.info("User Created Successfully!");
			return ResponseEntity.ok(savedUser);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
		}
	}
}
