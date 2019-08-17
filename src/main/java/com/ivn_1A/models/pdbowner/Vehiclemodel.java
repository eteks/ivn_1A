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
@Table(name = "vehiclemodel")
public class Vehiclemodel implements Serializable {
    private int id;
    private String modelname;
    private boolean status;   
    private Date modified_date;
    private Date created_date;
    private User created_or_updated_by;

    public Vehiclemodel() {
    }

    public Vehiclemodel(String modelname, boolean status, Date modified_date, Date created_date, User created_or_updated_by) {
        this.modelname = modelname;
        this.status = status;
        this.modified_date = modified_date;
        this.created_date = created_date;
        this.created_or_updated_by = created_or_updated_by;
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "modelname", length = 100, nullable = false)
    public String getModelname() {
            return modelname;
    }

    public void setModelname(String modelname) {
            this.modelname = modelname;
    }

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
