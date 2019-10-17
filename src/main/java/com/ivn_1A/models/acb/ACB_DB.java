/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.acb;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.net_sign.ECU;
import com.ivn_1A.models.net_sign.Signals;
import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author root
 */
public class ACB_DB {

    public static List<Tuple> getPdbVersionFromFeatureVersion() {
        try {

            System.out.println("GetFeatureversionListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Featureversion> lVGRoot = criteriaQuery.from(Featureversion.class);
//            criteriaQuery.distinct(true);
            criteriaQuery.multiselect(lVGRoot.get("pdbversion_id").get("id").alias("pdbid"),
                    lVGRoot.get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname")).distinct(true);
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"getPdbVersionFromFeatureVersion\" : " + e);
            return null;
        }
    }

    public static List<Pdbversion_group> loadFeaturesByPdbId(int id) {

        try {

            System.out.println("loadFeaturesByPdbId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Pdbversion_group> criteriaQuery = criteriaBuilder.createQuery(Pdbversion_group.class);
            Root<Pdbversion_group> pdbGRoot = criteriaQuery.from(Pdbversion_group.class);
//            criteriaQuery.distinct(true);
            criteriaQuery.where(criteriaBuilder.equal(pdbGRoot.get("pdbversion_id").get("id"), id));
            TypedQuery<Pdbversion_group> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"loadFeaturesByPdbId\" : " + e);
            return null;
        }
    }

    public static Map<String, Object> getFeaturesByPdbId(Pdbversion_group pdbversion_group) {

        try {

            System.out.println("getFeaturesByPdbId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Map<String, Object> vals = new HashMap<>();

            Root<Pdbversion_group> pdbGRoot = criteriaQuery.from(Pdbversion_group.class);
//            criteriaQuery.distinct(true);
            criteriaQuery.multiselect(pdbGRoot.get("pdbversion_id").get("id").alias("pdb_id"),
                    pdbGRoot.get("pdbversion_id").get("vehicle_id").get("id").alias("vehicle_id"),
                    pdbGRoot.get("vehiclemodel_id").get("modelname").alias("modelname"),
                    pdbGRoot.get("vehiclemodel_id").get("id").alias("vmm_id"))
                    .where(criteriaBuilder.equal(pdbGRoot.get("pdbversion_id").get("id"), pdbversion_group.getId()))
                    .groupBy(pdbGRoot.get("vehiclemodel_id").get("modelname"))
                    .orderBy(criteriaBuilder.desc(pdbGRoot.get("pdbversion_id").get("id")));
            vals.put("vehicledetail_list", session.createQuery(criteriaQuery).getResultList());

            criteriaQuery.multiselect(criteriaBuilder.function("group_concat_Distinct", String.class, pdbGRoot.get("id")).alias("pdbgroup_id"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, pdbGRoot.get("available_status")).alias("status"),
                    criteriaBuilder.function("group_concat", String.class, pdbGRoot.get("vehiclemodel_id").get("id")).alias("vmm_id"),
                    pdbGRoot.get("domain_and_features_mapping_id").get("id").alias("fid"),
                    pdbGRoot.get("domain_and_features_mapping_id").get("feature_id").get("feature_name").alias("featurename"),
                    pdbGRoot.get("domain_and_features_mapping_id").get("domain_id").get("domain_name").alias("domainname"))
                    .where(criteriaBuilder.equal(pdbGRoot.get("pdbversion_id").get("id"), pdbversion_group.getId()))
                    .groupBy(pdbGRoot.get("domain_and_features_mapping_id").get("id"));
            vals.put("featuredetail_list", session.createQuery(criteriaQuery).getResultList());

            criteriaQuery.multiselect(pdbGRoot.get("pdbversion_id").get("status").alias("status"))
                    .where(criteriaBuilder.equal(pdbGRoot.get("pdbversion_id").get("id"), pdbversion_group.getId()));
            vals.put("pdbversion_status", session.createQuery(criteriaQuery).getResultList());

            tx.commit();
            session.clear();
            return vals;
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"getFeaturesByPdbId\" : " + e);
            return null;
        }
    }

    public static Map<String, Object> getSignalsAndECU() {

        try {

            System.out.println("getFeaturesByPdbId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            Map<String, Object> vals = new HashMap<>();

            CriteriaQuery<Signals> criteriaQuery = criteriaBuilder.createQuery(Signals.class);
            Root<Signals> sigRoot = criteriaQuery.from(Signals.class);
            vals.put("signals", session.createQuery(criteriaQuery).getResultList());

            CriteriaQuery<ECU> criteriaQuerye = criteriaBuilder.createQuery(ECU.class);
            Root<ECU> ecuRoot = criteriaQuerye.from(ECU.class);
            vals.put("ecu", session.createQuery(criteriaQuerye).getResultList());

            tx.commit();
            session.clear();
            return vals;
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"getSignalsAndECU\" : " + e);
            return null;
        }
    }
}
