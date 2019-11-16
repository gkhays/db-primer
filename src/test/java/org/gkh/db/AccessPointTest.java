package org.gkh.db;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AccessPointTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertUsingHibernateUtil() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		AccessPoint ap = new AccessPoint();
		
		//ap.setId(10L);
		ap.setEssid("Starbucks");
		ap.setBssid("80:2a:a8:5a:fb:2a");
		ap.setVendor("Ubiquiti Networks Inc.");
		ap.setChannel(10);
		
		session.save(ap);
		session.getTransaction().commit();
	}
	
	/**
	 * Test using JPA and Hibernate 5.
	 */
	@Test
	public void testInsert() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbManager");
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();		
		AccessPoint ap = new AccessPoint();
		
		ap.setEssid("Fourbucks");
		ap.setBssid("80:2a:a8:5a:fb:2a");
		ap.setVendor("Ubiquiti Networks Inc.");
		ap.setChannel(10);
		
		entityManager.persist(ap);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
