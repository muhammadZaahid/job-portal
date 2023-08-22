package com.lawencon.admin.dao;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class QuestionDao extends AbstractJpaDao{
    public BigInteger getCountByTopicId(String topicId){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) FROM t_question where topic_id = :topicId");
        Query query = ConnHandler.getManager().createNativeQuery(sb.toString())
        .setParameter("topicId",topicId);

        return (BigInteger)query.getSingleResult();
    }
}
