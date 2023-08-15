package com.lawencon.candidate.dto.applicant;

public class ApplicantUpdateReqDto {

    private String applicantCode;
    private String currentStage;
    public String getApplicantCode() {
        return applicantCode;
    }
    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode;
    }
    public String getCurrentStage() {
        return currentStage;
    }
    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }
    
}
