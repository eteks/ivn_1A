package com.ivn_1A.configs;

import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateServletContextListener implements ServletContextListener {

    private Configuration config;
    private SessionFactory factory;
    private static Class clazz = HibernateServletContextListener.class;

    public static final String KEY_NAME = clazz.getName();

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        factory = (SessionFactory) event.getServletContext().getAttribute(KEY_NAME);
        factory.close();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            config = new Configuration().configure();
            factory = config.buildSessionFactory();

            //save the Hibernate session factory into serlvet context
            event.getServletContext().setAttribute(KEY_NAME, factory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
