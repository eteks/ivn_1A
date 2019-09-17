/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.models.pdbowner.*;
import com.ivn_1A.models.pdbowner.Legislationversion;
import com.ivn_1A.models.pdbowner.Querybuilder;
import java.io.Serializable;
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
@Table(name = "legislationversion_group")
public class Legislationversion_group implements Serializable {

    private int id;
    private Legislationversion legislationversion_id;
    private Querybuilder querybuilder_id; 
    private Vehiclemodel vehiclemodel_id; 
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
    @JoinColumn(name = "legislationversion_id", nullable = false)
    public Legislationversion getLegislationversion_id() {
        return legislationversion_id;
    }

    public void setLegislationversion_id(Legislationversion legislationversion_id) {
        this.legislationversion_id = legislationversion_id;
    }

    @OneToOne
    @JoinColumn(name = "querybuilder_id", nullable = false)
    public Querybuilder getQuerybuilder_id() {
        return querybuilder_id;
    }

    public void setQuerybuilder_id(Querybuilder querybuilder_id) {
        this.querybuilder_id = querybuilder_id;
    }
    
    @OneToOne
    @JoinColumn(name = "vehiclemodel_id", nullable = false)
    public Vehiclemodel getVehiclemodel_id() {
        return vehiclemodel_id;
    }

    public void setVehiclemodel_id(Vehiclemodel vehiclemodel_id) {
        this.vehiclemodel_id = vehiclemodel_id;
    }   

    @Column(name = "available_status", length = 20, nullable = false)
    public String getAvailable_status() {
        return available_status;
    }

    public void setAvailable_status(String available_status) {
        this.available_status = available_status;
    }

}
