package com.hostmdy.uni.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.uni.domain.Teacher;

public interface TeacherService {

	List<Teacher> getTeachers();
	Optional<Teacher> getTeacherById(Long id);
	
	Teacher createdTeacher(Teacher teacher);
	
	void deleteTeacherById(Long id);
}
