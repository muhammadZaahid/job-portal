package com.lawencon.admin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.ApplicationDao;
import com.lawencon.admin.dao.AssessmentDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertAdminReqDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.admin.dto.applicant.ApplicantUpdateReqDto;
import com.lawencon.admin.dto.applicant.ApplicantUpdateSeekerReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.Application;
import com.lawencon.admin.model.Assessment;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.User;
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
	@Autowired
	AssessmentDao assessmentDao;
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

		Applicant createdApplicant = applicantDao.save(applicant);

		if (createdApplicant != null) {

			Application application = new Application();
			application.setApplicant(createdApplicant);
			applicationDao.save(application);
			ConnHandler.commit();
			response.setId(createdApplicant.getId());
			response.setMessage("Applicant Created Successfully");
		} else {
			ConnHandler.rollback();
		}

		return response;

	}

	public InsertResDto createApplicantNoLogin(ApplicantInsertAdminReqDto request) {
		ConnHandler.begin();
		Supplier<String> supplier = () -> "System";
		InsertResDto response = new InsertResDto();

		try {
			Applicant applicant = new Applicant();
			Candidate candidate = candidateDao.getByCode(request.getCandidateCode());
			applicant.setCandidate(candidate);
			JobVacancy jobVacancy = jobVacancyDao.getByCode(request.getJobVacancyCode());
			applicant.setJobVacancy(jobVacancy);
			applicant.setCurrentStage("application");
			applicant.setStgApplication(true);
			applicant.setAppliedDate(LocalDate.now());
			applicant.setApplicantCode(request.getApplicantCode());

			Applicant createdApplicant = applicantDao.saveNoLogin(applicant, supplier);

			if (createdApplicant != null) {

				Application application = new Application();
				application.setApplicant(createdApplicant);
				applicationDao.saveNoLogin(application, supplier);
				ConnHandler.commit();
				response.setId(createdApplicant.getId());
				response.setMessage("Applicant Created Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
		}

		return response;

	}

	public UpdateResDto updateApplicant(String data) {
		ConnHandler.begin();
		final UpdateResDto response = new UpdateResDto();
		Applicant applicant = applicantDao.getById(Applicant.class, data);
		JobVacancy job = jobVacancyDao.getById(JobVacancy.class, applicant.getJobVacancy().getId());

		User userpic = userDao.getById(User.class, job.getUser().getId());

		if (applicant.getCurrentStage().equals("application")) {

			Assessment assessment = new Assessment();
			assessment.setApplicantId(applicant);
			assessment.setAssessmentLastEmailSend(LocalDateTime.now().toString());
			assessment.setAssessmentPic(userpic);
			assessmentDao.save(assessment);

			try {
				ResponseEntity<UpdateResDto> res = restTemplate.exchange("http://localhost:8081/seeker/applicant/"+applicant.getApplicantCode(),
						HttpMethod.PATCH, null, UpdateResDto.class);
				if (res.getStatusCode().equals(HttpStatus.OK)) {
					applicant.setCurrentStage("assessment");
					applicant.setStgAssessment(true);
					applicant.setVersion(applicant.getVersion() + 1);
					applicantDao.save(applicant);
					ConnHandler.commit();
					response.setVer(applicant.getVersion());
					response.setMessage("Success update status applicant");
				}
			} catch (Exception e) {
				e.printStackTrace();
				ConnHandler.rollback();
			}
		}

		return response;
	}
}
