package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.company.CompanyInsertReqDto;
import com.lawencon.candidate.service.CompanyService;

@RestController
@RequestMapping("/seeker/companies")
public class CompanyController {
    
    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@RequestBody CompanyInsertReqDto data){
        final InsertResDto response = companyService.createCompany(data);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
