package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.question.QuestionAssessmentInsertReqDto;
import com.lawencon.admin.dto.question.QuestionResDto;
import com.lawencon.admin.dto.question.QuestionTopicInsertReqDto;
import com.lawencon.admin.service.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/admin/questions")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionResDto>> getQuestions(){
        List<QuestionResDto> response = questionService.getAll();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InsertResDto> createQuestion(@RequestBody QuestionTopicInsertReqDto data){
        final InsertResDto response = questionService.createQuestion(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/assessment")
    public ResponseEntity<InsertResDto> insertAssessmentQuestion(@RequestBody QuestionAssessmentInsertReqDto data){
        final InsertResDto response = questionService.insertAssessment(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
