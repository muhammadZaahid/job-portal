package com.lawencon.admin.service.impl;

import java.util.ArrayList;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.ProfileDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.login.LoginResDto;
import com.lawencon.admin.dto.user.UserInsertReqDto;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.User;
import com.lawencon.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    ProfileDao profileDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    // public UserServiceImpl(ProfileDao profileDao, UserDao userDao){
    //     this.profileDao = profileDao;
    //     this.userDao = userDao;
    // }

    public InsertResDto addUser(UserInsertReqDto data){
        InsertResDto response = new InsertResDto();
        Supplier<String> createdBy = () -> "system";
        final Profile profile = new Profile();
        profile.setName(data.getFullName());
        profileDao.save(profile);

        final User user = new User();
        final String passwordEncoded = passwordEncoder.encode(data.getPassword());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoded);
        user.setProfile(profile);

        final User createdUser = userDao.save(user);

        if(createdUser != null){
            response.setId(createdUser.getId());
            response.setMessage("Account Created Successfully!");
        }

        return response;
    }

    @Override
    public LoginResDto login(LoginReqDto data){
        final LoginResDto loginResDto = new LoginResDto();
		
		final User user = userDao.getByEmail(data.getEmail());

        if(user != null){
            loginResDto.setProfileName(user.getProfile().getName());
            loginResDto.setUserId(user.getId());
        }

        return loginResDto;
    }
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByEmail(username);
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
		} 
		throw new UsernameNotFoundException("Email not found!");
	}

    
}
