/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.pdbowner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ivn_1A.controllers.repositories.Vehicle_Repository;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.ivn_1A.models.pdbowner.Vehiclemodel;
import java.util.Date;
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

    public String createVehicleVersion() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            System.out.println("Hai");
            request = ServletActionContext.getRequest();
            response = ServletActionContext.getResponse();

            String filename = IOUtils.toString(request.getInputStream(), "UTF-8");
            System.out.println("jsonData " + filename);
            final JsonNode readValue = mapper.readValue(filename, JsonNode.class);
            String vehicleName = readValue.get("vehicle_version").get("vehiclename").asText();
            System.out.println(vehicleName + " " + readValue.get("vehicle_version").get("vehiclename").asText());
            final ArrayNode arrayNode = (ArrayNode) readValue.get("vehicle_version").get("modelname");

            boolean a = false, b = false;
            if (vehicle_Repository.getVehicles(vehicleName).isEmpty()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehiclename(vehicleName);
                vehicle.setCreated_date(new Date());
                vehicle.setModified_date(new Date());
                vehicle.setCreated_or_updated_by(vehicle_Repository.getUser(1));
                vehicle.setStatus(true);
                a = vehicle_Repository.saveVehicle(vehicle);
            } else {
                response.setContentType("text/javascript");
                response.getWriter().println("<script>window.alert('Failed in Insertion of Vehicle'); window.location.href='index.jsp';</script>");
            }
            for (int i = 0; i < arrayNode.size(); i++) {

                System.out.println(arrayNode.get(i).asText());
                if (vehicle_Repository.getVehiclemodels(arrayNode.get(i).asText()).isEmpty()) {
                    Vehiclemodel vehiclemodel = new Vehiclemodel();
                    vehiclemodel.setModelname(arrayNode.get(i).asText());
                    vehiclemodel.setCreated_date(new Date());
                    vehiclemodel.setModified_date(new Date());
                    vehiclemodel.setCreated_or_updated_by(vehicle_Repository.getUser(1));
                    vehiclemodel.setStatus(true);
                    b = vehicle_Repository.saveModel(vehiclemodel);
                } else {
                    response.setContentType("text/javascript");
                    response.getWriter().println("<script>window.alert('Failed in Insertion of Model'); window.location.href='index.jsp';</script>");
                    break;
                }
            }
            if (a && b) {
                System.out.println("Done");
            } else {
                System.err.println("Error");
            }
            return "success";
        } catch (Exception e) {
            System.err.println("Error in \"Vehicle_Version_Group\" : " + e.getMessage());
            return null;
        }
    }
}
