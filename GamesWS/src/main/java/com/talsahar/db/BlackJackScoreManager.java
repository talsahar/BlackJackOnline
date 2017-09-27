package com.talsahar.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class BlackJackScoreManager {

	public BlackJackScoreManager() {
	}

	public double load(String username) {
		Session session = Connection.getNewSession();
		Query<BlackJackScore> query = session.createQuery("from BlackJackScore where Username='" + username + "'");
			return (query.list().isEmpty() == true) ? -1 : query.list().get(0).getScore();
	}

	public boolean save(String username, double d) {
		if (!isExists(username))
			return false;

		boolean b = false;

		Session session = Connection.getNewSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new BlackJackScore(username,d));
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
		Query<User> query = session.createQuery("from BlackJackScore where Username='" + username + "'");
		return query.list().isEmpty();
	}

}
