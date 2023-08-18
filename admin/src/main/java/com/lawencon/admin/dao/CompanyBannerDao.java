package com.lawencon.admin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Application;
import com.lawencon.admin.model.CompanyBanner;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class CompanyBannerDao extends AbstractJpaDao{
	
	@PersistenceContext
	EntityManager em;
	

	public String getCompanyBannerByCompanyId(String data) {
		
		String result = "";
		final String sql = "SELECT file_id FROM t_company_banner WHERE company_id = :companyId"; 
		
		try {
			
			final Object getId = this.em.createNativeQuery(sql)
					.setParameter("companyId", data)
					.getSingleResult();
			
			if(getId != null) {
				result = getId.toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return result;
		}
		
		return result;
		
	}
	
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
