/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.notification;

import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.models.pdbowner.Vehiclemodel;
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
public class StatusNotificationDB {

    public static void insertStatus(StatusNotification statusNotification) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();
            
            StatusNotification statusNotification1 = getVehicleModelByName(statusNotification);
            if (statusNotification1 == null) {
                s.save(statusNotification);
            }
            tx.commit();
            s.clear();
        } catch (Exception e) {
            System.out.println("Notification creation error message" + e.getMessage());
        }
    }
    
    
    //StatusNotification Data by Name
    public static StatusNotification getVehicleModelByName(StatusNotification statusNotification) {
        try {
            Session s = HibernateUtil.getThreadLocalSession();
            Transaction tx = s.beginTransaction();

            final CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            CriteriaQuery<StatusNotification> criteriaQuery = criteriaBuilder.createQuery(StatusNotification.class);

            Root<StatusNotification> vehicleModelRoot = criteriaQuery.from(StatusNotification.class);
            criteriaQuery.where(criteriaBuilder.equal(vehicleModelRoot.get("receiver_id"),  statusNotification.getReceiver_id()));
            TypedQuery<StatusNotification> dfm_result = s.createQuery(criteriaQuery);

            tx.commit();
            s.clear();
            return dfm_result.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error : \"getVehicleModelByName\"" + e);
            return null;
        }
    }

}
