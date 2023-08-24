package com.lawencon.admin.dto.applicant;

public class ApplicantDetailResDto {
    private String candidateId;
    private String currentStage;
    private Boolean application;
    private Boolean assessment;
    private Boolean interview;
    private Boolean mcu;
    private Boolean offer;
    private String jobVacancyId;
    private String appliedDate;
    
    public String getCandidateId() {
        return candidateId;
    }
    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }
    public String getCurrentStage() {
        return currentStage;
    }
    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }
    public Boolean getApplication() {
        return application;
    }
    public void setApplication(Boolean application) {
        this.application = application;
    }
    public Boolean getAssessment() {
        return assessment;
    }
    public void setAssessment(Boolean assessment) {
        this.assessment = assessment;
    }
    public Boolean getInterview() {
        return interview;
    }
    public void setInterview(Boolean interview) {
        this.interview = interview;
    }
    public Boolean getMcu() {
        return mcu;
    }
    public void setMcu(Boolean mcu) {
        this.mcu = mcu;
    }
    public Boolean getOffer() {
        return offer;
    }
    public void setOffer(Boolean offer) {
        this.offer = offer;
    }
    public String getJobVacancyId() {
        return jobVacancyId;
    }
    public void setJobVacancyId(String jobVacancyId) {
        this.jobVacancyId = jobVacancyId;
    }
    public String getAppliedDate() {
        return appliedDate;
    }
    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }
    
}
