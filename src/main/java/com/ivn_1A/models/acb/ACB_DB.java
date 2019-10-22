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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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

    public static List<Tuple> insertACBVersion(ACB_Version acb, String process, boolean is_acbsubversion, String subversion_status) {

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
                    if (subversion_status.equals("yes")) {
                        if (is_acbsubversion) {
                            
                            Join<ACB_Version, ACB_Version> acbs = acbRoot.join("subversion_of", JoinType.INNER);
                            acbs.on(criteriaBuilder.equal(acbs.get("subversion_of"), acbRoot.get("subversion_of")));
                            criteriaQuery.multiselect(acbs.get("id").alias("id"), acbs.get("acb_versionname").alias("acb_versionname"), 
                                    acbs.get("subversion_of").alias("subversion_of"))
                                    .where(criteriaBuilder.equal(acbs.get("id"), acb.getId()));
                            tuple = session.createQuery(criteriaQuery).getSingleResult();
                            versionname = (float) 0.1 + Float.valueOf(tuple.get("acb_versionname").toString());
                            oldversionname = Float.valueOf(tuple.get("acb_versionname").toString());
                            String s = Float.toString(oldversionname);
                            String p = s.substring(s.indexOf('.') + 1, s.length());
                            int f_value = Integer.parseInt(p);
                            if (f_value != 9) {
                                subversion_of = tuple.get("subversion_of").toString();
                            }
                        } else {
                            criteriaQuery.multiselect(acbRoot.get("id").alias("id"), acbRoot.get("acb_versionname").alias("acb_versionname"))
                                    .where(criteriaBuilder.isNull(acbRoot.get("subversion_of")))
                                    .orderBy(criteriaBuilder.desc(acbRoot.get("acb_versionname")));
                            tuple = session.createQuery(criteriaQuery).getSingleResult();
                            versionname = (float) 0.1 + Float.valueOf(tuple.get("acb_versionname").toString());
                            oldversionname = Float.valueOf(tuple.get("acb_versionname").toString());
                            String s = Float.toString(oldversionname);
                            String p = s.substring(s.indexOf('.') + 1, s.length());
                            int f_value = Integer.parseInt(p);
                            if (f_value != 9) {
                                subversion_of = tuple.get("subversion_of").toString();
                            }
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

    public static ACB_Version loadACBPreviousVehicleversionStatus(ACB_Version acb) throws SQLException {

        try {

            System.err.println("LoadIVNPreviousVehicleversionStatus");

            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ACB_Version> criteriaQuery = criteriaBuilder.createQuery(ACB_Version.class);
            Root<ACB_Version> ivnRoot = criteriaQuery.from(ACB_Version.class);

            criteriaQuery.distinct(true).where(criteriaBuilder.equal(ivnRoot.get("id"), acb.getId()));

            TypedQuery<ACB_Version> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"insertACBVersion\" : " + e);
            return null;
        }
    }

    public static int insertACBSignal(Object object) {
        try {

            System.out.println("getFeaturesByPdbId");
            int last_inserted_id = 0;
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            if (object instanceof ACB_InputSignals) {

                ACB_InputSignals aCB_InputSignals = (ACB_InputSignals) object;

                CriteriaQuery<ACB_InputSignals> criteriaQuery = criteriaBuilder.createQuery(ACB_InputSignals.class);
                Root<ACB_InputSignals> acbRoot = criteriaQuery.from(ACB_InputSignals.class);
                criteriaQuery.where(criteriaBuilder.equal(acbRoot.get("id"), aCB_InputSignals.getId()),
                        criteriaBuilder.equal(acbRoot.get("inputSignalId").get("id"), aCB_InputSignals.getInputSignalId().getId()),
                        criteriaBuilder.equal(acbRoot.get("inputNetwordId").get("id"), aCB_InputSignals.getInputNetwordId().getId()),
                        criteriaBuilder.equal(acbRoot.get("networkType"), aCB_InputSignals.getNetworkType()),
                        criteriaBuilder.equal(acbRoot.get("pdbversionGroupId").get("id"), aCB_InputSignals.getPdbversionGroupId().getId()));
                List<ACB_InputSignals> acb_in = session.createQuery(criteriaQuery).getResultList();

                if (!acb_in.isEmpty()) {

                    for (ACB_InputSignals acbin : acb_in) {
                        last_inserted_id = acbin.getId();
                    }
                    session.save(aCB_InputSignals);
                    last_inserted_id = aCB_InputSignals.getId();
                }

            } else if (object instanceof ACB_OutputSignals) {

                ACB_OutputSignals aCB_OutputSignals = (ACB_OutputSignals) object;

                CriteriaQuery<ACB_OutputSignals> criteriaQuery = criteriaBuilder.createQuery(ACB_OutputSignals.class);
                Root<ACB_OutputSignals> acbRoot = criteriaQuery.from(ACB_OutputSignals.class);
                criteriaQuery.where(criteriaBuilder.equal(acbRoot.get("id"), aCB_OutputSignals.getId()),
                        criteriaBuilder.equal(acbRoot.get("inputSignalId").get("id"), aCB_OutputSignals.getOutputSignalId().getId()),
                        criteriaBuilder.equal(acbRoot.get("inputNetwordId").get("id"), aCB_OutputSignals.getOutputNetwordId().getId()),
                        criteriaBuilder.equal(acbRoot.get("networkType"), aCB_OutputSignals.getNetworkType()),
                        criteriaBuilder.equal(acbRoot.get("pdbversionGroupId").get("id"), aCB_OutputSignals.getPdbversionGroupId().getId()));
                List<ACB_OutputSignals> acb_out = session.createQuery(criteriaQuery).getResultList();

                if (!acb_out.isEmpty()) {

                    for (ACB_OutputSignals acbout : acb_out) {
                        last_inserted_id = acbout.getId();
                    }
                    session.save(aCB_OutputSignals);
                    last_inserted_id = aCB_OutputSignals.getId();
                }
            }

            tx.commit();
            session.clear();
            return last_inserted_id;
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"insertACBSignal\" : " + e);
            return 0;
        }
    }
}
