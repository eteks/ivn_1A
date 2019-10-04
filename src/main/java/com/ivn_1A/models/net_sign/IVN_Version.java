/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.net_sign;

import com.ivn_1A.models.admin.User;
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
    private String ivn_manual_comment;
    private float ivn_reference_version;
    private String version_type;
    private boolean flag;
    private boolean status;
    private User created_or_updated_by;
    private Vehicle vehicleId;
    private Featureversion featureVersionId;

    public IVN_Version() {
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

    @Column(name = "ivn_version", nullable = false, columnDefinition = "float(10,1)")
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

    @Column(name = "ivn_manual_comment", columnDefinition = "Text")
    public String getIvn_manual_comment() {
        return ivn_manual_comment;
    }

    public void setIvn_manual_comment(String ivn_manual_comment) {
        this.ivn_manual_comment = ivn_manual_comment;
    }

    @Column(name = "ivn_reference_version", nullable = false)
    public float getIvn_reference_version() {
        return ivn_reference_version;
    }

    public void setIvn_reference_version(float ivn_reference_version) {
        this.ivn_reference_version = ivn_reference_version;
    }

    @Column(name = "version_type", nullable = false)
    public String getVersion_type() {
        return version_type;
    }

    public void setVersion_type(String version_type) {
        this.version_type = version_type;
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

    @OneToOne
    @JoinColumn(name = "created_or_updated_by", nullable = false)
    public User getCreated_or_updated_by() {
        return created_or_updated_by;
    }

    public void setCreated_or_updated_by(User created_or_updated_by) {
        this.created_or_updated_by = created_or_updated_by;
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
    @JoinColumn(name = "featureVersionId", nullable = false)
    public Featureversion getFeatureVersionId() {
        return featureVersionId;
    }

    public void setFeatureVersionId(Featureversion featureVersionId) {
        this.featureVersionId = featureVersionId;
    }
}
