package com.lawencon.admin.service;

import com.lawencon.admin.dao.ConstantDao;
import com.lawencon.admin.dao.ProfileDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.CountResDto;
import com.lawencon.admin.dto.InsertResDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.login.LoginResDto;
import com.lawencon.admin.dto.user.UserInsertReqDto;
import com.lawencon.admin.dto.user.UsersListResDto;
import com.lawencon.admin.dto.user.UsersResDto;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.User;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    UserDao userDao;
    @Autowired
    ProfileDao profileDao;
    @Autowired
    ConstantDao constantDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    public InsertResDto addUser(UserInsertReqDto data) {
        InsertResDto response = new InsertResDto();

        final Profile profile = new Profile();
        profile.setName(data.getFullName());
        profile.setPhone(data.getPhone());
        profileDao.save(profile);

        final User user = new User();
        user.setEmail(data.getEmail());
        final String encodedPassword = passwordEncoder.encode(data.getPassword());
        user.setPassword(encodedPassword);
        user.setProfile(profile);

        final User createdUser = userDao.save(user);

        if (createdUser != null) {
            response.setId(createdUser.getId());
            response.setMessage("Account Created Successfully!");
        }

        return response;
    }

    public LoginResDto login(LoginReqDto data) {
        final LoginResDto loginResDto = new LoginResDto();

        final User user = userDao.getByEmail(data.getEmail());

        if (user != null) {
            loginResDto.setProfileName(user.getProfile().getName());
            loginResDto.setUserId(user.getId());
        }

        return loginResDto;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.getByEmail(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    new ArrayList<>());
        }
        throw new UsernameNotFoundException("Email not found!");
    }

    public List<UsersResDto> getAllUser(){
        List<UsersResDto> responses = new ArrayList<>();
        
        userDao.getAll(User.class).forEach(u->{
            UsersResDto response = new UsersResDto();
            response.setFullname(u.getProfile().getName());
            response.setId(u.getId());
            
            responses.add(response);
        });

        return responses;
    }

    public List<UsersListResDto> getUsers(){
        List<UsersListResDto> responses = new ArrayList<>();

        userDao.getAll(User.class).forEach(u->{
            UsersListResDto response = new UsersListResDto();
            response.setId(u.getId());
            response.setEmail(u.getEmail());
            response.setFullname(u.getProfile().getName());
            response.setPhone(u.getProfile().getPhone());
            responses.add(response);
        });

        return responses;
    }
    
    public CountResDto getTotalUser() {
    	
    	CountResDto response = new CountResDto();
    	String total = constantDao.getTotal("t_user");
    	response.setTotal(total);
    	
    	return response;
    }
}
