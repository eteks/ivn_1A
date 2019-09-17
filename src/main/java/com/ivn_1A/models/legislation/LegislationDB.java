/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.legislation;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.pdbowner.Querybuilder;
import java.util.ArrayList;
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
 * @author ETS-05
 */
public class LegislationDB {

    public static List<Tuple> LoadPreviousLegislationCombinationData(Querybuilder querybuilder) {
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
            System.err.println("Error in \"LoadPreviousLegislationCombinationData\" : " + e);
            return null;
        }
    }
    
    public static List<Tuple> insertLegislationCombination(Querybuilder querybuilder) {
        
        try {

            System.out.println("LoadPreviousLegislationCombinationData");
            System.out.println("status_value" + querybuilder.getQuerybuilder_status());            
            if (querybuilder.getQuerybuilder_type().equals("create")) {
                
            } else {
                
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error in \"insertLegislationCombination\" : " + e);
            return null;
        }
    }
}
