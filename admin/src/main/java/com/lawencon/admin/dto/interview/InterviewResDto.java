package com.lawencon.admin.dto.interview;

public class InterviewResDto {
    private String id;
    private String applicantId;
    private String interviewVenue;
    private String interviewTime;
    private String interviewLocation;
    private String interviewNote;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getInterviewVenue() {
        return interviewVenue;
    }
    public void setInterviewVenue(String interviewVenue) {
        this.interviewVenue = interviewVenue;
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
    public String getInterviewNote() {
        return interviewNote;
    }
    public void setInterviewNote(String interviewNote) {
        this.interviewNote = interviewNote;
    }
}
