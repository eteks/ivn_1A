package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.admin.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ets-poc
 */
public class PDBOwnerDB  {
   public static List<Pdbversion> GetVersionname() {
        try {
//            Query pdbversion = s.createQuery("FROM Pdbversion p order by p.pdb_versionname desc").setParameter("pdb_reference_version", "1.0");
//            pdbversion.setMaxResults(1);
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            
            // create Criteria
//            CriteriaQuery<Pdbversion> criteriaQuery = s.getCriteriaBuilder().createQuery(Pdbversion.class);           
//            criteriaQuery.from(Pdbversion.class); 

            //Working code
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Pdbversion> criteriaQuery = builder.createQuery(Pdbversion.class);
            // The root of our search is sku
            Root<Pdbversion> test = criteriaQuery.from(Pdbversion.class);
            List<Predicate> restrictions = new ArrayList<Predicate>();
            restrictions.add(builder.isNull(test.get("pdb_reference_version")));
            criteriaQuery.where(restrictions.toArray(new Predicate[restrictions.size()]));
            criteriaQuery.orderBy(builder.desc(test.get("pdb_versionname")));
            //create resultset as list
            Query pdbversion = s.createQuery(criteriaQuery);
            pdbversion.setMaxResults(1);
            tx.commit();
            s.clear();
            return pdbversion.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetVersionname\" : " + e.getMessage());
            return null;
        }
    }
    public Pdbversion insertPDBVersion(Pdbversion pdbversion) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(pdbversion);
            tx.commit();
            s.clear();
            return pdbversion;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertPDBVersion\" : " + e.getMessage());
            return null;
        }
    }
    public Pdbversion_group insertPDBVersionGroup(Pdbversion_group pvg) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(pvg);
            tx.commit();
            s.clear();
            return pvg;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertPDBVersionGroup\" : " + e.getMessage());
            return null;
        }
    }

    public List GetPDBPreviousVersion_DomFea(int pdbversion_id) {
        try {
//            Query pdbversion = s.createQuery("FROM Pdbversion p order by p.pdb_versionname desc").setParameter("pdb_reference_version", "1.0");
//            pdbversion.setMaxResults(1);
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            
            // create Criteria
//            CriteriaQuery<Pdbversion> criteriaQuery = s.getCriteriaBuilder().createQuery(Pdbversion.class);           
//            criteriaQuery.from(Pdbversion.class); 

            //Working code
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Pdbversion> criteriaQuery = builder.createQuery(Pdbversion.class);
            // The root of our search is sku
            Root<Pdbversion_group> test = criteriaQuery.from(Pdbversion_group.class);
            List<Predicate> restrictions = new ArrayList<Predicate>();
            criteriaQuery.select(test.get("domain_and_features_mapping_id"));
            criteriaQuery.where(builder.equal(test.get("pdbversion_id"), pdbversion_id));
            criteriaQuery.groupBy(test.get("domain_and_features_mapping_id"));
            //create resultset as list
            List pdbversion = s.createQuery(criteriaQuery).list();
//            pdbversion.setMaxResults(1);
            tx.commit();
            s.clear();
            return pdbversion;
        } catch (Exception e) {
            System.err.println("Error in \"GetVersionname\" : " + e.getMessage());
            return null;
        }
    }
    public static List<Domain_and_Features_Mapping> LoadFeaturesList() {
        try {
            System.out.println("LoadFeaturesList");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            System.out.println("before resultdata");
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Domain_and_Features_Mapping> criteriaQuery = builder.createQuery(Domain_and_Features_Mapping.class);
            Root<Domain_and_Features_Mapping> dfm = criteriaQuery.from(Domain_and_Features_Mapping.class);
            Join<Domain_and_Features_Mapping, Domain> joindomain = dfm.join("domain_id",JoinType.INNER);
            Join<Domain_and_Features_Mapping, Features> joinfeatures = dfm.join("feature_id",JoinType.INNER);
            criteriaQuery.multiselect(joindomain.get("domain_name"), joinfeatures.get("feature_name"));
//            criteriaQuery.select(joindomain.get("domain_name"));
//            criteriaQuery.select(joinfeatures.get("feature_name"));
//            criteriaQuery.select(dfm.get("id"));
//            List<Predicate> conditions = new ArrayList();
//            conditions.add(builder.equal(joinOrganization.get("name"), "XYZ"));
            System.out.println("resultdata");
            //create resultset as list
            TypedQuery<Domain_and_Features_Mapping> dfm_result = s.createQuery(criteriaQuery);
//            List<Object[]> list = s.createQuery(query).getResultList();
//            Query dfm_result = s.createQuery(criteriaQuery);
            System.out.println(dfm_result);
            System.err.println(dfm_result);
            tx.commit();
            s.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
