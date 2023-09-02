package com.lawencon.admin.dao;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class ConstantDao extends AbstractJpaDao {

	public String getTotal(String tableName) {
		
		final String sql = "SELECT COUNT(*) FROM " + tableName;
		
		try {
			
			BigInteger result = (BigInteger) ConnHandler.getManager()
								.createNativeQuery(sql)
								.getSingleResult();
			return result.toString();
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
