/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.acb;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.GlobalDataStore;
import com.ivn_1A.models.net_sign.ECU;
import com.ivn_1A.models.net_sign.Signals;
import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import com.ivn_1A.models.pdbowner.Querybuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
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

    public static ACB_Version insertACBVersion(ACB_Version acb, String process, boolean is_acbsubversion, String subversion_status) {

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

                //SELECT id, acb_versionname FROM acbversion where subversion_of IS NULL ORDER BY acb_versionname DESC LIMIT 1
                criteriaQuery.multiselect(acbRoot.get("id").alias("id"), acbRoot.get("acb_versionname").alias("acb_versionname"))
                        .where(criteriaBuilder.isNull(acbRoot.get("subversion_of"))).orderBy(criteriaBuilder.asc(acbRoot.get("acb_versionname")));
                tuple = session.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

                if (tuple == null) {
                    versionname = (float) 1.0;
                } else {
                    float acbversionname = Float.valueOf(tuple.get("acb_versionname").toString());
                    if (subversion_status.equals("yes")) {
                        if (is_acbsubversion) {

                            //SELECT acb2.id, acb2.acb_versionname,acb2.subversion_of FROM acbversion acb1 INNER JOIN acbversion acb2 ON acb2.subversion_of=acb1.subversion_of where acb1.id=val
                            Join<ACB_Version, ACB_Version> acbs = acbRoot.join("subversion_of", JoinType.INNER);
                            acbs.on(criteriaBuilder.equal(acbs.get("subversion_of"), acbRoot.get("subversion_of")));
                            criteriaQuery.multiselect(acbs.get("id").alias("id"), acbs.get("acb_versionname").alias("acb_versionname"),
                                    acbs.get("subversion_of").alias("subversion_of"))
                                    .where(criteriaBuilder.equal(acbs.get("id"), acb.getId()))
                                    .orderBy(criteriaBuilder.desc(acbs.get("id")));
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

                            //SELECT id, acb_versionname FROM acbversion where subversion_of=val
                            criteriaQuery.multiselect(acbRoot.get("id").alias("id"), acbRoot.get("acb_versionname").alias("acb_versionname"))
                                    .where(criteriaBuilder.equal(acbRoot.get("subversion_of"), acb.getId()))
                                    .orderBy(criteriaBuilder.asc(acbRoot.get("acb_versionname")));
                            tuple = session.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
                            if (tuple == null) {
                                criteriaQuery.multiselect(acbRoot.get("id").alias("id"), acbRoot.get("acb_versionname").alias("acb_versionname"))
                                        .where(criteriaBuilder.equal(acbRoot.get("id"), acb.getId()));
                                tuple = session.createQuery(criteriaQuery).getSingleResult();
                                versionname = (float) 0.1 + Float.valueOf(tuple.get("acb_versionname").toString());
                                oldversionname = Float.valueOf(tuple.get("acb_versionname").toString());
                            } else {
                                versionname = (float) 0.1 + Float.valueOf(tuple.get("acb_versionname").toString());
                                oldversionname = Float.valueOf(tuple.get("acb_versionname").toString());
                            }
                            String s = Float.toString(oldversionname);
                            String p = s.substring(s.indexOf('.') + 1, s.length());
                            int f_value = Integer.parseInt(p);
                            if (f_value != 9) {
                                subversion_of = String.valueOf(acb.getId());
                            }
                        }
                    } else {
                        versionname = (float) 1.0 + acbversionname;
                    }
                }

                //SELECT acb_versionname FROM acbversion where acb_versionname=val
                criteriaQuery.multiselect(acbRoot.get("id").alias("id"), acbRoot.get("acb_versionname").alias("acb_versionname"))
                        .where(criteriaBuilder.equal(acbRoot.get("acb_versionname"), versionname))
                        .orderBy(criteriaBuilder.desc(acbRoot.get("id")));
                tuple = session.createQuery(criteriaQuery).getSingleResult();
                if (tuple != null) {

                    //SELECT id, acb_versionname FROM acbversion where subversion_of IS NULL ORDER BY acb_versionname DESC LIMIT 1
                    criteriaQuery.multiselect(acbRoot.get("acb_versionname").alias("acb_versionname"))
                            .where(criteriaBuilder.isNull(acbRoot.get("subversion_of")))
                            .orderBy(criteriaBuilder.desc(acbRoot.get("acb_versionname")));
                    tuple = session.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
                    versionname = (float) 1.0 + Float.valueOf(tuple.get("acb_versionname").toString());
                }

                ACB_Version acbv = new ACB_Version(versionname, acb.getCreated_date(), acb.getModified_date(), acb.getCreated_or_updated_by(), acb.isStatus(), acb.isFlag(), Integer.parseInt(subversion_of), acb.isFeatures_fully_touchedstatus());
                session.save(acbv);

                tx.commit();
                session.clear();
                return acbv;
            } else {

                session.save(acb);
                tx.commit();
                session.clear();
                return acb;
            }
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

    public static Object insertACBSignal(Object object) {
        try {

            System.out.println("getFeaturesByPdbId");
            Object obj = null;
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
                        criteriaBuilder.equal(acbRoot.get("pdbversionGroupId").get("id"), aCB_InputSignals.getPdbversionGroupId().getId()));
                List<ACB_InputSignals> acb_in = session.createQuery(criteriaQuery).getResultList();

                if (!acb_in.isEmpty()) {

                    for (ACB_InputSignals acbin : acb_in) {
                        obj = acbin;
                    }
                    session.save(aCB_InputSignals);
                    obj = aCB_InputSignals;
                }

            } else if (object instanceof ACB_OutputSignals) {

                ACB_OutputSignals aCB_OutputSignals = (ACB_OutputSignals) object;

                CriteriaQuery<ACB_OutputSignals> criteriaQuery = criteriaBuilder.createQuery(ACB_OutputSignals.class);
                Root<ACB_OutputSignals> acbRoot = criteriaQuery.from(ACB_OutputSignals.class);
                criteriaQuery.where(criteriaBuilder.equal(acbRoot.get("id"), aCB_OutputSignals.getId()),
                        criteriaBuilder.equal(acbRoot.get("inputSignalId").get("id"), aCB_OutputSignals.getOutputSignalId().getId()),
                        criteriaBuilder.equal(acbRoot.get("inputNetwordId").get("id"), aCB_OutputSignals.getOutputNetwordId().getId()),
                        criteriaBuilder.equal(acbRoot.get("pdbversionGroupId").get("id"), aCB_OutputSignals.getPdbversionGroupId().getId()));
                List<ACB_OutputSignals> acb_out = session.createQuery(criteriaQuery).getResultList();

                if (!acb_out.isEmpty()) {

                    for (ACB_OutputSignals acbout : acb_out) {
                        obj = acbout;
                    }
                    session.save(aCB_OutputSignals);
                    obj = aCB_OutputSignals;
                }
            }

            tx.commit();
            session.clear();
            return obj;
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"insertACBSignal\" : " + e);
            return 0;
        }
    }

    public static ACB_Version_Group_M insertACBVersionGroup(ACB_Version_Group_M acbvgm, String process, String button_type) {

        try {

            int resultSet_count = 0;
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            if (process.equals("update")) {

                System.out.println("update_if");
                final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

                CriteriaQuery<ACB_Version_Group_M> criteriaQuery = criteriaBuilder.createQuery(ACB_Version_Group_M.class);
                Root<ACB_Version_Group_M> acbvgmRoot = criteriaQuery.from(ACB_Version_Group_M.class);
                criteriaQuery.where(criteriaBuilder.equal(acbvgmRoot.get("acbVersionId").get("id"), acbvgm.getAcbVersionId().getId()),
                        criteriaBuilder.equal(acbvgmRoot.get("domainAndFeaturesMapping").get("id"), acbvgm.getDomainAndFeaturesMapping().getId()));
                List<ACB_Version_Group_M> acbvgms = session.createQuery(criteriaQuery).getResultList();

                CriteriaUpdate<ACB_Version_Group_M> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(ACB_Version_Group_M.class);
                Root<ACB_Version_Group_M> qRoot = criteriaUpdate.from(ACB_Version_Group_M.class);
                for (ACB_Version_Group_M acbvgm_ : acbvgms) {
                    criteriaUpdate.set("acbVersionId", acbvgm_.getAcbVersionId())
                            .set("ivnVersionId", acbvgm_.getIvnVersionId())
                            .set("pdbVersionId", acbvgm_.getPdbVersionId())
                            .set("vehicleId", acbvgm_.getVehicleId())
                            .set("domainAndFeaturesMapping", acbvgm_.getDomainAndFeaturesMapping())
                            .set("acbInputSignalsId", acbvgm_.getAcbInputSignalsId())
                            .set("acbOutputSignalsId", acbvgm_.getAcbOutputSignalsId())
                            .set("ecuId", acbvgm_.getEcuId())
                            .set("touchedStatus", acbvgm_.isTouchedStatus())
                            .where(criteriaBuilder.equal(qRoot.get("id"), acbvgm_.getId()));
                    int a = session.createQuery(criteriaUpdate).executeUpdate();
                    GlobalDataStore.globalData.add(acbvgm_.getId());
                }
                resultSet_count = acbvgms.size();
                System.out.println("getrow_count_____  : " + acbvgms.size());

                tx.commit();
                session.clear();
                return acbvgm;
            }

            if (resultSet_count == 0) {

                session.save(acbvgm);
                if (button_type.equals("other")) {
                    tx.commit();
                    session.clear();
                    return acbvgm;
                } //Avoid this condition for storing acb data from system owner
                else {
                    GlobalDataStore.globalData.add(acbvgm.getId());
                    tx.commit();
                    session.clear();
                    return acbvgm;
                }
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"insertACBVersionGroup\" : " + e);
            return null;
        }
    }

    public static void deleteACBVersion_Group(int acbversion_id, String action_type) {

        try {
            System.out.println("deleteACBVersion_Group" + GlobalDataStore.globalData);
            System.out.println("action_type" + action_type);
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            if (action_type.equals("update") && !GlobalDataStore.globalData.isEmpty()) {

                CriteriaDelete<ACB_Version_Group_M> criteriaDelete = criteriaBuilder.createCriteriaDelete(ACB_Version_Group_M.class);
                Root<ACB_Version_Group_M> acbvRoot = criteriaDelete.from(ACB_Version_Group_M.class);
                criteriaDelete.where(criteriaBuilder.equal(acbvRoot.get("acbVersionId").get("id"), acbversion_id),
                        criteriaBuilder.not(acbvRoot.get("id")).in(StringUtils.join(GlobalDataStore.globalData, ',')));
                session.createQuery(criteriaDelete).executeUpdate();

            }
            GlobalDataStore.globalData.clear();
            tx.commit();
            session.clear();
        } catch (Exception e) {
            System.err.println("Error in \'ACB_DB\' \"deleteACBVersion_Group\" : " + e);
        }
    }
}
