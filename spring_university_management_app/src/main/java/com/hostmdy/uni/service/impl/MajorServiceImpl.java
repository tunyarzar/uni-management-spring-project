package com.hostmdy.uni.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.uni.domain.Major;
import com.hostmdy.uni.repository.MajorRepository;
import com.hostmdy.uni.service.MajorService;

@Service
public class MajorServiceImpl implements MajorService {
	
	private final MajorRepository majorRepository;
	

	public MajorServiceImpl(MajorRepository majorRepository) {
		super();
		this.majorRepository = majorRepository;
	}

	@Override
	public List<Major> getMajors() {
		// TODO Auto-generated method stub
		return (List<Major>) majorRepository.findAll();
	}

	@Override
	public Optional<Major> getMajorById(Long id) {
		// TODO Auto-generated method stub
		return majorRepository.findById(id);
	}

	@Override
	public Major createdMajor(Major major) {
		// TODO Auto-generated method stub
		return majorRepository.save(major);
	}

	@Override
	public void deleteMajorById(Long id) {
		// TODO Auto-generated method stub
		majorRepository.deleteById(id);
	}

}
