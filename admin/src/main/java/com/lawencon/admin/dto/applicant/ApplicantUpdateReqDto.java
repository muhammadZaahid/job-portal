package com.lawencon.admin.dto.applicant;

public class ApplicantUpdateReqDto {
    private String applicantId;
    private String currentStage;
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getCurrentStage() {
        return currentStage;
    }
    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    
}
