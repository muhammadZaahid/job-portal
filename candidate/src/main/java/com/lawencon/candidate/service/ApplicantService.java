package com.lawencon.candidate.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.ApplicantDao;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.candidate.model.Applicant;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.JobVacancy;

@Service
public class ApplicantService {
    @Autowired
    ApplicantDao applicantDao;
    @Autowired
    CandidateDao candidateDao;
    @Autowired
    JobVacancyDao jobVacancyDao;
    @Autowired
    RestTemplate restTemplate;

    public InsertResDto createApplicant(ApplicantInsertReqDto request) {
		ConnHandler.begin();
		InsertResDto response = new InsertResDto();
		
		Applicant applicant = new Applicant();
		Candidate candidate = candidateDao.getById(Candidate.class, request.getCandidateId());
		applicant.setCandidate(candidate);		
		JobVacancy jobVacancy = jobVacancyDao.getById(JobVacancy.class, request.getJobVacancyId());
		applicant.setJobVacancy(jobVacancy);
		applicant.setCurrentStage("application");
		applicant.setStgApplication(true);
		applicant.setAppliedDate(LocalDate.now());	
			
		
		Applicant createdApplicant = applicantDao.save(applicant);
		
		if(createdApplicant != null) {
			ConnHandler.commit();
			response.setId(createdApplicant.getId());
			response.setMessage("Applicant Created Successfully");
		}else{
            ConnHandler.rollback();
        }
						
		return response;
		
	}    
}
