package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.QuestionTopic;

@Repository
public class QuestionTopicDao extends AbstractJpaDao{

    @SuppressWarnings(value = "unchecked")
    public QuestionTopic getByCode(String code) {
        List<QuestionTopic> queryResult = ConnHandler.getManager()
                .createNativeQuery("SELECT * FROM t_question_topic where code = :topicCode", QuestionTopic.class)
                .setParameter("topicCode", code)
                .getResultList();

        if (queryResult.size() != 0) {
            return queryResult.get(0);
        } else {
            return null;
        }
    }
}
