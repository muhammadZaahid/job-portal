package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.candidate.service.ApplicantService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("seeker/applicant")
public class ApplicantController {
    
    @Autowired
    ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@RequestBody ApplicantInsertReqDto data){
        final InsertResDto response = applicantService.createApplicant(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
