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

import com.hostmdy.uni.domain.Club;
import com.hostmdy.uni.service.ClubService;

@Controller
@RequestMapping("/club")
public class ClubController {

	private final ClubService clubService;

	public ClubController(ClubService clubService) {
		super();
		this.clubService = clubService;
	}
	
	@GetMapping("/show")
	public String showClub(Model model) {
		List<Club> clubs = clubService.getClubs();
		model.addAttribute("clubs", clubs);
		
		return "club/club-show";
	}
	
	@GetMapping("/new")
	public String clubForm(Model model) {
		Club club = new Club();
		model.addAttribute("club", club);
		return "club/club-form";
	}
	
	@PostMapping("/new")
	public String createClub(@ModelAttribute("club")Club club) {
		Club createdClub = clubService.createdClub(club);
		return "redirect:/club/show";
	}
	
	@GetMapping("/update/{id}")
	public String updatedClub(Model model,@PathVariable Long id) {
		Optional<Club> clubOpt=clubService.getClubById(id);
		if(clubOpt.isPresent()) {
			Club club = clubOpt.get();
			model.addAttribute("club", club);
		}
		return "club/club-form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteClubById(@PathVariable Long id,Model model) {
		clubService.deleteClubById(id);
		return "redirect:/club/show";
	}
}
