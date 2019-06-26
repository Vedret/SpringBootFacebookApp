package com.facebook.app.dao;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Repository;
import com.facebook.app.entity.Photos;
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
		Query theQuery = session.createQuery("from Users where facebookId = :facebookId ", Users.class);
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
		} 
	}

	@Override
	public boolean isUserExist(Users user) {

		return findById(user.getFacebookId()) != null;

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
		} 

	}

	// save user photos to the database
	public void saveImage(PagedList<Post> posts) {

		// get the current hibernate session
		Session session = getSession();

		for (int i = 0; i < posts.size(); i++) {
			// some posts are null
			if (posts.get(i).getPicture() != null) {

				String image = posts.get(i).getPicture();

				URL url = null;
				try {
					url = new URL(image);
				} catch (MalformedURLException e3) {

					e3.printStackTrace();
				}

				InputStream in = null;
				try {
					in = new BufferedInputStream(url.openStream());
				} catch (IOException e3) {

					e3.printStackTrace();
				}

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int n = 0;
				try {
					while (-1 != (n = in.read(buf))) {
						out.write(buf, 0, n);
					}
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					in.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				byte[] response = out.toByteArray();

				Photos photo = new Photos();
				photo.setPhotoName(posts.get(i).getId());
				photo.setFacebookId(posts.get(i).getId().split("\\_")[0]);
				photo.setFacebookPhotoUrl(posts.get(i).getPicture().split("\\?")[0]);
				photo.setFacebookPhoto(response);

				// save/update
				Transaction tx = null;
				try {
					tx = session.beginTransaction();

					session.save(photo);
					tx.commit();
				} catch (Exception e) {
					if (tx != null)
						tx.rollback();
					throw e;
				}
			}
			continue;

		}
		

	}
}
