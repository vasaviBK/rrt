package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.service.StudentService;

@RestController
@RequestMapping(path = "students")
public class StudentController {
	@Autowired
	private StudentService service;
	
	//http://localhost:9090/student-api/students/ - Post
	@PostMapping(path = "/")
	public ResponseEntity<String> saveStudent(@RequestBody Student student) {
		ResponseEntity<String> response = null;
		boolean result = service.addStudent(student);
		if(result)
			response = new ResponseEntity<String>("Student with id "+student.getStudentId()+" is added.", HttpStatus.CREATED);
		return response;
	}
	
	//http://localhost:9090/student-api/students/10
	@GetMapping(path = "{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId") int studentId) throws NoSuchStudentException {
		ResponseEntity<Student> response = null;
		Student student = service.findStudentById(studentId);
		response = new ResponseEntity<Student>(student, HttpStatus.OK);
		return response;
	}
	
	//http://machine-name:port-number/context-path/path-class/path-method
	//http://localhost:9090/student-api/students/ - Get
	@GetMapping(path = "/")
	public ResponseEntity<List<Student>> getAllStudents() {
		ResponseEntity<List<Student>> response = null;
		List<Student> list = service.findAllStudents();
		response = new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		return response;
	}
	//http://localhost:9090/student-api/students/50/70  -Get
	@GetMapping(path = "{min}/{max}")
	public ResponseEntity<List<Student>> getAllStudentByScore(@PathVariable("min") double min, @PathVariable("max")double max) {
		ResponseEntity<List<Student>> response = null;
		List<Student> list = service.findAllStudentsByScore(min, max);
		response = new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		return response;
	}
	//http://localhost:9090/student-api/students/byName/Tej  -Get
	@GetMapping(path = "byName/{studentName}")
	public ResponseEntity<List<Student>> getAllStudentByName(@PathVariable("studentName") String studentName){
		ResponseEntity<List<Student>> response = null;
		List<Student> list = service.findAllStudentByName(studentName);
		response = new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		return response;
	}
	
	
	//Handling the exception
	//@ExceptionHandler(value = NoSuchStudentException.class)
	//@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Student Not Found.")
	//public void handleException(NoSuchStudentException e) {
		
	//}
}
