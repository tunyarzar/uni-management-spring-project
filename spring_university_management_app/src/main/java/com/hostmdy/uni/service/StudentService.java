package com.hostmdy.uni.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.uni.domain.Student;

public interface StudentService {
	
	List<Student> getStudents();
	Optional<Student> getStudentById(Long id);
	
	Student createdStudent(Student student);
	
	void deleteStudentById(Long id);

}
