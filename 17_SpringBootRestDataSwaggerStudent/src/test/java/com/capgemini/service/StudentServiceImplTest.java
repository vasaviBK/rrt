package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;

@SpringBootTest
class StudentServiceImplTest {
	
	@Autowired
	private StudentService service;
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	void testFindStudentByIdShouldReturnStudent() throws NoSuchStudentException {
		Student expected = context.getBean(Student.class);
		expected.setStudentName("Test");
		expected.setStudentScore(40);
		expected.getHomeAddress().setCity("Test");
		expected.getHomeAddress().setPincode("000000");
		
		service.addStudent(expected);
		Student actual = service.findStudentById(expected.getStudentId());
		
		//assertEquals(expected, actual);
		assertEquals(expected.getStudentId(), actual.getStudentId());
		assertEquals(expected.getStudentName(), actual.getStudentName());
		assertEquals(expected.getStudentScore(), actual.getStudentScore());
		assertEquals(expected.getHomeAddress().getCity(), actual.getHomeAddress().getCity());
		assertEquals(expected.getHomeAddress().getPincode(), actual.getHomeAddress().getPincode());
		
		service.removeStudentById(expected.getStudentId());
	}

}
