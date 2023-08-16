package com.lawencon.admin.dto.question;

public class QuestionAnswerReqDto {
    private String answerText;
    private Boolean isCorrect;

    public String getAnswerText() {
        return answerText;
    }
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
    public Boolean getIsCorrect() {
        return isCorrect;
    }
    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }    
}
