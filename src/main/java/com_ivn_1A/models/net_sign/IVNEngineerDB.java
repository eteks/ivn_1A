/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_ivn_1A.models.net_sign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Querybuilder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ETS06
 */
public class IVNEngineerDB {

    //Insert Network Data
    public static Network insertNetworkData(Network network) {

        try {
            System.err.println("insertNetworkData");
            Network network1 = getNetworkByName(network);
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            if (network.getNetwork_type().equals("can") || network.getNetwork_type().equals("lin") || network.getNetwork_type().equals("hardware")) {

                if (network1 == null) {
                    session.save(network);
                    network1 = network;
                } else {
                    System.err.println("Null Values");
                }
            }

            tx.commit();
            session.clear();
            return network1;
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertNetworkData\' " + e);
            return null;
        }
    }

    //Network Data by Name
    public static Network getNetworkByName(Network network) {
        try {
            System.err.println("getNetworkByName");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Network> criteriaQuery = criteriaBuilder.createQuery(Network.class);

            Root<Network> networkRoot = criteriaQuery.from(Network.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("network_name"), network.getNetwork_name()),
                    criteriaBuilder.equal(networkRoot.get("network_type"), network.getNetwork_type()));
            TypedQuery<Network> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getNetworkByName\' " + e);
            return null;
        }
    }

    //Network Data by Id
    public static Network getNetworkById(int id) {
        try {
            System.err.println("getNetworkById");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Network> criteriaQuery = criteriaBuilder.createQuery(Network.class);

            Root<Network> networkRoot = criteriaQuery.from(Network.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("id"), id));
            TypedQuery<Network> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getNetworkById\' " + e);
            return null;
        }
    }

    //Insert Network Data
    public static ECU insertECUData(ECU ecu) {

        try {
            System.err.println("insertNetworkData");
            ECU ecu1 = getECUByName(ecu);
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            if (ecu1 == null) {
                session.save(ecu);
                ecu1 = ecu;
            } else {
                System.err.println("Null Values");
            }

            tx.commit();
            session.clear();
            return ecu1;
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertECUData\' " + e);
            return null;
        }
    }

    //ECU Data by Name
    public static ECU getECUByName(ECU ecu) {
        try {
            System.err.println("getECUByName");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ECU> criteriaQuery = criteriaBuilder.createQuery(ECU.class);

            Root<ECU> networkRoot = criteriaQuery.from(ECU.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("ecu_name"), ecu.getEcu_name()));
            TypedQuery<ECU> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getECUByName\' " + e);
            return null;
        }
    }

    //ECU Data by ID
    public static ECU getECUById(int id) {
        try {
            System.err.println("getECUById");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ECU> criteriaQuery = criteriaBuilder.createQuery(ECU.class);

            Root<ECU> networkRoot = criteriaQuery.from(ECU.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("id"), id));
            TypedQuery<ECU> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getECUById\' " + e);
            return null;
        }
    }

    public static List<Tuple> LoadNetwork() {
        try {

            System.err.println("LoadNetwork");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Network> networkRoot = criteriaQuery.from(Network.class);

            criteriaQuery.multiselect(networkRoot.get("id").alias("id"), networkRoot.get("network_name").alias("listitem"),
                    networkRoot.get("network_type").alias("ntype")).distinct(true)
                    .where(criteriaBuilder.equal(networkRoot.get("status"), true))
                    .orderBy(criteriaBuilder.desc(networkRoot.get("id")));

            TypedQuery<Tuple> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'LoadNetwork\' " + e);
            return null;
        }
    }

    public static List<Tuple> LoadECU() {

        try {

            System.err.println("LoadECU");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<ECU> ecuRoot = criteriaQuery.from(ECU.class);

            criteriaQuery.multiselect(ecuRoot.get("id").alias("id"), ecuRoot.get("ecu_name").alias("listitem"),
                    ecuRoot.get("ecu_description").alias("description")).distinct(true)
                    .where(criteriaBuilder.equal(ecuRoot.get("status"), true))
                    .orderBy(criteriaBuilder.desc(ecuRoot.get("id")));

            TypedQuery<Tuple> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'LoadECU\' " + e);
            return null;
        }
    }

    public static List<Tuple> LoadSignals() {

        try {

            System.err.println("LoadSignals");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Signals> ecuRoot = criteriaQuery.from(Signals.class);

            criteriaQuery.multiselect(ecuRoot.get("id").alias("id"), ecuRoot.get("signal_name").alias("listitem"),
                    ecuRoot.get("signal_description").alias("description")).distinct(true)
                    .where(criteriaBuilder.equal(ecuRoot.get("status"), true))
                    .orderBy(criteriaBuilder.desc(ecuRoot.get("id")));

            TypedQuery<Tuple> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'LoadSignals\' " + e);
            return null;
        }
    }

    //Insert Network Data
    public static Signals insertSignalData(Signals signals) {

        try {
            System.err.println("insertNetworkData");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            session.save(signals);

            tx.commit();
            session.clear();
            return signals;
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertSignalData\' " + e);
            return null;
        }
    }

    //Signal Data by Name
    public static Signals getSignalByName(String signals) {
        try {
            System.err.println("getECUByName");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Signals> criteriaQuery = criteriaBuilder.createQuery(Signals.class);

            Root<Signals> networkRoot = criteriaQuery.from(Signals.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("signal_name"), signals));
            TypedQuery<Signals> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getSignalByName\' " + e);
            return null;
        }
    }

    //Insert Network Data
    public static SignalTags insertSignalTagsData(SignalTags signalTags) {

        try {
            System.err.println("insertNetworkData");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            session.save(signalTags);

            tx.commit();
            session.clear();
            return signalTags;
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertSignalTagsData\' " + e);
            return null;
        }
    }

    //SignalTags Data by Name
    public static SignalTags getSignalTagsByName(String tagname) {
        try {
            System.err.println("getSignalTagsByName");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<SignalTags> criteriaQuery = criteriaBuilder.createQuery(SignalTags.class);

            Root<SignalTags> networkRoot = criteriaQuery.from(SignalTags.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("tagname"), tagname));
            TypedQuery<SignalTags> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getSignalTagsByName\' " + e);
            return null;
        }
    }

    //Signal Data by ID
    public static Signals getSignalDataByID(int id) {
        try {
            System.err.println("getSignalDataByID");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Signals> criteriaQuery = criteriaBuilder.createQuery(Signals.class);

            Root<Signals> signalRoot = criteriaQuery.from(Signals.class);
//            signalRoot.join("can_id_group", JoinType.INNER).on(criteriaBuilder.greaterThan(
//                    criteriaBuilder.function("find_in_set", Integer.class, signalRoot.get("can_id_group").get("id")
//                    ), 0));
//            signalRoot.join("vehiclemodel_id", JoinType.INNER);
            criteriaQuery.where(criteriaBuilder.equal(signalRoot.get("id"), id));
            TypedQuery<Signals> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getSignalDataByID\' " + e);
            return null;
        }
    }

    //Insert Network Data
    public static SignalTags_Mapping insertsignalTags_MappingData(SignalTags_Mapping signalTags_Mapping) {

        try {
            System.err.println("insertNetworkData");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            session.save(signalTags_Mapping);

            tx.commit();
            session.clear();
            return signalTags_Mapping;
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertsignalTags_MappingData\' " + e);
            return null;
        }
    }

    public static List<Tuple> LoadFeatureVersion(String filter) {

        try {
            System.err.println("LoadFeatureVersion");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Featureversion> fvRoot = criteriaQuery.from(Featureversion.class);

            criteriaQuery.multiselect(fvRoot.get("id").alias("id"), fvRoot.get("feature_versionname").alias("versionname"),
                    fvRoot.get("status").alias("status")).distinct(true);
            if (filter.equals("active")) {
                criteriaQuery.where(criteriaBuilder.equal(fvRoot.get("status"), true), criteriaBuilder.equal(fvRoot.get("flag"), true));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(fvRoot.get("feature_versionname")));

            TypedQuery<Tuple> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'LoadFeatureVersion\' " + e);
            return null;
        }
    }

    public static List<Tuple> LoadFeatureVersionById(int id) {

        try {
            System.err.println("LoadFeatureVersion");

            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Featureversion> fvRoot = criteriaQuery.from(Featureversion.class);

            criteriaQuery.multiselect(fvRoot.get("id").alias("id"), fvRoot.get("pdbversion_id").get("pdb_versionname").alias("pdbversionname"),
                    fvRoot.get("vehicle_id").get("id").alias("vid"), fvRoot.get("vehicle_id").get("vehiclename").alias("vname"), fvRoot.get("status").alias("status"),
                    fvRoot.get("flag").alias("flag")).distinct(true)
                    .where(criteriaBuilder.equal(fvRoot.get("status"), true), criteriaBuilder.equal(fvRoot.get("flag"), true), criteriaBuilder.equal(fvRoot.get("id"), id))
                    .orderBy(criteriaBuilder.desc(fvRoot.get("feature_versionname")));

            TypedQuery<Tuple> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'LoadFeatureVersion\' " + e);
            return null;
        }
    }

    public static IVN_Version LoadIVNPreviousVehicleversionStatus(int id) {

        try {

            System.err.println("LoadIVNPreviousVehicleversionStatus");

            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<IVN_Version> criteriaQuery = criteriaBuilder.createQuery(IVN_Version.class);
            Root<IVN_Version> ivnRoot = criteriaQuery.from(IVN_Version.class);

            criteriaQuery.distinct(true).where(criteriaBuilder.equal(ivnRoot.get("id"), id));

            TypedQuery<IVN_Version> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'LoadIVNPreviousVehicleversionStatus\' " + e);
            return null;
        }
    }

    public static IVN_Version insertIVNVersion(IVN_Version ivnv, String level) {
        try {
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            if (level.equals("create")) {

                session.save(ivnv);
                tx.commit();
                session.clear();
                return ivnv;
            } else {

                final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                // create update
                CriteriaUpdate<Querybuilder> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Querybuilder.class);
                // set the root class
                Root<Querybuilder> qRoot = criteriaUpdate.from(Querybuilder.class);
                // set update and where clause
//                criteriaUpdate.set("querybuilder_name", querybuilder.getQuerybuilder_name())
//                        .set("querybuilder_type", querybuilder.getQuerybuilder_type())
//                        .set("querybuilder_condition", querybuilder.getQuerybuilder_condition())
//                        .set("querybuilder_status", querybuilder.getQuerybuilder_status())
//                        .set("created_date", querybuilder.getCreated_date())
//                        .where(criteriaBuilder.equal(qRoot.get("id"), querybuilder.getId()));
                // perform update
                int a = session.createQuery(criteriaUpdate).executeUpdate();
                tx.commit();
                session.clear();
                if (a > 0) {
                    return ivnv;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertIVNVersion\' " + e);
            return null;
        }
    }

    public static IVN_Version_Group insertIVNVersionGroup(IVN_Version_Group iVN_Version_Group) {

        try {

            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            session.save(iVN_Version_Group);
            tx.commit();
            session.clear();
            return iVN_Version_Group;
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertIVNVersionGroup\' " + e);
            return null;
        }
    }

    public static List<IVN_Version_Group> GetIVNVersion_Listing() {
        try {

            System.err.println("DB GetIVNVersion_Listing");

            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<IVN_Version_Group> criteriaQuery = criteriaBuilder.createQuery(IVN_Version_Group.class);
            Root<IVN_Version_Group> ivnRoot = criteriaQuery.from(IVN_Version_Group.class);

            criteriaQuery.distinct(true);

            TypedQuery<IVN_Version_Group> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getResultList();

        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'GetIVNVersion_Listing\' " + e);
            return null;
        }
    }

    public static List<Tuple> loadIVNVersion_ListingByVehicleId(int id) {
        try {

            System.err.println("loadIVNVersion_ListingByVehicleId");
            System.err.println("VehicleId       " + id);
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<IVN_Version_Group> ivnRoot = criteriaQuery.from(IVN_Version_Group.class);

            criteriaQuery.multiselect(criteriaBuilder.function("group_concat", String.class, ivnRoot.get("ivnVersionId").get("id")).alias("id"),
                    criteriaBuilder.function("group_concat", String.class, ivnRoot.get("ivnVersionId").get("ivn_version")).alias("ivn_version"))
                    .where(criteriaBuilder.equal(ivnRoot.get("vehicleId"), id)).distinct(true);

            TypedQuery<Tuple> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getResultList();

        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'loadIVNVersion_ListingByVehicleId\' " + e);
            return null;
        }
    }
}
