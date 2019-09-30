/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_ivn_1A.models.net_sign;

import com.ivn_1A.configs.HibernateUtil;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<ECU> criteriaQuery = criteriaBuilder.createQuery(ECU.class);

            Root<ECU> networkRoot = criteriaQuery.from(ECU.class);
            criteriaQuery.where(criteriaBuilder.equal(networkRoot.get("ecu_name"), ecu.getEcu_name()));
            TypedQuery<ECU> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"IVNEngineerDB\" \'getECUByName\' " + e);
            return null;
        }
    }
}
