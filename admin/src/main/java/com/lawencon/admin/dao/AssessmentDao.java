package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Assessment;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class AssessmentDao extends AbstractJpaDao{
    @SuppressWarnings(value = "unchecked")
    public Assessment getByApplicantId(String id){
        List<Assessment> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_assessment where applicant_id = :applicantId",Assessment.class)
        .setParameter("applicantId",id)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
