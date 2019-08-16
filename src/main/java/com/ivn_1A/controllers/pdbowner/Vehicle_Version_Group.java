/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.pdbowner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ivn_1A.controllers.repositories.Vehicle_Repository;
import com.ivn_1A.jsons.Vehicle_Model;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.ivn_1A.models.pdbowner.Vehiclemodel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ETS-05
 */
public class Vehicle_Version_Group {

    public static HttpServletRequest request;
    public static HttpServletResponse response;
    public static Vehicle_Repository vehicle_Repository = new Vehicle_Repository();

    private Map<String, String> msgs = new HashMap<String, String>();
    private Vehicle_Model vm;

    public Vehicle_Model getVm() {
        return vm;
    }

    public void setVm(Vehicle_Model vm) {
        this.vm = vm;
    }

    public Map<String, String> getMsgs() {
        return msgs;
    }

    public void setMsgs(Map<String, String> msgs) {
        this.msgs = msgs;
    }

    public String createVehicleVersion() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            request = ServletActionContext.getRequest();
            response = ServletActionContext.getResponse();

            Vehicle_Model vehicle_Model = new Vehicle_Model();
            Vehicle v = null;
            List<Vehiclemodel> vehiclemodels = new ArrayList<>();
            String filename = IOUtils.toString(request.getInputStream(), "UTF-8");
            final JsonNode readValue = mapper.readValue(filename, JsonNode.class);
            String vehicleName = readValue.get("vehicle_version").get("vehiclename").asText();
            final ArrayNode arrayNode = (ArrayNode) readValue.get("vehicle_version").get("modelname");

            if (vehicle_Repository.getVehicles(vehicleName).isEmpty()) {

                Vehicle vehicle = new Vehicle(vehicleName, true, new Date(), new Date(), vehicle_Repository.getUser(1));
                vehicle_Repository.saveVehicle(vehicle);
                if (vehicle_Repository.saveVehicle(vehicle)) {
                    v = vehicle_Repository.getVehiclesByName(vehicleName);
                    vehicle_Model.setVehicle_id(v.getId());
                    msgs.put("status", "Vehicle Data Inserted");
                    setMsgs(msgs);
                } else {
                    msgs.put("status", "Vehicle Data not Inserted");
                    setMsgs(msgs);
                }
            } else {
                v = vehicle_Repository.getVehiclesByName(vehicleName);
                vehicle_Model.setVehicle_id(v.getId());
                msgs.put("status", "Vehicle Already Exist");
                setMsgs(msgs);
            }
            for (int i = 0; i < arrayNode.size(); i++) {

                System.out.println(arrayNode.get(i).asText());
                if (vehicle_Repository.getVehiclemodels(arrayNode.get(i).asText()).isEmpty()) {

                    Vehiclemodel vehiclemodel = new Vehiclemodel(arrayNode.get(i).asText(), true, new Date(), new Date(), vehicle_Repository.getUser(1));

                    if (vehicle_Repository.saveModel(vehiclemodel)) {
                        vehiclemodels.add(vehicle_Repository.getVehicleModelsByName(arrayNode.get(i).asText()));
                        msgs.put("status", "Model Data Inserted");
                        setMsgs(msgs);
                    } else {
                        msgs.put("status", "Model Data not Inserted");
                        setMsgs(msgs);
                    }
                } else {
                    vehiclemodels.add(vehicle_Repository.getVehicleModelsByName(arrayNode.get(i).asText()));
                    msgs.put("status", "Model Already Exist");
                    setMsgs(msgs);
                }
            }

            List<HashMap<String, Object>> models = new ArrayList<>();;
            HashMap<String, Object> nameMap = null;

            for (Vehiclemodel vehiclemodel : vehiclemodels) {
                
                nameMap = new HashMap<>();
                nameMap.put("model_name", vehiclemodel.getModelname());
                nameMap.put("model_id", vehiclemodel.getId());
                models.add(nameMap);
            }

            vehicle_Model.setModels(models);
            setVm(vehicle_Model);
            System.err.println(ow.writeValueAsString(vehicle_Model));

            return "success";
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Version_Group\" : " + e.getMessage());
            msgs.put("status", "Error in \"Vehicle_Version_Group\" : " + e.getMessage());
            return "success";
        }
    }
}
