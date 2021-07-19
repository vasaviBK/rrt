package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;

public interface StudentService {
	public boolean addStudent(Student student);
	public Student findStudentById(int studentId)throws NoSuchStudentException;
	public List<Student> findAllStudents();
	public List<Student> findAllStudentByName(String name);
	public List<Student> findAllStudentsByScore(double min, double max);
	public boolean removeStudentById(int studentId) throws NoSuchStudentException;
}
