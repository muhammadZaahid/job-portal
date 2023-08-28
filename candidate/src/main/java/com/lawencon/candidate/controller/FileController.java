package com.lawencon.candidate.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.model.File;
import com.lawencon.candidate.service.FileService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("seeker/file")
public class FileController {

	@Autowired
	FileService fileService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getFileById(@PathVariable("id") String fileId){
		
		final File file = fileService.getById(fileId);
		final String fileName = "attachment";
		final byte[] fileByte = Base64.getDecoder().decode(file.getFiles());
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + "." + file.getFileFormat())
				.body(fileByte);				
	}
}
