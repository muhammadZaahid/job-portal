package com.lawencon.candidate.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.User;

@Repository
public class UserDao extends AbstractJpaDao{

    public User getByEmail(String email){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tu.id, tu.password, tc.name, tc.candidate_code, tu.candidate_id ")
		.append("FROM ")
		.append("t_user tu ")
		.append("INNER JOIN ")
		.append("t_candidate tc ON tu.candidate_id = tc.id ")
		.append("WHERE tu.email = :email");
		try {
			final Object userObj = ConnHandler.getManager().createNativeQuery(sb.toString())
					.setParameter("email", email)
					.getSingleResult();
			
			final Object[] userArr = (Object[])userObj;
			
			User user = null;
			if(userArr.length > 0) {
				user = new User();
				user.setId((userArr[0].toString()));
				user.setPassword(userArr[1].toString());
				
				final Candidate candidate = new Candidate();				
				candidate.setName(userArr[2].toString());
                candidate.setCandidateCode(userArr[3].toString());
                candidate.setId(userArr[4].toString());
				user.setCandidate(candidate);
			}
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
