package com.cybrilla.listener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class PaymentContext implements ServletContextListener {

	private static EntityManagerFactory emf;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		emf = Persistence.createEntityManagerFactory("cybrilla");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		emf.close();
	}

	public static EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

}
