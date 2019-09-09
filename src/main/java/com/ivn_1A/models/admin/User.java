/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.admin;

import com.ivn_1A.models.Groups;
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
 * @author ets-poc
 */
@Entity
@Table(name = "user")
public class User {

    private int id;
    private String username;
    private Groups groups_id;

    public User() {
    }

    public User(String username, Groups groups_id) {
        this.username = username;
        this.groups_id = groups_id;
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

    @Column(name = "username", length = 100, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @OneToOne
    @JoinColumn(name = "groups_id", nullable = false)
    public Groups getGroups_id() {
        return groups_id;
    }

    public void setGroups_id(Groups groups_id) {
        this.groups_id = groups_id;
    }
}
