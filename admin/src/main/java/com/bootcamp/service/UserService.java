package com.bootcamp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bootcamp.dto.InsertResDto;
import com.bootcamp.dto.login.LoginReqDto;
import com.bootcamp.dto.login.LoginResDto;
import com.bootcamp.dto.user.UserInsertReqDto;

public interface UserService extends UserDetailsService{
    
    InsertResDto addUser(UserInsertReqDto data);
    LoginResDto login(LoginReqDto data);
}
