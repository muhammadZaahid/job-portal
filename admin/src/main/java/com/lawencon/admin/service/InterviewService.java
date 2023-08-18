package com.lawencon.admin.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.admin.dao.ApplicantDao;
import com.lawencon.admin.dao.InterviewDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.interview.InterviewInsertReqDto;
import com.lawencon.admin.model.Applicant;
import com.lawencon.admin.model.Interview;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.util.DateUtil;

@Service
public class InterviewService {
    
    @Autowired
    InterviewDao interviewDao;
    @Autowired
    ApplicantDao applicantDao;
    @Autowired
    JobVacancyDao jobVacancyDao;
    @Autowired
    RestTemplate restTemplate;

    public InsertResDto insertInterview (InterviewInsertReqDto data){
        final InsertResDto response = new InsertResDto();

        Applicant applicant = applicantDao.getById(Applicant.class, data.getApplicantId());
        JobVacancy jobVacancy = applicant.getJobVacancy();

        Interview interview = new Interview();
        interview.setInterviewVenue(data.getInterviewVenue());
        interview.setInterviewTime(DateUtil.parseStringToDate(data.getInterviewTime()));
        interview.setInterviewMap(data.getInterviewLocation());
        interview.setApplicant(applicant);
        interview.setInterviewPic(jobVacancy.getUser());
        interview.setInterviewLastEmailSend(LocalDateTime.now().toString());

        Interview createdInterview = interviewDao.save(interview);

        if(createdInterview != null){
            response.setId(createdInterview.getId());
            response.setMessage("Success Arrange Interview with Candidate!");
        }
        return response;
    }
}
