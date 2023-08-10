package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.company.CompanyInsertReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.service.CompanyService;
import com.lawencon.base.ConnHandler;

@RestController
@RequestMapping("admin/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> createCompany(@RequestBody CompanyInsertReqDto request){
		
		ConnHandler.begin();
		InsertResDto response = companyService.createCompany(request);
		
		ConnHandler.commit();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<CompanyResDto> getAllCompany(){
		ConnHandler.begin();
		CompanyResDto response = companyService.getAllCompany();
		
		ConnHandler.commit();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
