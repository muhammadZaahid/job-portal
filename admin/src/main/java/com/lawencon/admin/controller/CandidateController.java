package com.lawencon.admin.controller;

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

import com.lawencon.admin.dto.CountResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.candidate.CandidateInsertReqDto;
import com.lawencon.admin.dto.candidate.CandidateResDto;
import com.lawencon.admin.dto.candidate.CandidateSeekerInsertReqDto;
import com.lawencon.admin.dto.candidate.CandidateSeekerUpdateReqDto;
import com.lawencon.admin.service.CandidateService;
import com.lawencon.base.ConnHandler;

@RestController
@RequestMapping("admin/candidate")
public class CandidateController {
	
	@Autowired
	CandidateService candidateService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> createCandidate(@RequestBody CandidateInsertReqDto request){
		
		ConnHandler.begin();
		InsertResDto response = candidateService.createCandidate(request);
		ConnHandler.commit();
		
		return new ResponseEntity<>(response, HttpStatus.CREATED); 
	}
	
	@PostMapping(value = "/seeker")
	public ResponseEntity<InsertResDto> createCandidateFromSeeker(@RequestBody CandidateSeekerInsertReqDto data){
		ConnHandler.begin();
		InsertResDto response = candidateService.saveCandidateFromSeeker(data);
		ConnHandler.commit();

		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CandidateResDto>> getAllCandidates(){
		
		List<CandidateResDto> response = candidateService.getAllCandidate();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CandidateResDto> getCandidateById(@PathVariable("id") String candidateId){
		CandidateResDto response = candidateService.getByCandidateId(candidateId);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/seeker")
	public ResponseEntity<UpdateResDto> updateCandidateSeeker(@RequestBody CandidateSeekerUpdateReqDto data){
		final UpdateResDto response = candidateService.updateFromSeeker(data);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<CountResDto> getTotalCandidate(){
	
		final CountResDto response = candidateService.getTotalCandidate();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}

