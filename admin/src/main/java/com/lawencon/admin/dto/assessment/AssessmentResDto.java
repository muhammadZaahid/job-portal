package com.lawencon.admin.dto.assessment;

public class AssessmentResDto {
    private String id;
    private String picId;
    private String picName;
    private String picEmail;
    private String picPhone;
    private String assessmentNote;
    private Double assessmentScore;
    private String assessmentTime;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPicId() {
        return picId;
    }
    public void setPicId(String picId) {
        this.picId = picId;
    }
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public String getPicEmail() {
        return picEmail;
    }
    public void setPicEmail(String picEmail) {
        this.picEmail = picEmail;
    }
    public String getPicPhone() {
        return picPhone;
    }
    public void setPicPhone(String picPhone) {
        this.picPhone = picPhone;
    }
    public String getAssessmentNote() {
        return assessmentNote;
    }
    public void setAssessmentNote(String assessmentNote) {
        this.assessmentNote = assessmentNote;
    }
    public Double getAssessmentScore() {
        return assessmentScore;
    }
    public void setAssessmentScore(Double assessmentScore) {
        this.assessmentScore = assessmentScore;
    }
    public String getAssessmentTime() {
        return assessmentTime;
    }
    public void setAssessmentTime(String assessmentTime) {
        this.assessmentTime = assessmentTime;
    }


}
