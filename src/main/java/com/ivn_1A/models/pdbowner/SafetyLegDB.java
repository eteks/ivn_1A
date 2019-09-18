/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ets-poc
 */
public class SafetyLegDB {
    public static List<Querybuilder> LoadCombinationList(String querybuilder_type) {
        try {
            System.out.println("LoadCombinationList");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            
            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Querybuilder> criteriaQuery = criteriaBuilder.createQuery(Querybuilder.class);

            Root<Querybuilder> querybuilder = criteriaQuery.from(Querybuilder.class);
            criteriaQuery.where(criteriaBuilder.equal(querybuilder.get("querybuilder_type"), querybuilder_type));
            TypedQuery<Querybuilder> qb_result = s.createQuery(criteriaQuery);
            
            tx.commit();
            s.clear();
            return qb_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"LoadCombinationList\" : " + e);
            return null;
        }
    }
}
