package com.lawencon.admin.controller;

import java.util.List;

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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
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
	public ResponseEntity<List<CompanyResDto>> getAllCompany(){

		List<CompanyResDto> response = companyService.getAllCompany();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
