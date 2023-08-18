package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Interview;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class InterviewDao extends AbstractJpaDao{
    @SuppressWarnings(value = "unchecked")
    public Interview getByApplicantId(String id){
        List<Interview> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_interview where applicant_id = :applicantId",Interview.class)
        .setParameter("applicantId",id)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
