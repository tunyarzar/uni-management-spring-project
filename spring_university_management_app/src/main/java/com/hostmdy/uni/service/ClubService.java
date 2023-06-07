package com.hostmdy.uni.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.uni.domain.Club;

public interface ClubService {
	
	List<Club> getClubs();
	Optional<Club> getClubById(Long id);
	
	Club createdClub(Club club);
	
	void deleteClubById(Long id);

}
