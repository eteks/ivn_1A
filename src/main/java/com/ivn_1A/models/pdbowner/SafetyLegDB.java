/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
            System.err.println("Error in \"SafetyLegDB\" \"LoadCombinationList\" : " + e);
            return null;
        }
    }

    public static List<Legislationversion> GetVersionname(int vehicle_id, String version_type) {
        System.out.println("Entered GetVersionname");
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
            CriteriaQuery<Legislationversion> criteriaQuery = criteriaBuilder.createQuery(Legislationversion.class);
            // The root of our search is sku
            Root<Legislationversion> test = criteriaQuery.from(Legislationversion.class);
            List<Predicate> restrictions = new ArrayList<>();
            restrictions.add(criteriaBuilder.isNull(test.get("legislation_reference_version")));
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
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("legislation_versionname")));
            //create resultset as list
            Query pdbversion = s.createQuery(criteriaQuery).setMaxResults(1);

            tx.commit();
            s.clear();
            return pdbversion.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"GetVersionname\" : " + e.getMessage());
            return null;
        }
    }

    public static List<Safetyversion> GetSafetyVersionname(int vehicle_id, String version_type) {
        System.out.println("Entered GetSafetyVersionname");
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
            CriteriaQuery<Safetyversion> criteriaQuery = criteriaBuilder.createQuery(Safetyversion.class);
            // The root of our search is sku
            Root<Safetyversion> test = criteriaQuery.from(Safetyversion.class);
            List<Predicate> restrictions = new ArrayList<>();
            restrictions.add(criteriaBuilder.isNull(test.get("safety_reference_version")));
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
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("safety_versionname")));
            //create resultset as list
            Query safetyversion = s.createQuery(criteriaQuery).setMaxResults(1);

            tx.commit();
            s.clear();
            return safetyversion.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"GetSafetyVersionname\" : " + e.getMessage());
            return null;
        }
    }

    //Pdbversion group Data
    public static List<Tuple> loadLegislationversion_groupByVehicleId(int id, String action) {
        try {
            System.err.println("loadLegislationversion_groupByVehicleId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Legislationversion> legislationversion = criteriaQuery.from(Legislationversion.class);

            criteriaQuery.multiselect(legislationversion.get("id").alias("lid"), legislationversion.get("legislation_versionname").alias("lversion"),
                    legislationversion.get("status").alias("status"));
            if (action.equals("edit")) {
                criteriaQuery.where(criteriaBuilder.equal(legislationversion.get("vehicle_id").get("id"), id));
            } else {
                criteriaQuery.where(criteriaBuilder.equal(legislationversion.get("status"), true), criteriaBuilder.equal(legislationversion.get("flag"), true),
                        criteriaBuilder.equal(legislationversion.get("vehicle_id").get("id"), id));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(legislationversion.get("legislation_versionname")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"loadLegislationversion_groupByVehicleId\" : " + e);
            return null;
        }
    }

    public static List<Tuple> loadSafetyversion_groupByVehicleId(int id, String action) {
        try {
            System.err.println("loadSafetyversion_groupByVehicleId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Safetyversion> safetyversion = criteriaQuery.from(Safetyversion.class);

            criteriaQuery.multiselect(safetyversion.get("id").alias("sid"), safetyversion.get("safety_versionname").alias("sversion"),
                    safetyversion.get("status").alias("status"));
            if (action.equals("edit")) {
                criteriaQuery.where(criteriaBuilder.equal(safetyversion.get("vehicle_id").get("id"), id));
            } else {
                criteriaQuery.where(criteriaBuilder.equal(safetyversion.get("status"), true), criteriaBuilder.equal(safetyversion.get("flag"), true),
                        criteriaBuilder.equal(safetyversion.get("vehicle_id").get("id"), id));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(safetyversion.get("safety_versionname")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"loadSafetyversion_groupByVehicleId\" : " + e);
            return null;
        }
    }

    public static Legislationversion insertLegilsationVersion(Legislationversion legislationversion) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            s.save(legislationversion);

            tx.commit();
            s.clear();
            return legislationversion;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"insertLegilsationVersion\" : " + e.getMessage());
            return null;
        }
    }

    public static Safetyversion insertSafetyVersion(Safetyversion safetyversion) {
        System.out.println("insertSafetyVersion");
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            s.save(safetyversion);

            tx.commit();
            s.clear();
            return safetyversion;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"insertSafetyVersion\" : " + e.getMessage());
            return null;
        }
    }

    public static Querybuilder getQuerybuilder(Long id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Querybuilder qb = s.get(Querybuilder.class, id);
            tx.commit();
            s.clear();
            return qb;
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"getQuerybuilder\" : " + e.getMessage());
            return null;
        }
    }

    public static Legislationversion_group insertLegislationVersionGroup(Legislationversion_group leg_gp) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(leg_gp);
            tx.commit();
            s.clear();
            return leg_gp;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"insertPDBVersionGroup\" : " + e.getMessage());
            return null;
        }
    }

    public static Safetyversion_group insertSafetyVersionGroup(Safetyversion_group saf_gp) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(saf_gp);
            tx.commit();
            s.clear();
            return saf_gp;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"insertSafetyVersionGroup\" : " + e.getMessage());
            return null;
        }
    }

    public static Safetyversion getSafetyversion(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Safetyversion safversion = s.get(Safetyversion.class, id);
            tx.commit();
            s.clear();
            return safversion;
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"getSafetyversion\" : " + e.getMessage());
            return null;
        }
    }

    public static Legislationversion getLegislationversion(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Legislationversion legversion = s.get(Legislationversion.class, id);
            tx.commit();
            s.clear();
            return legversion;
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"getLegislationversion\" : " + e.getMessage());
            return null;
        }
    }

    public static List<Tuple> GetSafetyListing() {
        try {

            System.out.println("GetSafetyListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Safetyversion_group> svg = criteriaQuery.from(Safetyversion_group.class);
//            criteriaQuery.distinct(true);
            criteriaQuery.multiselect(svg.get("safetyversion_id").get("id").alias("saf_id"), svg.get("safetyversion_id").get("safety_versionname").alias("saf"),
                    svg.get("safetyversion_id").get("created_date").alias("created_date"), svg.get("safetyversion_id").get("modified_date").alias("modified_date"),
                    svg.get("safetyversion_id").get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname"), svg.get("safetyversion_id").get("vehicle_id").get("vehiclename").alias("vehiclename"),
                    svg.get("safetyversion_id").get("flag").alias("flag"), svg.get("safetyversion_id").get("status").alias("status"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, svg.get("vehiclemodel_id").get("modelname")).alias("modelname"))
                    .distinct(true).orderBy(criteriaBuilder.desc(svg.get("safetyversion_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"GetSafetyListing\" : " + e);
            return null;
        }
    }

    public static List<Tuple> GetSafetyCombinationListing() {
        try {

            System.out.println("GetLegislationCombinationListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Querybuilder> qBRoot = criteriaQuery.from(Querybuilder.class);
            criteriaQuery.multiselect(qBRoot.get("id").alias("saf_id"), qBRoot.get("querybuilder_name").alias("saf"), qBRoot.get("created_date").alias("created_date"),
                    qBRoot.get("modified_date").alias("modified_date"), qBRoot.get("querybuilder_condition").alias("combination"), qBRoot.get("querybuilder_status").alias("status"))
                    .distinct(true).where(criteriaBuilder.equal(qBRoot.get("querybuilder_type"), "safety"))
                    .orderBy(criteriaBuilder.desc(qBRoot.get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \"GetLegislationCombinationListing\" : " + e);
            return null;
        }
    }

    public static List<Tuple> LoadSafetyversion_groupData(int safety_version_id, String actionString) {

        try {
            System.out.println("LoadSafetyversion_groupData");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Safetyversion_group> pRoot = criteriaQuery.from(Safetyversion_group.class);
//            pRoot.join("safetyversion_id", JoinType.INNER);
//            pRoot.join("querybuilder_id", JoinType.INNER);
//            pRoot.join("vehiclemodel_id", JoinType.INNER);

            criteriaQuery.multiselect(pRoot.get("id").alias("safetyversion_group_id"), pRoot.get("safetyversion_id").get("id").alias("saf_id"),
                    pRoot.get("safetyversion_id").get("safety_versionname").alias("saf"), pRoot.get("safetyversion_id").get("status").alias("status"),
                    pRoot.get("safetyversion_id").get("flag").alias("flag"), pRoot.get("safetyversion_id").get("vehicle_id").get("vehiclename").alias("vehiclename"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, pRoot.get("vehiclemodel_id").get("modelname")).alias("modelname"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, pRoot.get("vehiclemodel_id").get("id")).alias("model_id"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, pRoot.get("available_status")).alias("available_status"),
                    pRoot.get("safetyversion_id").get("vehicle_id").get("id").alias("vehicle_id"),
                    pRoot.get("querybuilder_id").get("id").alias("qb_id"), pRoot.get("querybuilder_id").get("querybuilder_name").alias("qb_name"),
                    pRoot.get("safetyversion_id").get("pdbversion_id").get("id").alias("pdb_versionid"), pRoot.get("safetyversion_id").get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname")).distinct(true);

            if (actionString.equals("edit")) {
                criteriaQuery.where(criteriaBuilder.equal(pRoot.get("safetyversion_id").get("id"), safety_version_id));
            } else {
                criteriaQuery.where(criteriaBuilder.equal(pRoot.get("safetyversion_id").get("status"), true),
                        criteriaBuilder.equal(pRoot.get("safetyversion_id").get("flag"), true),
                        criteriaBuilder.equal(pRoot.get("safetyversion_id").get("id"), safety_version_id));
            }

            criteriaQuery.orderBy(criteriaBuilder.desc(pRoot.get("safetyversion_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \'LoadSafetyversion_groupData\' : " + e);
            return null;
        }
    }

    public static List<Tuple> LoadLegislationversion_groupData(int legislation_version_id, String actionString) {

        System.out.println("LoadPDBPreviousVehicleversionData");
        try {
            System.err.println("GetVehicleVersion_Listing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Legislationversion_group> pRoot = criteriaQuery.from(Legislationversion_group.class);
            
            criteriaQuery.multiselect(pRoot.get("id").alias("legisversion_group_id"), pRoot.get("legislationversion_id").get("id").alias("leg_id"),
                    pRoot.get("legislationversion_id").get("legislation_versionname").alias("leg"), pRoot.get("legislationversion_id").get("status").alias("status"),
                    pRoot.get("legislationversion_id").get("flag").alias("flag"), pRoot.get("legislationversion_id").get("vehicle_id").get("vehiclename").alias("vehiclename"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, pRoot.get("vehiclemodel_id").get("modelname")).alias("modelname"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, pRoot.get("vehiclemodel_id").get("id")).alias("model_id"),
                    criteriaBuilder.function("group_concat_Distinct", String.class, pRoot.get("available_status")).alias("available_status"),
                    pRoot.get("legislationversion_id").get("vehicle_id").get("id").alias("vehicle_id"),
                    pRoot.get("querybuilder_id").get("id").alias("qb_id"), pRoot.get("querybuilder_id").get("querybuilder_name").alias("qb_name"),
                    pRoot.get("legislationversion_id").get("pdbversion_id").get("id").alias("pdb_versionid"), 
                    pRoot.get("legislationversion_id").get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname")).distinct(true);

            if (actionString.equals("edit")) {
                criteriaQuery.where(criteriaBuilder.equal(pRoot.get("legislationversion_id").get("id"), legislation_version_id));
            } else {
                criteriaQuery.where(criteriaBuilder.equal(pRoot.get("legislationversion_id").get("status"), true),
                        criteriaBuilder.equal(pRoot.get("legislationversion_id").get("flag"), true),
                        criteriaBuilder.equal(pRoot.get("legislationversion_id").get("id"), legislation_version_id));
            }

            criteriaQuery.orderBy(criteriaBuilder.desc(pRoot.get("legislationversion_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"SafetyLegDB\" \'LoadLegislationversion_groupData\' : " + e);
            return null;
        }
    }
    
    
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

    public static long insertLegislationCombination(Querybuilder querybuilder) {

        try {

            System.out.println("LoadPreviousLegislationCombinationData");
            System.out.println("status_value" + querybuilder.getQuerybuilder_status());
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            if (querybuilder.getQuerybuilder_status()) {

                session.save(querybuilder);
                tx.commit();
                session.clear();
                return querybuilder.getId();
            } else {

                final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                // create update
                CriteriaUpdate<Querybuilder> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Querybuilder.class);
                // set the root class
                Root<Querybuilder> qRoot = criteriaUpdate.from(Querybuilder.class);
                // set update and where clause
                criteriaUpdate.set("querybuilder_name", querybuilder.getQuerybuilder_name())
                        .set("querybuilder_type", querybuilder.getQuerybuilder_type())
                        .set("querybuilder_condition", querybuilder.getQuerybuilder_condition())
                        .set("querybuilder_status", querybuilder.getQuerybuilder_status())
                        .set("created_date", querybuilder.getCreated_date())
                        .where(criteriaBuilder.equal(qRoot.get("id"), querybuilder.getId()));
                // perform update
                int a = session.createQuery(criteriaUpdate).executeUpdate();
                tx.commit();
                session.clear();
                if (a > 0) {
                    return querybuilder.getId();
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            System.err.println("Error in \"insertLegislationCombination\" : " + e);
            return 0;
        }
    }

    public static List<Tuple> GetLegislationListing() {
        try {

            System.out.println("GetLegislationListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Legislationversion_group> lVGRoot = criteriaQuery.from(Legislationversion_group.class);
//            criteriaQuery.distinct(true);
            criteriaQuery.distinct(true).multiselect(lVGRoot.get("vehiclemodel_id").get("modelname").alias("modelname"), lVGRoot.get("legislationversion_id").get("id").alias("leg_id"), lVGRoot.get("legislationversion_id").get("legislation_versionname").alias("leg"),
                    lVGRoot.get("legislationversion_id").get("created_date").alias("created_date"), lVGRoot.get("legislationversion_id").get("modified_date").alias("modified_date"),
                    lVGRoot.get("legislationversion_id").get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname"), lVGRoot.get("legislationversion_id").get("vehicle_id").get("vehiclename").alias("vehiclename"),
                    lVGRoot.get("legislationversion_id").get("flag").alias("flag"), lVGRoot.get("legislationversion_id").get("status").alias("status"));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetLegislationListing\" : " + e);
            return null;
        }
    }
//    public static List<Legislationversion_group> GetLegislationListing() {
//        try {
//
//            System.out.println("GetLegislationListing");
//            Session session = HibernateUtil.getThreadLocalSession();
//            Transaction tx = session.beginTransaction();
//
//            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Legislationversion_group> criteriaQuery = criteriaBuilder.createQuery(Legislationversion_group.class);
//            Root<Legislationversion_group> lVGRoot = criteriaQuery.from(Legislationversion_group.class);
//            criteriaQuery.distinct(true);
//            TypedQuery<Legislationversion_group> typedQuery = session.createQuery(criteriaQuery);
//
//            tx.commit();
//            session.clear();
//            return typedQuery.getResultList();
//        } catch (Exception e) {
//            System.err.println("Error in \"GetLegislationListing\" : " + e);
//            return null;
//        }
//    }
    
    public static List<Tuple> GetLegislationCombinationListing() {
        try {

            System.out.println("GetLegislationCombinationListing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Querybuilder> qBRoot = criteriaQuery.from(Querybuilder.class);
            criteriaQuery.multiselect(qBRoot.get("id").alias("leg_id"), qBRoot.get("querybuilder_name").alias("leg"), qBRoot.get("created_date").alias("created_date"),
                    qBRoot.get("modified_date").alias("modified_date"), qBRoot.get("querybuilder_condition").alias("combination"), qBRoot.get("querybuilder_status").alias("status"))
                    .distinct(true).where(criteriaBuilder.equal(qBRoot.get("querybuilder_type"), "legislation"))
                    .orderBy(criteriaBuilder.desc(qBRoot.get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetLegislationCombinationListing\" : " + e);
            return null;
        }
    }

    public static List<Tuple> LoadPreviousSafetyCombinationData(Querybuilder querybuilder) {
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
            System.err.println("Error in \"LoadPreviousSafetyCombinationData\" : " + e);
            return null;
        }
    }

    public static long insertSafetyCombination(Querybuilder querybuilder) {

        try {

            System.out.println("LoadPreviousLegislationCombinationData");
            System.out.println("status_value" + querybuilder.getQuerybuilder_status());
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();
            if (querybuilder.getQuerybuilder_status()) {

                session.save(querybuilder);
                tx.commit();
                session.clear();
                return querybuilder.getId();
            } else {

                final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                // create update
                CriteriaUpdate<Querybuilder> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Querybuilder.class);
                // set the root class
                Root<Querybuilder> qRoot = criteriaUpdate.from(Querybuilder.class);
                // set update and where clause
                criteriaUpdate.set("querybuilder_name", querybuilder.getQuerybuilder_name())
                        .set("querybuilder_type", querybuilder.getQuerybuilder_type())
                        .set("querybuilder_condition", querybuilder.getQuerybuilder_condition())
                        .set("querybuilder_status", querybuilder.getQuerybuilder_status())
                        .set("created_date", querybuilder.getCreated_date())
                        .where(criteriaBuilder.equal(qRoot.get("id"), querybuilder.getId()));
                // perform update
                int a = session.createQuery(criteriaUpdate).executeUpdate();
                tx.commit();
                session.clear();
                if (a > 0) {
                    return querybuilder.getId();
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            System.err.println("Error in \"insertSafetyCombination\" : " + e);
            return 0;
        }
    }

//    public static List<Tuple> GetSafetyListing() {
//        try {
//
//            System.out.println("GetSafetyListing");
//            Session session = HibernateUtil.getThreadLocalSession();
//            Transaction tx = session.beginTransaction();
//
//            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
//            Root<Legislationversion_group> lVGRoot = criteriaQuery.from(Legislationversion_group.class);
////            criteriaQuery.distinct(true);
//            criteriaQuery.multiselect(lVGRoot.get("legislationversion_id").get("id").alias("saf_id"), lVGRoot.get("legislationversion_id").get("legislation_versionname").alias("saf"), 
//                    lVGRoot.get("legislationversion_id").get("created_date").alias("created_date"), lVGRoot.get("legislationversion_id").get("modified_date").alias("modified_date"),
//                    lVGRoot.get("legislationversion_id").get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname"), lVGRoot.get("legislationversion_id").get("vehicle_id").get("vehiclename").alias("vehiclename"), 
//                    lVGRoot.get("legislationversion_id").get("flag").alias("flag"), lVGRoot.get("legislationversion_id").get("status").alias("status"),
//                    criteriaBuilder.function("group_concat_Distinct", String.class, lVGRoot.get("vehiclemodel_id").get("modelname")).alias("modelname"))
//                    .distinct(true).orderBy(criteriaBuilder.desc(lVGRoot.get("legislationversion_id").get("id")));
//            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);
//
//            tx.commit();
//            session.clear();
//            return typedQuery.getResultList();
//        } catch (Exception e) {
//            System.err.println("Error in \"GetSafetyListing\" : " + e);
//            return null;
//        }
//    }
    
    
//    public static List<Tuple> GetSafetyCombinationListing() {
//        try {
//
//            System.out.println("GetLegislationCombinationListing");
//            Session session = HibernateUtil.getThreadLocalSession();
//            Transaction tx = session.beginTransaction();
//
//            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
//            Root<Querybuilder> qBRoot = criteriaQuery.from(Querybuilder.class);
//            criteriaQuery.multiselect(qBRoot.get("id").alias("saf_id"), qBRoot.get("querybuilder_name").alias("saf"), qBRoot.get("created_date").alias("created_date"),
//                    qBRoot.get("modified_date").alias("modified_date"), qBRoot.get("querybuilder_condition").alias("combination"), qBRoot.get("querybuilder_status").alias("status"))
//                    .distinct(true).where(criteriaBuilder.equal(qBRoot.get("querybuilder_type"), "safety"))
//                    .orderBy(criteriaBuilder.desc(qBRoot.get("id")));
//            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);
//
//            tx.commit();
//            session.clear();
//            return typedQuery.getResultList();
//        } catch (Exception e) {
//            System.err.println("Error in \"GetSafetyCombinationListing\" : " + e);
//            return null;
//        }
//    }

}
