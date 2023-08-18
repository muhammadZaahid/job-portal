package com.lawencon.admin.dto.question;

public class QuestionAssessmentInsertSeekerReqDto {
    private String topicCode;
    private String jobVacancyCode;

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
