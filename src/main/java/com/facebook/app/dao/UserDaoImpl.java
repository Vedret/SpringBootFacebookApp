package com.facebook.app.dao;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	
	@Override
	public void saveUser(Users user) {

		// get the current hibernate session
		Session session = getSession();

		// save/update the user
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.saveOrUpdate(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}
	
	@Override
	public boolean isUserExist(Users user) {
		
		return findById(user.getFacebookId())!=null;
	}
	
	@Override
	public void deleteUser(String facebookId) {
 
		// get the current hibernate session
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			// delete
			Query theQuery = session.createQuery("delete from Users where facebookId = :facebookId ");
			theQuery.setParameter("facebookId", facebookId);
			theQuery.executeUpdate();	
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
				
	}
	
	
	
	}


