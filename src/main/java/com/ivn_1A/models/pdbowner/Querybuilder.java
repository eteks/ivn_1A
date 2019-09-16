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
@Table(name = "querybuilder")
public class Querybuilder implements Serializable {

    private Long id;
    private String querybuilder_name;
    private String querybuilder_type;
    private String querybuilder_condition;
    private boolean querybuilder_status;   
    private Date modified_date;
    private Date created_date;
    private User created_or_updated_by;
    
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "querybuilder_name", nullable = false, columnDefinition ="Text")
    public String getQuerybuilder_name() {
        return querybuilder_name;
    }

    public void setQuerybuilder_name(String querybuilder_name) {
        this.querybuilder_name = querybuilder_name;
    }

    public String getQuerybuilder_type() {
        return querybuilder_type;
    }

    public void setQuerybuilder_type(String querybuilder_type) {
        this.querybuilder_type = querybuilder_type;
    }
    @Column(name = "querybuilder_condition", nullable = false, columnDefinition ="Text")
    public String getQuerybuilder_condition() {
        return querybuilder_condition;
    }

    public void setQuerybuilder_condition(String querybuilder_condition) {
        this.querybuilder_condition = querybuilder_condition;
    }
    @Column(name = "querybuilder_status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean getQuerybuilder_status() {
        return querybuilder_status;
    }

    public void setQuerybuilder_status(boolean querybuilder_status) {
        this.querybuilder_status = querybuilder_status;
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
