/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.task;

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
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ETS06
 */
@Entity
@Table(name = "tasks_group")
public class Tasks_Group implements Serializable {
    
    private int id;
    private Tasks task_id;
    private int version_id;
    private String version_name;
    private boolean accepted_status;
    private String accepted_by;
    private Date accepted_date;
    private boolean completed_status;
    private String completed_by;
    private Date completed_date;
    private User created_or_updated_by;
    private String sender_id;
    private String receiver_id;
    private boolean verfications;

    public Tasks_Group() {
    }

    public Tasks_Group(Tasks task_id, int version_id, String version_name, boolean accepted_status, String accepted_by, Date accepted_date, boolean completed_status, String completed_by, Date completed_date, User created_or_updated_by, String sender_id, String receiver_id, boolean verfications) {
        this.task_id = task_id;
        this.version_id = version_id;
        this.version_name = version_name;
        this.accepted_status = accepted_status;
        this.accepted_by = accepted_by;
        this.accepted_date = accepted_date;
        this.completed_status = completed_status;
        this.completed_by = completed_by;
        this.completed_date = completed_date;
        this.created_or_updated_by = created_or_updated_by;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.verfications = verfications;
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
    @JoinColumn(name = "task_id", nullable = false)
    public Tasks getTask_id() {
        return task_id;
    }

    public void setTask_id(Tasks task_id) {
        this.task_id = task_id;
    }

    @Column(name = "version_id", nullable = false)
    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    @Column(name = "version_name", nullable = false)
    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    @Column(name = "accepted_status", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isAccepted_status() {
        return accepted_status;
    }

    public void setAccepted_status(boolean accepted_status) {
        this.accepted_status = accepted_status;
    }

    @Column(name = "accepted_by", nullable = false)
    public String getAccepted_by() {
        return accepted_by;
    }

    public void setAccepted_by(String accepted_by) {
        this.accepted_by = accepted_by;
    }

    @Column(name = "completed_status", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isCompleted_status() {
        return completed_status;
    }

    public void setCompleted_status(boolean completed_status) {
        this.completed_status = completed_status;
    }

    @Column(name = "completed_by", nullable = false)
    public String getCompleted_by() {
        return completed_by;
    }

    public void setCompleted_by(String completed_by) {
        this.completed_by = completed_by;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "accepted_date", nullable = false)
    public Date getAccepted_date() {
        return accepted_date;
    }

    public void setAccepted_date(Date accepted_date) {
        this.accepted_date = accepted_date;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completed_date", nullable = false)
    public Date getCompleted_date() {
        return completed_date;
    }

    public void setCompleted_date(Date completed_date) {
        this.completed_date = completed_date;
    }

    @OneToOne
    @JoinColumn(name = "created_or_updated_by", nullable = false)
    public User getCreated_or_updated_by() {
        return created_or_updated_by;
    }

    public void setCreated_or_updated_by(User created_or_updated_by) {
        this.created_or_updated_by = created_or_updated_by;
    }

    @Column(name = "sender_id", nullable = false)
    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    @Column(name = "receiver_id", nullable = false)
    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public boolean isVerfications() {
        return verfications;
    }

    public void setVerfications(boolean verfications) {
        this.verfications = verfications;
    }

    @Override
    public String toString() {
        return "Tasks_Group{" + "id=" + id + ", task_id=" + task_id + ", version_id=" + version_id + ", version_name=" + version_name + ", accepted_status=" + accepted_status + ", accepted_by=" + accepted_by + ", accepted_date=" + accepted_date + ", completed_status=" + completed_status + ", completed_by=" + completed_by + ", completed_date=" + completed_date + ", created_or_updated_by=" + created_or_updated_by + ", sender_id=" + sender_id + ", receiver_id=" + receiver_id + ", verfications=" + verfications + '}';
    }
    
}
