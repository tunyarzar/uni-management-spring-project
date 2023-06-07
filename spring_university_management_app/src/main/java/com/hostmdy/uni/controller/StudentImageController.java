package com.hostmdy.uni.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.uni.domain.Student;
import com.hostmdy.uni.domain.Teacher;
import com.hostmdy.uni.service.ImageService;
import com.hostmdy.uni.service.StudentImageService;
import com.hostmdy.uni.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentImageController {

	private final StudentService studentService;
	private final StudentImageService studentImageService;
	
	public StudentImageController(StudentService studentService, StudentImageService studentImageService) {
		super();
		this.studentService = studentService;
		this.studentImageService = studentImageService;
	}
	
	@GetMapping("/student/{id}/image")
	public String showImageForm(@PathVariable Long id, Model model) {
		model.addAttribute("id",id);
		return "student/image-upload";
	}
	
	@PostMapping("/student/{id}/image")
	public String submitImage(@PathVariable Long id,@RequestParam MultipartFile imagefile) {
		studentImageService.saveStudentToDb(id, imagefile);
		
		return "redirect:/student/details/"+id;
	}
	
	@GetMapping("/student/{id}/studentImage")
	public void rederImageFromDB(@PathVariable Long id, HttpServletResponse response) {
		Optional<Student> studentOpt = studentService.getStudentById(id);
		
		if(studentOpt.isPresent()) {
			Student student = studentOpt.get();
			
			if(student.getImage() != null) {
			byte[] byteArray = new byte[student.getImage().length];
			
			int i = 0;
			
			for(final byte b : student.getImage()) {
				byteArray[i++] = b;
			}
			
			try {
				InputStream ls = new ByteArrayInputStream(byteArray);
				response.setContentType("image/jpeg");
				
				IOUtils.copy(ls, response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

		}else {
			System.out.println("$$$$ Studnet is null");
		}
		
	
	}

	
}
