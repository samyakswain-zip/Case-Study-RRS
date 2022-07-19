package com.yashtech.rrs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yashtech.rrs.filters.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	// Calling of PasswordEncoder that uses the BCrypt strong hashing function
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	// To enable HTTP Security in Spring
	protected void configure(HttpSecurity http) throws Exception {
		// disables Cross-Site Request Forgery.
		http.csrf().disable();
		// Specifies the various session creation policies for Spring Security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login/**", "/register", "/hello").permitAll();
		http.authorizeRequests().anyRequest().authenticated();

		// add filter before
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	@Bean
	// container for authentication providers, giving a consistent interface to them
	// all
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
