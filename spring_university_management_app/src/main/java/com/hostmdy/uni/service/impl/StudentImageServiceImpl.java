package com.hostmdy.uni.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.uni.domain.Student;
import com.hostmdy.uni.repository.StudentRepository;
import com.hostmdy.uni.service.StudentImageService;

import jakarta.transaction.Transactional;

@Service
public class StudentImageServiceImpl implements StudentImageService {
	
	private final StudentRepository studentRepository;

	public StudentImageServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	@Transactional
	public void saveStudentToDb(Long id, MultipartFile file) {
		// TODO Auto-generated method stub
		if (id != null) {
			Student student = studentRepository.findById(id).get();

			try {
				byte[] byteObjects = new byte[file.getBytes().length];

				int i = 0;
				for (final byte b : file.getBytes()) {
					byteObjects[i++] = b;
				}

				student.setImage(byteObjects);
				studentRepository.save(student);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(" #### Error Occurs!");
			}

		} else {
			System.out.println("###### RecipeId is null");
		}
	}
	}


