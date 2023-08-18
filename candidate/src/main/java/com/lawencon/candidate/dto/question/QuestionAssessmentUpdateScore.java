package com.lawencon.candidate.dto.question;

public class QuestionAssessmentUpdateScore {
    private String applicantCode;
    private Double score;

    public QuestionAssessmentUpdateScore(String applicantCode, Double score) {
        this.applicantCode = applicantCode;
        this.score = score;
    }
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
