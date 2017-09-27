package com.talsahar.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UsersManager {

	public UsersManager() {
	}

	public boolean login(String username, String password) {
		Session session = Connection.getNewSession();
		password = "" + password.hashCode();
		Query<User> query = session.createQuery("from Users where Username='" + username + "' AND Password='" + password + "'");
		return !query.list().isEmpty();

	}

	public boolean register(String username, String password) {
		if (!isExists(username))
			return false;

		boolean b = false;

		Session session = Connection.getNewSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new User(username, "" + password.hashCode()));
			tx.commit();
			b = true;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			return b;
		}
	}

	private boolean isExists(String username) {
		Session session = Connection.getNewSession();
		Query<User> query = session.createQuery("from Users where Username='" + username + "'");
		return query.list().isEmpty();
	}

}
