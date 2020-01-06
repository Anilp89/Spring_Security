package com.stark.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.stark.dao.UserRepository;
import com.stark.model.User;

@Service
@Transactional
public class UserRepositoryImpl implements UserRepository{
	
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public User getActiveUser(String userName) {
		 String sql = "Select e from " + User.class.getName() + " e " //
                 + " Where e.userName = :userName ";
		 Query query = entityManager.createQuery(sql, User.class);
         query.setParameter("userName", userName);
         return (User) query.getSingleResult();
	}

}
