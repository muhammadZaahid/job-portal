package com.lawencon.candidate.dto.question;

public class QuestionAssessmentInsertReqDto {
    private String jobVacancyCode;
    private String topicCode;
    public String getJobVacancyCode() {
        return jobVacancyCode;
    }
    public void setJobVacancyCode(String jobVacancyCode) {
        this.jobVacancyCode = jobVacancyCode;
    }
    public String getTopicCode() {
        return topicCode;
    }
    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }
    
}
