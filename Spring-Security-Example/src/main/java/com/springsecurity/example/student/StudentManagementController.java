package com.springsecurity.example.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final List<Student> students = Arrays.asList(
            new Student("Priyadharshan", 1),
		new Student("Sara Vijayakumar", 2),
		new Student("Theka", 3)
    );

    @GetMapping
    public List<Student> getStudents(){
        logger.info("GET MAPPING REQUEST");
        return students;
    }

    @PostMapping
    public Student postStudent(@RequestBody Student student){
        logger.info("POST MAPPING REQUEST");
        return student;
    }

    @PutMapping
    public Student putStudent(@RequestBody Student student){
        logger.info("PUT MAPPING REQUEST");
        return student;
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable(name = "studentId") int studentId){
        logger.info("DELETE MAPPING REQUEST");
    }
}
