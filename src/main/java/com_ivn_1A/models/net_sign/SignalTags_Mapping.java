/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com_ivn_1A.models.net_sign;

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

/**
 *
 * @author ETS06
 */
@Entity
@Table(name = "signaltags_mapping")
public class SignalTags_Mapping implements Serializable {
    
    private int id;
    private Signals signal_id;
    private SignalTags signalTag_id;
    private Date created_date;

    public SignalTags_Mapping() {
    }

    public SignalTags_Mapping(Signals signal_id, SignalTags signalTag_id, Date created_date) {
        this.signal_id = signal_id;
        this.signalTag_id = signalTag_id;
        this.created_date = created_date;
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
    @JoinColumn(name = "signal_id", nullable = false)
    public Signals getSignal_id() {
        return signal_id;
    }

    public void setSignal_id(Signals signal_id) {
        this.signal_id = signal_id;
    }
    
    @OneToOne
    @JoinColumn(name = "signalTag_id", nullable = false)
    public SignalTags getSignalTag_id() {
        return signalTag_id;
    }

    public void setSignalTag_id(SignalTags signalTag_id) {
        this.signalTag_id = signalTag_id;
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
        return "SignalTags_Mapping{" + "id=" + id + ", signal_id=" + signal_id + ", signalTag_id=" + signalTag_id + ", created_date=" + created_date + '}';
    }
    
}
