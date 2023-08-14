package com.lawencon.candidate.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CompanyDao;
import com.lawencon.candidate.dao.JobLevelDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.jobvacancy.InsertJobVacancyReqDto;
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
        jobVacancy.setStartDate(DateUtil.parseStringToDate(data.getStartDate()).toLocalDate());
        jobVacancy.setEndDate(DateUtil.parseStringToDate(data.getEndDate()).toLocalDate());

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
}
