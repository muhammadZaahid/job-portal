package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dao.MedicalDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.medical.MedicalInsertReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.File;
import com.lawencon.admin.model.Medical;
import com.lawencon.base.ConnHandler;
import com.lawencon.util.DateUtil;

@Service
public class MedicalService {
    
    @Autowired
    MedicalDao medicalDao;
    @Autowired
    ApplicantDao applicantDao;
    @Autowired
    FileDao fileDao;

    public InsertResDto insert(MedicalInsertReqDto data){
        ConnHandler.begin();
        final InsertResDto response = new InsertResDto();
        System.out.println(data.getApplicantId());
        Applicant applicant = applicantDao.getById(Applicant.class,data.getApplicantId());

        Medical medical = new Medical();
        medical.setApplicantId(applicant);
        medical.setMedicalDate(DateUtil.parseToDateOnly(data.getMedicalDate()));
        medical.setMedicalLocation(data.getMedicalLocation());
        medical.setMedicalNotes(data.getMedicalNotes());
        
        if(data.getMedicalFile() != null){
            File file = new File();
            file.setFiles(data.getMedicalFile().getFiles());
            file.setFileFormat(data.getMedicalFile().getFileFormat());
            File createdFile = fileDao.save(file);
            medical.setMedicalFile(createdFile);
        }

        Medical createdMedical = medicalDao.save(medical);

        if(createdMedical != null){
            ConnHandler.commit();
            response.setId(createdMedical.getId());
            response.setMessage("Success Insert Medical Check-up Candidate!");
        }else{
            ConnHandler.rollback();
            throw new RuntimeException();
        }

        return response;
    }
}
