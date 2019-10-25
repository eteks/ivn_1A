/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ets-poc
 */
public class FeatureversionDB {

    public static Pdbversion GetPdbversionByVehicleId(int vehicle_id) {

        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();
//        Map<String, Object> results = new HashMap<String, Object>();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Pdbversion> criteriaQuery = criteriaBuilder.createQuery(Pdbversion.class);
        Root<Pdbversion> pdbversion = criteriaQuery.from(Pdbversion.class);
        criteriaQuery.where(criteriaBuilder.equal(pdbversion.get("status"), true), criteriaBuilder.equal(pdbversion.get("flag"), true),
                criteriaBuilder.equal(pdbversion.get("vehicle_id").get("id"), vehicle_id))
                .orderBy(criteriaBuilder.desc(pdbversion.get("pdb_versionname")));
        Pdbversion pdb = s.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

//        results.put("pdb_results", pdb_results.getResultList());
        tx.commit();
        s.clear();
        return pdb;
    }

    public static Legislationversion GetLegversionByVehicleId(int vehicle_id) {

        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Legislationversion> criteriaQuery = criteriaBuilder.createQuery(Legislationversion.class);
        Root<Legislationversion> legversion = criteriaQuery.from(Legislationversion.class);
        criteriaQuery.where(criteriaBuilder.equal(legversion.get("status"), true), criteriaBuilder.equal(legversion.get("flag"), true),
                criteriaBuilder.equal(legversion.get("vehicle_id").get("id"), vehicle_id))
                .orderBy(criteriaBuilder.desc(legversion.get("legislation_versionname")));
        Legislationversion leg = s.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

//        results.put("pdb_results", pdb_results.getResultList());
        tx.commit();
        s.clear();
        return leg;
    }

    public static Safetyversion GetSafetyversionByVehicleId(int vehicle_id) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Safetyversion> criteriaQuery = criteriaBuilder.createQuery(Safetyversion.class);
        Root<Safetyversion> safversion = criteriaQuery.from(Safetyversion.class);
        criteriaQuery.where(criteriaBuilder.equal(safversion.get("status"), true), criteriaBuilder.equal(safversion.get("flag"), true),
                criteriaBuilder.equal(safversion.get("vehicle_id").get("id"), vehicle_id))
                .orderBy(criteriaBuilder.desc(safversion.get("safety_versionname")));
        Safetyversion saf = s.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

        tx.commit();
        s.clear();
        return saf;
    }

    public static Featureversion GetFeatureversionByVehicleId(int vehicle_id) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Featureversion> criteriaQuery = criteriaBuilder.createQuery(Featureversion.class);
        Root<Featureversion> safversion = criteriaQuery.from(Featureversion.class);

        criteriaQuery.where(criteriaBuilder.equal(safversion.get("status"), true), criteriaBuilder.equal(safversion.get("flag"), true),
                criteriaBuilder.equal(safversion.get("vehicle_id").get("id"), vehicle_id))
                .orderBy(criteriaBuilder.desc(safversion.get("feature_versionname")));

        Featureversion fea = s.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

        tx.commit();
        s.clear();
        return fea;
    }

    public static Featureversion insertFeatureVersion(Featureversion featureversion) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            s.save(featureversion);

            tx.commit();
            s.clear();
            return featureversion;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertFeatureVersion\" : " + e.getMessage());
            return null;
        }
    }

    public static Featureversion GetVersionname() {
        System.out.println("Entered GetVersionname");
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Featureversion> criteriaQuery = criteriaBuilder.createQuery(Featureversion.class);

            Root<Featureversion> test = criteriaQuery.from(Featureversion.class);
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("feature_versionname")));
            TypedQuery<Featureversion> dfm_result = s.createQuery(criteriaQuery).setMaxResults(1);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"GetVersionname\" : " + e.getMessage());
            return null;
        }
    }

    public static Featureversion GetVersionnameById(int id) {
        System.out.println("Entered GetVersionnameById");
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Featureversion> criteriaQuery = criteriaBuilder.createQuery(Featureversion.class);
            Root<Featureversion> fRoot = criteriaQuery.from(Featureversion.class);
            criteriaQuery.where(criteriaBuilder.equal(fRoot.get("id"), id));
            TypedQuery<Featureversion> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            System.out.println("Entered GetVersionnameById DONE");
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in \"GetVersionnameById\" : " + e.getMessage());
            return null;
        }
    }

    public static List<Tuple> GetFeatureversionListing() {
        try {

            System.out.println("GetFeatureversionListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Featureversion> fvg = criteriaQuery.from(Featureversion.class);
            fvg.join("vehicle_id", JoinType.INNER);
            fvg.join("pdbversion_id", JoinType.INNER);
            fvg.join("safetyversion_id", JoinType.INNER);
            fvg.join("legislationversion_id", JoinType.INNER);

            criteriaQuery.multiselect(fvg.get("id").alias("fea_id"), fvg.get("feature_versionname").alias("feature_versionname"),
                    fvg.get("created_date").alias("created_date"), 
                    fvg.get("modified_date").alias("modified_date"),
                    fvg.get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname"), 
                    fvg.get("pdbversion_id").get("id").alias("pdbid"), 
                    fvg.get("legislationversion_id").get("legislation_versionname").alias("legislation_versionname"), 
                    fvg.get("safetyversion_id").get("safety_versionname").alias("safety_versionname"), 
                    fvg.get("vehicle_id").get("vehiclename").alias("vehiclename"),
                    fvg.get("flag").alias("flag"), fvg.get("status").alias("status"))
                    .groupBy(fvg.get("created_date"))
                    .orderBy(criteriaBuilder.desc(fvg.get("id")));
            
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetLegislationListing\" : " + e);
            return null;
        }
    }

    public static Featureversion GetVersionname(int vehicle_id, String version_type) {
        
        System.out.println("Entered GetVersionname2");
        System.out.println("vehicle_id" + vehicle_id);
        try {
//            Query pdbversion = s.createQuery("FROM Pdbversion p order by p.pdb_versionname desc").setParameter("pdb_reference_version", "1.0");
//            pdbversion.setMaxResults(1);
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            // create Criteria
//            CriteriaQuery<Pdbversion> criteriaQuery = s.getCriteriaBuilder().createQuery(Pdbversion.class);           
//            criteriaQuery.from(Pdbversion.class); 
            //Working code
            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Featureversion> criteriaQuery = criteriaBuilder.createQuery(Featureversion.class);
            // The root of our search is sku
            Root<Featureversion> test = criteriaQuery.from(Featureversion.class);
            List<Predicate> restrictions = new ArrayList<>();
            restrictions.add(criteriaBuilder.isNull(test.get("feature_reference_version")));
//            criteriaQuery.where(restrictions.toArray(new Predicate[restrictions.size()]));
            if (version_type.equals("new")) {
                criteriaQuery.where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(
                                        test.get("vehicle_id"), vehicle_id
                                ),
                                criteriaBuilder.equal(
                                        test.get("version_type"), version_type
                                )
                        )
                );
            } else {
                criteriaQuery.where(
                        criteriaBuilder.equal(
                                test.get("vehicle_id"), vehicle_id
                        )
                );
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("feature_versionname")));
            //create resultset as list
            Featureversion featureversion = s.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

            tx.commit();
            s.clear();
            return featureversion;
        } catch (Exception e) {
            System.err.println("Error in \"FeatureversionDB\" \"GetVersionname2\" : " + e.getMessage());
            return null;
        }
    }
    
    public static Map<String, Object> GetFeaPreviousVersion_LegSafPdb(int prevpdb_id, int curpdb_id) {
        try {
//            Session s = HibernateUtil.getThreadLocalSession();
//            Transaction tx = s.beginTransaction();
            Map<String, Object> results = new HashMap<>();

            String removed = "removed";
            String added = "added";

            List<Featureversion> removed_Legislation = GetComparedLegislation(prevpdb_id, curpdb_id, removed);
            results.put("removed_Legislation", StringUtils.join(removed_Legislation, ","));

            List<Featureversion> added_Legislation = GetComparedLegislation(prevpdb_id, curpdb_id, added);
            results.put("added_Legislation", StringUtils.join(added_Legislation, ","));

            List<Featureversion> removed_Safety = GetComparedSafety(prevpdb_id, curpdb_id, removed);
            results.put("removed_Safety", StringUtils.join(removed_Safety, ","));

            List<Featureversion> added_Safety = GetComparedSafety(prevpdb_id, curpdb_id, added);
            results.put("added_Safety", StringUtils.join(added_Safety, ","));

            List<Featureversion> removed_pdb = GetComparedPdbversion(prevpdb_id, curpdb_id, removed);
            results.put("removed_pdb", StringUtils.join(removed_pdb, ","));

            List<Featureversion> added_pdb = GetComparedPdbversion(prevpdb_id, curpdb_id, added);
            results.put("added_pdb", StringUtils.join(added_pdb, ","));

//            
//            tx.commit();
//            s.clear();
            return results;
        } catch (Exception e) {
            System.err.println("Error in \"GetPDBPreviousVersion_DomFea\" : " + e.getMessage());
            return null;
        }
    }
    
    public static List<Featureversion> GetComparedLegislation(int prevpdb_id, int curpdb_id, String feature_type) {
                
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Featureversion> feaversion_groupRoot = criteriaQuery.from(Featureversion.class);
        Join<Featureversion, Legislationversion> legJoin = feaversion_groupRoot.join("legislationversion_id", JoinType.INNER);
        criteriaQuery.select(legJoin.get("legislation_versionname")).distinct(true);

        Subquery<Featureversion> subquery = criteriaQuery.subquery(Featureversion.class);
        Root<Featureversion> subQueryRoot = subquery.from(Featureversion.class);
        subquery.select(subQueryRoot.get("legislationversion_id")).distinct(true);

        if (feature_type.equals("removed")) {
            System.out.println("entered into removed");
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("id"), curpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    feaversion_groupRoot.get("id"), prevpdb_id
                            ),
                            criteriaBuilder.not(feaversion_groupRoot.get("legislationversion_id").in(subquery))
                    )
            );
        } else {
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("id"), prevpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    feaversion_groupRoot.get("id"), curpdb_id
                            ),
                            criteriaBuilder.not(feaversion_groupRoot.get("legislationversion_id").in(subquery))
                    )
            );
        }

//            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id)),criteriaBuilder.not(criteriaBuilder.exists(subquery))); 
        TypedQuery<Featureversion> vehmod_results = s.createQuery(criteriaQuery);
        tx.commit();
        s.clear();
        return vehmod_results.getResultList();
    }

    public static List<Featureversion> GetComparedSafety(int prevpdb_id, int curpdb_id, String feature_type) {
        
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Featureversion> feaversion_groupRoot = criteriaQuery.from(Featureversion.class);
        Join<Featureversion, Safetyversion> safJoin = feaversion_groupRoot.join("safetyversion_id", JoinType.INNER);
        criteriaQuery.select(safJoin.get("safety_versionname")).distinct(true);

        Subquery<Featureversion> subquery = criteriaQuery.subquery(Featureversion.class);
        Root<Featureversion> subQueryRoot = subquery.from(Featureversion.class);
        subquery.select(subQueryRoot.get("safetyversion_id")).distinct(true);

        if (feature_type.equals("removed")) {
            System.out.println("entered into removed");
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("id"), curpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    feaversion_groupRoot.get("id"), prevpdb_id
                            ),
                            criteriaBuilder.not(feaversion_groupRoot.get("safetyversion_id").in(subquery))
                    )
            );
        } else {
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("id"), prevpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    feaversion_groupRoot.get("id"), curpdb_id
                            ),
                            criteriaBuilder.not(feaversion_groupRoot.get("safetyversion_id").in(subquery))
                    )
            );
        }

//            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id)),criteriaBuilder.not(criteriaBuilder.exists(subquery))); 
        TypedQuery<Featureversion> vehmod_results = s.createQuery(criteriaQuery);
        tx.commit();
        s.clear();
        return vehmod_results.getResultList();
    }
    
    public static List<Featureversion> GetComparedPdbversion(int prevpdb_id, int curpdb_id, String feature_type) {
        
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Featureversion> feaversion_groupRoot = criteriaQuery.from(Featureversion.class);
        Join<Featureversion, Pdbversion> pdbJoin = feaversion_groupRoot.join("pdbversion_id", JoinType.INNER);
        criteriaQuery.select(pdbJoin.get("pdb_versionname")).distinct(true);

        Subquery<Featureversion> subquery = criteriaQuery.subquery(Featureversion.class);
        Root<Featureversion> subQueryRoot = subquery.from(Featureversion.class);
        subquery.select(subQueryRoot.get("pdbversion_id")).distinct(true);

        if (feature_type.equals("removed")) {
            System.out.println("entered into removed");
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("id"), curpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    feaversion_groupRoot.get("id"), prevpdb_id
                            ),
                            criteriaBuilder.not(feaversion_groupRoot.get("pdbversion_id").in(subquery))
                    )
            );
        } else {
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("id"), prevpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    feaversion_groupRoot.get("id"), curpdb_id
                            ),
                            criteriaBuilder.not(feaversion_groupRoot.get("pdbversion_id").in(subquery))
                    )
            );
        }

//            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id)),criteriaBuilder.not(criteriaBuilder.exists(subquery))); 
        TypedQuery<Featureversion> vehmod_results = s.createQuery(criteriaQuery);
        tx.commit();
        s.clear();
        return vehmod_results.getResultList();
    }
}
