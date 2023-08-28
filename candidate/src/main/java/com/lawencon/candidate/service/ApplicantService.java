package com.lawencon.candidate.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.lawencon.candidate.dao.UserDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.candidate.dto.applicant.ApplicantResDto;
import com.lawencon.candidate.dto.applicant.ApplicantInsertAdminReqDto;
import com.lawencon.candidate.model.Applicant;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.candidate.model.User;
import com.lawencon.security.principal.PrincipalService;
import com.lawencon.util.GeneratorUtil;

@Service
public class ApplicantService {
	@Autowired
	PrincipalService principalService;
	@Autowired
	ApplicantDao applicantDao;
	@Autowired
	CandidateDao candidateDao;
	@Autowired
	JobVacancyDao jobVacancyDao;
	@Autowired
	UserDao userDao;
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
		applicant.setApplicantCode(GeneratorUtil.generateCode());
		Applicant createdApplicant = applicantDao.save(applicant);

		if (createdApplicant != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<ApplicantInsertAdminReqDto> reqBody = new HttpEntity<ApplicantInsertAdminReqDto>(
					new ApplicantInsertAdminReqDto(candidate.getCandidateCode(), jobVacancy.getJobVacancyCode(),
							applicant.getApplicantCode()),
					headers);
			try {
				ResponseEntity<InsertResDto> res = restTemplate.exchange(
						"http://localhost:8080/admin/applicant/seeker", HttpMethod.POST, reqBody, InsertResDto.class);
				if (res.getStatusCode().equals(HttpStatus.CREATED)) {
					ConnHandler.commit();
					response.setId(createdApplicant.getId());
					response.setMessage("Sukses Melamar Posisi " + jobVacancy.getTitle() + " !");
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

	public UpdateResDto updateApplicant(String data) {
		ConnHandler.begin();
		Supplier<String> supplier = () -> "System";
		final UpdateResDto response = new UpdateResDto();

		Applicant applicant = applicantDao.getByCode(data);

		if (applicant != null) {
			if (applicant.getCurrentStage().equals("application")) {
				applicant.setCurrentStage("assessment");
				applicant.setStgAssessment(true);
			} else if (applicant.getCurrentStage().equals("assessment")) {
				applicant.setCurrentStage("interview");
				applicant.setStgInterview(true);
			} else if (applicant.getCurrentStage().equals("interview")) {
				applicant.setCurrentStage("mcu");
				applicant.setStgMcu(true);
			} else if (applicant.getCurrentStage().equals("mcu")) {
				applicant.setCurrentStage("offer");
				applicant.setStgOffer(true);
			} else if (applicant.getCurrentStage().equals("offer")) {
				applicant.setCurrentStage("hired");
			}
			applicant.setVersion(applicant.getVersion() + 1);
			applicantDao.saveNoLogin(applicant, supplier);
			ConnHandler.commit();
			response.setVer(applicant.getVersion());
			response.setMessage("Success update status applicant");
		}

		return response;
	}

	public List<ApplicantResDto> getApplicantByUser() {
		User user = userDao.getById(User.class, principalService.getAuthPrincipal().toString());
		List<Applicant> applicants = applicantDao.getAllByCandidate(user.getCandidate().getId());
		List<ApplicantResDto> responses = new ArrayList<>();

		for (int i = 0; i < applicants.size(); i++) {
			ApplicantResDto response = new ApplicantResDto();
			response.setJobVacancyId(applicants.get(i).getJobVacancy().getId());
			response.setJobTitle(applicants.get(i).getJobVacancy().getTitle());
			response.setCompanyName(applicants.get(i).getJobVacancy().getCompany().getCompanyName());
			response.setCurrentStage(applicants.get(i).getCurrentStage());
			response.setAppliedDate(applicants.get(i).getAppliedDate().toString());
			responses.add(response);
		}
		return responses;
	}

	public Boolean checkIfApplied(String candidateId, String jobVacancyId){
		return applicantDao.checkApplied(candidateId, jobVacancyId);
	}
}
