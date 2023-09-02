package com.lawencon.admin.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Candidate;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class CandidateDao extends AbstractJpaDao {

    @SuppressWarnings(value = "unchecked")
    public Candidate getByCode(String code){
        List<Candidate> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_candidate where candidate_code = :candidateCode",Candidate.class)
        .setParameter("candidateCode",code)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
       
}
