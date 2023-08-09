package com.lawencon.admin.service;

import com.lawencon.admin.dto.InsertResDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.login.LoginResDto;
import com.lawencon.admin.dto.user.UserInsertReqDto;

@Service
public interface UserService extends UserDetailsService{
    
    InsertResDto addUser(UserInsertReqDto data);
    LoginResDto login(LoginReqDto data);
}
