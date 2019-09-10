/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models;

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
 * @author ETS-05
 */
@Entity
@Table(name = "groups")
public class Groups implements Serializable {
    //id, group_name, status, route_pages, is_superadmin, modified_date, created_date
    private String group_name;
    private String route_pages;
    private boolean status;
    private int id;
    private boolean is_superadmin;
    private Date modified_date;
    private Date created_date;

    public Groups() {
    }
    
    public Groups(String group_name, String route_pages, boolean status, int id, boolean is_superadmin, Date modified_date, Date created_date) {
        this.group_name = group_name;
        this.route_pages = route_pages;
        this.status = status;
        this.id = id;
        this.is_superadmin = is_superadmin;
        this.modified_date = modified_date;
        this.created_date = created_date;
    }

    @Column(name = "group_name", nullable = false, columnDefinition ="Text")
    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    @Column(name = "route_pages", nullable = false, columnDefinition ="Text")
    public String getRoute_pages() {
        return route_pages;
    }

    public void setRoute_pages(String route_pages) {
        this.route_pages = route_pages;
    }

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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

    @Column(name = "is_superadmin", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean isIs_superadmin() {
        return is_superadmin;
    }

    public void setIs_superadmin(boolean is_superadmin) {
        this.is_superadmin = is_superadmin;
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

    @Override
    public String toString() {
        return "Groups{" + "group_name=" + group_name + ", route_pages=" + route_pages + ", status=" + status + ", id=" + id + ", is_superadmin=" + is_superadmin + ", modified_date=" + modified_date + ", created_date=" + created_date + '}';
    }
}
