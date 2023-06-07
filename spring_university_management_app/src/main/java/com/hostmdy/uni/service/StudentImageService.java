package com.hostmdy.uni.service;

import org.springframework.web.multipart.MultipartFile;

public interface StudentImageService {

	void saveStudentToDb(Long id,MultipartFile  file);
}
