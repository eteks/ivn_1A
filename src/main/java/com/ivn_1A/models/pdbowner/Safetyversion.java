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
@Table(name = "safetyversion")
public class Safetyversion implements Serializable{
    private int id;
    private float safety_versionname;
    private String safety_manual_comment;
    private Float safety_reference_version;
    private String version_type;
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
    @Column(name = "safety_versionname", nullable = false, columnDefinition="Float(10,1)")
    public float getSafety_versionname() {
        return safety_versionname;
    }

    public void setSafety_versionname(float safety_versionname) {
        this.safety_versionname = safety_versionname;
    }
    @Column(name = "safety_manual_comment", nullable = false, columnDefinition ="Text")
    public String getSafety_manual_comment() {
        return safety_manual_comment;
    }

    public void setSafety_manual_comment(String safety_manual_comment) {
        this.safety_manual_comment = safety_manual_comment;
    }
    @Column(name = "safety_reference_version", nullable = true, columnDefinition="Float(10,1)")
    public Float getSafety_reference_version() {
        return safety_reference_version;
    }

    public void setSafety_reference_version(Float safety_reference_version) {
        this.safety_reference_version = safety_reference_version;
    }
    
    @Column(name = "version_type", nullable = false, length =50)
    // version type data will be stored as new, minor_changes, major_changes
    public String getVersion_type() {
        return version_type;
    }

    public void setVersion_type(String version_type) {
        this.version_type = version_type;
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
