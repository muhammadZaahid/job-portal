package com.lawencon.admin.dto.interview;

public class InterviewInsertReqDto {
    private String applicantId;
    private String interviewVenue;
    private String interviewTime;
    private String interviewLocation;
    
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getInterviewTime() {
        return interviewTime;
    }
    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }
    public String getInterviewLocation() {
        return interviewLocation;
    }
    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }
    public String getInterviewVenue() {
        return interviewVenue;
    }
    public void setInterviewVenue(String interviewVenue) {
        this.interviewVenue = interviewVenue;
    }
}
