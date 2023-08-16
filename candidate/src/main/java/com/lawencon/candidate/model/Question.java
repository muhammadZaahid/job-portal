package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_question")
public class Question extends BaseEntity{
    
    @Column(name="question_desc")
    private String question;

    @OneToOne
    @JoinColumn(name = "topic_id")
    private QuestionTopic questionTopic;

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public QuestionTopic getQuestionTopic() {
        return questionTopic;
    }
    public void setQuestionTopic(QuestionTopic questionTopic) {
        this.questionTopic = questionTopic;
    }    
}
