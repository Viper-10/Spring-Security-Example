package com.springsecurity.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static com.springsecurity.example.security.UserRoles.*; 

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	PasswordEncoder passwordEncoder; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "index", "/css/*", "/js/*")
			.permitAll()
			.antMatchers("/api/**")
			.hasRole(STUDENT.name())
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();		
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		
		UserDetails user1 = User.builder().username("Priyadharshan")
		.password(passwordEncoder.encode("password"))
		// STUDENT.name() returns STUDENT(the name of the enum variable)
		.roles(STUDENT.name())
		.build();
		
		UserDetails user2 = User.builder()
		.username("Sara Vijayakumar")
		.password(passwordEncoder.encode("P4ssword"))
		.roles(ADMIN.name())
		.build();
		
		
		UserDetails user3 = User.builder()
				.username("Tom")
				.password(passwordEncoder.encode("Password"))
				.roles(ADMIN_TRAINEE.name())
				.build();		
		
		return new InMemoryUserDetailsManager(user1, user2, user3);
	}	
}
