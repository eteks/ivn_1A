/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.legislation_safty;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.pdbowner.Legislationversion_group;
import com.ivn_1A.models.pdbowner.Querybuilder;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ETS-05
 */
public class SafetyDB {

    public static List<Tuple> LoadPreviousSafetyCombinationData(Querybuilder querybuilder) {
        try {

            System.out.println("LoadPreviousLegislationCombinationData");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Querybuilder> qBRoot = criteriaQuery.from(Querybuilder.class);
            criteriaQuery.multiselect(qBRoot.get("querybuilder_name").alias("querybuilder_name"), qBRoot.get("querybuilder_type").alias("querybuilder_type"),
                    qBRoot.get("querybuilder_condition").alias("querybuilder_condition"), qBRoot.get("querybuilder_status").alias("querybuilder_status"))
                    .distinct(true).where(criteriaBuilder.equal(qBRoot.get("id"), querybuilder.getId()));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"LoadPreviousSafetyCombinationData\" : " + e);
            return null;
        }
    }

    public static long insertSafetyCombination(Querybuilder querybuilder) {

        try {

            System.out.println("LoadPreviousLegislationCombinationData");
            System.out.println("status_value" + querybuilder.getQuerybuilder_status());
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            if (querybuilder.getQuerybuilder_status()) {

                session.save(querybuilder);
                tx.commit();
                session.clear();
                return querybuilder.getId();
            } else {

                final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                // create update
                CriteriaUpdate<Querybuilder> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Querybuilder.class);
                // set the root class
                Root<Querybuilder> qRoot = criteriaUpdate.from(Querybuilder.class);
                // set update and where clause
                criteriaUpdate.set("querybuilder_name", querybuilder.getQuerybuilder_name())
                        .set("querybuilder_type", querybuilder.getQuerybuilder_type())
                        .set("querybuilder_condition", querybuilder.getQuerybuilder_condition())
                        .set("querybuilder_status", querybuilder.getQuerybuilder_status())
                        .set("created_date", querybuilder.getCreated_date())
                        .where(criteriaBuilder.equal(qRoot.get("id"), querybuilder.getId()));
                // perform update
                int a = session.createQuery(criteriaUpdate).executeUpdate();
                tx.commit();
                session.clear();
                if (a > 0) {
                    return querybuilder.getId();
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            System.err.println("Error in \"insertSafetyCombination\" : " + e);
            return 0;
        }
    }

    public static List<Tuple> GetSafetyListing() {
        try {

            System.out.println("GetSafetyListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Legislationversion_group> lVGRoot = criteriaQuery.from(Legislationversion_group.class);
//            criteriaQuery.distinct(true);
            criteriaQuery.multiselect(lVGRoot.get("legislationversion_id").get("id").alias("saf_id"), lVGRoot.get("legislationversion_id").get("legislation_versionname").alias("saf"), 
                    lVGRoot.get("legislationversion_id").get("created_date").alias("created_date"), lVGRoot.get("legislationversion_id").get("modified_date").alias("modified_date"),
                    lVGRoot.get("legislationversion_id").get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname"), lVGRoot.get("legislationversion_id").get("vehicle_id").get("vehiclename").alias("vehiclename"), 
                    lVGRoot.get("legislationversion_id").get("flag").alias("flag"), lVGRoot.get("legislationversion_id").get("status").alias("status"),
                    criteriaBuilder.function("group_concat", String.class, lVGRoot.get("vehiclemodel_id").get("modelname")).alias("modelname"))
                    .distinct(true).orderBy(criteriaBuilder.desc(lVGRoot.get("legislationversion_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetSafetyListing\" : " + e);
            return null;
        }
    }
    
    
    public static List<Tuple> GetSafetyCombinationListing() {
        try {

            System.out.println("GetLegislationCombinationListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Querybuilder> qBRoot = criteriaQuery.from(Querybuilder.class);
            criteriaQuery.multiselect(qBRoot.get("id").alias("saf_id"), qBRoot.get("querybuilder_name").alias("saf"), qBRoot.get("created_date").alias("created_date"),
                    qBRoot.get("modified_date").alias("modified_date"), qBRoot.get("querybuilder_condition").alias("combination"), qBRoot.get("querybuilder_status").alias("status"))
                    .distinct(true).where(criteriaBuilder.equal(qBRoot.get("querybuilder_type"), "safety"))
                    .orderBy(criteriaBuilder.desc(qBRoot.get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetSafetyCombinationListing\" : " + e);
            return null;
        }
    }
}
