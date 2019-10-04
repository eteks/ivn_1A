/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.net_sign;

import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.Vehicle;
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
@Table(name = "ivnversiongroup")
public class IVN_Version_Group implements Serializable {

    private int id;
    private IVN_Version ivnVersionId;
    private Signals signalsId;
    private ECU ecuId;
    private Date created_date;

    public IVN_Version_Group() {
    }

    public IVN_Version_Group(IVN_Version ivnVersionId, Signals signalsId, ECU ecuId, Date created_date) {
        this.ivnVersionId = ivnVersionId;
        this.signalsId = signalsId;
        this.ecuId = ecuId;
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
    @JoinColumn(name = "ivnVersionId", nullable = false)
    public IVN_Version getIvnVersionId() {
        return ivnVersionId;
    }

    public void setIvnVersionId(IVN_Version ivnVersionId) {
        this.ivnVersionId = ivnVersionId;
    }

    @OneToOne
    @JoinColumn(name = "signalsId")
    public Signals getSignalsId() {
        return signalsId;
    }

    public void setSignalsId(Signals signalsId) {
        this.signalsId = signalsId;
    }

    @OneToOne
    @JoinColumn(name = "ecuId")
    public ECU getEcuId() {
        return ecuId;
    }

    public void setEcuId(ECU ecuId) {
        this.ecuId = ecuId;
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
