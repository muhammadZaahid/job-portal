package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.model.File;

@Service
public class FileService {

	@Autowired
	FileDao fileDao;
	
	public File getById(String fileId) {
		return fileDao.getById(File.class, fileId);
	}
}
