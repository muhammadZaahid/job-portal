package com.lawencon.candidate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.Company;

@Repository
public class CompanyDao extends AbstractJpaDao {

    @SuppressWarnings(value = "unchecked")
    public Company getCompanyByCode(String code) {
        List<Company> queryResult = ConnHandler.getManager()
                .createNativeQuery("SELECT * FROM t_company where company_code = :companyCode", Company.class)
                .setParameter("companyCode", code)
                .getResultList();

        if (queryResult.size() != 0) {
            return queryResult.get(0);
        } else {
            return null;
        }
    }
}
