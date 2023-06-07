package com.hostmdy.uni.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void saveImageToDb(Long id,MultipartFile  file);
	
}
