package com.example.demo.studentdao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.studententity.Student;
 

public interface StudentRepository extends JpaRepository<Student, Integer> {

	// add a method to sort by last name
		public List<Student> findAllByOrderByLastNameAsc();
		
		 
}
