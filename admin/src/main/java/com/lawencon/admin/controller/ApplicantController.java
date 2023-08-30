package com.lawencon.admin.controller;

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

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.applicant.ApplicantDetailResDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertAdminReqDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.admin.dto.applicant.ApplicantsResDto;
import com.lawencon.admin.service.ApplicantService;
import com.lawencon.base.ConnHandler;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/applicant")
public class ApplicantController {
	
	@Autowired
	ApplicantService applicantService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> createApplicant(@RequestBody ApplicantInsertReqDto request){
		
		InsertResDto response = applicantService.createApplicant(request);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/seeker")
	public ResponseEntity<InsertResDto> createApplicantNoLogin(@RequestBody ApplicantInsertAdminReqDto data){
		final InsertResDto response = applicantService.createApplicantNoLogin(data);

		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UpdateResDto> updateStatusApplicant(@PathVariable("id") String data){
		final UpdateResDto response = applicantService.updateApplicant(data);

		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<ApplicantsResDto>> getAllApplicants(@RequestParam(required = false,name = "page") Integer page, @RequestParam(required = false,name = "limit") Integer limit){
		final List<ApplicantsResDto> response = applicantService.getAllApplicants(page,limit);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApplicantDetailResDto> getById(@PathVariable("id")String applicantId){
		final ApplicantDetailResDto response = applicantService.getById(applicantId);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
