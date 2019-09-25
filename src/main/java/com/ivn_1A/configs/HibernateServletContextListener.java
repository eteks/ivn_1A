package com.ivn_1A.configs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            SessionFactory factory = (SessionFactory) event.getServletContext().getAttribute(HibernateServletContextListener.class.getName());
            factory.close();
        } catch (Exception e) {
            System.out.println("Error in \"HibernateServletContextListener\" contextDestroyed : " + e);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            Configuration config = new Configuration().configure();
            SessionFactory factory = config.buildSessionFactory();

            //save the Hibernate session factory into serlvet context
            event.getServletContext().setAttribute(HibernateServletContextListener.class.getName(), factory);
        } catch (Exception e) {
            System.out.println("Error in \"HibernateServletContextListener\" contextInitialized : " + e);
        }
    }
}
