package com.lawencon.candidate.dto.question;

import java.util.List;

public class QuestionInsertReqDto {
    private String questionDesc;
    private List<QuestionAnswerReqDto> options;
    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public List<QuestionAnswerReqDto> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionAnswerReqDto> options) {
        this.options = options;
    }
    
}
