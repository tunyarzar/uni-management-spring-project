package com.hostmdy.uni.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.uni.domain.Teacher;
import com.hostmdy.uni.repository.TeacherRepository;
import com.hostmdy.uni.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	private final TeacherRepository teacherRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<Teacher> getTeachers() {
		// TODO Auto-generated method stub
		return (List<Teacher>) teacherRepository.findAll();
	}

	@Override
	public Optional<Teacher> getTeacherById(Long id) {
		// TODO Auto-generated method stub
		return teacherRepository.findById(id);
	}

	@Override
	public Teacher createdTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacherById(Long id) {
		// TODO Auto-generated method stub
		teacherRepository.deleteById(id);
	}

}
