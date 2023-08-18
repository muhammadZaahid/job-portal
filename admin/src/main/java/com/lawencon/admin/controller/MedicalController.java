package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.medical.MedicalInsertReqDto;
import com.lawencon.admin.service.MedicalService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/medical")
public class MedicalController {
    
    @Autowired
    MedicalService medicalService;

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@RequestBody MedicalInsertReqDto data){
        InsertResDto response = medicalService.insert(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
