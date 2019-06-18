package com.facebook.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.facebook.app.entity.Users;
@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Users findById(String facebookId) {
		 Users user = entityManager.find(Users.class, facebookId);
	        if (user == null) {
	            throw new EntityNotFoundException("Can't find user for ID "
	                + facebookId);
	        }
	        return user;
	    }
	}


