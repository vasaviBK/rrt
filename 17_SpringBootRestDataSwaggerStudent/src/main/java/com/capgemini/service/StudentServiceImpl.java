package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.repository.StudentRepository;

@Service("service")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository repository;
	
	@Override
	@Transactional //CMT
	public boolean addStudent(Student student) {
		boolean result = false;
		student = repository.save(student);
		if(student.getStudentId() > 0)
			result = true;
		return result;
	}

	@Override
	public Student findStudentById(int studentId) throws NoSuchStudentException {
		if(repository.existsById(studentId)) {
			return repository.findById(studentId).get();
		}
		throw new NoSuchStudentException("Student with id "+studentId+" not found.");
	}

	@Override
	public List<Student> findAllStudents() {
		return repository.findAll();
	}

	@Override
	public List<Student> findAllStudentByName(String name) {
		return repository.readAllName(name);
	}

	@Override
	public List<Student> findAllStudentsByScore(double min, double max) {
		return repository.readAllByScore(min, max);
	}

	@Override
	public boolean removeStudentById(int studentId) throws NoSuchStudentException {
		if(repository.existsById(studentId)) {
			repository.deleteById(studentId);
			return true;
		}
		throw new NoSuchStudentException("Student with id "+studentId+" not found.");
	}

}
