package com.lawencon.candidate.service;

import java.time.LocalDate;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.ApplicantDao;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.candidate.dto.applicant.ApplicantUpdateReqDto;
import com.lawencon.candidate.dto.applicant.ApplicantInsertAdminReqDto;
import com.lawencon.candidate.model.Applicant;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.util.GeneratorUtil;

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
		System.out.println(request.getCandidateId());
		System.out.println(request.getJobVacancyId());
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
		applicant.setApplicantCode(GeneratorUtil.generateUniqueProductCode());
		Applicant createdApplicant = applicantDao.save(applicant);

		if (createdApplicant != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<ApplicantInsertAdminReqDto> reqBody = new HttpEntity<ApplicantInsertAdminReqDto>(
					new ApplicantInsertAdminReqDto(candidate.getCandidateCode(), jobVacancy.getJobVacancyCode(),applicant.getApplicantCode()),
					headers);
			try {
				ResponseEntity<InsertResDto> res = restTemplate.exchange(
						"http://localhost:8080/admin/applicant/seeker", HttpMethod.POST, reqBody, InsertResDto.class);
				if (res.getStatusCode().equals(HttpStatus.CREATED)) {
					ConnHandler.commit();
					response.setId(createdApplicant.getId());
					response.setMessage("Applicant Created Successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ConnHandler.rollback();
			}

		} else {
			ConnHandler.rollback();
		}

		return response;

	}

	public UpdateResDto updateApplicant(String data){
		ConnHandler.begin();
		Supplier<String> supplier = () -> "System";
		final UpdateResDto response = new UpdateResDto();

		Applicant applicant = applicantDao.getByCode(data);

		if(applicant != null){
			if(applicant.getCurrentStage().equals("application")){
				applicant.setCurrentStage("assessment");
				applicant.setStgAssessment(true);
				applicant.setVersion(applicant.getVersion() + 1);
				applicantDao.saveNoLogin(applicant, supplier);
				ConnHandler.commit();
				response.setVer(applicant.getVersion());
				response.setMessage("Success update status applicant");
			}
		}

		return response;
	}
}
