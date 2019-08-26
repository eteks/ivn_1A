package com.ivn_1A.models.pdbowner;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.admin.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author ets-poc
 */
public class PDBOwnerDB {

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
            List<Predicate> restrictions = new ArrayList<Predicate>();
            restrictions.add(criteriaBuilder.isNull(test.get("pdb_reference_version")));
            criteriaQuery.where(restrictions.toArray(new Predicate[restrictions.size()]));
            criteriaQuery.orderBy(criteriaBuilder.desc(test.get("pdb_versionname")));
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

            if (domain1 != null) {
                s.save(domain);
            }

            tx.commit();
            s.clear();
            return domain1;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertPDBVersionGroup\" : " + e.getMessage());
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
            criteriaQuery.where(criteriaBuilder.equal(domainRoot.get("domain_name"), domainName)).orderBy(criteriaBuilder.desc(domainRoot.get("id")));
            TypedQuery<Domain> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error : " + e);
            return null;
        }
    }

    public static Features saveFeatures(Features features) {
        try {
            Features features1 = getFeaturesByName(features.getFeature_name());
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            if (features1 != null) {
                s.save(features);
            }
            tx.commit();
            s.clear();
            return features1;
//            return pdbversion.getId();
        } catch (Exception e) {
            System.err.println("Error in \"insertPDBVersionGroup\" : " + e.getMessage());
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
            criteriaQuery.where(criteriaBuilder.equal(featureRoot.get("feature_name"), featureName)).orderBy(criteriaBuilder.desc(featureRoot.get("id")));
            TypedQuery<Features> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error : " + e);
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
            System.err.println("Error in \"insertPDBVersionGroup\" : " + e.getMessage());
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
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public static Map<String, Object> GetPDBPreviousVersion_DomFea(int prevpdb_id, int curpdb_id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Map<String, Object> results = new HashMap<String, Object>();

//            //Working code
//            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
//            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
////            Expression<String> exp1 = criteriaQuery.concat(Expression<String> a, String b, Expression<String> c ,Expression<String> d ,String e);
////            Expression<String> exp1 = criteriaBuilder.concat(Expression<String> a, String b);
//            Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
//            Join<Pdbversion_group, Domain_and_Features_Mapping> domfeaJoin = pdbversion_groupRoot.join("domain_and_features_mapping_id", JoinType.INNER);
//            Join<Domain_and_Features_Mapping, Domain> domJoin = domfeaJoin.join("domain_id", JoinType.INNER);
//            Join<Domain_and_Features_Mapping, Features> feaJoin = domfeaJoin.join("feature_id", JoinType.INNER); 
//            criteriaQuery.select(
//                    criteriaBuilder.function("group_concat", 
//                    String.class, criteriaBuilder.concat(
//                    criteriaBuilder.concat(
//                        criteriaBuilder.concat(
//                            "(", domJoin.<String>get("domain_name")
//                        ),
//                            ")"
//                    ),
//                        feaJoin.<String>get("feature_name")
//            )));
////            criteriaQuery.select(
////                    criteriaBuilder.function("group_concat", 
////                    String.class, criteriaBuilder.concat(
////                    criteriaBuilder.concat(
////                        criteriaBuilder.concat(
////                            "(", domJoin.<String>get("domain_name")
////                        ),
////                            ")"
////                    ),
////                        feaJoin.<String>get("feature_name")
////            )));
//
//            Subquery<Pdbversion_group > subquery = criteriaQuery.subquery(Pdbversion_group .class);
//            Root<Pdbversion_group> subQueryRoot = subquery.from(Pdbversion_group.class);
//            subquery.select(subQueryRoot.get("domain_and_features_mapping_id")).distinct(true);
//            subquery.where(criteriaBuilder.equal(subQueryRoot.get("pdbversion_id").get("id"), curpdb_id));
//            
////            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id)));
////            criteriaQuery.where(criteriaBuilder.not(criteriaBuilder.exists(subquery))); 
//            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id)),criteriaBuilder.not(criteriaBuilder.exists(subquery))); 
////            criteriaQuery.select(criteriaBuilder.construct(Pdbversion_group.class, 
////                    criteriaBuilder.function("group_concat", 
////                    String.class, criteriaBuilder.concat(
////                    criteriaBuilder.concat(
////                        criteriaBuilder.concat(
////                            "(", domJoin.<String>get("domain_name")
////                        ),
////                            ")"
////                    ),
////                        feaJoin.<String>get("feature_name")
////            ))));
////            criteriaQuery.select(
////                    criteriaBuilder.concat(
////                        criteriaBuilder.concat(
////                            criteriaBuilder.concat(
////                                "(", domJoin.<String>get("domain_name")
////                            ),
////                                ")"
////                        ),
////                            feaJoin.<String>get("feature_name")
////                        )
////            );
////            criteriaQuery.where(criteriaBuilder.equal(pdbversion_groupRoot.get("pdbversion_id").get("id"), prevpdb_id));
//            
//
//            
//            System.err.println(criteriaQuery.toString());
//            
//            TypedQuery<Features> removed_features = s.createQuery(criteriaQuery);
//            results.put("removed_features", removed_features.getResultList());
            
            TypedQuery removed_features = s.createQuery("SELECT DISTINCT pvg.domain_and_features_mapping_id as dfm_id, CONCAT('(',d.domain_name,')',' ',f.feature_name) as dom_fea FROM Pdbversion_group as pvg \n" +
            "INNER JOIN Domain_and_Features_Mapping as dfm ON dfm.id = pvg.domain_and_features_mapping_id \n" +
            "INNER JOIN Domain as d ON d.id=dfm.domain_id \n" +
            "INNER JOIN Features as f ON f.id=dfm.feature_id\n" +
            "WHERE pdbversion_id="+prevpdb_id+" AND pvg.domain_and_features_mapping_id NOT IN (SELECT DISTINCT domain_and_features_mapping_id FROM Pdbversion_group WHERE pdbversion_id="+curpdb_id+")");
              results.put("removed_features", removed_features.getResultList());
//            System.out.println("removed_features"+removed_features);   

//            Query added_features = s.createQuery("SELECT GROUP_CONCAT(DISTINCT(pvg.domain_and_features_mapping_id)) as dfm_id, \n" +
//                    "GROUP_CONCAT(DISTINCT(CONCAT('(',d.domain_name,')',' ',f.feature_name))) as \n" +
//                    "dom_fea  \n"
//                    + "FROM Pdbversion_group as pvg \n"
//                    + "INNER JOIN Domain_and_Features_Mapping as dfm ON dfm.id = pvg.domain_and_features_mapping_id \n"
//                    + "INNER JOIN Domain as d ON d.id=dfm.domain_id \n"
//                    + "INNER JOIN Features as f ON f.id=dfm.feature_id\n"
//                    + "WHERE pvg.pdbversion_id=" + curpdb_id + " AND pvg.domain_and_features_mapping_id NOT IN \n"
//                    + "(SELECT DISTINCT domain_and_features_mapping_id FROM Pdbversion_group WHERE pdbversion_id=" + prevpdb_id + ")");
//            results.put("added_features", added_features.getResultList());
//            System.out.println("added_features"+added_features);    

            tx.commit();
            s.clear();
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
            Root<Domain_and_Features_Mapping> dfm = criteriaQuery.from(Domain_and_Features_Mapping.class);
            Join<Domain_and_Features_Mapping, Domain> joindomain = dfm.join("domain_id", JoinType.INNER);
            Join<Domain_and_Features_Mapping, Features> joinfeatures = dfm.join("feature_id", JoinType.INNER);
//            criteriaQuery.multiselect(joindomain.get("domain_id"), joinfeatures.get("feature_id"));
//            criteriaQuery.select(joindomain.get("domain_name"));
//            criteriaQuery.select(joinfeatures.get("feature_name"));
//            criteriaQuery.select(dfm.get("id"));
//            List<Predicate> conditions = new ArrayList();
//            conditions.add(criteriaBuilder.equal(joinOrganization.get("name"), "XYZ"));
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
            return null;
        }
    }

    //Pdbversion group Data
    public static List<Pdbversion_group> loadVehicleAndModelName(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Pdbversion_group> criteriaQuery = criteriaBuilder.createQuery(Pdbversion_group.class);
            Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
            Join<Pdbversion_group, Vehicle> joindomain = pdbversion_groupRoot.join("vehicle_id", JoinType.INNER);
            Join<Pdbversion_group, Vehiclemodel> joinfeatures = joindomain.join("vehiclemodel_id", JoinType.INNER);
            criteriaQuery.where(criteriaBuilder.equal(joinfeatures.get("vehicle_id").get("id"), id));
            TypedQuery<Pdbversion_group> dfm_result = s.createQuery(criteriaQuery);
            System.out.println("dfm_result" + dfm_result.getResultList());
            tx.commit();
            s.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error : " + e);
            return null;
        }
    }

    //Pdbversion group Data
    public static List<Object[]> loadPdbversion_groupByVehicleId(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
            criteriaQuery.multiselect(pdbversion_groupRoot.get("pdbversion_id").get("id"), pdbversion_groupRoot.get("pdbversion_id").get("pdb_versionname")).distinct(true).where(criteriaBuilder.equal(pdbversion_groupRoot.get("vehicle_id").get("id"), id));
            List<Object[]> res = s.createQuery(criteriaQuery).getResultList();
            tx.commit();
            s.clear();
            return res;
        } catch (Exception e) {
            System.err.println("Error : " + e);
            return null;
        }
    }

    //Pdbversion group Data
    public static List<Pdbversion_group> loadPdbversion_groupByVehicleId1(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

            Root<Pdbversion_group> pdbversion_groupRoot = criteriaQuery.from(Pdbversion_group.class);
            Join<Pdbversion_group, Vehicle> vehicleJoin = pdbversion_groupRoot.join("id", JoinType.INNER);
            Join<Pdbversion_group, Vehiclemodel> modelJoin = vehicleJoin.join("id", JoinType.INNER);
            criteriaQuery.select(vehicleJoin.get("vehiclename")).distinct(true);
            criteriaQuery.select(criteriaBuilder.construct(Pdbversion_group.class, criteriaBuilder.function("group_concat", String.class, modelJoin.get("modelname"))));
            criteriaQuery.where(criteriaBuilder.equal(pdbversion_groupRoot.get("vehicle_id").get("id"), id));
            System.err.println(criteriaQuery.toString());
            TypedQuery<Pdbversion_group> dfm_result = s.createQuery(criteriaQuery);
            System.out.println("dfm_result" + dfm_result.getResultList());
            tx.commit();
            s.clear();
            return dfm_result.getResultList();
        } catch (Exception e) {
            System.err.println("Error : " + e);
            return null;
        }
    }

    //Pdbversion group Data
    public static HashMap<String, Object> loadPdbversion_groupByVehicleId2(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Pdbversion_group> pRoot = criteriaQuery.from(Pdbversion_group.class);
            Root<Vehicle> vRoot = criteriaQuery.from(Vehicle.class);
            Root<Vehiclemodel> vmRoot = criteriaQuery.from(Vehiclemodel.class);

            criteriaQuery.multiselect(pRoot, vRoot.get("id"), vRoot.get("vehiclename"), criteriaBuilder.function("group_concat", String.class, vmRoot.get("modelname")), criteriaBuilder.function("group_concat", String.class, vmRoot.get("id"))).distinct(true)
                    .where(criteriaBuilder.equal(pRoot.get("vehicle_id").get("id"), vRoot.get("id")), criteriaBuilder.equal(pRoot.get("vehiclemodel_id").get("id"), vmRoot.get("id")), criteriaBuilder.equal(pRoot.get("vehicle_id").get("id"), id))
                    .orderBy(criteriaBuilder.desc(pRoot.get("pdbversion_id").get("id")));

            List<Object[]> reObjects = s.createQuery(criteriaQuery).getResultList();
            HashMap<String, Object> hashMap = new HashMap<>();
            for (Object[] reObject : reObjects) {

                int vid = (Integer) reObject[1];
                System.out.println(vid);
                hashMap.put("vehicle_id", vid);

                String vehicle = (String) reObject[2];
                System.out.println(vehicle);
                hashMap.put("vehiclename", vehicle);

                String vehiclemodel = (String) reObject[3];
                System.out.println(vehiclemodel);
                hashMap.put("modelname", vehiclemodel);

                String mid = (String) reObject[4];
                System.out.println(mid);
                hashMap.put("model_id", mid);
            }

            tx.commit();
            s.clear();
            return hashMap;
        } catch (Exception e) {
            System.err.println("Error : " + e);
            return null;
        }
    }

//    List<PostSummaryDTO> postSummaries = entityManager
//    .createQuery(
//        "select " +
//        "   p.id as id, " +
//        "   p.title as title, " +
//        "   group_concat(t.name) as tags " +
//        "from Post p " +
//        "left join p.tags t " +
//        "group by p.id, p.title")
//    .unwrap(Query.class)
//    .setResultTransformer(
//        Transformers.aliasToBean(PostSummaryDTO.class)
//    )
//    .getResultList();
//    Criteria criteria = getHibernateSession().createCriteria(A.class);
//    criteria.createAlias("b", "b", JoinType.INNER_JOIN);
//    criteria.createAlias("b.r", "b.r", JoinType.INNER_JOIN);
//    criteria.createAlias("b.c", "b.c", JoinType.LEFT_OUTER_JOIN);
//    ProjectionList projectionList = Projections.projectionList();
//    // THE BELOW LINE WILL MAKE SURE COULMN a IS DISTINCT
//    projectionList.add(Projections.distinct(Projections.property("a")), "a");
//    // THE BELOW LINKE WILL GROUP IT BY COLUMN c
//    projectionList.add(Projections.groupProperty("c"));
//    // ADD all the fields that u want in projection
//    criteria.setProjection(projectionList);
//    criteria.setResultTransformer(Transformers.aliasToBean(A.class));
//    return criteria.list();
}
