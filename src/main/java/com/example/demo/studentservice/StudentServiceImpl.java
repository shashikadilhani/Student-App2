package com.example.demo.studentservice;

import java.util.List;
import java.util.Optional;

//import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.studentdao.StudentRepository;
import com.example.demo.studententity.Student;
 

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository theStuedntReposiroty) {
		studentRepository = theStuedntReposiroty;
	}
	@Override
	public List<Student> findAll() {

		return studentRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Student findById(int theId) {
		 
		Optional<Student> result = studentRepository.findById(theId);
		
		Student theStudent = null;
		
		if(result.isPresent()) {theStudent=result.get();}
		
		else {
			throw new RuntimeException("din not found");
		}
		
		return theStudent;
	}

	@Override
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
		
	}

	@Override
	public void deleteById(int theId) {
		 studentRepository.deleteById(theId);
		
	}
	
	 
}
