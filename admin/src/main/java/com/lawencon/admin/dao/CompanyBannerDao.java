package com.lawencon.admin.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;

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
	
}
