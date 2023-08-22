package com.lawencon.admin.dto.assessment;

public class AssessmentUpdateReqDto {
    private String assessmentNotes;
    private Double assessmentScore;
    
    public String getAssessmentNotes() {
        return assessmentNotes;
    }
    public void setAssessmentNotes(String assessmentNotes) {
        this.assessmentNotes = assessmentNotes;
    }
    public Double getAssessmentScore() {
        return assessmentScore;
    }
    public void setAssessmentScore(Double assessmentScore) {
        this.assessmentScore = assessmentScore;
    }
    
}
