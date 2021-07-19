package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	public List<Student> readStudentsByStudentName(String studentName);
	
	@Query(value = "Select s From Student s Where s.studentName = :name")
	public List<Student> readAllName(@Param("name") String studentName);

	@Query(value = "Select s From Student s Where s.studentScore Between :min And :max")
	public List<Student> readAllByScore(@Param("min") double min,@Param("max") double max);
}
