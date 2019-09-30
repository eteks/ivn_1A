/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_ivn_1A.models.net_sign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import java.util.Date;
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

    public static List<Tuple> insertSignalData(Signals signals, ArrayNode signal_tags) {

        try {

            System.err.println("insertSignalData");
            Signals signals1 = getSignalByName(signals);
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            if (signals1 == null) {
                session.save(signals);
                signals1 = signals;
                int last_inserted_id = signals1.getId();
                int tag_id = 0;
                for (JsonNode signal_tag : signal_tags) {
                    String tagname = signal_tag.asText();
                    SignalTags st = getSignalTagsByName(tagname);
                    if (st == null) {
                        st = new SignalTags(tagname, new Date(), new Date(), PDBOwnerDB.getUser(1), true);
                        session.save(st);
                        tag_id = st.getId();
                    } else {
                        tag_id = st.getId();
                    }
                    SignalTags_Mapping stm = new SignalTags_Mapping(signals1, st, new Date());
                    session.save(stm);
                    tag_id = stm.getId();
                }
//                final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//                CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
//                Root<SignalTags_Mapping> stmRoot = criteriaQuery.from(SignalTags_Mapping.class);
//                stmRoot.join("signal_id", JoinType.INNER);
//                stmRoot.join("signalTag_id", JoinType.INNER);
//                
//                criteriaQuery.multiselect(stmRoot.get("id").alias("sid"), stmRoot.get("signal_name").alias("listitem"),
//                    stmRoot.get("signal_alias").alias("salias"), stmRoot.get("signal_description").alias("description"),
//                    criteriaBuilder.function("group_concat", String.class, pRoot.get("feature_id").get("created_date")).alias("created_date"),
//                    criteriaBuilder.function("group_concat", String.class, pRoot.get("feature_id").get("created_date")).alias("created_date")).distinct(true)
//                    .where(criteriaBuilder.equal(stmRoot.get("status"), true))
//                    .orderBy(criteriaBuilder.desc(stmRoot.get("id")));
//
//            TypedQuery<Tuple> dfm_result = session.createQuery(criteriaQuery);
                
                System.err.println("!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@######################Null Values");
                
            } else {
                System.err.println("Null Values");
            }

            tx.commit();
            session.clear();
            return null;
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'insertSignalData\' " + e);
            return null;
        }
    }

    //Signal Data by Name
    public static Signals getSignalByName(Signals signals) {
        try {
            System.err.println("getECUByName");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Signals> criteriaQuery = criteriaBuilder.createQuery(Signals.class);

            Root<Signals> networkRoot = criteriaQuery.from(Signals.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("signal_name"), signals.getSignal_name()));
            TypedQuery<Signals> dfm_result = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getSignalByName\' " + e);
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

}
