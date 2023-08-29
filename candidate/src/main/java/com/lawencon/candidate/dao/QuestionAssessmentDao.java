package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.QuestionAssessment;

@Repository
public class QuestionAssessmentDao extends AbstractJpaDao{
    
    @SuppressWarnings("unchecked")
    public QuestionAssessment getByJobId(String jobVacancyId){
        List<QuestionAssessment> result = ConnHandler.getManager().createNativeQuery(
            "SELECT * FROM t_question_assessment WHERE job_vacancy_id = :jobVacancyId", QuestionAssessment.class)
            .setParameter("jobVacancyId",jobVacancyId)
            .getResultList();

        if(result != null){
            return result.get(0);
        }else{
            return null;
        }
    }
}
