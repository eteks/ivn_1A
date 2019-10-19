/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.acb;

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

/**
 *
 * @author root
 */
@Entity
@Table(name = "acb_version")
public class ACB_Version {
    
    
    private int id;
    private String acb_versionname;
    private Date modified_date;
    private Date created_date;
    private User created_or_updated_by;
    private boolean status;
    private boolean flag;
    private int subversion_of;
    private boolean features_fully_touchedstatus;

    public ACB_Version(String acb_versionname, Date modified_date, Date created_date, User created_or_updated_by, boolean status, boolean flag, int subversion_of, boolean features_fully_touchedstatus) {
        this.acb_versionname = acb_versionname;
        this.modified_date = modified_date;
        this.created_date = created_date;
        this.created_or_updated_by = created_or_updated_by;
        this.status = status;
        this.flag = flag;
        this.subversion_of = subversion_of;
        this.features_fully_touchedstatus = features_fully_touchedstatus;
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

    @Column(name = "acb_versionname", nullable = false)
    public String getAcb_versionname() {
        return acb_versionname;
    }

    public void setAcb_versionname(String acb_versionname) {
        this.acb_versionname = acb_versionname;
    }

    @CreationTimestamp
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

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Column(name = "flag", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Column(name = "subversion_of", nullable = false)
    public int getSubversion_of() {
        return subversion_of;
    }

    public void setSubversion_of(int subversion_of) {
        this.subversion_of = subversion_of;
    }

    @Column(name = "features_fully_touchedstatus", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean isFeatures_fully_touchedstatus() {
        return features_fully_touchedstatus;
    }

    public void setFeatures_fully_touchedstatus(boolean features_fully_touchedstatus) {
        this.features_fully_touchedstatus = features_fully_touchedstatus;
    }

    @Override
    public String toString() {
        return "ACB_Version{" + "id=" + id + ", acb_versionname=" + acb_versionname + ", modified_date=" + modified_date + ", created_date=" + created_date + ", created_or_updated_by=" + created_or_updated_by + ", status=" + status + ", flag=" + flag + ", subversion_of=" + subversion_of + ", features_fully_touchedstatus=" + features_fully_touchedstatus + '}';
    }
    
    
}
