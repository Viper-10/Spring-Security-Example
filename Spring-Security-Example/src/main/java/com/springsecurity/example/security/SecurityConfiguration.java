package com.springsecurity.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

		/*
		If there are multiple User roles, then authentication by role becomes difficult, since we need to
		mention multiple users for each api end point. Instead permission based authentication is used.

		Those who have a particular permission can access the end point.
		 */
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
//				.antMatchers(HttpMethod.GET, "/management/api/**").hasAuthority("course:read")
				.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority("course:write")
				.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority("course:write")
				.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority("course:write")
				.antMatchers("/api/**").hasRole(STUDENT.name())

			.anyRequest().authenticated()
			.and().httpBasic();


	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		UserDetails user1 = User.builder().username("Priyadharshan")
		.password(passwordEncoder.encode("password"))
		// STUDENT.name() returns STUDENT(the name of the enum variable)
//		.roles(STUDENT.name())
				.authorities(STUDENT.getAuthorities())
		.build();
		
		UserDetails user2 = User.builder()
		.username("Sara Vijayakumar")
		.password(passwordEncoder.encode("P4ssword"))
//		.roles(ADMIN.name())
				.authorities(ADMIN.getAuthorities())
		.build();
		
		
		UserDetails user3 = User.builder()
				.username("Tom")
				.password(passwordEncoder.encode("Password"))
//				.roles(ADMIN_TRAINEE.name())
				.authorities(ADMIN_TRAINEE.getAuthorities())
				.build();		
		
		return new InMemoryUserDetailsManager(user1, user2, user3);
	}	
}
