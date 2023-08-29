package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.blacklist.BlacklistInsertReqDto;
import com.lawencon.admin.service.BlacklistService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/blacklist")
public class BlacklistController {
    @Autowired
    BlacklistService blacklistService;

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@RequestBody BlacklistInsertReqDto data){
        final InsertResDto response = blacklistService.insert(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
