package com.hostmdy.uni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.uni.domain.Major;
import com.hostmdy.uni.service.MajorService;

@Controller
@RequestMapping("/major")
public class MajorController {
	
	private final MajorService majorService;

	public MajorController(MajorService majorService) {
		super();
		this.majorService = majorService;
	}
	
	@GetMapping("/show")
	public String showMajor(Model model) {
		List<Major> majors = majorService.getMajors();
		model.addAttribute("majors",majors);
		
		return "major/major-show";
	}
	
	@GetMapping("/new")
	public String majorForm(Model model) {
		Major major =new Major();
		model.addAttribute("major", major);
		return "major/major-form";
	}

	@PostMapping("/new")
	public String subbmitForm(@ModelAttribute("major")Major major) {
		Major createdMajor = majorService.createdMajor(major);
		return "redirect:/major/show";
	}
	
	@GetMapping("/update/{id}")
	public String updatedMajor(Model model,@PathVariable Long id) {
		Optional<Major> majorOpt = majorService.getMajorById(id);
		
		if(majorOpt.isPresent()) {
		Major major =majorOpt.get();
		model.addAttribute("major", major);
		}
		
		return "major/major-form";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteMajorById(@PathVariable Long id,Model model) {
		majorService.deleteMajorById(id);
		return "redirect:/major/show";
		
	}
}
