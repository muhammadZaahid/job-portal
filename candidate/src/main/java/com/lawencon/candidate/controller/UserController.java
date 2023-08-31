package com.lawencon.candidate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.user.UserInsertReqDto;
import com.lawencon.candidate.dto.user.UserResDto;
import com.lawencon.candidate.dto.user.UserUpdateReqDto;
import com.lawencon.candidate.service.UserService;

@Validated
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

    @PutMapping()
    public ResponseEntity<UpdateResDto> update(@Valid @RequestBody UserUpdateReqDto data){
        final UpdateResDto response = userService.updateCandidate(data);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<UserResDto> getUser(){
        final UserResDto response = userService.getDetailUser();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
