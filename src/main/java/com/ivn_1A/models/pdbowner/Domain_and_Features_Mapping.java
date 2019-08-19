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
@Table(name = "domain_and_features_mapping")
public class Domain_and_Features_Mapping implements Serializable {
    private int id;
    private Domain domain_id;
    private Features feature_id;
    
    public Domain_and_Features_Mapping() {
    }

    public Domain_and_Features_Mapping(Domain domain_id, Features feature_id) {

        this.domain_id = domain_id;
        this.feature_id = feature_id;
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
      
    @OneToOne
    @JoinColumn(name = "domain_id", nullable = false)
    public Domain getDomain_id() {
        return domain_id;
    }
    
    public void setDomain_id(Domain domain_id) {
        this.domain_id = domain_id;
    }
    
    @OneToOne
    @JoinColumn(name = "feature_id", nullable = false)
    public Features getFeature_id() {
        return feature_id;
    }
    
    public void setFeature_id(Features feature_id) {
        this.feature_id = feature_id;
    }
}
