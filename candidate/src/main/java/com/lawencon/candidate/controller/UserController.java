package com.lawencon.candidate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.user.UserInsertReqDto;
import com.lawencon.candidate.service.UserService;

@RestController
@RequestMapping("seeker/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@Valid @RequestBody UserInsertReqDto data){
        final InsertResDto response = userService.insertUser(data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
