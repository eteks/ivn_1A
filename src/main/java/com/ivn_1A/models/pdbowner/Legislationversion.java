/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.pdbowner.Pdbversion;
import com.ivn_1A.models.pdbowner.Vehicle;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "legislationversion")
public class Legislationversion implements Serializable{
    private int id;
    private float legislation_versionname;
    private String legislation_manual_comment;
    private Float legislation_reference_version;
    private Vehicle vehicle_id;
    private Pdbversion pdbversion_id;
    private boolean status;  
    private boolean flag; 
    private Date modified_date;
    private Date created_date;
    private User created_or_updated_by;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "legislation_versionname", nullable = false, columnDefinition="Float(10,1)")
    public float getLegislation_versionname() {
        return legislation_versionname;
    }

    public void setLegislation_versionname(float legislation_versionname) {
        this.legislation_versionname = legislation_versionname;
    }
    @Column(name = "legislation_manual_comment", nullable = false, columnDefinition ="Text")
    public String getLegislation_manual_comment() {
        return legislation_manual_comment;
    }

    public void setLegislation_manual_comment(String legislation_manual_comment) {
        this.legislation_manual_comment = legislation_manual_comment;
    }
    @Column(name = "legislation_reference_version", nullable = true, columnDefinition="Float(10,1)")
    public Float getLegislation_reference_version() {
        return legislation_reference_version;
    }

    public void setLegislation_reference_version(Float legislation_reference_version) {
        this.legislation_reference_version = legislation_reference_version;
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
