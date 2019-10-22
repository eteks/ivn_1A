/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.task;

import com.ivn_1A.configs.HibernateUtil;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author ETS06
 */
public class TasksDB {

    //Tasks Data insertion
    public static Tasks insertTasks(Tasks tasks) {
        try {
            System.err.println("insertTasks");
            Tasks t = getTasksByVehicleId(tasks);
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            if (t == null) {
                s.save(tasks);
                t = tasks;
            }

            tx.commit();
            s.clear();
            return t;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'insertTasks\' : " + e);
            return null;
        }
    }

    //Tasks Data by Vehicle id
    public static Tasks getTasksByVehicleId(Tasks tasks) {
        try {
            System.err.println("getTasksByVehicleId");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tasks> criteriaQuery = criteriaBuilder.createQuery(Tasks.class);

            Root<Tasks> tasksRoot = criteriaQuery.from(Tasks.class);
            criteriaQuery.where(criteriaBuilder.equal(tasksRoot.get("vehicle_id").get("id"), tasks.getVehicle_id().getId()));
            TypedQuery<Tasks> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'getTasksByVehicleId\' : " + e);
            return null;
        }
    }

    //Tasks Data by id
    public static Tasks getTasksById(int t_id) {
        try {
            System.err.println("getTasksById");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tasks> criteriaQuery = criteriaBuilder.createQuery(Tasks.class);

            Root<Tasks> tasksRoot = criteriaQuery.from(Tasks.class);
            criteriaQuery.where(criteriaBuilder.equal(tasksRoot.get("id"), t_id));
            TypedQuery<Tasks> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'getTasksById\' : " + e);
            return null;
        }
    }

    //Tasks_Group Data insertion
    public static Tasks_Group insertTasks_Group(Tasks_Group tasks_Group) {
        try {
            System.err.println("insertTasks_Group");
//            Tasks_Group tg = getTasks_GroupByTasksId(tasks_Group);
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

//            if (tg == null) {
//                s.save(tasks_Group);
//                tg = tasks_Group;
//            }
            s.save(tasks_Group);

            tx.commit();
            s.clear();
            return tasks_Group;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'insertTasks_Group\' : " + e);
            return null;
        }
    }

    //Tasks_Group Data updation
    public static Tasks_Group updateTasks_Group(Tasks_Group tasks_Group) {

        try {

            System.out.println("updateTasks_Group");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            // create update
            CriteriaUpdate<Tasks_Group> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Tasks_Group.class);
            // set the root class
            Root<Tasks_Group> qRoot = criteriaUpdate.from(Tasks_Group.class);
            // set update and where clause
            criteriaUpdate.set("completed_by", tasks_Group.getCompleted_by())
                    .set("completed_status", tasks_Group.isCompleted_status())
                    .set("version_id", tasks_Group.getVersion_id())
                    .set("version_name", tasks_Group.getVersion_name())
                    .set("completed_date", tasks_Group.getCompleted_date())
                    .set("verfications", true)
                    .where(criteriaBuilder.equal(qRoot.get("id"), tasks_Group.getId()));
            // perform update
            int a = session.createQuery(criteriaUpdate).executeUpdate();
            tx.commit();
            session.clear();
            if (a > 0) {
                System.out.println("Done " + a + "  " + tasks_Group.getCompleted_date());
                return tasks_Group;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'updateTasks_Group\' : " + e);
            return null;
        }
    }

    //Tasks_Group Data by Tasks id
    public static Tasks_Group getTasks_GroupByTasksId(Tasks_Group tasks_Group) {
        try {
            System.err.println("getTasks_GroupByTasksId");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tasks_Group> criteriaQuery = criteriaBuilder.createQuery(Tasks_Group.class);

            Root<Tasks_Group> tgRoot = criteriaQuery.from(Tasks_Group.class);
            criteriaQuery.where(criteriaBuilder.equal(tgRoot.get("task_id").get("id"), tasks_Group.getTask_id().getId()));
            TypedQuery<Tasks_Group> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'getTasks_GroupByTasksId\' : " + e);
            return null;
        }
    }

    //Tasks_Group Data by id
    public static Tasks_Group getTasks_GroupById(int id) {
        try {
            System.err.println("getTasks_GroupById");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tasks_Group> criteriaQuery = criteriaBuilder.createQuery(Tasks_Group.class);

            Root<Tasks_Group> tgRoot = criteriaQuery.from(Tasks_Group.class);
            criteriaQuery.where(criteriaBuilder.equal(tgRoot.get("id"), id));
            TypedQuery<Tasks_Group> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'getTasks_GroupById\' : " + e);
            return null;
        }
    }

    //Tasks_Group Data
    public static List<Tasks_Group> getTasks(String sender, String receiver) {

        try {
            System.err.println("getTasks");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tasks_Group> criteriaQuery = criteriaBuilder.createQuery(Tasks_Group.class);

            Root<Tasks_Group> tgRoot = criteriaQuery.from(Tasks_Group.class);
//            criteriaQuery.where(criteriaBuilder.equal(tgRoot.get("sender_id"), sender))
//                    .orderBy(criteriaBuilder.desc(tgRoot.get("task_id").get("id")));
            criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.equal(tgRoot.get("receiver_id"), receiver),
                    criteriaBuilder.equal(tgRoot.get("sender_id"), sender)))
                    .orderBy(criteriaBuilder.desc(tgRoot.get("accepted_date")));
            TypedQuery<Tasks_Group> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'getTasks\' : " + e);
            return null;
        }
    }
//    public static Map<String, Object> getTasks(String sender, String receiver) {
//
//        try {
//            System.err.println("getTasks");
//            Session s = HibernateUtil.getThreadLocalSession();
//            Transaction tx = s.beginTransaction();
//
//            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
//            Map<String, Object> columns = new HashMap<>();
//            CriteriaQuery<Tasks_Group> criteriaQuery = criteriaBuilder.createQuery(Tasks_Group.class);
//            Root<Tasks_Group> tgRoot = criteriaQuery.from(Tasks_Group.class);
//            criteriaQuery.where(criteriaBuilder.equal(tgRoot.get("sender_id"), sender), 
//                    criteriaBuilder.equal(tgRoot.get("verfications"), true))
//                    .orderBy(criteriaBuilder.desc(tgRoot.get("task_id").get("id")));
//            columns.put("senders", s.createQuery(criteriaQuery).setMaxResults(1).getResultList());
//
//            
//            CriteriaQuery<Tasks_Group> criteriaQuerys = criteriaBuilder.createQuery(Tasks_Group.class);
//            Root<Tasks_Group> tgRoots = criteriaQuerys.from(Tasks_Group.class);
//            criteriaQuerys.where(criteriaBuilder.or(criteriaBuilder.equal(tgRoots.get("receiver_id"), receiver),
//                    criteriaBuilder.equal(tgRoots.get("sender_id"), sender)), criteriaBuilder.equal(tgRoots.get("verfications"), false))
//                    .orderBy(criteriaBuilder.desc(tgRoots.get("task_id").get("id")));
//            columns.put("receivers", s.createQuery(criteriaQuerys).setMaxResults(1).getResultList());
//
//            tx.commit();
//            s.clear();
//            return columns;
//        } catch (Exception e) {
//            System.err.println("Error in \"TasksDB\" \'getTasks\' : " + e);
//            return null;
//        }
//    }
}
