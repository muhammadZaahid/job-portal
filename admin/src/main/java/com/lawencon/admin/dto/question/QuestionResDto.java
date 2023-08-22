package com.lawencon.admin.dto.question;

import java.util.List;

public class QuestionResDto {
    private String questionId;
    private String questionDesc;
    private List<QuestionAnswerResDto> answerOptions;

    public String getQuestionId() {
        return questionId;
    }
    public void setQuestionId(String id) {
        this.questionId = id;
    }
    public String getQuestionDesc() {
        return questionDesc;
    }
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }
    public List<QuestionAnswerResDto> getAnswerOptions() {
        return answerOptions;
    }
    public void setAnswerOptions(List<QuestionAnswerResDto> answerOptions) {
        this.answerOptions = answerOptions;
    }
  
}
