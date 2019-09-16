/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.models.admin.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ets-poc
 */
@Entity
@Table(name = "featureversion")
public class Featureversion implements Serializable {
    
    private int id;
    private Vehicle vehicle_id;
    private Pdbversion pdbversion_id;
    private Safetyversion safetyversion_id;
    private Legislationversion legislationversion_id;
    private boolean status;  
    private boolean flag; 
    private Date modified_date;
    private Date created_date;
    private User created_or_updated_by;
    
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @JoinColumn(name = "pdbversion_id", nullable = false)
    public Pdbversion getPdbversion_id() {
        return pdbversion_id;
    }

    public void setPdbversion_id(Pdbversion pdbversion_id) {
        this.pdbversion_id = pdbversion_id;
    }
    @OneToOne
    @JoinColumn(name = "safetyversion_id", nullable = false)
    public Safetyversion getSafetyversion_id() {
        return safetyversion_id;
    }

    public void setSafetyversion_id(Safetyversion safetyversion_id) {
        this.safetyversion_id = safetyversion_id;
    }
    @OneToOne
    @JoinColumn(name = "legislationversion_id", nullable = false)
    public Legislationversion getLegislationversion_id() {
        return legislationversion_id;
    }

    public void setLegislationversion_id(Legislationversion legislationversion_id) {
        this.legislationversion_id = legislationversion_id;
    }   
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Column(name = "flag", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", nullable = false)
    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    @OneToOne
    @JoinColumn(name = "created_or_updated_by", nullable = false)
    public User getCreated_or_updated_by() {
        return created_or_updated_by;
    }

    public void setCreated_or_updated_by(User created_or_updated_by) {
        this.created_or_updated_by = created_or_updated_by;
    }
}
