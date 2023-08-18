package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.interview.InterviewInsertReqDto;
import com.lawencon.admin.service.ApplicantService;
import com.lawencon.admin.service.InterviewService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/interview")
public class InterviewController {
    
    @Autowired
    InterviewService interviewService;
    @Autowired
    ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<InsertResDto> insertInterview(@RequestBody InterviewInsertReqDto data){
        InsertResDto response = interviewService.insertInterview(data);
        if(response != null){
            applicantService.updateApplicant(data.getApplicantId());
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
