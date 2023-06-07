package com.hostmdy.uni.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hostmdy.uni.domain.Teacher;
import com.hostmdy.uni.repository.StudentRepository;
import com.hostmdy.uni.repository.TeacherRepository;
import com.hostmdy.uni.service.ImageService;

import jakarta.transaction.Transactional;

@Service
public  class ImageServiceImpl implements ImageService {

	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;

	public ImageServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
		super();
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}

	@Override
	@Transactional
	public void saveImageToDb(Long id, MultipartFile file) {
		// TODO Auto-generated method stub
		if (id != null) {
			Teacher teacher = teacherRepository.findById(id).get();

			try {
				byte[] byteObjects = new byte[file.getBytes().length];

				int i = 0;
				for (final byte b : file.getBytes()) {
					byteObjects[i++] = b;
				}

				teacher.setImg(byteObjects);
				teacherRepository.save(teacher);

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
