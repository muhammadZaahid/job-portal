package com.lawencon.admin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AssessmentDao;
import com.lawencon.admin.dao.ProfileDao;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.assessment.AssessmentResDto;
import com.lawencon.admin.dto.assessment.AssessmentUpdateReqDto;
import com.lawencon.admin.model.Assessment;
import com.lawencon.base.ConnHandler;
import com.lawencon.util.DateUtil;

@Service
public class AssessmentService {
    @Autowired
    AssessmentDao assessmentDao;
    @Autowired
    ProfileDao profileDao;

    public AssessmentResDto getByApplicantId(String applicantId) {
        final AssessmentResDto response = new AssessmentResDto();

        Assessment assessment = assessmentDao.getByApplicantId(applicantId);

        response.setPicId(assessment.getAssessmentPic().getId());
        response.setPicName(assessment.getAssessmentPic().getProfile().getName());
        response.setPicPhone(assessment.getAssessmentPic().getProfile().getPhone());
        response.setPicEmail(assessment.getAssessmentPic().getEmail());
        response.setAssessmentNote(assessment.getAssessmentNote());
        response.setAssessmentScore(assessment.getAssessmentScore());
        response.setId(assessment.getId());
        try{
            response.setAssessmentTime(DateUtil.parseLocalDateTimeToDate(assessment.getAssessmentTime()));
        }catch(Exception e){
            response.setAssessmentTime(null);
        }

        return response;
    }

    @Transactional
    public UpdateResDto updateScoreAndNotes(String applicantId, AssessmentUpdateReqDto data){
        ConnHandler.begin();
        final UpdateResDto response = new UpdateResDto();
        if(data.getAssessmentScore() < 0 || data.getAssessmentScore() > 100){
            throw new RuntimeException("Masukkan nilai yang valid!");
        }
        try{
            Assessment assessment = assessmentDao.getById(Assessment.class,applicantId);
        assessment.setAssessmentNote(data.getAssessmentNotes());
        assessment.setAssessmentScore(data.getAssessmentScore());
        Assessment updatedAssessment = assessmentDao.saveAndFlush(assessment);

        ConnHandler.commit();
        response.setVer(updatedAssessment.getVersion());
        response.setMessage("Success Update Assessment Data!");

        }catch(Exception e){
            ConnHandler.rollback();
            throw new RuntimeException("Update Score & Notes Failed!");
        }
        
        return response;
    }
}
