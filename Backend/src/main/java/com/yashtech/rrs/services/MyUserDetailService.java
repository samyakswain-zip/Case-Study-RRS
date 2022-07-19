package com.yashtech.rrs.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yashtech.rrs.models.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailService implements UserDetailsService {

	private final UserService userService;

	public MyUserDetailService(UserService userService) {
		this.userService = userService;
	}

	@Override
	// loading user
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found in database");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getUserRole()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}
}
