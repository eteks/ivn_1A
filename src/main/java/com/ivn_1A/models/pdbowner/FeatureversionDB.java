/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import java.util.HashMap;
import java.util.Map;
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
public class FeatureversionDB {
    public static Map<String, Object> GetPdbSafetyLeg_version(int vehicle_id) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();
        Map<String, Object> results = new HashMap<String, Object>();
        
        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Pdbversion> pdbversion = criteriaQuery.from(Pdbversion.class);
        criteriaQuery.where(criteriaBuilder.equal(pdbversion.get("status"), true), criteriaBuilder.equal(pdbversion.get("flag"), true),
                        criteriaBuilder.equal(pdbversion.get("vehicle_id").get("id"), vehicle_id));
        criteriaQuery.multiselect(pdbversion.get("id").alias("pid"), pdbversion.get("pdb_versionname").alias("name"), 
                    pdbversion.get("status").alias("status"));       
        TypedQuery<Pdbversion_group> pdb_results = s.createQuery(criteriaQuery);
        
        results.put("pdb_results", pdb_results.getResultList());
        
        tx.commit();
        s.clear();       
        return results;
    }
}
