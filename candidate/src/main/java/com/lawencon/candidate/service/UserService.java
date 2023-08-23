package com.lawencon.candidate.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.dao.UserDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.login.LoginReqDto;
import com.lawencon.candidate.dto.login.LoginResDto;
import com.lawencon.candidate.dto.user.UserInsertAdminReqDto;
import com.lawencon.candidate.dto.user.UserInsertReqDto;
import com.lawencon.candidate.dto.user.UserUpdateAdminReqDto;
import com.lawencon.candidate.dto.user.UserUpdateReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.File;
import com.lawencon.candidate.model.User;
import com.lawencon.util.GeneratorUtil;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    CandidateDao candidateDao;
    @Autowired
    UserDao userDao;
    @Autowired
    FileDao fileDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RestTemplate restTemplate;

    public InsertResDto insertUser(UserInsertReqDto data) {
        ConnHandler.begin();
        InsertResDto response = new InsertResDto();

        Supplier<String> supplier = () -> "system";
        final Candidate candidate = new Candidate();
        candidate.setName(data.getFullName());
        candidate.setCandidateCode(GeneratorUtil.generateCode());
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
            ResponseEntity<InsertResDto> res = restTemplate.exchange("http://localhost:8080/admin/candidate/seeker",
                    HttpMethod.POST, reqBody, InsertResDto.class);
            if (res.getStatusCode().equals(HttpStatus.CREATED)) {
                ConnHandler.commit();
                response.setId(createdUser.getId());
                response.setMessage("Success register as new candidate!");
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

    public UpdateResDto updateCandidate(String userId, UserUpdateReqDto data){
        ConnHandler.begin();
        final UpdateResDto response = new UpdateResDto();
        
        User user = userDao.getById(User.class, userId);
        Candidate candidate = user.getCandidate();

        candidate.setNik(data.getNik());
        candidate.setName(data.getName());
        candidate.setPhone(data.getPhone());
        candidate.setBirthPlace(data.getBirthPlace());
        candidate.setBirthDate(LocalDate.parse(data.getBirthDate()));
        candidate.setSocmed1(data.getSocmed1());
        candidate.setSocmed2(data.getSocmed2());
        candidate.setSocmed3(data.getSocmed3());
        candidate.setExperienceYear(data.getExperienceYear());
        candidate.setSalaryExpectation(data.getSalaryExpectation());
        if(data.getPhoto() != null){
            File filePhoto = fileDao.getById(File.class, candidate.getPhoto().getId());
            filePhoto.setFiles(data.getPhoto().getFiles());
            filePhoto.setFileFormat(data.getPhoto().getFileFormat());
            fileDao.saveAndFlush(filePhoto);
        }
        if(data.getResume() != null){
            File fileResume = fileDao.getById(File.class, candidate.getResume().getId());
            fileResume.setFiles(data.getResume().getFiles());
            fileResume.setFileFormat(data.getResume().getFileFormat());
            fileDao.saveAndFlush(fileResume);
        }
        Candidate updated = candidateDao.saveAndFlush(candidate);

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserUpdateAdminReqDto> reqBody = new HttpEntity<UserUpdateAdminReqDto>(
                new UserUpdateAdminReqDto(candidate.getCandidateCode(), data.getNik(), data.getName(), data.getPhone(), 
                data.getBirthPlace(), data.getBirthDate(), data.getSocmed1(), data.getSocmed2(), data.getSocmed3(), 
                data.getExperienceYear(), data.getSalaryExpectation(), data.getPhoto(), data.getResume()));
            
            ResponseEntity<UpdateResDto> res = restTemplate.exchange("http://localhost:8080/admin/candidate/seeker",HttpMethod.PUT,reqBody,UpdateResDto.class);
            if(res.getStatusCode().equals(HttpStatus.OK)){
                ConnHandler.commit();
                response.setVer(updated.getVersion());
                response.setMessage("Success update profile!");
            }else{
                ConnHandler.rollback();
                throw new RuntimeException("Error! Check Admin API!");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error while updating profile!");
        }
        return response;
    }
}
