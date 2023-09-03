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
import com.lawencon.candidate.dto.file.FileReqDto;
import com.lawencon.candidate.dto.login.LoginReqDto;
import com.lawencon.candidate.dto.login.LoginResDto;
import com.lawencon.candidate.dto.user.UserInsertAdminReqDto;
import com.lawencon.candidate.dto.user.UserInsertReqDto;
import com.lawencon.candidate.dto.user.UserResDto;
import com.lawencon.candidate.dto.user.UserUpdateAdminReqDto;
import com.lawencon.candidate.dto.user.UserUpdateReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.File;
import com.lawencon.candidate.model.User;
import com.lawencon.security.principal.PrincipalService;
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
    @Autowired
    PrincipalService principalService;

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
            loginResDto.setCandidateId(user.getCandidate().getId());
            loginResDto.setCandidateCode(user.getCandidate().getCandidateCode());
        }

        return loginResDto;
    }

    public UpdateResDto updateCandidate(UserUpdateReqDto data) {
        ConnHandler.begin();
        final UpdateResDto response = new UpdateResDto();
        valNonBK(data);
        User user = userDao.getById(User.class, data.getId());
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
        Boolean isPhoto = false;
        Boolean isResume = false;

        if (candidate.getPhoto() != null) {
            isPhoto = true;
        }

        if (candidate.getResume() != null) {
            isResume = true;
        }
        if (data.getPhoto().getFiles() != null && !data.getPhoto().getFiles().isEmpty()) {
            if (isPhoto) {
                File filePhoto = fileDao.getById(File.class, candidate.getPhoto().getId());
                filePhoto.setFiles(data.getPhoto().getFiles());
                filePhoto.setFileFormat(data.getPhoto().getFileFormat());
                fileDao.saveAndFlush(filePhoto);
                candidate.setPhoto(filePhoto);
            } else {
                File newPhoto = new File();
                newPhoto.setFiles(data.getPhoto().getFiles());
                newPhoto.setFileFormat(data.getPhoto().getFileFormat());
                fileDao.save(newPhoto);
                candidate.setPhoto(newPhoto);
            }
        }
        if (data.getResume() != null && !data.getResume().getFiles().isEmpty()) {
            if (isResume) {
                File fileResume = fileDao.getById(File.class, candidate.getResume().getId());
                fileResume.setFiles(data.getResume().getFiles());
                fileResume.setFileFormat(data.getResume().getFileFormat());
                fileDao.saveAndFlush(fileResume);
                candidate.setResume(fileResume);
            } else {
                File file = new File();
                file.setFiles(data.getResume().getFiles());
                file.setFileFormat(data.getResume().getFileFormat());
                fileDao.save(file);
                candidate.setResume(file);
            }
        }
        Candidate updated = candidateDao.saveAndFlush(candidate);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserUpdateAdminReqDto> reqBody = new HttpEntity<UserUpdateAdminReqDto>(
                    new UserUpdateAdminReqDto(candidate.getCandidateCode(), data.getNik(), data.getName(),
                            data.getPhone(),
                            data.getBirthPlace(), data.getBirthDate(), data.getSocmed1(), data.getSocmed2(),
                            data.getSocmed3(),
                            data.getExperienceYear(), data.getSalaryExpectation(), data.getPhoto(), data.getResume()));

            ResponseEntity<UpdateResDto> res = restTemplate.exchange("http://localhost:8080/admin/candidate/seeker",
                    HttpMethod.PUT, reqBody, UpdateResDto.class);
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                ConnHandler.commit();
                response.setVer(updated.getVersion());
                response.setMessage("Success update profile!");
            } else {
                ConnHandler.rollback();
                throw new RuntimeException("Error! Check Admin API!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while updating profile!");
        }
        return response;
    }

    public UserResDto getDetailUser() {
        final UserResDto response = new UserResDto();

        User user = userDao.getById(User.class, principalService.getAuthPrincipal().toString());
        response.setId(user.getCandidate().getId());
        response.setNik(user.getCandidate().getNik());
        response.setName(user.getCandidate().getName());
        response.setEmail(user.getCandidate().getEmail());
        response.setPhone(user.getCandidate().getPhone());
        response.setBirthPlace(user.getCandidate().getBirthPlace());
        if (user.getCandidate().getBirthDate() == null) {
            response.setBirthDate(null);
        } else {
            response.setBirthDate(user.getCandidate().getBirthDate().toString());
        }
        response.setSocmed1(user.getCandidate().getSocmed1());
        response.setSocmed2(user.getCandidate().getSocmed2());
        response.setSocmed3(user.getCandidate().getSocmed3());
        response.setExperienceYear(user.getCandidate().getExperienceYear());
        response.setSalaryExpectation(user.getCandidate().getSalaryExpectation());
        if (user.getCandidate().getPhoto() == null) {
            response.setPhotoId(null);
            response.setPhoto(null);
        } else {
            response.setPhotoId(user.getCandidate().getPhoto().getId());
            FileReqDto photo = new FileReqDto();
            photo.setFiles(user.getCandidate().getPhoto().getFiles());
            photo.setFileFormat(user.getCandidate().getPhoto().getFileFormat());
            response.setPhoto(photo);
        }
        if (user.getCandidate().getResume() == null) {
            response.setResumeId(null);
            response.setResume(null);
        } else {
            response.setResumeId(user.getCandidate().getResume().getId());
            FileReqDto resume = new FileReqDto();
            resume.setFiles(user.getCandidate().getResume().getFiles());
            resume.setFileFormat(user.getCandidate().getResume().getFileFormat());
            response.setResume(resume);
        }

        return response;
    }

    public void valNonBK(UserUpdateReqDto user) {
        if (user.getName() != null || !user.getName().isEmpty()) {
            throw new RuntimeException("Please fill your name!");
        }
        if (user.getNik() == null || user.getNik().isEmpty()) {
            throw new RuntimeException("Please fill your identity number!");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            System.out.println("kan kosong");
            throw new RuntimeException("Please fill your mobile / phone number!");
        }
        if (user.getBirthPlace() == null || user.getBirthPlace().isEmpty()) {
            throw new RuntimeException("Please fill your birth place!");
        }
        if (user.getBirthDate() == null || user.getBirthDate().isEmpty()) {
            throw new RuntimeException("Please fill your birth date!");
        }
        if (user.getExperienceYear() == null) {
            throw new RuntimeException("Please fill your experience year!");
        }
        if (user.getSalaryExpectation() == null) {
            throw new RuntimeException("Please fill your salary expectation!");
        }
    }

    public Boolean checkProfile() {
        User user = userDao.getById(User.class, principalService.getAuthPrincipal().toString());
        if (user.getCandidate().getResume() == null && user.getCandidate().getPhoto() == null) {
            return false;
        } else {
            return true;
        }
    }
}
