package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.candidate.model.CompanyLogo;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class CompanyLogoDao extends AbstractJpaDao{
    
	@SuppressWarnings("unchecked")
    public CompanyLogo getByCompanyId(String companyId){
        List<CompanyLogo> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_company_logo where company_id = :companyId",CompanyLogo.class)
        .setParameter("companyId",companyId)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
