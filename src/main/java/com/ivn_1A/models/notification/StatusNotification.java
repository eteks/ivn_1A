/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.notification;

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
 * @author ETS-05
 */
@Entity
@Table(name = "statusnotification")
public class StatusNotification implements Serializable {
    
    private int id;
    private int receiver_id;
    private Notification notification_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "receiver_id", nullable = false)
    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }
    
    @OneToOne
    @JoinColumn(name = "notification_id", nullable = false)
    public Notification getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(Notification notification_id) {
        this.notification_id = notification_id;
    }
    
    
}
