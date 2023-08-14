package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.UserDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.login.LoginReqDto;
import com.lawencon.candidate.dto.login.LoginResDto;
import com.lawencon.candidate.dto.user.UserInsertAdminReqDto;
import com.lawencon.candidate.dto.user.UserInsertReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.User;
import com.lawencon.util.GeneratorUtil;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    CandidateDao candidateDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    public InsertResDto insertUser(UserInsertReqDto data) {
        ConnHandler.begin();
        InsertResDto response = new InsertResDto();

        Supplier<String> supplier = () -> "system";
        final Candidate candidate = new Candidate();
        candidate.setName(data.getFullName());
        candidate.setCandidateCode(GeneratorUtil.generateUniqueProductCode());
        candidate.setEmail(data.getEmail());
        candidateDao.saveNoLogin(candidate, supplier);

        final User user = new User();
        user.setEmail(data.getEmail());
        final String encodedPassword = passwordEncoder.encode(data.getPassword());
        user.setPassword(encodedPassword);
        user.setCandidate(candidate);

        final User createdUser = userDao.saveNoLogin(user, supplier);

        if (createdUser != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<UserInsertAdminReqDto> reqBody = new HttpEntity<UserInsertAdminReqDto>(
                    new UserInsertAdminReqDto(data.getEmail(), data.getFullName(), candidate.getCandidateCode()));
            System.out.println(reqBody.toString());
            ResponseEntity<InsertResDto> res = restTemplate.exchange("http://localhost:8080/admin/candidate/seeker",
                    HttpMethod.POST, reqBody, InsertResDto.class);
            System.out.println(res);
            if (res.getStatusCode().equals(HttpStatus.CREATED)) {
                ConnHandler.commit();
                response.setId(res.getBody().getId());
                response.setMessage(res.getBody().getMessage());
            } else {
                ConnHandler.rollback();
            }
        } else {
            ConnHandler.rollback();
        }
        return response;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.getByEmail(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    new ArrayList<>());
        }
        throw new UsernameNotFoundException("Email not found!");
    }

    public LoginResDto login(LoginReqDto data) {
        final LoginResDto loginResDto = new LoginResDto();

        final User user = userDao.getByEmail(data.getEmail());

        if (user != null) {
            loginResDto.setCandidateName(user.getCandidate().getName());
            loginResDto.setUserId(user.getId());
            loginResDto.setCandidateCode(user.getCandidate().getCandidateCode());
        }

        return loginResDto;
    }
}
