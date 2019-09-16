/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.notification;

import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.pdbowner.Pdbversion;
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
 * @author ETS-05
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable {

    private int id;
    private User sender_id;
    private String receiver_id;
    private int version_type_id;
    private Pdbversion version_id;
    private Date created_date;

    public Notification() {
    }

    public Notification(User sender_id, String receiver_id, int version_type_id, Pdbversion version_id, Date created_date) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.version_type_id = version_type_id;
        this.version_id = version_id;
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
    @JoinColumn(name = "sender_id", nullable = false)
    public User getSender_id() {
        return sender_id;
    }

    public void setSender_id(User sender_id) {
        this.sender_id = sender_id;
    }

    @Column(name = "receiver_id", nullable = false, columnDefinition ="Text")
    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }
    
    @Column(name = "version_type_id", nullable = false)
    public int getVersion_type_id() {
        return version_type_id;
    }

    public void setVersion_type_id(int version_type_id) {
        this.version_type_id = version_type_id;
    }

    @OneToOne
    @JoinColumn(name = "version_id", nullable = false)
    public Pdbversion getVersion_id() {
        return version_id;
    }

    public void setVersion_id(Pdbversion version_id) {
        this.version_id = version_id;
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
        return "Notification{" + "id=" + id + ", sender_id=" + sender_id + ", receiver_id=" + receiver_id + ", version_type_id=" + version_type_id + ", version_id=" + version_id + ", created_date=" + created_date + '}';
    }
}
