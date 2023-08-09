package com.bootcamp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.dto.InsertResDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/test")
public class TestController {
    
    @GetMapping
    public ResponseEntity<InsertResDto> getTest(){
        final InsertResDto tes = new InsertResDto();
        return new ResponseEntity<>(tes, HttpStatus.OK);
    }
}
