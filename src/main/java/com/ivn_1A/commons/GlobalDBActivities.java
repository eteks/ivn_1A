/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.commons;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.Groups;
import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.pdbowner.Domain_and_Features_Mapping;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.ivn_1A.models.pdbowner.Vehiclemodel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ETS-05
 */
public class GlobalDBActivities {

    public static Map<String, Integer> GetModuleCount() {
        try {
            Map<String, Integer> map = new HashMap<>();
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            // create Criteria
            CriteriaQuery<Vehicle> vehicleQuery = s.getCriteriaBuilder().createQuery(Vehicle.class);
            vehicleQuery.from(Vehicle.class);
            //create resultset as list
            int vehiclecount = s.createQuery(vehicleQuery).getResultList().size();
            map.put("vehiclecount", vehiclecount);
            
            // create Criteria
            CriteriaQuery<User> userQuery = s.getCriteriaBuilder().createQuery(User.class);
            userQuery.from(User.class);
            //create resultset as list
            int usercount = s.createQuery(userQuery).getResultList().size();
            map.put("usercount", usercount);
            
            // create Criteria
            CriteriaQuery<Vehiclemodel> vehiclemodelQuery = s.getCriteriaBuilder().createQuery(Vehiclemodel.class);
            vehiclemodelQuery.from(Vehiclemodel.class);
            //create resultset as list
            int vehiclemodelcount = s.createQuery(vehiclemodelQuery).getResultList().size();
            map.put("modelcount", vehiclemodelcount);
            
            tx.commit();
            s.clear();
            return map;
        } catch (Exception e) {
            System.err.println("Error in \"GlobalDBActivities\" : " + e);
            return null;
        }
    }
    
    public static List<Tuple> GetUserGroups() {
        
        try {
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Groups> pRoot = criteriaQuery.from(Groups.class);

            criteriaQuery.multiselect(pRoot.get("id").alias("id"), pRoot.get("group_name").alias("group_name"),
                    pRoot.get("status").alias("status"), pRoot.get("route_pages").alias("route_pages"),
                    pRoot.get("is_superadmin").alias("is_superadmin"));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetUserGroups\" : " + e);
            return null;
        }
    }
}
