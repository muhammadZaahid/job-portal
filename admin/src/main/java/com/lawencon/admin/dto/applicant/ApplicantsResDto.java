package com.lawencon.admin.dto.applicant;

public class ApplicantsResDto {
    private String applicantId;
    private String candidateName;
    private String candidateEmail;
    private String candidateNik;
    private String jobTitle;
    private String currentStage;
    private String appliedDate;

    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getCandidateName() {
        return candidateName;
    }
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
    public String getCandidateEmail() {
        return candidateEmail;
    }
    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }
    public String getCandidateNik() {
        return candidateNik;
    }
    public void setCandidateNik(String candidateNik) {
        this.candidateNik = candidateNik;
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
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    
}
