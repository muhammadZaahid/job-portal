package com.lawencon.admin.dto.question;

import java.util.List;

public class QuestionTopicInsertReqDto {
    private String topicTitle;
    private List<QuestionInsertReqDto> questions;
    public String getTopicTitle() {
        return topicTitle;
    }
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
    public List<QuestionInsertReqDto> getQuestions() {
        return questions;
    }
    public void setQuestions(List<QuestionInsertReqDto> questions) {
        this.questions = questions;
    }

    
}
