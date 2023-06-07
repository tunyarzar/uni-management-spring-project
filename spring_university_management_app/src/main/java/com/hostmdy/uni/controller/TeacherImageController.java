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

import com.hostmdy.uni.domain.Teacher;
import com.hostmdy.uni.service.ImageService;
import com.hostmdy.uni.service.TeacherService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TeacherImageController {

	private final TeacherService teacherService;
	private final ImageService imageService;
	public TeacherImageController(TeacherService teacherService, ImageService imageService) {
		super();
		this.teacherService = teacherService;
		this.imageService = imageService;
	}
	
	
	@GetMapping("/teacher/{id}/image")
	public String showImageForm(@PathVariable Long id, Model model) {
		model.addAttribute("id",id);
		return "teacher/image-upload";
	}
	
	@PostMapping("/teacher/{id}/image")
	public String submitImage(@PathVariable Long id,@RequestParam MultipartFile imagefile) {
		imageService.saveImageToDb(id, imagefile);
		
		return "redirect:/teacher/details/"+id;
	}
	
	@GetMapping("/teacher/{id}/teacherImage")
	public void rederImageFromDB(@PathVariable Long id, HttpServletResponse response) {
		Optional<Teacher> teacherOpt = teacherService.getTeacherById(id);
		
		if(teacherOpt.isPresent()) {
			Teacher teacher = teacherOpt.get();
			
			if(teacher.getImg() != null) {
			byte[] byteArray = new byte[teacher.getImg().length];
			
			int i = 0;
			
			for(final byte b : teacher.getImg()) {
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
			System.out.println("$$$$ Recipe is null");
		}
		
	
	}

	
}
