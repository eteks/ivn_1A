/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.pdbowner;

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
 * @author Indu
 */
@Entity
@Table(name = "pdb_log_for_DomainFeatures")
public class Pdb_log_for_DomainFeatures {
    private int id;
    private Pdbversion current_pdbversion_id;
    private Pdbversion reference_pdbversion_id;
    private String removed_dom_fea_mapping_ids;
    private Date created_date;
      
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
    @JoinColumn(name = "current_pdbversion_id", nullable = false)
    public Pdbversion getCurrent_pdbversion_id() {
        return current_pdbversion_id;
    }
    
    public void setCurrent_pdbversion_id(Pdbversion current_pdbversion_id) {
        this.current_pdbversion_id = current_pdbversion_id;
    }
    
    @OneToOne
    @JoinColumn(name = "reference_pdbversion_id", nullable = false)
    public Pdbversion getReference_pdbversion_id() {
        return reference_pdbversion_id;
    }
    
    public void setReference_pdbversion_id(Pdbversion reference_pdbversion_id) {
        this.reference_pdbversion_id = reference_pdbversion_id;
    }
    
    @Column(name = "removed_dom_fea_mapping_ids", nullable = false, columnDefinition ="Text")
    public String getRemoved_dom_fea_mapping_ids() {
            return removed_dom_fea_mapping_ids;
    }

    public void setRemoved_dom_fea_mapping_ids(String removed_dom_fea_mapping_ids) {
            this.removed_dom_fea_mapping_ids = removed_dom_fea_mapping_ids;
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
}
