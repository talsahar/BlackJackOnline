package com.talsahar.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {

	private SessionFactory factory;

	private Connection() {
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

	private static class Holder {
		public static final Connection connection = new Connection();
	}

	public static Session getNewSession() {
		return Holder.connection.factory.openSession();
	}

}
