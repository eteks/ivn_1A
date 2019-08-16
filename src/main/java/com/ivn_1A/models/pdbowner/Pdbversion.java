/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

import com.ivn_1A.models.admin.User;
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
@Table(name = "pdbversion")
public class Pdbversion {
   private int id;
    private float pdb_versionname;
    private String pdb_manual_comment;
    private float pdb_reference_version;
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
    
    @Column(name = "pdb_versionname", nullable = false, columnDefinition="Float(10,2)")
    public float getPdb_versionname() {
            return pdb_versionname;
    }

    public void setPdb_versionname(float pdb_versionname) {
            this.pdb_versionname = pdb_versionname;
    }
    
    @Column(name = "pdb_reference_version", nullable = true, columnDefinition="Float(10,2)")
    public float getPdb_reference_version() {
            return pdb_reference_version;
    }

    public void setPdb_reference_version(float pdb_reference_version) {
            this.pdb_reference_version = pdb_reference_version;
    }
    
    @Column(name = "pdb_manual_comment", nullable = false, columnDefinition ="Text")
    public String getPdb_manual_comment() {
            return pdb_manual_comment;
    }

    public void setPdb_manual_comment(String pdb_manual_comment) {
            this.pdb_manual_comment = pdb_manual_comment;
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
    
//    @Column(name = "modified_date", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
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
