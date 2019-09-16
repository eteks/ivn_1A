package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.admin.User;
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
import org.hibernate.query.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author ets-poc
 */
public class PDBOwnerDB {

    public static List<Pdbversion_group> GetComparedFeatures(int prevpdb_id, int curpdb_id, String feature_type) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
        Join<Pdbversion_group, Domain_and_Features_Mapping> domfeaJoin = pdbversion_groupRoot.join("domain_and_features_mapping_id", JoinType.INNER);
        Join<Domain_and_Features_Mapping, Domain> domJoin = domfeaJoin.join("domain_id", JoinType.INNER);
        Join<Domain_and_Features_Mapping, Features> feaJoin = domfeaJoin.join("feature_id", JoinType.INNER);
        criteriaQuery.select(
                criteriaBuilder.concat(
                        criteriaBuilder.concat(
                                criteriaBuilder.concat(
                                        "(", domJoin.<String>get("domain_name")
                                ),
                                ")"
                        ),
                        feaJoin.<String>get("feature_name")
                )).distinct(true);

        Subquery<Pdbversion_group> subquery = criteriaQuery.subquery(Pdbversion_group.class);
        Root<Pdbversion_group> subQueryRoot = subquery.from(Pdbversion_group.class);
        subquery.select(subQueryRoot.get("domain_and_features_mapping_id")).distinct(true);
        if (feature_type.equals("removed")) {
            System.out.println("entered into removed");
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("pdbversion_id").get("id"), curpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id
                            ),
                            criteriaBuilder.not(pdbversion_groupRoot.get("domain_and_features_mapping_id").in(subquery))
                    )
            );
        } else {
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("pdbversion_id").get("id"), prevpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    pdbversion_groupRoot.get("pdbversion_id").get("id"), curpdb_id
                            ),
                            criteriaBuilder.not(pdbversion_groupRoot.get("domain_and_features_mapping_id").in(subquery))
                    )
            );
        }

//            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id)),criteriaBuilder.not(criteriaBuilder.exists(subquery))); 
        TypedQuery<Pdbversion_group> feature_results = s.createQuery(criteriaQuery);
        tx.commit();
        s.clear();
        return feature_results.getResultList();
    }

    public static List<Pdbversion_group> GetComparedVehicleModels(int prevpdb_id, int curpdb_id, String feature_type) {
        Session s = HibernateUtil.getThreadLocalSession();
        Transaction tx = s.beginTransaction();

        //Working code
        final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
        Join<Pdbversion_group, Vehiclemodel> vehmodJoin = pdbversion_groupRoot.join("vehiclemodel_id", JoinType.INNER);
        criteriaQuery.select(vehmodJoin.get("modelname")).distinct(true);

        Subquery<Pdbversion_group> subquery = criteriaQuery.subquery(Pdbversion_group.class);
        Root<Pdbversion_group> subQueryRoot = subquery.from(Pdbversion_group.class);
        subquery.select(subQueryRoot.get("vehiclemodel_id")).distinct(true);

        if (feature_type.equals("removed")) {
            System.out.println("entered into removed");
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("pdbversion_id").get("id"), curpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id
                            ),
                            criteriaBuilder.not(pdbversion_groupRoot.get("vehiclemodel_id").in(subquery))
                    )
            );
        } else {
            subquery.where(criteriaBuilder.equal(subQueryRoot.get("pdbversion_id").get("id"), prevpdb_id));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(
                                    pdbversion_groupRoot.get("pdbversion_id").get("id"), curpdb_id
                            ),
                            criteriaBuilder.not(pdbversion_groupRoot.get("vehiclemodel_id").in(subquery))
                    )
            );
        }

//            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id)),criteriaBuilder.not(criteriaBuilder.exists(subquery))); 
        TypedQuery<Pdbversion_group> vehmod_results = s.createQuery(criteriaQuery);
        tx.commit();
        s.clear();
        return vehmod_results.getResultList();
    }

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
            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Pdbversion> criteriaQuery = criteriaBuilder.createQuery(Pdbversion.class);
            // The root of our search is sku
            Root<Pdbversion> test = criteriaQuery.from(Pdbversion.class);
            List<Predicate> restrictions = new ArrayList<>();
            restrictions.add(criteriaBuilder.isNull(test.get("pdb_reference_version")));
            criteriaQuery.where(restrictions.toArray(new Predicate[restrictions.size()]));
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("pdb_versionname")));
            //create resultset as list
            Query pdbversion = s.createQuery(criteriaQuery).setMaxResults(1);

            tx.commit();
            s.clear();
            return pdbversion.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetVersionname\" : " + e.getMessage());
            return null;
        }
    }

    public static Pdbversion insertPDBVersion(Pdbversion pdbversion) {
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

    public static Pdbversion_group insertPDBVersionGroup(Pdbversion_group pvg) {
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

    public static Domain saveDomain(Domain domain) {
        try {
            Domain domain1 = getDomainByName(domain.getDomain_name());
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            if (domain1 == null) {
                s.save(domain);
                domain1 = domain;
            }

            tx.commit();
            s.clear();
            return domain1;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"saveDomain\" : " + e.getMessage());
            return null;
        }
    }

    //Domain Data by Name
    public static Domain getDomainByName(String domainName) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Domain> criteriaQuery = criteriaBuilder.createQuery(Domain.class);

            Root<Domain> domainRoot = criteriaQuery.from(Domain.class);
            criteriaQuery.where(criteriaBuilder.equal(domainRoot.get("domain_name"), domainName));
            TypedQuery<Domain> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error : \"getDomainByName\"" + e);
            return null;
        }
    }

    public static Features saveFeatures(Features features) {
        try {
            Features features1 = getFeaturesByName(features.getFeature_name());
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            if (features1 == null) {
                s.save(features);
                features1 = features;
            }
            tx.commit();
            s.clear();
            return features1;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"saveFeatures\" : " + e.getMessage());
            return null;
        }
    }

    //Domain Data by Name
    public static Features getFeaturesByName(String featureName) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Features> criteriaQuery = criteriaBuilder.createQuery(Features.class);

            Root<Features> featureRoot = criteriaQuery.from(Features.class);
            criteriaQuery.where(criteriaBuilder.equal(featureRoot.get("feature_name"), featureName));
            TypedQuery<Features> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error : \"getFeaturesByName\"" + e);
            return null;
        }
    }

    public static Domain_and_Features_Mapping saveDomain_and_Features_Mapping(Domain_and_Features_Mapping dfm) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(dfm);
            tx.commit();
            s.clear();
            return dfm;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"saveDomain_and_Features_Mapping\" : " + e.getMessage());
            return null;
        }
    }

    public static User getUser(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            User user = s.get(User.class, id);
            tx.commit();
            s.clear();
            return user;
        } catch (Exception e) {
            System.err.println("Error in \"getUser\" : " + e.getMessage());
            return null;
        }
    }

    public static Vehicle saveVehicles(Vehicle vehicle) {
        try {
            Vehicle vehicle1 = getVehicleByName(vehicle.getVehiclename());
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            if (vehicle1 == null) {
                s.save(vehicle);
                vehicle1 = vehicle;
            }
            tx.commit();
            s.clear();
            return vehicle1;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"saveVehicles\" : " + e.getMessage());
            return null;
        }
    }

    //Vehicle Data by Name
    public static Vehicle getVehicleByName(String vehicleName) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Vehicle> criteriaQuery = criteriaBuilder.createQuery(Vehicle.class);

            Root<Vehicle> vehicleRoot = criteriaQuery.from(Vehicle.class);
            criteriaQuery.where(criteriaBuilder.equal(vehicleRoot.get("vehiclename"), vehicleName));
            TypedQuery<Vehicle> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error : \"getVehicleByName\"" + e);
            return null;
        }
    }

    public static Vehiclemodel saveVehicleModel(Vehiclemodel vehiclemodel) {
        try {
            Vehiclemodel vehiclemodel1 = getVehicleModelByName(vehiclemodel.getModelname());
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            if (vehiclemodel1 == null) {
                s.save(vehiclemodel);
                vehiclemodel1 = vehiclemodel;
            }
            tx.commit();
            s.clear();
            return vehiclemodel1;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"saveVehicleModel\" : " + e.getMessage());
            return null;
        }
    }

    //Vehiclemodel Data by Name
    public static Vehiclemodel getVehicleModelByName(String vehicleModelName) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Vehiclemodel> criteriaQuery = criteriaBuilder.createQuery(Vehiclemodel.class);

            Root<Vehiclemodel> vehicleModelRoot = criteriaQuery.from(Vehiclemodel.class);
            criteriaQuery.where(criteriaBuilder.equal(vehicleModelRoot.get("modelname"), vehicleModelName));
            TypedQuery<Vehiclemodel> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error : \"getVehicleModelByName\"" + e);
            return null;
        }
    }

    public static Map<String, Object> GetPDBPreviousVersion_DomFea(int prevpdb_id, int curpdb_id) {
        try {
//            Session s = HibernateUtil.getThreadLocalSession();
//            Transaction tx = s.beginTransaction();
            Map<String, Object> results = new HashMap<String, Object>();

            String removed = "removed";
            String added = "added";

            List<Pdbversion_group> removed_features = GetComparedFeatures(prevpdb_id, curpdb_id, removed);
            results.put("removed_features", StringUtils.join(removed_features, ","));

            List<Pdbversion_group> added_features = GetComparedFeatures(prevpdb_id, curpdb_id, added);
            results.put("added_features", StringUtils.join(added_features, ","));

            List<Pdbversion_group> removed_models = GetComparedVehicleModels(prevpdb_id, curpdb_id, removed);
            results.put("removed_models", StringUtils.join(removed_models, ","));

            List<Pdbversion_group> added_models = GetComparedVehicleModels(prevpdb_id, curpdb_id, added);
            results.put("added_models", StringUtils.join(added_models, ","));

//            
//            tx.commit();
//            s.clear();
            return results;
        } catch (Exception e) {
            System.err.println("Error in \"GetPDBPreviousVersion_DomFea\" : " + e.getMessage());
            return null;
        }
    }

    public static List<Domain_and_Features_Mapping> LoadFeaturesList() {
        try {
            System.out.println("LoadFeaturesList");
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            System.out.println("before resultdata");
            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Domain_and_Features_Mapping> criteriaQuery = criteriaBuilder.createQuery(Domain_and_Features_Mapping.class);
            criteriaQuery.from(Domain_and_Features_Mapping.class);
            System.out.println("resultdata");
            //create resultset as list
            TypedQuery<Domain_and_Features_Mapping> dfm_result = s.createQuery(criteriaQuery);
            System.out.println(dfm_result);
            System.err.println(dfm_result);
            tx.commit();
            s.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"LoadFeaturesList\" : " + e);
            return null;
        }
    }

    //Vehicle Data
    public static List<Vehicle> loadVehicleVersion() {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            // create Criteria
            CriteriaQuery<Vehicle> criteriaQuery = s.getCriteriaBuilder().createQuery(Vehicle.class);
            criteriaQuery.from(Vehicle.class);
            //create resultset as list
            List<Vehicle> vehicles = s.createQuery(criteriaQuery).getResultList();
            System.err.println(vehicles);
            tx.commit();
            s.clear();
            return vehicles;
        } catch (Exception e) {
            System.err.println("Error in \"loadVehicleVersion\" : " + e);
            return null;
        }
    }

    //Pdbversion group Data
    public static List<Tuple> loadPdbversion_groupByVehicleId(int id, String action) {
        try {
            System.err.println("loadPdbversion_groupByVehicleId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);

            criteriaQuery.multiselect(pdbversion_groupRoot.get("pdbversion_id").get("id").alias("pid"), pdbversion_groupRoot.get("pdbversion_id").get("pdb_versionname").alias("pversion"), 
                    pdbversion_groupRoot.get("pdbversion_id").get("status").alias("status")).distinct(true);
            if (action.equals("edit")) {
                criteriaQuery.where(criteriaBuilder.equal(pdbversion_groupRoot.get("vehicle_id").get("id"), id));
            } else {
                criteriaQuery.where(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("status"), true), criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("flag"), true),
                        criteriaBuilder.equal(pdbversion_groupRoot.get("vehicle_id").get("id"), id));
            }
            criteriaQuery.orderBy(criteriaBuilder.desc(pdbversion_groupRoot.get("pdbversion_id").get("pdb_versionname")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error \"loadPdbversion_groupByVehicleId\" : " + e);
            return null;
        }
    }

    //Vehicle and Vehicle Model Data
    public static List<Tuple> loadVehicleAndModelByVehicleId(int id) {
        try {
            System.err.println("loadPdbversion_groupByVehicleId");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Pdbversion_group> pRoot = criteriaQuery.from(Pdbversion_group.class);

            criteriaQuery.multiselect(pRoot.get("pdbversion_id").get("pdb_versionname").alias("versionname"), pRoot.get("pdbversion_id").get("status").alias("status"), pRoot.get("vehicle_id").get("id").alias("vehicle_id"),
                    pRoot.get("vehicle_id").get("vehiclename").alias("vehiclename"), criteriaBuilder.function("group_concat", String.class, pRoot.get("vehiclemodel_id").get("id")).alias("modelid"),
                    criteriaBuilder.function("group_concat", String.class, pRoot.get("vehiclemodel_id").get("modelname")).alias("modelname"))
                    .distinct(true).where(criteriaBuilder.equal(pRoot.get("pdbversion_id").get("id"), id)).orderBy(criteriaBuilder.desc(pRoot.get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error \"loadVehicleAndModelByVehicleId\" : " + e);
            return null;
        }
    }

    public static List<Tuple> GetVehicleModel_Listing() {

        try {
            System.err.println("GetVehicleModel_Listing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Pdbversion_group> pRoot = criteriaQuery.from(Pdbversion_group.class);
            pRoot.join("vehicle_id", JoinType.INNER);
            pRoot.join("vehiclemodel_id", JoinType.INNER);
//            Root<Vehicle> vRoot = criteriaQuery.from(Vehicle.class);
            Root<Vehiclemodel> vmRoot = criteriaQuery.from(Vehiclemodel.class);

            criteriaQuery.multiselect(pRoot.get("vehiclemodel_id").get("modelname").alias("modelname"), pRoot.get("vehiclemodel_id").get("status").alias("status"), pRoot.get("vehiclemodel_id").get("created_date").alias("created_date"),
                    pRoot.get("vehiclemodel_id").get("modified_date").alias("modified_date"), criteriaBuilder.function("group_concat", String.class, pRoot.get("vehicle_id").get("vehiclename")).alias("vehiclename"),
                    criteriaBuilder.function("group_concat", String.class, pRoot.get("pdbversion_id").get("pdb_versionname")).alias("versionname"))
                    .distinct(true).where(criteriaBuilder.equal(pRoot.get("vehiclemodel_id").get("id"), vmRoot.get("id")))
                    .groupBy(pRoot.get("vehiclemodel_id").get("modelname")).orderBy(criteriaBuilder.desc(pRoot.get("vehiclemodel_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetVehicleModel_Listing\" : " + e);
            return null;
        }
    }

    public static List<Tuple> getVehicle_Listing() {

        try {
            System.err.println("getVehicle_Listing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Pdbversion_group> pRoot = criteriaQuery.from(Pdbversion_group.class);
            pRoot.join("vehicle_id", JoinType.INNER);
            criteriaQuery.multiselect(pRoot.get("pdbversion_id").get("id").alias("pdb_version_id"), pRoot.get("vehicle_id").get("vehiclename").alias("vehiclename"), pRoot.get("vehicle_id").get("status").alias("status"),
                    pRoot.get("vehicle_id").get("created_date").alias("created_date"), pRoot.get("vehicle_id").get("modified_date").alias("modified_date"), criteriaBuilder.function("group_concat", String.class, pRoot.get("pdbversion_id").get("pdb_versionname")).alias("pdb_version_name"))
                    .distinct(true).groupBy(pRoot.get("vehicle_id").get("vehiclename")).orderBy(criteriaBuilder.desc(pRoot.get("vehicle_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"getVehicle_Listing\" : " + e);
            return null;
        }
    }

    public static List<Tuple> GetPDBVersion_Listing() {
        try {
            System.err.println("GetVehicleVersion_Listing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Pdbversion_group> pRoot = criteriaQuery.from(Pdbversion_group.class);
            pRoot.join("pdbversion_id", JoinType.INNER);
            pRoot.join("vehicle_id", JoinType.INNER);
            pRoot.join("vehiclemodel_id", JoinType.INNER);

            criteriaQuery.multiselect(pRoot.get("pdbversion_id").get("id").alias("pdb_id"), pRoot.get("pdbversion_id").get("pdb_versionname").alias("pdb_versionname"), criteriaBuilder.function("group_concat", String.class, pRoot.get("vehicle_id").get("id")).alias("vehicle_id"),
                    criteriaBuilder.function("group_concat", String.class, pRoot.get("vehicle_id").get("vehiclename")).alias("vehiclename"), criteriaBuilder.function("group_concat", String.class, pRoot.get("vehiclemodel_id").get("modelname")).alias("modelname"),
                    pRoot.get("pdbversion_id").get("status").alias("status"), pRoot.get("pdbversion_id").get("flag").alias("flag"), pRoot.get("pdbversion_id").get("created_date").alias("created_date"), pRoot.get("pdbversion_id").get("modified_date").alias("modified_date"))
                    .distinct(true).groupBy(pRoot.get("pdbversion_id").get("pdb_versionname")).orderBy(criteriaBuilder.desc(pRoot.get("pdbversion_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetPDBVersion_Listing\" : " + e);
            return null;
        }
    }

    public static List<Tuple> LoadPDBDomainFeatures(int pdb_id) {
        System.out.println("LoadPDBDomainFeatures");
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
//        //Working code
            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
            Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
            criteriaQuery.where(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), pdb_id));
            criteriaQuery.multiselect(pdbversion_groupRoot.get("vehiclemodel_id").get("id").alias("model_id"),
                    pdbversion_groupRoot.get("domain_and_features_mapping_id").get("id").alias("fid"),
                    pdbversion_groupRoot.get("available_status").alias("status"),
                    pdbversion_groupRoot.get("domain_and_features_mapping_id").get("domain_id").get("domain_name").alias("domainname"),
                    pdbversion_groupRoot.get("domain_and_features_mapping_id").get("feature_id").get("feature_name").alias("featurename"),
                    pdbversion_groupRoot.get("pdbversion_id").get("status").alias("pdbstatus")
            );
//        criteriaQuery.multiselect(pdbversion_groupRoot.get("vehiclemodel_id").get("id"),
//                                  pdbversion_groupRoot.get("domain_and_features_mapping_id").get("id"),
//                                  pdbversion_groupRoot.get("available_status"),
//                                  pdbversion_groupRoot.get("domain_and_features_mapping_id").get("domain_id").get("domain_name"),
//                                  pdbversion_groupRoot.get("domain_and_features_mapping_id").get("feature_id").get("feature_name")
//                                );
//        TypedQuery<Pdbversion_group> feature_results = s.createQuery(criteriaQuery);
            List<Tuple> feature_results = s.createQuery(criteriaQuery).getResultList();

//        for (Tuple fea : feature_results) {
//                System.out.println("fea"+fea.get("featurename"));
//        }
//            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
//            CriteriaQuery<Pdbversion_group> criteriaQuery = criteriaBuilder.createQuery(Pdbversion_group.class);
//            Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
//            criteriaQuery.where(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"),pdb_id)); 
////            criteriaQuery.select(pdbversion_groupRoot.get("vehiclemodel_id").get("id"));
//            
//            //create resultset as list
//            TypedQuery<Pdbversion_group> feature_results = s.createQuery(criteriaQuery);
            tx.commit();
            s.clear();
            return feature_results;
        } catch (Exception e) {
            System.err.println("Error in \"LoadPDBDomainFeatures\" : " + e);
            return null;
        }
    }

    public static List<Tuple> GetDomainFeaturesListing() {
        try {
            System.err.println("GetVehicleVersion_Listing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Domain_and_Features_Mapping> pRoot = criteriaQuery.from(Domain_and_Features_Mapping.class);
            pRoot.join("domain_id", JoinType.INNER);
            pRoot.join("feature_id", JoinType.INNER);

            criteriaQuery.multiselect(pRoot.get("id").alias("dfm_id"), pRoot.get("domain_id").get("domain_name").alias("domain_name"),
                    pRoot.get("feature_id").get("feature_name").alias("feature_name"), pRoot.get("feature_id").get("id").alias("fid"),
                    pRoot.get("feature_id").get("created_date").alias("created_date"),
                    pRoot.get("feature_id").get("modified_date").alias("modified_date"))
                    .orderBy(criteriaBuilder.desc(pRoot.get("domain_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetDomainFeaturesListing\" : " + e);
            return null;
        }
    }

    public static List<Tuple> GetDomainFeaturesListing1() {
        try {
            System.err.println("GetVehicleVersion_Listing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

            Root<Domain_and_Features_Mapping> pRoot = criteriaQuery.from(Domain_and_Features_Mapping.class);
            pRoot.join("domain_id", JoinType.INNER);
            pRoot.join("feature_id", JoinType.INNER);

            criteriaQuery.multiselect(pRoot.get("id").alias("dfm_id"), pRoot.get("domain_id").get("domain_name").alias("domain_name"), criteriaBuilder.function("group_concat", String.class, pRoot.get("feature_id").get("feature_name")).alias("feature_name"),
                    criteriaBuilder.function("group_concat", String.class, pRoot.get("feature_id").get("created_date")).alias("created_date"),
                    criteriaBuilder.function("group_concat", String.class, pRoot.get("feature_id").get("modified_date")).alias("modified_date"))
                    .orderBy(criteriaBuilder.desc(pRoot.get("domain_id").get("id")));
            TypedQuery<Tuple> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"GetDomainFeaturesListing\" : " + e);
            return null;
        }
    }

    public static List<Pdbversion_group> LoadPDBPreviousVehicleversionData(int pdb_version_id) {

        System.out.println("LoadPDBPreviousVehicleversionData");
        try {
            System.err.println("GetVehicleVersion_Listing");
            Session session = HibernateUtil.getThreadLocalSession();
            Transaction tx = session.beginTransaction();

            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Pdbversion_group> criteriaQuery = criteriaBuilder.createQuery(Pdbversion_group.class);

            Root<Pdbversion_group> pRoot = criteriaQuery.from(Pdbversion_group.class);
            pRoot.join("pdbversion_id", JoinType.INNER);
            pRoot.join("vehicle_id", JoinType.INNER);
            pRoot.join("vehiclemodel_id", JoinType.INNER);

            criteriaQuery.distinct(true).where(criteriaBuilder.equal(pRoot.get("pdbversion_id").get("id"), pdb_version_id))
                    .groupBy(pRoot.get("vehiclemodel_id").get("modelname"), pRoot.get("vehiclemodel_id").get("id"))
                    .orderBy(criteriaBuilder.desc(pRoot.get("pdbversion_id").get("id")));
            TypedQuery<Pdbversion_group> typedQuery = session.createQuery(criteriaQuery);

            tx.commit();
            session.clear();
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.err.println("Error in \"LoadPDBPreviousVehicleversionData\" : " + e);
            return null;
        }
    }

    public static Vehicle getVehicle(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Vehicle vehicle = s.get(Vehicle.class, id);
            tx.commit();
            s.clear();
            return vehicle;
        } catch (Exception e) {
            System.err.println("Error in \"getUser\" : " + e.getMessage());
            return null;
        }
    }

    public static Vehiclemodel getVehiclemodel(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Vehiclemodel vehiclemodel = s.get(Vehiclemodel.class, id);
            tx.commit();
            s.clear();
            return vehiclemodel;
        } catch (Exception e) {
            System.err.println("Error in \"getUser\" : " + e.getMessage());
            return null;
        }
    }

    public static Domain_and_Features_Mapping getDomain_and_Features_Mapping(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Domain_and_Features_Mapping dfm = s.get(Domain_and_Features_Mapping.class, id);
            tx.commit();
            s.clear();
            return dfm;
        } catch (Exception e) {
            System.err.println("Error in \"getUser\" : " + e.getMessage());
            return null;
        }
    }
    
    
    //Pdbversion Data by Name
    public static Pdbversion getPdbversionByName(float versionname) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Pdbversion> criteriaQuery = criteriaBuilder.createQuery(Pdbversion.class);

            Root<Pdbversion> pdbversionRoot = criteriaQuery.from(Pdbversion.class);
            criteriaQuery.select(pdbversionRoot).distinct(true).where(criteriaBuilder.equal(pdbversionRoot.get("pdb_versionname"), versionname));
            TypedQuery<Pdbversion> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getResultList().get(0);
        } catch (Exception e) {
            System.err.println("Error : \"getPdbversionByName\"" + e);
            return null;
        }
    }

}
