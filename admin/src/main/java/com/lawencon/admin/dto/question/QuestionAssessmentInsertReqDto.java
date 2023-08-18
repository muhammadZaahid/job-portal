package com.lawencon.admin.dto.question;

public class QuestionAssessmentInsertReqDto {
    private String topicId;
    private String jobVacancyId;

    public String getJobVacancyId() {
        return jobVacancyId;
    }
    public void setJobVacancyId(String jobVacancyId) {
        this.jobVacancyId = jobVacancyId;
    }
    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
