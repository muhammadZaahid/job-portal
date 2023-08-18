// package com.lawencon.admin.model;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import com.lawencon.base.BaseEntity;

// @Entity
// @Table(name="t_question_answer")
// public class QuestionAnswer extends BaseEntity{
    
//     @OneToMany
//     @JoinColumn(name="question_assessment_id")
//     private QuestionAssessment questionAssessment;

//     @Column(name="answer")
//     private String answer;

//     public QuestionAssessment getQuestionAssessment() {
//         return questionAssessment;
//     }

//     public void setQuestionAssessment(QuestionAssessment questionAssessment) {
//         this.questionAssessment = questionAssessment;
//     }

//     public String getAnswer() {
//         return answer;
//     }

//     public void setAnswer(String answer) {
//         this.answer = answer;
//     }    
// }
