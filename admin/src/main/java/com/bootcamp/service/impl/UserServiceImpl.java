package com.bootcamp.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bootcamp.dao.ProfileDao;
import com.bootcamp.dao.UserDao;
import com.bootcamp.dto.InsertResDto;
import com.bootcamp.dto.login.LoginReqDto;
import com.bootcamp.dto.login.LoginResDto;
import com.bootcamp.dto.user.UserInsertReqDto;
import com.bootcamp.model.Profile;
import com.bootcamp.model.User;
import com.bootcamp.service.UserService;

public class UserServiceImpl implements UserService{
    
    private final ProfileDao profileDao;
    private final UserDao userDao;

    public UserServiceImpl(ProfileDao profileDao, UserDao userDao){
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    public InsertResDto addUser(UserInsertReqDto data){
        InsertResDto response = new InsertResDto();
        
        final Profile profile = new Profile();
        profile.setName(data.getFullName());
        profileDao.save(profile);

        final User user = new User();
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    
}
