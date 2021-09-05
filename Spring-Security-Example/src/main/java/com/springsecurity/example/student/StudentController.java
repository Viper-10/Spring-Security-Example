package com.springsecurity.example.student;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	
	public static final List<Student> students = new LinkedList<>(); 
	
	static {
		students.add(new Student("Priyadharshan", 1));
		students.add(new Student("Sara Vijayakumar", 2));
		students.add(new Student("Theka", 3));
	}
	
	@GetMapping("{studentId}")
	public Student handleStudentQuery(@PathVariable("studentId") int studentId) throws CustomStudentNotFoundException{
		for(Student s : students) {
			if(s.getStudentId() == studentId) return s; 
		}
		
		throw new CustomStudentNotFoundException(); 
	}
}
