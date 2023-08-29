package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.CompanyLogo;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class CompanyLogoDao extends AbstractJpaDao {
	
	public String getCompanyLogoByCompanyId(String data) {
		String result = "";
		final String sql = "SELECT file_id FROM t_company_logo where company_id = :companyId";
		
		try {
			final Object getId = ConnHandler.getManager().createNativeQuery(sql)
					.setParameter("companyId", data)
					.getSingleResult();
			
			if(getId != null) {
				result = getId.toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
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
