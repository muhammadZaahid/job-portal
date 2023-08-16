package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.Question;

@Repository
public class QuestionDao extends AbstractJpaDao {
    @SuppressWarnings(value = "unchecked")
    public List<Question> getQuestionsByTopic(String id) {
        List<Question> queryResult = ConnHandler.getManager()
                .createNativeQuery("SELECT * FROM t_question where topic_id = :topicId", Question.class)
                .setParameter("topicId", id)
                .getResultList();

        if (queryResult.size() != 0) {
            return queryResult;
        } else {
            return null;
        }
    }
}
