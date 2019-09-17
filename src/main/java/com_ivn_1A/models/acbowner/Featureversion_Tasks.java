/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_ivn_1A.models.acbowner;

import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.pdbowner.Featureversion;
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
@Table(name = "featureversion_tasks")
public class Featureversion_Tasks implements Serializable {    
    private int id;
    private Featureversion featureversion_id;
    private boolean approval_status;  
    private boolean completion_status;  
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
    @JoinColumn(name = "featureversion_id", nullable = false)
    public Featureversion getFeatureversion_id() {
        return featureversion_id;
    }

    public void setFeatureversion_id(Featureversion featureversion_id) {
        this.featureversion_id = featureversion_id;
    }
    @Column(name = "approval_status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(boolean approval_status) {
        this.approval_status = approval_status;
    }
    @Column(name = "completion_status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean getCompletion_status() {
        return completion_status;
    }

    public void setCompletion_status(boolean completion_status) {
        this.completion_status = completion_status;
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
