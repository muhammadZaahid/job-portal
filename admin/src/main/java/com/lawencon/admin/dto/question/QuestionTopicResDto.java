package com.lawencon.admin.dto.question;

import java.math.BigInteger;

public class QuestionTopicResDto {
    private String topicId;
    private String topicName;
    private BigInteger questionCount;

    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public String getTopicName() {
        return topicName;
    }
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    public BigInteger getQuestionCount() {
        return questionCount;
    }
    public void setQuestionCount(BigInteger questionCount) {
        this.questionCount = questionCount;
    }
    
}
