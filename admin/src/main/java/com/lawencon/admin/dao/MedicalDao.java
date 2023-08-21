package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Medical;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class MedicalDao extends AbstractJpaDao{
    @SuppressWarnings(value = "unchecked")
    public Medical getByApplicantId(String id){
        List<Medical> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_mcu where applicant_id = :applicantId",Medical.class)
        .setParameter("applicantId",id)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
