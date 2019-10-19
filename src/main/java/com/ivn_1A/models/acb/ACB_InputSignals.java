/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.acb;

import com.ivn_1A.models.net_sign.Network;
import com.ivn_1A.models.net_sign.Signals;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@Table(name = "acb_inputsignals")
public class ACB_InputSignals implements Serializable {
    
    private int id;
    private Signals inputSignalId;
    private Network inputNetwordId;
    private String networkType;
    private Pdbversion_group pdbversionGroupId;

    public ACB_InputSignals(Signals inputSignalId, Network inputNetwordId, String networkType, Pdbversion_group pdbversionGroupId) {
        this.inputSignalId = inputSignalId;
        this.inputNetwordId = inputNetwordId;
        this.networkType = networkType;
        this.pdbversionGroupId = pdbversionGroupId;
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
    @JoinColumn(name = "inputSignalId", nullable = false)
    public Signals getInputSignalId() {
        return inputSignalId;
    }

    public void setInputSignalId(Signals inputSignalId) {
        this.inputSignalId = inputSignalId;
    }

    @OneToOne
    @JoinColumn(name = "inputNetwordId", nullable = false)
    public Network getInputNetwordId() {
        return inputNetwordId;
    }

    public void setInputNetwordId(Network inputNetwordId) {
        this.inputNetwordId = inputNetwordId;
    }

    @Column(name = "networkType", nullable = false)
    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    @OneToOne
    @JoinColumn(name = "pdbversionGroupId", nullable = false)
    public Pdbversion_group getPdbversionGroupId() {
        return pdbversionGroupId;
    }

    public void setPdbversionGroupId(Pdbversion_group pdbversionGroupId) {
        this.pdbversionGroupId = pdbversionGroupId;
    }

    @Override
    public String toString() {
        return "ACB_InputSignals{" + "id=" + id + ", inputSignalId=" + inputSignalId + ", inputNetwordId=" + inputNetwordId + ", networkType=" + networkType + ", pdbversionGroupId=" + pdbversionGroupId + '}';
    }
    
    
}
