package com.lawencon.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.CountResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.user.UserInsertReqDto;
import com.lawencon.admin.dto.user.UsersListResDto;
import com.lawencon.admin.dto.user.UsersResDto;
import com.lawencon.admin.service.UserService;

import com.lawencon.base.ConnHandler;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@Valid @RequestBody UserInsertReqDto data){
        ConnHandler.begin();
        final InsertResDto response = userService.addUser(data);
        ConnHandler.commit();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/pic-list")
    public ResponseEntity<List<UsersResDto>> getUsers(){
        final List<UsersResDto> response = userService.getAllUser();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsersListResDto>> getListUsers(){
        final List<UsersListResDto> response = userService.getUsers();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping("/count")
    public ResponseEntity<CountResDto> getTotalUser(){
    	CountResDto response = userService.getTotalUser();
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
