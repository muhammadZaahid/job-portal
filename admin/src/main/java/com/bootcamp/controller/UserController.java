package com.bootcamp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.dto.InsertResDto;
import com.bootcamp.dto.user.UserInsertReqDto;
import com.bootcamp.service.UserService;

@RestController
@RequestMapping("admin/users")
public class UserController {
    
    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<InsertResDto> insert(@Valid @RequestBody UserInsertReqDto data){
        final InsertResDto response = userService.addUser(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
