package com.example.demo.studentservice;

import java.util.List;

 

import com.example.demo.studententity.Student;
 
public interface StudentService {

	public List<Student> findAll();
	
	public Student findById(int theId);
	
	public void save(Student theStudent);
	
	public void deleteById(int theId);
	
	 
	
}
