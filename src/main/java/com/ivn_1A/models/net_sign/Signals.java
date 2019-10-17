/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.models.net_sign;

import com.ivn_1A.models.admin.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ETS06
 */
@Entity
@Table(name = "signals")
public class Signals implements Serializable {

    private int id;
    private String signal_name;
    private String signal_alias;
    private String signal_description;
    private int signal_length;
    private String signal_byteorder;
    private String signal_unit;
    private String signal_valuetype;
    private int signal_initvalue;
    private double signal_factor;
    private int signal_offset;
    private int signal_minimum;
    private int signal_maximum;
    private String signal_valuetable;
    private Collection<Network> can_id_group = new ArrayList<>();
    private Collection<Network> lin_id_group = new ArrayList<>();
    private Collection<Network> hw_id_group = new ArrayList<>();
    private Collection<SignalTags> signalTagses = new ArrayList<>();
    private Date modified_date;
    private Date created_date;
    private User created_or_updated_by;
    private boolean status;

    public Signals() {
    }

    public Signals(String signal_name, String signal_alias, String signal_description, int signal_length, String signal_byteorder, String signal_unit, String signal_valuetype, int signal_initvalue, double signal_factor, int signal_offset, int signal_minimum, int signal_maximum, String signal_valuetable, Collection<Network> can_id_group, Collection<Network> lin_id_group, Collection<Network> hw_id_group, Collection<SignalTags> signalTagses, Date modified_date, Date created_date, User created_or_updated_by, boolean status) {
        this.signal_name = signal_name;
        this.signal_alias = signal_alias;
        this.signal_description = signal_description;
        this.signal_length = signal_length;
        this.signal_byteorder = signal_byteorder;
        this.signal_unit = signal_unit;
        this.signal_valuetype = signal_valuetype;
        this.signal_initvalue = signal_initvalue;
        this.signal_factor = signal_factor;
        this.signal_offset = signal_offset;
        this.signal_minimum = signal_minimum;
        this.signal_maximum = signal_maximum;
        this.signal_valuetable = signal_valuetable;
        this.can_id_group = can_id_group;
        this.lin_id_group = lin_id_group;
        this.hw_id_group = hw_id_group;
        this.signalTagses = signalTagses;
        this.modified_date = modified_date;
        this.created_date = created_date;
        this.created_or_updated_by = created_or_updated_by;
        this.status = status;
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

    @Column(name = "signal_name", unique = true, nullable = false)
    public String getSignal_name() {
        return signal_name;
    }

    public void setSignal_name(String signal_name) {
        this.signal_name = signal_name;
    }

    @Column(name = "signal_alias", unique = true, nullable = false)
    public String getSignal_alias() {
        return signal_alias;
    }

    public void setSignal_alias(String signal_alias) {
        this.signal_alias = signal_alias;
    }

    @Type(type = "text")
    @Column(name = "signal_description", nullable = false, columnDefinition = "Text")
    public String getSignal_description() {
        return signal_description;
    }

    public void setSignal_description(String signal_description) {
        this.signal_description = signal_description;
    }

    @Column(name = "signal_length", nullable = false)
    public int getSignal_length() {
        return signal_length;
    }

    public void setSignal_length(int signal_length) {
        this.signal_length = signal_length;
    }

    @Column(name = "signal_byteorder", nullable = false)
    public String getSignal_byteorder() {
        return signal_byteorder;
    }

    public void setSignal_byteorder(String signal_byteorder) {
        this.signal_byteorder = signal_byteorder;
    }

    @Column(name = "signal_unit", nullable = false)
    public String getSignal_unit() {
        return signal_unit;
    }

    public void setSignal_unit(String signal_unit) {
        this.signal_unit = signal_unit;
    }

    @Column(name = "signal_valuetype", nullable = false)
    public String getSignal_valuetype() {
        return signal_valuetype;
    }

    public void setSignal_valuetype(String signal_valuetype) {
        this.signal_valuetype = signal_valuetype;
    }

    @Column(name = "signal_initvalue", nullable = false)
    public int getSignal_initvalue() {
        return signal_initvalue;
    }

    public void setSignal_initvalue(int signal_initvalue) {
        this.signal_initvalue = signal_initvalue;
    }

    @Column(name = "signal_factor", nullable = false)
    public double getSignal_factor() {
        return signal_factor;
    }

    public void setSignal_factor(double signal_factor) {
        this.signal_factor = signal_factor;
    }

    @Column(name = "signal_offset", nullable = false)
    public int getSignal_offset() {
        return signal_offset;
    }

    public void setSignal_offset(int signal_offset) {
        this.signal_offset = signal_offset;
    }

    @Column(name = "signal_minimum", nullable = false)
    public int getSignal_minimum() {
        return signal_minimum;
    }

    public void setSignal_minimum(int signal_minimum) {
        this.signal_minimum = signal_minimum;
    }

    @Column(name = "signal_maximum", nullable = false)
    public int getSignal_maximum() {
        return signal_maximum;
    }

    public void setSignal_maximum(int signal_maximum) {
        this.signal_maximum = signal_maximum;
    }

    @Column(name = "signal_valuetable", nullable = false)
    public String getSignal_valuetable() {
        return signal_valuetable;
    }

    public void setSignal_valuetable(String signal_valuetable) {
        this.signal_valuetable = signal_valuetable;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sig_can", joinColumns = {
        @JoinColumn(name = "sig_id")}, inverseJoinColumns = {
        @JoinColumn(name = "can_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<Network> getCan_id_group() {
        return can_id_group;
    }

    public void setCan_id_group(Collection<Network> can_id_group) {
        this.can_id_group = can_id_group;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sig_lin", joinColumns = {
        @JoinColumn(name = "sig_id")}, inverseJoinColumns = {
        @JoinColumn(name = "lin_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<Network> getLin_id_group() {
        return lin_id_group;
    }

    public void setLin_id_group(Collection<Network> lin_id_group) {
        this.lin_id_group = lin_id_group;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sig_hw", joinColumns = {
        @JoinColumn(name = "sig_id")}, inverseJoinColumns = {
        @JoinColumn(name = "hw_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<Network> getHw_id_group() {
        return hw_id_group;
    }

    public void setHw_id_group(Collection<Network> hw_id_group) {
        this.hw_id_group = hw_id_group;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sig_tag", joinColumns = {
        @JoinColumn(name = "sig_id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<SignalTags> getSignalTagses() {
        return signalTagses;
    }

    public void setSignalTagses(Collection<SignalTags> signalTagses) {
        this.signalTagses = signalTagses;
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

    @OneToOne
    @JoinColumn(name = "created_or_updated_by", nullable = false)
    public User getCreated_or_updated_by() {
        return created_or_updated_by;
    }

    public void setCreated_or_updated_by(User created_or_updated_by) {
        this.created_or_updated_by = created_or_updated_by;
    }

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1) default 1")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Signals{" + "id=" + id + ", signal_name=" + signal_name + ", signal_alias=" + signal_alias + ", signal_description=" + signal_description + ", signal_length=" + signal_length + ", signal_byteorder=" + signal_byteorder + ", signal_unit=" + signal_unit + ", signal_valuetype=" + signal_valuetype + ", signal_initvalue=" + signal_initvalue + ", signal_factor=" + signal_factor + ", signal_offset=" + signal_offset + ", signal_minimum=" + signal_minimum + ", signal_maximum=" + signal_maximum + ", signal_valuetable=" + signal_valuetable + ", can_id_group=" + can_id_group + ", lin_id_group=" + lin_id_group + ", hw_id_group=" + hw_id_group + ", modified_date=" + modified_date + ", created_date=" + created_date + ", created_or_updated_by=" + created_or_updated_by + ", status=" + status + '}';
    }

}
