package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.QuestionOption;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class QuestionOptionDao extends AbstractJpaDao{
    @SuppressWarnings("unchecked")
    public List<QuestionOption> getByQuestionId(String questionId){
        List<QuestionOption> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_question_option where question_id = :questionId",QuestionOption.class)
        .setParameter("questionId",questionId)
        .getResultList();

        return queryResult;
    }
}
