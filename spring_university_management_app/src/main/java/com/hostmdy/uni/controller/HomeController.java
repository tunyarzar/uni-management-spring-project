package com.hostmdy.uni.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping({"","/index","/"})
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping({"/login"})
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping({"/signup"})
	public String singnup(Model model) {
		return "signup";
	}

}
