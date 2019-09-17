/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.notification;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.configs.VersionType;
import com.ivn_1A.models.admin.User;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ETS-05
 */
public class NotificationDB {

    public static int insertNotification(Notification notification) {
        try {
            System.err.println("insertNotification");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(notification);
            tx.commit();
            s.clear();
            return notification.getId();
        } catch (Exception e) {
            System.out.println("Notification creation error message" + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static Notification getNotification(int notificationId) {
        try {
            System.err.println("getNotification");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Notification notification = s.get(Notification.class, notificationId);
            tx.commit();
            s.clear();
            return notification;
        } catch (Exception e) {
            System.out.println("Notification getting error message" + e.getMessage());
            return null;
        }
    }

    public static int getGroupIdForUser(int userId) {

        try {
            System.err.println("getGroupIdForUser");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), userId));
            TypedQuery<User> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult().getId();
        } catch (Exception e) {
            System.err.println("Error : \"getGroupIdForUser\"" + e);
            return 0;
        }
    }

    public static boolean getReadStatusForNotification(int userId, int notification_id) {

        try {
            System.err.println("getReadStatusForNotification");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<StatusNotification> criteriaQuery = criteriaBuilder.createQuery(StatusNotification.class);

            Root<StatusNotification> userRoot = criteriaQuery.from(StatusNotification.class);
            criteriaQuery.where(criteriaBuilder.equal(userRoot.get("receiver_id"), userId));
            TypedQuery<StatusNotification> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result != null;
        } catch (Exception e) {
            System.err.println("Error : \"getGroupIdForUser\"" + e);
            return false;
        }
    }

    public static List<Tuple> getUnreadNotification(int user_id) {

        try {
            System.err.println("getUnreadNotification");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Notification> notificationRoot = criteriaQuery.from(Notification.class);
            Subquery<StatusNotification> subquery = criteriaQuery.subquery(StatusNotification.class);
            Root<StatusNotification> statusNotificationRoot = criteriaQuery.from(StatusNotification.class);
            subquery.select(statusNotificationRoot.get("notification_id")).distinct(true);

            criteriaQuery.multiselect(notificationRoot.get("id").alias("id"), notificationRoot.get("sender_id").get("username").alias("firstname"),
                    notificationRoot.get("version_type_id").alias("version_type_id"), notificationRoot.get("version_id").get("pdb_versionname").alias("version_id"),
                    notificationRoot.get("created_date").alias("created_date")).distinct(true)
                    .where(
                            criteriaBuilder.and(
                                    criteriaBuilder.not(notificationRoot.get("id").in(subquery)),
                                    criteriaBuilder.notEqual(notificationRoot.get("sender_id").get("id"), user_id),
                                    criteriaBuilder.like(notificationRoot.get("receiver_id").as(String.class), "'%" + user_id + "%'")
                            )
                    ).orderBy(criteriaBuilder.desc(notificationRoot.get("created_date")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

//            typedQuery.getResultList().stream().map((tuple) -> {
//                Map<String, Object> columns = new HashMap<>();
//                columns.put("id", tuple.get("id"));
//                columns.put("firstname", tuple.get("firstname"));
//                columns.put("version_type_id", tuple.get("version_type_id"));
//                columns.put("version_id", tuple.get("version_id"));
//                columns.put("created_date", tuple.get("created_date"));
//                return columns;
//            }).map((columns) -> {
//                notificationList.add(columns);
//                return columns;
//            }).forEachOrdered((columns) -> {
//                System.out.println("colums___________" + columns);
//            });
            tx.commit();
            session.clear();

            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error : \"getGroupIdForUser\"" + e);
            return null;
        }
    }

    public static List<Tuple> getNotificationList(int user_id) {
        try {

            System.err.println("getNotificationList");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Notification> notificationRoot = criteriaQuery.from(Notification.class);

            criteriaQuery.multiselect(notificationRoot.get("id").alias("id"), notificationRoot.get("sender_id").get("username").alias("firstname"),
                    notificationRoot.get("version_type_id").alias("version_type_id"), notificationRoot.get("version_id").get("pdb_versionname").alias("version_id"),
                    notificationRoot.get("created_date").alias("created_date")).distinct(true)
                    //                    .where(
                    //                            criteriaBuilder.and(
                    //                                    criteriaBuilder.notEqual(notificationRoot.get("sender_id").get("id"), user_id),
                    //                                    criteriaBuilder.like(notificationRoot.get("receiver_id").as(String.class), "'%" + user_id + "%'")
                    //                            )
                    //                    )
                    .orderBy(criteriaBuilder.desc(notificationRoot.get("created_date")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();

            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error : \"getGroupIdForUser\"" + e);
            return null;
        }
    }

    public static List<Tuple> readNotification(int notification_id) {

        try {
            System.err.println("getReadStatusForNotification");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Notification> notificationRoot = criteriaQuery.from(Notification.class);
            criteriaQuery.multiselect(notificationRoot.get("version_type_id").alias("version_type_id"), notificationRoot.get("version_id").get("id").alias("version_id"))
                    .where(criteriaBuilder.equal(notificationRoot.get("id"), notification_id));
            TypedQuery<Tuple> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error : \"getGroupIdForUser\"" + e);
            return null;
        }
    }

}
