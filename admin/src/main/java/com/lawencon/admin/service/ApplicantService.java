package com.lawencon.admin.service;

import java.time.LocalDate;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.ApplicationDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertAdminReqDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.Application;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.base.ConnHandler;

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
			
			Application application = new Application();
			application.setApplicant(createdApplicant);
			applicationDao.save(application);
			ConnHandler.commit();
			response.setId(createdApplicant.getId());
			response.setMessage("Applicant Created Successfully");
		}else{
			ConnHandler.rollback();
		}
						
		return response;
		
	}

	public InsertResDto createApplicantNoLogin(ApplicantInsertAdminReqDto request) {
		Supplier<String> supplier = () -> "System";
		InsertResDto response = new InsertResDto();
		
		Applicant applicant = new Applicant();
		Candidate candidate = candidateDao.getByCode(request.getCandidateCode());
		applicant.setCandidate(candidate);		
		JobVacancy jobVacancy = jobVacancyDao.getByCode(request.getJobVacancyCode());
		applicant.setJobVacancy(jobVacancy);
		applicant.setCurrentStage("application");
		applicant.setStgApplication(true);
		applicant.setAppliedDate(LocalDate.now());	
			
		
		Applicant createdApplicant = applicantDao.saveNoLogin(applicant,supplier);
		
		if(createdApplicant != null) {
			
			Application application = new Application();
			application.setApplicant(createdApplicant);
			applicationDao.saveNoLogin(application,supplier);
			response.setId(createdApplicant.getId());
			response.setMessage("Applicant Created Successfully");
		}
						
		return response;
		
	}
}
