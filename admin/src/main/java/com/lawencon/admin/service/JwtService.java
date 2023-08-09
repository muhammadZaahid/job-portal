package com.lawencon.admin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    
    String generateJwt(Map<String, Object> claims);

	Map<String, Object> parseJwt(String jwt);
}
