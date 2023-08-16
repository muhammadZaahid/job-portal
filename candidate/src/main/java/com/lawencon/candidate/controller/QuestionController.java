package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.question.QuestionTopicInsertSeekerReqDto;
import com.lawencon.candidate.dto.question.QuestionsResDto;
import com.lawencon.candidate.service.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/seeker/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping
    public ResponseEntity<InsertResDto> createQuestion(@RequestBody QuestionTopicInsertSeekerReqDto data){
        final InsertResDto response = questionService.createQuestion(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<List<QuestionsResDto>> getQuestions(@PathVariable("code") String code){
        List<QuestionsResDto> response = questionService.getQuestionByCode(code);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
