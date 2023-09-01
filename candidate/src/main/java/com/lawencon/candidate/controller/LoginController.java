package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lawencon.candidate.dto.login.LoginReqDto;
import com.lawencon.candidate.dto.login.LoginResDto;
import com.lawencon.candidate.dto.login.TokenReqDto;
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
	@Autowired
	RestTemplate restTemplate;
    
    @PostMapping
	public ResponseEntity<?> login(@RequestBody LoginReqDto user) {
		final String tokenUrl = "http://localhost:8082/token";

		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

		authenticationManager.authenticate(auth);
		final LoginResDto loggedInUser = userService.login(user);

		final TokenReqDto tokens = new TokenReqDto();
		tokens.setId(loggedInUser.getUserId());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final RequestEntity<TokenReqDto> token = RequestEntity.post(tokenUrl).headers(headers).body(tokens);

		final ResponseEntity<String> response = restTemplate.exchange(token,String.class);

		final LoginResDto loginRes = new LoginResDto();
		loginRes.setToken(response.getBody());
		loginRes.setUserId(loggedInUser.getUserId());
		loginRes.setCandidateId(loggedInUser.getCandidateId());
		loginRes.setCandidateName(loggedInUser.getCandidateName());
        loginRes.setCandidateCode(loggedInUser.getCandidateCode());

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
}
