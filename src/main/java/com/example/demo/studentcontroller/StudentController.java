package com.example.demo.studentcontroller;

//import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

//import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.studententity.Student;
import com.example.demo.studentservice.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	/*private List<Student> theStudents;
	
	@PostConstruct
	private void loadData() {
		
		// create Students
	Student stu1 = new Student(1, "Leslie", "Andrews", "leslie@luv2code.com");
	Student stu2 = new Student(2, "Emma", "Baumgarten", "emma@luv2code.com");
	Student stu3 = new Student(3, "Avani", "Gupta", "avani@luv2code.com");

		// create the list
		theStudents = new ArrayList<>();
		
		// add to the list
		theStudents.add(stu1);
		theStudents.add(stu2);
		theStudents.add(stu3);
	
	}*/
	
	private StudentService studentService;
	
	public  StudentController(StudentService theStuedntService) {
		
		studentService = theStuedntService;
	}
	
	@GetMapping("/list")
	public String listStuednts(Model theModel) {
		
		 List<Student> theStudents = studentService.findAll();
		 theModel.addAttribute("students", theStudents);
		 
		return  "students/list-students";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		
		theModel.addAttribute("student", theStudent);
		
		return "students/student-form";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
									Model theModel) {
		
		// get the student from the service
		Student theStudent = studentService.findById(theId);
		
		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("student", theStudent);
		
		// send over to our form
		return "students/student-form";			
	}
	
	@PostMapping("/save")
	public String saveStudent(
			@ModelAttribute("student") @Valid Student theStudent,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "students/student-form";
		}
		else {		
			// save the student
			studentService.save(theStudent);
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/students/list";
		}
	}
	@GetMapping("/delete")
public String delete(@RequestParam("studentId") int theId) {
		
		// delete the student
		studentService.deleteById(theId);
		
		// redirect to /students/list
		return "redirect:/students/list";
		
	}
	
	

}
