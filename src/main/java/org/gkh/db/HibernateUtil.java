package org.gkh.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable t) {
			// TODO - Add a logger
			System.err.println("SessionFactory creation failed." + t);
			throw new ExceptionInInitializerError(t);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
	
}
