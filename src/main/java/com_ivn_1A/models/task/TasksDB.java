/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_ivn_1A.models.task;

import com.ivn_1A.configs.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
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

    //Tasks_Group Data insertion
    public static Tasks_Group insertTasks_Group(Tasks_Group tasks_Group) {
        try {
            System.err.println("insertTasks_Group");
            Tasks_Group tg = getTasks_GroupByTasksId(tasks_Group);
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            if (tg == null) {
                s.save(tasks_Group);
                tg = tasks_Group;
            }

            tx.commit();
            s.clear();
            return tg;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'insertTasks_Group\' : " + e);
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
    
    //Tasks_Group Data
    public static List<Tasks_Group> getTasks() {
        
        try {
            System.err.println("getTasks_GroupByTasksId");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tasks_Group> criteriaQuery = criteriaBuilder.createQuery(Tasks_Group.class);

            Root<Tasks_Group> tgRoot = criteriaQuery.from(Tasks_Group.class);
            criteriaQuery.distinct(true);
            TypedQuery<Tasks_Group> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"TasksDB\" \'getTasks\' : " + e);
            return null;
        }
    }
    
}
