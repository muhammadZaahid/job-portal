package com.lawencon.admin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_question_assessment",uniqueConstraints = {@UniqueConstraint(columnNames = {"job_vacancy_id","topic_id"})})
public class QuestionAssessment extends BaseEntity{

    @OneToOne
    @JoinColumn(name="job_vacancy_id")
    private JobVacancy jobVacancy;

    @OneToOne
    @JoinColumn(name="topic_id")
    private QuestionTopic questionTopic;

    public JobVacancy getJobVacancy() {
        return jobVacancy;
    }

    public void setJobVacancy(JobVacancy jobVacancy) {
        this.jobVacancy = jobVacancy;
    }

    public QuestionTopic getQuestionTopic() {
        return questionTopic;
    }

    public void setQuestionTopic(QuestionTopic questionTopic) {
        this.questionTopic = questionTopic;
    }

}
