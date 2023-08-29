package com.lawencon.candidate.dto.applicant;

public class ApplicantResDto {
    private String jobVacancyId;
    private String jobTitle;
    private String companyName;
    private String currentStage;
    private String appliedDate;
    private String companyPhotoId;
    private String topicId;
    
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCurrentStage() {
        return currentStage;
    }
    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }
    public String getAppliedDate() {
        return appliedDate;
    }
    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }
    public String getJobVacancyId() {
        return jobVacancyId;
    }
    public void setJobVacancyId(String jobVacancyId) {
        this.jobVacancyId = jobVacancyId;
    }
    public String getCompanyPhotoId() {
        return companyPhotoId;
    }
    public void setCompanyPhotoId(String companyPhotoId) {
        this.companyPhotoId = companyPhotoId;
    }
    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String assessmentId) {
        this.topicId = assessmentId;
    }
    
}
