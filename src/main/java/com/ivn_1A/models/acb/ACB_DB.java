/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.acb;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
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
}
