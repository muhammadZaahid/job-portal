package com.lawencon.candidate.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.login.LoginReqDto;
import com.lawencon.candidate.dto.login.LoginResDto;
import com.lawencon.candidate.service.JwtService;
import com.lawencon.candidate.service.UserService;

@RestController
@RequestMapping("seeker/login")
public class LoginController {

	@Autowired
    UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;
    
    @PostMapping
	public ResponseEntity<?> login(@RequestBody LoginReqDto user) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

		authenticationManager.authenticate(auth);
		final LoginResDto loggedInUser = userService.login(user);

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);

		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id", loggedInUser.getUserId());

		final LoginResDto loginRes = new LoginResDto();
		loginRes.setToken(jwtService.generateJwt(claims));
		loginRes.setUserId(loggedInUser.getUserId());
		loginRes.setCandidateId(loggedInUser.getCandidateId());
		loginRes.setCandidateName(loggedInUser.getCandidateName());
        loginRes.setCandidateCode(loggedInUser.getCandidateCode());

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
}
