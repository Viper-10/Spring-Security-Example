package com.springsecurity.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderImpl {
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder(10); 
	}
}
