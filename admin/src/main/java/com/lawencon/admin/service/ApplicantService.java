package com.lawencon.admin.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.ApplicationDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.Application;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.JobVacancy;

@Service
public class ApplicantService {

	@Autowired
	ApplicantDao applicantDao;
	
	@Autowired
	CandidateDao candidateDao;
	
	@Autowired
	JobVacancyDao jobVacancyDao;
	
	@Autowired
	ApplicationDao applicationDao;
	
	public InsertResDto createApplicant(ApplicantInsertReqDto request) {
		
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
			
			Application application = new Application();
			application.setApplicant(createdApplicant);
			applicationDao.save(application);
			response.setId(createdApplicant.getId());
			response.setMessage("Applicant Created Successfully");
		}
						
		return response;
		
	}
}
