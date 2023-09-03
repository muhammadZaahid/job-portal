package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyDetailResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyUpdateReqDto;
import com.lawencon.candidate.service.JobVacancyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
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

    @GetMapping()
    public ResponseEntity<List<JobVacancyResDto>> getAll(){
        final List<JobVacancyResDto> response = jobVacancyService.getAllJobVacancy();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping
   	public ResponseEntity<UpdateResDto> updateJobVacancy(@RequestBody JobVacancyUpdateReqDto request){
   		
   		UpdateResDto response = jobVacancyService.updateJobVacancy(request);
   		
   		return new ResponseEntity<>(response, HttpStatus.OK);
   				
   	}
    
    @GetMapping("/{id}")
    public ResponseEntity<JobVacancyDetailResDto> getById(@PathVariable("id") String id){
    	
    	JobVacancyDetailResDto response = jobVacancyService.getJobVacancyById(id);
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
