package com.lawencon.admin.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.login.LoginResDto;
import com.lawencon.admin.dto.user.UserInsertReqDto;

public interface UserService extends UserDetailsService{
    
    InsertResDto addUser(UserInsertReqDto data);
    LoginResDto login(LoginReqDto data);
}
