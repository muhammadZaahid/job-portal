package com.lawencon.admin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.JobLevelDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.jobvacancy.AllJobVacancyResDto;
import com.lawencon.admin.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.admin.dto.jobvacancy.InsertJobVacancySeekerReqDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyUpdateReqDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyUpdateSeekerReqDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.JobLevel;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.User;
import com.lawencon.base.ConnHandler;
import com.lawencon.util.DateUtil;
import com.lawencon.util.GeneratorUtil;

@Service
public class JobVacancyService {

    @Autowired
    JobVacancyDao jobVacancyDao;
    @Autowired
    JobLevelDao jobLevelDao;
    @Autowired
    CompanyDao companyDao;
    @Autowired
    UserDao userDao;
    @Autowired
    RestTemplate restTemplate;

    public InsertResDto insert(InsertJobVacancyReqDto data) {
        final InsertResDto response = new InsertResDto();

        final JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setTitle(data.getTitle());
        jobVacancy.setJobVacancyCode(GeneratorUtil.generateCode());
        jobVacancy.setLocation(data.getLocation());
        jobVacancy.setBenefitDesc(data.getBenefitDesc());
        jobVacancy.setSalaryPublish(data.getSalaryPublish());
        jobVacancy.setSalaryFrom(data.getSalaryFrom());
        jobVacancy.setSalaryTo(data.getSalaryTo());
        jobVacancy.setStartDate(LocalDate.parse(data.getStartDate()));
        jobVacancy.setEndDate(LocalDate.parse(data.getEndDate()));

        final Company company = companyDao.getById(Company.class, data.getCompanyId());
        jobVacancy.setCompany(company);

        final User pic = userDao.getById(User.class, data.getPicId());
        jobVacancy.setUser(pic);

        final JobLevel jobLevel = jobLevelDao.getById(JobLevel.class, data.getJobLevelId());
        jobVacancy.setJobLevel(jobLevel);

        final JobVacancy createdJob = jobVacancyDao.save(jobVacancy);

        if (createdJob != null) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<InsertJobVacancySeekerReqDto> reqBody = new HttpEntity<InsertJobVacancySeekerReqDto>(
                    new InsertJobVacancySeekerReqDto(data.getTitle(), company.getCompanyCode(),
                            createdJob.getJobVacancyCode(), data.getJobLevelId(), data.getLocation(),
                            data.getBenefitDesc(), data.getSalaryFrom(), data.getSalaryTo(), data.getSalaryPublish(),
                            data.getStartDate(), data.getEndDate()),
                    headers);

            ResponseEntity<InsertResDto> res = restTemplate.exchange("http://localhost:8081/seeker/job-vacancies",
                    HttpMethod.POST, reqBody, InsertResDto.class);
            if (res.getStatusCode().equals(HttpStatus.CREATED)) {
                ConnHandler.commit();
                response.setId(createdJob.getId());
                response.setMessage("Success Create Job Vacancy!");
            }else{
                ConnHandler.rollback();
            }
        }
        return response;
    }

    public List<AllJobVacancyResDto> getAllJobVacancy() {

        List<AllJobVacancyResDto> responses = new ArrayList<>();
        companyDao.getAll(JobVacancy.class).forEach(c -> {
            AllJobVacancyResDto response = new AllJobVacancyResDto();

            response.setId(c.getId());
            response.setTitle(c.getTitle());
            response.setCode(c.getJobVacancyCode());
            response.setCompanyName(c.getCompany().getCompanyName());
            response.setPicName(c.getUser().getProfile().getName());
            response.setJobLevelName(c.getJobLevel().getJobLevelName());
            response.setLocation(c.getLocation());
            response.setStartDate(c.getStartDate().toString());
            response.setEndDate(c.getEndDate().toString());
            responses.add(response);

        });

        ;
        return responses;
    }
    
    public UpdateResDto updateJobVacancy(JobVacancyUpdateReqDto request) {
    	
    	ConnHandler.begin();
    	
    	final UpdateResDto updateResDto = new UpdateResDto();
    	
    	final JobVacancy jobVacancy = jobVacancyDao.getById(JobVacancy.class, request.getJobVacancyId());
    	final User pic = userDao.getById(User.class, request.getPicId());
    	final JobLevel jobLevel = jobLevelDao.getById(JobLevel.class, request.getJobLevelId());
   
    	jobVacancy.setTitle(request.getTitle());
    	jobVacancy.setUser(pic);
    	jobVacancy.setJobLevel(jobLevel);
    	jobVacancy.setLocation(request.getLocation());
    	jobVacancy.setBenefitDesc(request.getBenefitDesc());
    	jobVacancy.setSalaryFrom(request.getSalaryFrom());
    	jobVacancy.setSalaryTo(request.getSalaryTo());
    	jobVacancy.setSalaryPublish(request.getSalaryPublish());
    	jobVacancy.setStartDate(DateUtil.parseStringToDate(request.getStartDate()).toLocalDate());
    	jobVacancy.setEndDate(DateUtil.parseStringToDate(request.getEndDate()).toLocalDate());
    	
    	jobVacancy.setVersion(jobVacancy.getVersion() + 1);
    	
    	JobVacancy updatedJobVacancy = jobVacancyDao.save(jobVacancy);
    	
    	if(updatedJobVacancy != null) {
    		
    		HttpHeaders headers = new HttpHeaders();
    		
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		HttpEntity<JobVacancyUpdateSeekerReqDto> reqBody = new HttpEntity<JobVacancyUpdateSeekerReqDto>(

    				new JobVacancyUpdateSeekerReqDto(
    							
    							jobVacancy.getJobVacancyCode(),
    							request.getTitle(),
    							request.getJobLevelId(),
    							request.getLocation(),
    							request.getBenefitDesc(),
    							request.getSalaryFrom(),
    							request.getSalaryTo(),
    							request.getSalaryPublish(),
    							request.getStartDate(),
    							request.getEndDate()    							
    							)
    				);
    		
    		ResponseEntity<UpdateResDto> updateRes = restTemplate.exchange(
					"http://localhost:8081/seeker/job-vacancies",
					HttpMethod.PUT,
					reqBody,
					UpdateResDto.class
					);
			
			if(updateRes.getStatusCode().equals(HttpStatus.OK)) {
				ConnHandler.commit();
				updateResDto.setVer(updatedJobVacancy.getVersion());
				updateResDto.setMessage("Job Vacancy Updated Successfully");
				
			}else {
				ConnHandler.rollback();
				throw new RuntimeException();
			}
    	}
    	
    	return updateResDto;
    }

}
