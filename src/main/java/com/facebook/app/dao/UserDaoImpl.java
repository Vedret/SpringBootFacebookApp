package com.facebook.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.facebook.app.entity.Users;
@Repository
public class UserDaoImpl implements UserDao {
	
	// need to inject the session factory
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

	@Override
	@Transactional
	
	public Users findById(String facebookId) {
		
		// get the current hibernate session
		Session session = getSession();
				
		// create a query
		Query theQuery = 
				session.createQuery("from Users where facebookId = :facebookId ", Users.class);
		theQuery.setParameter("facebookId", facebookId);
					
		// return the results		
		return (Users) ((org.hibernate.query.Query<Users>) theQuery).uniqueResult();
	}
	
	
	
	}


