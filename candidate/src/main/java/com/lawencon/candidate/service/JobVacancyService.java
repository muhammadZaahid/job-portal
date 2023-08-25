package com.lawencon.candidate.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CompanyDao;
import com.lawencon.candidate.dao.JobLevelDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dao.UserDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyDetailResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyUpdateReqDto;
import com.lawencon.candidate.model.Company;
import com.lawencon.candidate.model.JobLevel;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.util.DateUtil;

@Service
public class JobVacancyService {

    @Autowired
    JobVacancyDao jobVacancyDao;
    @Autowired
    CompanyDao companyDao;
    @Autowired
    JobLevelDao jobLevelDao;
    @Autowired
    UserDao userDao;

    public InsertResDto insert(InsertJobVacancyReqDto data) {
        ConnHandler.begin();
        Supplier<String> supplier = () -> "System";
        final InsertResDto response = new InsertResDto();

        final JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setTitle(data.getTitle());
        jobVacancy.setJobVacancyCode(data.getJobVacancyCode());
        jobVacancy.setLocation(data.getLocation());
        jobVacancy.setBenefitDesc(data.getBenefitDesc());
        jobVacancy.setSalaryPublish(data.getSalaryPublish());
        jobVacancy.setSalaryFrom(data.getSalaryFrom());
        jobVacancy.setSalaryTo(data.getSalaryTo());
        jobVacancy.setStartDate(LocalDate.parse(data.getStartDate()));
        jobVacancy.setEndDate(LocalDate.parse(data.getEndDate()));

        final Company company = companyDao.getCompanyByCode(data.getCompanyCode());
        jobVacancy.setCompany(company);


        final JobLevel jobLevel = jobLevelDao.getById(JobLevel.class, data.getJobLevelId());
        jobVacancy.setJobLevel(jobLevel);

        final JobVacancy createdJob = jobVacancyDao.saveNoLogin(jobVacancy,supplier);
        if(createdJob != null){
            ConnHandler.commit();
            response.setId(createdJob.getId());
            response.setMessage("Success Create Job Vacancy!");
        }

        return response;
    }

    public List<JobVacancyResDto> getAllJobVacancy() {

        List<JobVacancyResDto> responses = new ArrayList<>();
        jobVacancyDao.getAll(JobVacancy.class).forEach(c -> {
            JobVacancyResDto response = new JobVacancyResDto();

            response.setId(c.getId());
            response.setTitle(c.getTitle());
            response.setCode(c.getJobVacancyCode());
            response.setCompanyName(c.getCompany().getCompanyName());
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
    	
    	Supplier<String> supplier = ()-> "System";
    	
    	final JobVacancy jobVacancy = jobVacancyDao.getByCode(request.getJobVacancyCode());
    	final JobLevel jobLevel = jobLevelDao.getById(JobLevel.class, request.getJobLevelId());
    
    	jobVacancy.setTitle(request.getTitle());    
    	jobVacancy.setJobLevel(jobLevel);
    	jobVacancy.setLocation(request.getLocation());
    	jobVacancy.setBenefitDesc(request.getBenefitDesc());
    	jobVacancy.setSalaryFrom(request.getSalaryFrom());
    	jobVacancy.setSalaryTo(request.getSalaryTo());
    	jobVacancy.setSalaryPublish(request.getSalaryPublish());
    	jobVacancy.setStartDate(LocalDate.parse(request.getStartDate()));
        jobVacancy.setEndDate(LocalDate.parse(request.getEndDate()));
    	
    	jobVacancy.setVersion(jobVacancy.getVersion() + 1);
    	
    	JobVacancy updatedJobVacancy = jobVacancyDao.saveNoLogin(jobVacancy, supplier);
    	
    	if(updatedJobVacancy != null) {    		    	
			ConnHandler.commit();
			updateResDto.setVer(updatedJobVacancy.getVersion());
			updateResDto.setMessage("Job Vacancy Updated Successfully");						
    	}
    	
    	return updateResDto;
    }

	public JobVacancyDetailResDto getJobVacancyById(String jobVacancyId) {
		
		final JobVacancyDetailResDto response = new JobVacancyDetailResDto();		
		JobVacancy jobVacancy = jobVacancyDao.getById(JobVacancy.class, jobVacancyId);
		
		response.setId(jobVacancy.getId());
		response.setCode(jobVacancy.getJobVacancyCode());
		response.setTitle(jobVacancy.getTitle());
		response.setCompanyName(jobVacancy.getCompany().getCompanyName());
		response.setJobLevelName(jobVacancy.getJobLevel().getJobLevelName());
		response.setLocation(jobVacancy.getLocation());
		response.setStartDate(jobVacancy.getStartDate().toString());
		response.setEndDate(jobVacancy.getEndDate().toString());
		response.setBenefitDesc(jobVacancy.getBenefitDesc());
		response.setSalaryPublish(jobVacancy.isSalaryPublish());
		response.setSalaryFrom(jobVacancy.getSalaryFrom());
		response.setSalaryTo(jobVacancy.getSalaryTo());
		
		return response;
	}

}
