package com.lawencon.admin.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.user.UserInsertReqDto;
import com.lawencon.admin.service.UserService;
import com.lawencon.base.ConnHandler;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("admin/users")
public class UserController {
    
    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@Valid @RequestBody UserInsertReqDto data){
        ConnHandler.begin();
        final InsertResDto response = userService.addUser(data);
        ConnHandler.commit();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
