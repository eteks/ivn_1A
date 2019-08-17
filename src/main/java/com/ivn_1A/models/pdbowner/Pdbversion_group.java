/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ets-poc
 */
@Entity
@Table(name = "pdbversion_group")
public class Pdbversion_group {
    private int id;
    private Pdbversion pdbversion_id;
    private Vehicle vehicle_id;
    private Vehiclemodel vehiclemodel_id;
    private Domain_and_Features_Mapping domain_and_features_mapping_id;
    private String available_status;
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      
    @OneToOne
    @JoinColumn(name = "pdbversion_id", nullable = false)
    public Pdbversion getPdbversion_id() {
        return pdbversion_id;
    }
    
    public void setPdbversion_id(Pdbversion pdbversion_id) {
        this.pdbversion_id = pdbversion_id;
    }
    
    @OneToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    public Vehicle getVehicle_id() {
        return vehicle_id;
    }
    
    public void setVehicle_id(Vehicle vehicle_id) {
        this.vehicle_id = vehicle_id;
    } 
    
    @OneToOne
    @JoinColumn(name = "vehiclemodel_id", nullable = false)
    public Vehiclemodel getVehiclemodel_id() {
        return vehiclemodel_id;
    }
    
    public void setVehiclemodel_id(Vehiclemodel vehiclemodel_id) {
        this.vehiclemodel_id = vehiclemodel_id;
    } 
    
    @OneToOne
    @JoinColumn(name = "domain_and_features_mapping_id", nullable = false)
    public Domain_and_Features_Mapping getDomain_and_features_mapping_id() {
        return domain_and_features_mapping_id;
    }
    
    public void setDomain_and_features_mapping_id(Domain_and_Features_Mapping domain_and_features_mapping_id) {
        this.domain_and_features_mapping_id = domain_and_features_mapping_id;
    } 
    
    @Column(name = "available_status", length = 20, nullable = false)
    public String getAvailable_status() {
            return available_status;
    }

    public void setAvailable_status(String available_status) {
            this.available_status = available_status;
    }
}
