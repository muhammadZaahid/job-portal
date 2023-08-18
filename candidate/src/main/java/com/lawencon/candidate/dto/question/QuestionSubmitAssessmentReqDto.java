package com.lawencon.candidate.dto.question;

import java.util.List;

public class QuestionSubmitAssessmentReqDto {
    private String applicantId;
    private List<QuestionAssessmentAnswerReqDto> answers;
    
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public List<QuestionAssessmentAnswerReqDto> getAnswers() {
        return answers;
    }
    public void setAnswers(List<QuestionAssessmentAnswerReqDto> answers) {
        this.answers = answers;
    }

    
}
