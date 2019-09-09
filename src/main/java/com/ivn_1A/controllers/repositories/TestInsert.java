/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.repositories;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.Groups;
import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.pdbowner.Domain;
import com.ivn_1A.models.pdbowner.Domain_and_Features_Mapping;
import com.ivn_1A.models.pdbowner.Features;
import com.ivn_1A.models.pdbowner.Pdbversion;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.ivn_1A.models.pdbowner.Vehiclemodel;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ETS-05
 */
public class TestInsert {

    public String execute() {
        
        
        System.err.println("Test");
        Date date = new Date();
        TestInsert testInsert = new TestInsert();
        try {
            
            Groups groups = new Groups("IVN Supervisor", "ivn_supervisor", true, 0, true, date, date);
            testInsert.saveGroups(groups);
            User user = new User("Ram", groups);
            testInsert.saveUser(user);
//            Domain domain = new Domain();
//            domain.setCreated_date(new Date());
//            domain.setCreated_or_updated_by(new TestInsert().getUser(1));
//            domain.setDomain_name("Cars");
//            domain.setModified_date(new Date());
//            domain.setStatus(false);
//            new TestInsert().saveDomain(domain);
//
//            Vehicle vehicle = new Vehicle();
//            vehicle.setCreated_date(new Date());
//            vehicle.setCreated_or_updated_by(new TestInsert().getUser(1));
//            vehicle.setModified_date(new Date());
//            vehicle.setVehiclename("Benz");
//            vehicle.setStatus(true);
//            new TestInsert().saveVehicle(vehicle);
//            
//            Vehiclemodel vehiclemodel = new Vehiclemodel();
//            for (int i = 5; i < 6; i++) {
//                vehiclemodel.setCreated_date(new Date());
//                vehiclemodel.setModified_date(new Date());
//                vehiclemodel.setCreated_or_updated_by(new TestInsert().getUser(1));
//                vehiclemodel.setModelname("m" + (i + 1));
//                vehiclemodel.setStatus(false);
//                new TestInsert().saveVehicleModel(vehiclemodel);
//            }
//
//            Features features = new Features();
//            features.setCreated_date(date);
//            features.setCreated_or_updated_by(new TestInsert().getUser(1));
//            features.setFeature_description("Colored seat are really awesome");
//            features.setFeature_name("Colored Seat");
//            features.setFeature_type("Color");
//            features.setModified_date(date);
//            features.setStatus(false);
//            testInsert.saveFeatures(features);
//
//            Domain_and_Features_Mapping domain_and_Features_Mapping = new Domain_and_Features_Mapping();
//            domain_and_Features_Mapping.setDomain_id(new TestInsert().getDomain(1));
//            domain_and_Features_Mapping.setFeature_id(testInsert.getFeatures(1));
//            testInsert.saveDomain_and_Features_Mapping(domain_and_Features_Mapping);
//            
//            Pdbversion pdbversion = new Pdbversion();
//            pdbversion.setCreated_date(date);
//            pdbversion.setCreated_or_updated_by(testInsert.getUser(2));
//            pdbversion.setFlag(false);
//            pdbversion.setModified_date(date);
//            pdbversion.setPdb_manual_comment("hai");
//            pdbversion.setPdb_versionname(1.0f);
//            pdbversion.setStatus(false);
//            testInsert.savePdbversion(pdbversion);
            
//            Pdbversion_group pdbversion_group = new Pdbversion_group();
//            pdbversion_group.setAvailable_status("audi available");
//            pdbversion_group.setDomain_and_features_mapping_id(testInsert.getDomain_and_Features_Mapping(1));
//            pdbversion_group.setPdbversion_id(testInsert.getPdbversion(3));
//            pdbversion_group.setVehiclemodel_id(testInsert.getVehiclemodel(6));
//            pdbversion_group.setVehicle_id(testInsert.getVehicle(1));
//            testInsert.savePdbversion_group(pdbversion_group);
            return "success";
        } catch (Exception e) {
            System.err.println("Error : " + e);
            return "failed";
        }
    }

    public boolean saveVehicle(Vehicle vehicle) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehicle);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public boolean saveGroups(Groups groups) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(groups);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public boolean saveUser(User user) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(user);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public boolean saveVehicleModel(Vehiclemodel vehicle) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehicle);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public boolean saveDomain(Domain vehicle) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehicle);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e);
            return false;
        }
    }

    public boolean saveDomain_and_Features_Mapping(Domain_and_Features_Mapping vehicle) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehicle);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public boolean saveFeatures(Features vehicle) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehicle);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public boolean savePdbversion(Pdbversion vehicle) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehicle);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public boolean savePdbversion_group(Pdbversion_group vehicle) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehicle);
//            s.createQuery("INSERT INTO `user`(username) SELECT 'Ram' FROM (select 1) as dummy WHERE NOT EXISTS (SELECT 1 from `user` WHERE username  = 'Ram')");
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    //Main Repository
    public User getUser(int id) {
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

    public Domain getDomain(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Domain user = s.get(Domain.class, id);
            tx.commit();
            s.clear();
            return user;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public Features getFeatures(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Features user = s.get(Features.class, id);
            tx.commit();
            s.clear();
            return user;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public Domain_and_Features_Mapping getDomain_and_Features_Mapping(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Domain_and_Features_Mapping user = s.get(Domain_and_Features_Mapping.class, id);
            tx.commit();
            s.clear();
            return user;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public Pdbversion getPdbversion(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Pdbversion user = s.get(Pdbversion.class, id);
            tx.commit();
            s.clear();
            return user;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public Vehiclemodel getVehiclemodel(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Vehiclemodel user = s.get(Vehiclemodel.class, id);
            tx.commit();
            s.clear();
            return user;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public Vehicle getVehicle(int id) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            Vehicle user = s.get(Vehicle.class, id);
            tx.commit();
            s.clear();
            return user;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

}
