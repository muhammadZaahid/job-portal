package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.assessment.AssessmentResDto;
import com.lawencon.admin.dto.assessment.AssessmentUpdateReqDto;
import com.lawencon.admin.service.AssessmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/assessment")
public class AssessmentController {
    
    @Autowired
    AssessmentService assessmentService;

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentResDto> getByApplicantId(@PathVariable("id") String applicantId){
        AssessmentResDto response = assessmentService.getByApplicantId(applicantId);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateResDto> updateScoreAndNotes(@PathVariable("id") String assessmentId, @RequestBody AssessmentUpdateReqDto data){
        UpdateResDto response = assessmentService.updateScoreAndNotes(assessmentId,data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
