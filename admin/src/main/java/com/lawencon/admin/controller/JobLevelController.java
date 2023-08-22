package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.joblevel.JobLevelResDto;
import com.lawencon.admin.service.JobLevelService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/job-level")
public class JobLevelController {
 
    @Autowired
    JobLevelService jobLevelService;

    @GetMapping
    public ResponseEntity<List<JobLevelResDto>> getAll(){
        List<JobLevelResDto> response = jobLevelService.getAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
