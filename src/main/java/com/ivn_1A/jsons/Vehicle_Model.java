/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.jsons;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ETS-05
 */
public class Vehicle_Model {
    
    private int vehicle_id;
    private List<HashMap<String, Object>> models;

    public Vehicle_Model() {
    }

    public Vehicle_Model(int vehicle_id, List<HashMap<String, Object>> models) {
        this.vehicle_id = vehicle_id;
        this.models = models;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public List<HashMap<String, Object>> getModels() {
        return models;
    }

    public void setModels(List<HashMap<String, Object>> models) {
        this.models = models;
    }
    
    
}
