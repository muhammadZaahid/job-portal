package com.lawencon.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Blacklist;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class BlacklistDao extends AbstractJpaDao {

    @SuppressWarnings("unchecked")
    public Blacklist getBlacklist(String candidateId, String companyId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM t_blacklist ");
        sb.append("WHERE candidate_id = :candidateId ");
        sb.append("AND company_id = :companyId");

        try {
            List<Blacklist> result = ConnHandler.getManager().createNativeQuery(sb.toString(), Blacklist.class)
                    .setParameter("candidateId", candidateId)
                    .setParameter("companyId", companyId)
                    .getResultList();

            if (result != null) {
                return result.get(0);
            } else {
                return new Blacklist();
            }
        } catch (Exception e) {
            return new Blacklist();
        }
    }

    public Boolean isBlacklist(String candidateId, String companyId) {
        if (getBlacklist(candidateId, companyId).getId() == null) {
            return false;
        } else {
            return true;
        }
    }
}
