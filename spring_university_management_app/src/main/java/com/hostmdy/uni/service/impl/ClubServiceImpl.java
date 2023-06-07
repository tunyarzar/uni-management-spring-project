package com.hostmdy.uni.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.uni.domain.Club;
import com.hostmdy.uni.repository.ClubRepository;
import com.hostmdy.uni.service.ClubService;

@Service
public class ClubServiceImpl implements ClubService{
	
	private final ClubRepository clubRepository;
	

	public ClubServiceImpl(ClubRepository clubRepository) {
		super();
		this.clubRepository = clubRepository;
	}

	@Override
	public List<Club> getClubs() {
		// TODO Auto-generated method stub
		return (List<Club>) clubRepository.findAll();
	}

	@Override
	public Optional<Club> getClubById(Long id) {
		// TODO Auto-generated method stub
		return clubRepository.findById(id);
	}

	@Override
	public Club createdClub(Club club) {
		// TODO Auto-generated method stub
		return clubRepository.save(club);
	}

	@Override
	public void deleteClubById(Long id) {
		// TODO Auto-generated method stub
		clubRepository.deleteById(id);
	}

}
