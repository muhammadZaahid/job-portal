package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.JobLevelDao;
import com.lawencon.admin.dto.joblevel.JobLevelResDto;
import com.lawencon.admin.model.JobLevel;

@Service
public class JobLevelService {

    @Autowired
    JobLevelDao jobLevelDao;

    public List<JobLevelResDto> getAll(){
        List<JobLevelResDto> responses = new ArrayList<>();

        jobLevelDao.getAll(JobLevel.class).forEach(j->{
            JobLevelResDto response = new JobLevelResDto();
            
            response.setId(j.getId());
            response.setJobLevelName(j.getJobLevelName());
            responses.add(response);
        });

        return responses;
    }
}
