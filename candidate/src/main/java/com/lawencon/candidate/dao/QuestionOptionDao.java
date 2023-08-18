package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.QuestionOption;

@Repository
public class QuestionOptionDao extends AbstractJpaDao{

    @SuppressWarnings("unchecked")
    public List<QuestionOption> getOptionsByQuestion(String id){
        List<QuestionOption> queryResult = ConnHandler.getManager()
                .createNativeQuery("SELECT * FROM t_question_option where question_id = :questionId", QuestionOption.class)
                .setParameter("questionId", id)
                .getResultList();

        if(queryResult.size() > 0){
            return queryResult;
        }else{
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public Boolean getIsCorrect(String questionId, String answerId){
        List<QuestionOption> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_question_option where question_id = :questionId and id = :answerId",QuestionOption.class)
        .setParameter("questionId",questionId)
        .setParameter("answerId",answerId)
        .getResultList();

        if(queryResult.size() > 0){
            return queryResult.get(0).getIsCorrect();
        }else{
            return null;
        }
    }
}
