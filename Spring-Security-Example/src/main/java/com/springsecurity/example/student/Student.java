package com.springsecurity.example.student;

public class Student {
	public String studentName; 
	public int studentId;
	
	public Student() {
		
	}
	
	public Student(String studentName, int studentId) {
		this.studentName = studentName;
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", studentId=" + studentId + "]";
	} 
	
	
}
