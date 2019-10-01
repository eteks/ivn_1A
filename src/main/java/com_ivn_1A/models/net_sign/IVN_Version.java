/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_ivn_1A.models.net_sign;

import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.Vehicle;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ETS06
 */
@Entity
@Table(name = "ivnversion")
public class IVN_Version implements Serializable {

    private int id;
    private float ivn_version;
    private Date modified_date;
    private Date created_date;
    private String version_name;
    private String pdb_manual_comment;
    private boolean flag;
    private boolean status;

    public IVN_Version() {
    }

    public IVN_Version(float ivn_version, Date modified_date, Date created_date, String version_name, String pdb_manual_comment, boolean flag, boolean status) {
        this.ivn_version = ivn_version;
        this.modified_date = modified_date;
        this.created_date = created_date;
        this.version_name = version_name;
        this.pdb_manual_comment = pdb_manual_comment;
        this.flag = flag;
        this.status = status;
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

    @Column(name = "ivn_version", nullable = false, columnDefinition = "Float(10,1)")
    public float getIvn_version() {
        return ivn_version;
    }

    public void setIvn_version(float ivn_version) {
        this.ivn_version = ivn_version;
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

    @Column(name = "version_name", nullable = false)
    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }
    
    @Column(name = "pdb_manual_comment", columnDefinition ="Text")
    public String getPdb_manual_comment() {
        return pdb_manual_comment;
    }

    public void setPdb_manual_comment(String pdb_manual_comment) {
        this.pdb_manual_comment = pdb_manual_comment;
    }

    @Column(name = "flag", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
