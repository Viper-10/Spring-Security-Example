package com.springsecurity.example.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomStudentNotFoundException extends Exception {
	
	CustomStudentNotFoundException(){
		super("Student with given student id not found");
	}
	
	CustomStudentNotFoundException(String message){
		super(message); 
	}
}
