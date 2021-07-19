package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.repository.StudentRepository;
@SpringBootTest
class studentServiceMocking {

	@Autowired
	private StudentService service;
	
	@Autowired
	private ApplicationContext context;
	@MockBean
	private StudentRepository repository;
	@Test
	void testFindStudentByIdShouldReturnStudent() throws NoSuchStudentException {
		
		
		Student expected=context.getBean(Student.class);
		expected.setStudentId(1);
		expected.setStudentName("Test");
		expected.setStudentScore(40);
		
		expected.getHomeAddress().setAddressId(1);
		expected.getHomeAddress().setCity("Test");
		expected.getHomeAddress().setPincode("000000");
		//setting up  expectation when you call methods
		when(repository.existsById(expected.getStudentId())).thenReturn(true);
		Optional<Student>expectation=Optional.of(expected);
		when(repository.findById(expected.getStudentId())).thenReturn(expectation);
		
		//actual method call which we are testing
		Student actual = service.findStudentById(expected.getStudentId());
		
		//assertEquals(expected, actual);
		assertEquals(expected.getStudentId(), actual.getStudentId());
		assertEquals(expected.getStudentName(), actual.getStudentName());
		assertEquals(expected.getStudentScore(), actual.getStudentScore());
		assertEquals(expected.getHomeAddress().getCity(), actual.getHomeAddress().getCity());
		assertEquals(expected.getHomeAddress().getPincode(), actual.getHomeAddress().getPincode());
		
		service.removeStudentById(expected.getStudentId());
		
	
	}
	void testFindStudentByIdShouldThrowNoSuchStudentException() {
		int studentId=-1;
		when(repository.existsById(studentId)).thenReturn(false);
		assertThrows(NoSuchStudentException.class,()->service.findStudentById(studentId));
	}

}
