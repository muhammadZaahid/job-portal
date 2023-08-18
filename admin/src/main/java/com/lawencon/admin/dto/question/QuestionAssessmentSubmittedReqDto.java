package com.lawencon.admin.dto.question;

public class QuestionAssessmentSubmittedReqDto {
    private String applicantCode;
    private Double score;
    
    public String getApplicantCode() {
        return applicantCode;
    }
    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

    
}
