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
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.uni.domain.Club;
import com.hostmdy.uni.domain.Major;
import com.hostmdy.uni.domain.Student;
import com.hostmdy.uni.domain.Teacher;
import com.hostmdy.uni.service.ClubService;
import com.hostmdy.uni.service.MajorService;
import com.hostmdy.uni.service.StudentService;
import com.hostmdy.uni.service.TeacherService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private final StudentService studentService;
	private final TeacherService teacherService;
	private final MajorService majorService;
	private final ClubService clubService;
	
	public StudentController(StudentService studentService, TeacherService teacherService, MajorService majorService,
			ClubService clubService) {
		super();
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.majorService = majorService;
		this.clubService = clubService;
	}

	@GetMapping("/show")
	public String showStudent(Model model) {
		List<Student> students = studentService.getStudents();
		model.addAttribute("students", students);
		
		return "student/student-list";
	}
	
	@GetMapping("/new")
	public String studentForm(Model model) {
		List<Major> majors = majorService.getMajors();
		List<Club> clubs = clubService.getClubs();
		Student student = new Student();
		
		model.addAttribute("student", student);
		model.addAttribute("clubs", clubs);
		model.addAttribute("majors", majors);
		return "student/student-form";
	}

	@PostMapping("/new")
	public String subbmitForm(@ModelAttribute("student")Student student,@RequestParam Long majorId,@RequestParam Long clubId) {
		Major major = majorService.getMajorById(majorId).get();
		Club club = clubService.getClubById(clubId).get();
		student.setMajor(major);
		student.setClubs(club);
		
		Student createdStudent = studentService.createdStudent(student);
		
		return "redirect:/student/show";
	}
	
	@GetMapping("/details/{id}")
	public String showStudentDetails(Model model,@PathVariable Long id) {
		
		Optional<Student> studentOpt = studentService.getStudentById(id);
		
		if(studentOpt.isPresent()) {
			Student student = studentOpt.get();
			model.addAttribute("student", student);
			
		}
		
		return "student/student-details";
	}
	
	@GetMapping("/update/{id}")
	public String updateStudent(Model model,@PathVariable Long id) {
		List<Major> majors = majorService.getMajors();
		List<Club> clubs = clubService.getClubs();
		Optional<Student> studentOpt = studentService.getStudentById(id);
		
		if(studentOpt.isPresent()) {
			Student student = studentOpt.get();
			model.addAttribute("student", student);
			model.addAttribute("clubs", clubs);
			model.addAttribute("majors", majors);
		}
		
		return "student/student-form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/student/show";
	}
}
