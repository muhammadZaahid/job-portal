package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Applicant;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class ApplicantDao extends AbstractJpaDao{
    @SuppressWarnings(value = "unchecked")
    public Applicant getByCode(String code){
        List<Applicant> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_applicant where applicant_code = :applicantCode",Applicant.class)
        .setParameter("applicantCode",code)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
