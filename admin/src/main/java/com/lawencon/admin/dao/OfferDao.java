package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Offer;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class OfferDao extends AbstractJpaDao{
    
    @SuppressWarnings("unchecked")
    public Offer getByApplicantId(String applicantId){
        List<Offer> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_offer where applicant_id = :applicantId",Offer.class)
        .setParameter("applicantId",applicantId)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
