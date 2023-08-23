package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.model.File;

@Service
public class FileService {
    
    @Autowired
    FileDao fileDao;

    public File getById(String fileId){
        return fileDao.getById(File.class,fileId);
    }
}
