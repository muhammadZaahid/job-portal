package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.candidate.model.CompanyBanner;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class CompanyBannerDao extends AbstractJpaDao{
    
	@SuppressWarnings("unchecked")
    public CompanyBanner getByCompanyId(String companyId){
        List<CompanyBanner> queryResult = ConnHandler.getManager()
        .createNativeQuery("SELECT * FROM t_company_banner where company_id = :companyId",CompanyBanner.class)
        .setParameter("companyId",companyId)
        .getResultList();

        if(queryResult.size() != 0){
           return queryResult.get(0);
        }else{
            return null;
        }
    }
}
