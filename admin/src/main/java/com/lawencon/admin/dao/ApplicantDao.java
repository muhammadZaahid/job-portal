package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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

    @SuppressWarnings(value = "unchecked")
    public List<Applicant> getAllByVacancy(String jobVacancyId){
        List<Applicant> queryRes = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_applicant where job_vacancy_id = :jobVacancyId", Applicant.class)
        .setParameter("jobVacancyId",jobVacancyId)
        .getResultList();

        return queryRes;
    }

    @SuppressWarnings("unchecked")
    public List<Applicant> getAllPaged(Integer page, Integer limit){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM t_applicant");
        Query query = ConnHandler.getManager()
        .createNativeQuery(sb.toString(), Applicant.class);
        
        List<Applicant> list = new ArrayList<>();

        if(page != null && limit != null){
            list = query.setFirstResult(((page - 1) * limit))
                    .setMaxResults(limit)
                    .getResultList();
        }else{
            list = query.getResultList();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    public Applicant getByCandidateAndJob(String candidateId, String jobVacancyId){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM t_applicant ");
        sb.append("WHERE candidate_id = :candidateId ");
        sb.append("AND job_vacancy_id = :jobVacancyId");

        Query query = ConnHandler.getManager().createNativeQuery(sb.toString(), Applicant.class)
        .setParameter("candidateId",candidateId)
        .setParameter("jobVacancyId",jobVacancyId);

        List<Applicant> list = query.getResultList();

        if(list.size() > 0){
            return list.get(0);
        }else{
            return new Applicant();
        }
    }

    public Boolean checkHasApplied(String candidateId, String jobVacancyId){
        if(getByCandidateAndJob(candidateId, jobVacancyId).getId() == null){
            return false;
        }else{
            return true;
        }
    }
}
