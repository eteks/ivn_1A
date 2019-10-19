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

    public static List<Tuple> LoadACBPreviousVehicleversionStatus(ACB_Version acb) {

        try {

            System.out.println("getFeaturesByPdbId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<ACB_Version> acbRoot = criteriaQuery.from(ACB_Version.class);
            criteriaQuery.multiselect(acbRoot.get("flag").alias("flag"), acbRoot.get("status").alias("status"))
                    .where(criteriaBuilder.equal(acbRoot.get("id"), acb.getId()));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"LoadACBPreviousVehicleversionStatus\" : " + e);
            return null;
        }
    }

    public static List<Tuple> insertACBVersion(ACB_Version acb, String process, String sup, boolean isAcbsubversion) {

        try {

            float versionname = 0.0f;
            String subversion_of = null;
            float oldversionname = 0.0f;

            System.out.println("insertACBVersion");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<ACB_Version> acbRoot = criteriaQuery.from(ACB_Version.class);
            Tuple tuple = null;
            if (process.equals("create")) {

                criteriaQuery.multiselect(acbRoot.get("id").alias("id"), acbRoot.get("acb_versionname").alias("acb_versionname"))
                        .where(criteriaBuilder.isNull(acbRoot.get("subversion_of"))).orderBy(criteriaBuilder.desc(acbRoot.get("acb_versionname")));
                tuple = session.createQuery(criteriaQuery).getSingleResult();

                if (tuple == null) {
                    versionname = (float) 1.0;
                } else {
                    float acbversionname = Float.valueOf(tuple.get("acb_versionname").toString());
                    if (sup.equals("yes")) {
                        if (isAcbsubversion) {
                            criteriaQuery.multiselect(acbRoot.get("id").alias("id"), acbRoot.get("acb_versionname").alias("acb_versionname"))
                                    .where(criteriaBuilder.isNull(acbRoot.get("subversion_of"))).orderBy(criteriaBuilder.desc(acbRoot.get("acb_versionname")));
                            tuple = session.createQuery(criteriaQuery).getSingleResult();
                        }
                    } else {
                        versionname = (float) 1.0 + acbversionname;
                    }
                }
            } else {
            }

            tx.commit();
            session.clear();
            return null;
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"insertACBVersion\" : " + e);
            return null;
        }
    }
}
