package com.ivn_1A.configs;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ThreadLocal session = new ThreadLocal();

    static {
        sessionFactory = (SessionFactory) ServletActionContext.getServletContext().getAttribute(HibernateServletContextListener.KEY_NAME);
    }

    public static Session getThreadLocalSession() {
        Session s = (Session) session.get();
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() {
        Session s = (Session) session.get();
        if (s != null) {
            s.close();
            session.set(null);
        }
    }
}
