/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ets-poc
 */
public class FeatureversionDB {
    public static List<Tuple> GetPdbversion(int vehicle_id) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();
//        Map<String, Object> results = new HashMap<String, Object>();
        
        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Pdbversion> pdbversion = criteriaQuery.from(Pdbversion.class);
        criteriaQuery.where(criteriaBuilder.equal(pdbversion.get("status"), true), criteriaBuilder.equal(pdbversion.get("flag"), true),
                        criteriaBuilder.equal(pdbversion.get("vehicle_id").get("id"), vehicle_id));
        criteriaQuery.multiselect(pdbversion.get("id").alias("pid"), pdbversion.get("pdb_versionname").alias("name"), 
                    pdbversion.get("status").alias("status")); 
        criteriaQuery.orderBy(criteriaBuilder.desc(pdbversion.get("pdb_versionname")));
        TypedQuery<Tuple> pdb_results = s.createQuery(criteriaQuery).setMaxResults(1);
        
//        results.put("pdb_results", pdb_results.getResultList());
        
        tx.commit();
        s.clear();       
        return pdb_results.getResultList();
    }
    
    public static List<Tuple> GetLegversion(int vehicle_id) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();
//        Map<String, Object> results = new HashMap<String, Object>();
        
        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Legislationversion> legversion = criteriaQuery.from(Legislationversion.class);
        criteriaQuery.where(criteriaBuilder.equal(legversion.get("status"), true), criteriaBuilder.equal(legversion.get("flag"), true),
                        criteriaBuilder.equal(legversion.get("vehicle_id").get("id"), vehicle_id));
        criteriaQuery.multiselect(legversion.get("id").alias("lid"), legversion.get("legislation_versionname").alias("name"), 
                    legversion.get("status").alias("status")); 
        criteriaQuery.orderBy(criteriaBuilder.desc(legversion.get("legislation_versionname")));
        TypedQuery<Tuple> leg_results = s.createQuery(criteriaQuery).setMaxResults(1);
        
//        results.put("pdb_results", pdb_results.getResultList());
        
        tx.commit();
        s.clear();       
        return leg_results.getResultList();
    }
    
    public static List<Tuple> GetSafetyversion(int vehicle_id) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();
//        Map<String, Object> results = new HashMap<String, Object>();
        
        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Safetyversion> safversion = criteriaQuery.from(Safetyversion.class);
        criteriaQuery.where(criteriaBuilder.equal(safversion.get("status"), true), criteriaBuilder.equal(safversion.get("flag"), true),
                        criteriaBuilder.equal(safversion.get("vehicle_id").get("id"), vehicle_id));
        criteriaQuery.multiselect(safversion.get("id").alias("sid"), safversion.get("safety_versionname").alias("name"), 
                    safversion.get("status").alias("status")); 
        criteriaQuery.orderBy(criteriaBuilder.desc(safversion.get("safety_versionname")));
        TypedQuery<Tuple> saf_results = s.createQuery(criteriaQuery).setMaxResults(1);
        
//        results.put("pdb_results", pdb_results.getResultList());
        
        tx.commit();
        s.clear();       
        return saf_results.getResultList();
    }
    public static Featureversion insertFeatureVersion(Featureversion featureversion) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            s.save(featureversion);

            tx.commit();
            s.clear();
            return featureversion;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertFeatureVersion\" : " + e.getMessage());
            return null;
        }
    }
    
    public static List<Featureversion> GetVersionname() {
        System.out.println("Entered GetVersionname");
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Featureversion> criteriaQuery = criteriaBuilder.createQuery(Featureversion.class);
            Root<Featureversion> test = criteriaQuery.from(Featureversion.class);
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("feature_versionname")));
            //create resultset as list
            Query pdbversion = s.createQuery(criteriaQuery).setMaxResults(1);

            tx.commit();
            s.clear();
            return pdbversion.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetVersionname\" : " + e.getMessage());
            return null;
        }
    }
}
