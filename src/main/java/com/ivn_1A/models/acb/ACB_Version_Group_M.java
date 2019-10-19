/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.acb;

import com.ivn_1A.models.net_sign.ECU;
import com.ivn_1A.models.net_sign.IVN_Version;
import com.ivn_1A.models.pdbowner.Domain_and_Features_Mapping;
import com.ivn_1A.models.pdbowner.Pdbversion;
import com.ivn_1A.models.pdbowner.Vehicle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author root
 */
@Entity
@Table(name = "acb_version_group_m")
public class ACB_Version_Group_M implements Serializable {
    
    private int id;
    private ACB_Version acbVersionId;
    private IVN_Version ivnVersionId;
    private Pdbversion pdbVersionId;
    private Vehicle vehicleId;
    private Domain_and_Features_Mapping domainAndFeaturesMapping;
    private Collection<ACB_InputSignals> acbInputSignalsId = new ArrayList<>();
    private Collection<ACB_OutputSignals> acbOutputSignalsId = new ArrayList<>();
    private ECU ecuId;
    private boolean touchedStatus;

    public ACB_Version_Group_M(ACB_Version acbVersionId, IVN_Version ivnVersionId, Pdbversion pdbVersionId, Vehicle vehicleId, Domain_and_Features_Mapping domainAndFeaturesMapping, ECU ecuId, boolean touchedStatus) {
        this.acbVersionId = acbVersionId;
        this.ivnVersionId = ivnVersionId;
        this.pdbVersionId = pdbVersionId;
        this.vehicleId = vehicleId;
        this.domainAndFeaturesMapping = domainAndFeaturesMapping;
        this.ecuId = ecuId;
        this.touchedStatus = touchedStatus;
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
    @JoinColumn(name = "acbVersionId", nullable = false)
    public ACB_Version getAcbVersionId() {
        return acbVersionId;
    }

    public void setAcbVersionId(ACB_Version acbVersionId) {
        this.acbVersionId = acbVersionId;
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
    @JoinColumn(name = "pdbVersionId", nullable = false)
    public Pdbversion getPdbVersionId() {
        return pdbVersionId;
    }

    public void setPdbVersionId(Pdbversion pdbVersionId) {
        this.pdbVersionId = pdbVersionId;
    }

    @OneToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
    }

    @OneToOne
    @JoinColumn(name = "domainAndFeaturesMapping", nullable = false)
    public Domain_and_Features_Mapping getDomainAndFeaturesMapping() {
        return domainAndFeaturesMapping;
    }

    public void setDomainAndFeaturesMapping(Domain_and_Features_Mapping domainAndFeaturesMapping) {
        this.domainAndFeaturesMapping = domainAndFeaturesMapping;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "acb_in", joinColumns = {
        @JoinColumn(name = "acbg_id")}, inverseJoinColumns = {
        @JoinColumn(name = "in_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<ACB_InputSignals> getAcbInputSignalsId() {
        return acbInputSignalsId;
    }

    public void setAcbInputSignalsId(Collection<ACB_InputSignals> acbInputSignalsId) {
        this.acbInputSignalsId = acbInputSignalsId;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "acb_out", joinColumns = {
        @JoinColumn(name = "acbg_id")}, inverseJoinColumns = {
        @JoinColumn(name = "out_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<ACB_OutputSignals> getAcbOutputSignalsId() {
        return acbOutputSignalsId;
    }

    public void setAcbOutputSignalsId(Collection<ACB_OutputSignals> acbOutputSignalsId) {
        this.acbOutputSignalsId = acbOutputSignalsId;
    }

    @OneToOne
    @JoinColumn(name = "ecuId", nullable = false)
    public ECU getEcuId() {
        return ecuId;
    }

    public void setEcuId(ECU ecuId) {
        this.ecuId = ecuId;
    }

    @Column(name = "touchedStatus", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean isTouchedStatus() {
        return touchedStatus;
    }

    public void setTouchedStatus(boolean touchedStatus) {
        this.touchedStatus = touchedStatus;
    }

    @Override
    public String toString() {
        return "ACB_Version_Group_M{" + "id=" + id + ", acbVersionId=" + acbVersionId + ", ivnVersionId=" + ivnVersionId + ", pdbVersionId=" + pdbVersionId + ", vehicleId=" + vehicleId + ", domainAndFeaturesMapping=" + domainAndFeaturesMapping + ", acbInputSignalsId=" + acbInputSignalsId + ", acbOutputSignalsId=" + acbOutputSignalsId + ", ecuId=" + ecuId + ", touchedStatus=" + touchedStatus + '}';
    }
    
    
}
