package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertAdminReqDto;
import com.lawencon.admin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.admin.service.ApplicantService;
import com.lawencon.base.ConnHandler;

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

}
