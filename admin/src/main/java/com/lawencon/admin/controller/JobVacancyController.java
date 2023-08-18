package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.company.CompanyUpdateReqDto;
import com.lawencon.admin.dto.jobvacancy.AllJobVacancyResDto;
import com.lawencon.admin.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyUpdateReqDto;
import com.lawencon.admin.service.JobVacancyService;
import com.lawencon.base.ConnHandler;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/job-vacancies")
public class JobVacancyController {
    
    @Autowired
    JobVacancyService jobVacancyService;

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@RequestBody InsertJobVacancyReqDto data){
        ConnHandler.begin();
        final InsertResDto response = jobVacancyService.insert(data);
        ConnHandler.commit();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AllJobVacancyResDto>> getAllJobVacancy(){
        List<AllJobVacancyResDto> response = jobVacancyService.getAllJobVacancy();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping
	public ResponseEntity<UpdateResDto> updateJobVacancy(@RequestBody JobVacancyUpdateReqDto request){
		
		UpdateResDto response = jobVacancyService.updateJobVacancy(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
				
	}	
}
