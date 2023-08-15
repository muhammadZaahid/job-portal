package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Application;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class ApplicationDao extends AbstractJpaDao{

    @SuppressWarnings("unchecked")
    public Application getByApplicant(String applicantId){
        List<Application> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_application where applicant_id = :applicantId",Application.class)
        .setParameter("applicantId",applicantId)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
