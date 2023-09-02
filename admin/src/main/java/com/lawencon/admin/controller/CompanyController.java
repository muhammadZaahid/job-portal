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
import com.lawencon.admin.dto.company.CompanyDetailResDto;
import com.lawencon.admin.dto.company.CompanyInsertReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.dto.company.CompanyUpdateReqDto;
import com.lawencon.admin.service.CompanyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> createCompany(@RequestBody CompanyInsertReqDto request){
		
		InsertResDto response = companyService.createCompany(request);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CompanyResDto>> getAllCompany(){

		List<CompanyResDto> response = companyService.getAllCompany();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDetailResDto> getCompany(@PathVariable("id") String id){

		CompanyDetailResDto response = companyService.getCompanyById(id);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UpdateResDto> updateCompany(@RequestBody CompanyUpdateReqDto request){
		
		UpdateResDto response = companyService.updateCompany(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
				
	}	
	
	@GetMapping("/count")
	public ResponseEntity<CountResDto> getTotalCompany(){
		CountResDto response = companyService.getTotalCompany();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
