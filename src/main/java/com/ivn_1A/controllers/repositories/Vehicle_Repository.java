/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.repositories;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.ivn_1A.models.pdbowner.Vehiclemodel;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ETS-05
 */
public class Vehicle_Repository {

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

    public List<Vehicle> getVehicles(String vname) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            List<Vehicle> getVehicles = s.createQuery("FROM Vehicle v WHERE v.vehiclename = :vname").setParameter("vname", vname).list();
            tx.commit();
            s.clear();
            return getVehicles;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public boolean saveModel(Vehiclemodel vehiclemodel) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            s.save(vehiclemodel);
            tx.commit();
            s.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return false;
        }
    }

    public List<Vehiclemodel> getVehiclemodels(String mname) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            List<Vehiclemodel> getVehiclemodels = s.createQuery("FROM Vehiclemodel v WHERE v.modelname = :mname").setParameter("mname", mname).list();
            tx.commit();
            s.clear();
            return getVehiclemodels;
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
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

    public Vehicle getVehiclesByName(String name) {

        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
            Root<Vehicle> customer = criteriaQuery.from(Vehicle.class);
            criteriaQuery.where(builder.equal(customer.get("vehiclename"), name));
            System.out.println(criteriaQuery.toString());
            Query<Vehicle> query = s.createQuery(criteriaQuery);
            Vehicle vehicle = (Vehicle) query.getResultList().get(0);

            tx.commit();
            s.clear();
            return vehicle;
        } catch (Exception e) {
            System.out.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }

    public Vehiclemodel getVehicleModelsByName(String name) {

        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Vehiclemodel> criteriaQuery = builder.createQuery(Vehiclemodel.class);
            Root<Vehiclemodel> customer = criteriaQuery.from(Vehiclemodel.class);
            criteriaQuery.where(builder.equal(customer.get("modelname"), name));
            System.out.println(criteriaQuery.toString());
            Query<Vehiclemodel> query = s.createQuery(criteriaQuery);
            Vehiclemodel vehiclemodel = (Vehiclemodel) query.getResultList().get(0);

            tx.commit();
            s.clear();
            return vehiclemodel;
        } catch (Exception e) {
            System.out.println("Error in \"Vehicle_Repository\" : " + e.getMessage());
            return null;
        }
    }
}
