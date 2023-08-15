package com.lawencon.admin.dto.applicant;

public class ApplicantUpdateSeekerReqDto {
    private String applicantCode;
    private String currentStage;

    public ApplicantUpdateSeekerReqDto(String applicantCode, String currentStage) {
        this.applicantCode = applicantCode;
        this.currentStage = currentStage;
    }
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
