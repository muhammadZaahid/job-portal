package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.candidate.service.JobVacancyService;

@RestController
@RequestMapping("/seeker/job-vacancies")
public class JobVacancyController {
    @Autowired
    JobVacancyService jobVacancyService;
    
    @PostMapping
    public ResponseEntity<InsertResDto> insert(@RequestBody InsertJobVacancyReqDto data){
        final InsertResDto response = jobVacancyService.insert(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
