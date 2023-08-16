package com.lawencon.admin.dto.question;

import java.util.List;

public class QuestionTopicInsertSeekerReqDto {
    private String topicTitle;
    private String topicCode;
    private List<QuestionInsertReqDto> questions;

    
    public QuestionTopicInsertSeekerReqDto(String topicTitle, String topicCode, List<QuestionInsertReqDto> questions) {
        this.topicTitle = topicTitle;
        this.topicCode = topicCode;
        this.questions = questions;
    }
    public String getTopicTitle() {
        return topicTitle;
    }
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
    public String getTopicCode() {
        return topicCode;
    }
    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }
    public List<QuestionInsertReqDto> getQuestions() {
        return questions;
    }
    public void setQuestions(List<QuestionInsertReqDto> questions) {
        this.questions = questions;
    }
}
