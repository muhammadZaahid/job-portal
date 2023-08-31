package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    public Boolean checkApplied(String candidateId, String jobVacancyId){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM t_applicant ");
        sb.append("WHERE candidate_id = :candidateId ");
        sb.append("AND job_vacancy_id = :jobVacancyId ");
        try{
            Object queryResult = ConnHandler.getManager()
        .createNativeQuery(sb.toString(), Applicant.class)
        .setParameter("candidateId",candidateId)
        .setParameter("jobVacancyId", jobVacancyId)
        .getSingleResult();

        if(queryResult != null){
            return true;
        }else{
            return false;
        }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public String getApplicantId(String candidateId, String jobVacancyId ) {
        String sql = "SELECT id FROM t_applicant ta "
                + "WHERE ta.candidate_id = :candidateId "
                + "AND ta.job_vacancy_id = :jobVacancId ";
        
        try {
        	
        	String result = (String) ConnHandler.getManager()
        					.createNativeQuery(sql)
        					.setParameter("candidateId", candidateId)
                            .setParameter("jobVacancId", jobVacancyId)
                            .getSingleResult();
        	return result;
        }catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
}
