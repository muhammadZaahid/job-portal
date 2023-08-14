package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.JobVacancy;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class JobVacancyDao extends AbstractJpaDao{
    
    @SuppressWarnings(value = "unchecked")
    public JobVacancy getByCode(String code){
        List<JobVacancy> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_job_vacancy where job_vacancy_code = :jobVacancyCode",JobVacancy.class)
        .setParameter("jobVacancyCode",code)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
