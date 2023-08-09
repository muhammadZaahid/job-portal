package com.lawencon.admin.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lawencon.admin.dao.ProfileDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.login.LoginResDto;
import com.lawencon.admin.dto.user.UserInsertReqDto;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.User;
import com.lawencon.admin.service.UserService;
import org.springframework.stereotype.Service;

@Service
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
