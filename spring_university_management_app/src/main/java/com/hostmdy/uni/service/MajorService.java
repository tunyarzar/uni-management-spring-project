package com.hostmdy.uni.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.uni.domain.Major;

public interface MajorService {
	
	List<Major> getMajors();
	Optional<Major> getMajorById(Long id);
	
	Major createdMajor(Major Major);
	
	void deleteMajorById(Long id);

}
