package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.applicant.ApplicantCheckApplyReqDto;
import com.lawencon.candidate.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.candidate.dto.applicant.ApplicantResDto;
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

    @PatchMapping("/{code}")
    public ResponseEntity<UpdateResDto> updateStatus(@PathVariable("code") String applicantCode){
        final UpdateResDto response = applicantService.updateApplicant(applicantCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ApplicantResDto>> getAllApplicants(){
        final List<ApplicantResDto> response = applicantService.getApplicantByUser();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkIfApplied(@RequestParam("cId") String candidateId, @RequestParam("jId") String jobVacancyId){
        Boolean response = applicantService.checkIfApplied(candidateId, jobVacancyId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
