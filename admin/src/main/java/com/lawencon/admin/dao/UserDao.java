package com.lawencon.admin.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.User;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class UserDao extends AbstractJpaDao{
	@PersistenceContext
	private EntityManager em;

    public User getByEmail(String email){
        final String sql = "SELECT "
				+ "tu.id, tu.password, tp.full_name "
				+ "FROM "
				+ "t_user tu "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE tu.email = :email";
		try {
			final Object userObj = this.em.createNativeQuery(sql)
					.setParameter("email", email)
					.getSingleResult();
			
			final Object[] userArr = (Object[])userObj;
			
			User user = null;
			if(userArr.length > 0) {
				user = new User();
				user.setId((userArr[0].toString()));
				user.setPassword(userArr[1].toString());
				
				final Profile profile = new Profile();
				profile.setName(userArr[2].toString());
				user.setProfile(profile);
				

			}
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
