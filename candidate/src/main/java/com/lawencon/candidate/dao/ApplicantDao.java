package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.Applicant;


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

    @SuppressWarnings("unchecked")
    public List<Applicant> getAllByCandidate(String candidateId){
        List<Applicant> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_applicant where candidate_id = :candidateId", Applicant.class)
        .setParameter("candidateId", candidateId)
        .getResultList();

        return queryResult;
    }
}
