package com.lawencon.candidate.dto.question;

public class QuestionAssessmentAnswerReqDto {
    private String questionId;
    private String answerId;

    public String getQuestionId() {
        return questionId;
    }
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getAnswerId() {
        return answerId;
    }
    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }    
}
