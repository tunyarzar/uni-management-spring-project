package com.hostmdy.uni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.uni.domain.Student;
import com.hostmdy.uni.domain.Teacher;
import com.hostmdy.uni.service.StudentService;
import com.hostmdy.uni.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	private final TeacherService teacherService;
	private final StudentService studentService;
	
	public TeacherController(TeacherService teacherService, StudentService studentService) {
		super();
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@GetMapping("/show")
	public String showTeacherList(Model model) {
		List<Teacher> teachers = teacherService.getTeachers();
		model.addAttribute("teachers", teachers);
		
		return "teacher/teacher-list";
				
	}
	
	@GetMapping("/new")
	public String teacherForm(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "teacher/teacher-form";
	}
	
	@PostMapping("/new")
	public String submitForm(@ModelAttribute("teacher")Teacher teacher) {
		Teacher createdTeacher = teacherService.createdTeacher(teacher);
		return "redirect:/teacher/show";
	}
	
	@GetMapping("/update/{id}")
	public String updateTeacher(Model model,@PathVariable Long id) {
		Optional<Teacher> teacherOpt = teacherService.getTeacherById(id);
		
		if(teacherOpt.isPresent()) {
			Teacher teacher = teacherOpt.get();
			model.addAttribute("teacher", teacher);
		}
		
		return "teacher/teacher-form";
	}
	
	@GetMapping("/details/{id}")
	public String showTeacherDetails(Model model,@PathVariable Long id) {
		Optional<Teacher> teacherOpt = teacherService.getTeacherById(id);
		
		if(teacherOpt.isPresent()) {
			Teacher teacher = teacherOpt.get();
			model.addAttribute("teacher", teacher);
		}
		
		return "teacher/teacher-details";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacherById(id);
		return "redirect:/teacher/show";
	}
}
