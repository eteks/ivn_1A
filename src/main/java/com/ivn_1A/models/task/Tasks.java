/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.task;

import com.ivn_1A.models.pdbowner.Vehicle;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ETS06
 */
@Entity
@Table(name = "tasks")
public class Tasks implements Serializable {
    
    private int id;
    private String task_name;
    private Vehicle vehicle_id;
    private String created_name;
    private Date created_date;

    public Tasks() {
    }

    public Tasks(String task_name, Vehicle vehicle_id, String created_name, Date created_date) {
        this.task_name = task_name;
        this.vehicle_id = vehicle_id;
        this.created_name = created_name;
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

    @Column(name = "task_name", nullable = false)
    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    @OneToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    public Vehicle getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Vehicle vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    @Column(name = "created_name", nullable = false)
    public String getCreated_name() {
        return created_name;
    }

    public void setCreated_name(String created_name) {
        this.created_name = created_name;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
