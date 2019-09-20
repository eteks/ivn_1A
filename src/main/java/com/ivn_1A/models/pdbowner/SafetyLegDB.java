/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
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
public class SafetyLegDB {
    public static List<Querybuilder> LoadCombinationList(String querybuilder_type) {
        try {
            System.out.println("LoadCombinationList");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            
            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Querybuilder> criteriaQuery = criteriaBuilder.createQuery(Querybuilder.class);

            Root<Querybuilder> querybuilder = criteriaQuery.from(Querybuilder.class);
            criteriaQuery.where(criteriaBuilder.equal(querybuilder.get("querybuilder_type"), querybuilder_type));
            TypedQuery<Querybuilder> qb_result = s.createQuery(criteriaQuery);
            
            tx.commit();
            s.clear();
            return qb_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"LoadCombinationList\" : " + e);
            return null;
        }
    }
    public static List<Legislationversion> GetVersionname(int vehicle_id, String version_type) {
        System.out.println("Entered GetVersionname");
        System.out.println("vehicle_id"+vehicle_id);
        try {
//            Query pdbversion = s.createQuery("FROM Pdbversion p order by p.pdb_versionname desc").setParameter("pdb_reference_version", "1.0");
//            pdbversion.setMaxResults(1);
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            // create Criteria
//            CriteriaQuery<Pdbversion> criteriaQuery = s.getCriteriaBuilder().createQuery(Pdbversion.class);           
//            criteriaQuery.from(Pdbversion.class); 
            //Working code
            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Legislationversion> criteriaQuery = criteriaBuilder.createQuery(Legislationversion.class);
            // The root of our search is sku
            Root<Legislationversion> test = criteriaQuery.from(Legislationversion.class);
            List<Predicate> restrictions = new ArrayList<>();
            restrictions.add(criteriaBuilder.isNull(test.get("legislation_reference_version")));
//            criteriaQuery.where(restrictions.toArray(new Predicate[restrictions.size()]));
            if(version_type.equals("new")){
                criteriaQuery.where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(
                                        test.get("vehicle_id"), vehicle_id
                                ),
                                criteriaBuilder.equal(
                                        test.get("version_type"),version_type
                                )
                        )
                );
            }
            else{
                criteriaQuery.where(
                        criteriaBuilder.equal(
                                test.get("vehicle_id"), vehicle_id
                        )
                );
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("legislation_versionname")));
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
    
    //Pdbversion group Data
    public static List<Tuple> loadLegislationversion_groupByVehicleId(int id, String action) {
        try {
            System.err.println("loadLegislationversion_groupByVehicleId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Legislationversion> legislationversion = criteriaQuery.from(Legislationversion.class);

            criteriaQuery.multiselect(legislationversion.get("id").alias("lid"), legislationversion.get("legislation_versionname").alias("lversion"), 
                    legislationversion.get("status").alias("status"));
            if (action.equals("edit")) {
                criteriaQuery.where(criteriaBuilder.equal(legislationversion.get("vehicle_id").get("id"), id));
            } else {
                criteriaQuery.where(criteriaBuilder.equal(legislationversion.get("status"), true), criteriaBuilder.equal(legislationversion.get("flag"), true),
                        criteriaBuilder.equal(legislationversion.get("vehicle_id").get("id"), id));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(legislationversion.get("legislation_versionname")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error \"loadLegislationversion_groupByVehicleId\" : " + e);
            return null;
        }
    }
    public static Legislationversion insertLegilsationVersion(Legislationversion legislationversion) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            s.save(legislationversion);

            tx.commit();
            s.clear();
            return legislationversion;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertLegilsationVersion\" : " + e.getMessage());
            return null;
        }
    }
    public static Querybuilder getQuerybuilder(Long id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Querybuilder qb = s.get(Querybuilder.class, id);
            tx.commit();
            s.clear();
            return qb;
        } catch (Exception e) {
            System.err.println("Error in \"getQuerybuilder\" : " + e.getMessage());
            return null;
        }
    }
    public static Legislationversion_group insertLegislationVersionGroup(Legislationversion_group leg_gp) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(leg_gp);
            tx.commit();
            s.clear();
            return leg_gp;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertPDBVersionGroup\" : " + e.getMessage());
            return null;
        }
    }
    public static Safetyversion getSafetyversion(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Safetyversion safversion = s.get(Safetyversion.class, id);
            tx.commit();
            s.clear();
            return safversion;
        } catch (Exception e) {
            System.err.println("Error in \"getSafetyversion\" : " + e.getMessage());
            return null;
        }
    }
    public static Legislationversion getLegislationversion(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Legislationversion legversion = s.get(Legislationversion.class, id);
            tx.commit();
            s.clear();
            return legversion;
        } catch (Exception e) {
            System.err.println("Error in \"getLegislationversion\" : " + e.getMessage());
            return null;
        }
    }
}
