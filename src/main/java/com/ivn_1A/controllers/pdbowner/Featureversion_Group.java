/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.pdbowner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.models.pdbowner.Domain_and_Features_Mapping;
import com.ivn_1A.models.pdbowner.FeatureversionDB;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.opensymphony.xwork2.ActionContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ets-poc
 */
public class Featureversion_Group {
    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
//    Session session = HibernateUtil.getThreadLocalSession();
    private List<Vehicle> vehicleversion_result;
    private List<Tuple> tupleObjects = new ArrayList<>();
    Gson gson = new Gson();
    private String result_data_obj;

    public String FeatureversionPage() {

        System.out.println("Entered");
        System.out.println("FeatureversionPage");

//        This will execute if url contains parameter(id and action-edit, view)
//        try {
//            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//            System.out.println("request" + request);
//            System.out.println("id_value" + request.getParameter("id"));
//            System.out.println("action_value" + request.getParameter("action"));
//            List<Pdbversion_group> pdbversion_group_List = PDBOwnerDB.LoadPDBPreviousVehicleversionData(Integer.parseInt(request.getParameter("id")));
//            List<Map<String, Object>> pdb_map_result = new ArrayList<>();
//            pdbversion_group_List.stream().map((pdbversion_group) -> {
//                Map<String, Object> vehicleMap = new HashMap<>();
//                System.out.println("vehver_id"+pdbversion_group.getPdbversion_id().getVehicle_id().getId());
////                vehicleMap.put("vehver_id", pdbversion_group.getVehicle_id().getId());
////                vehicleMap.put("vehiclename", pdbversion_group.getVehicle_id().getVehiclename());
//                vehicleMap.put("vehver_id", pdbversion_group.getPdbversion_id().getVehicle_id().getId());
//                System.out.println("vehiclename"+pdbversion_group.getPdbversion_id().getVehicle_id().getVehiclename());
//                vehicleMap.put("vehiclename", pdbversion_group.getPdbversion_id().getVehicle_id().getVehiclename());
//                System.out.println("modelname"+pdbversion_group.getVehiclemodel_id().getModelname());
//                vehicleMap.put("modelname", pdbversion_group.getVehiclemodel_id().getModelname());
//                System.out.println("pdbversion_group_id"+pdbversion_group.getId());
//                vehicleMap.put("pdbversion_group_id", pdbversion_group.getId());
//                System.out.println("pdbversion_id"+pdbversion_group.getPdbversion_id().getId());
//                vehicleMap.put("pdbversion_id", pdbversion_group.getPdbversion_id().getId());
//                System.out.println("pdbversion_name"+pdbversion_group.getPdbversion_id().getPdb_versionname());
//                vehicleMap.put("pdbversion_name", pdbversion_group.getPdbversion_id().getPdb_versionname());
//                System.out.println("status"+pdbversion_group.getPdbversion_id().getStatus());
//                vehicleMap.put("status", pdbversion_group.getPdbversion_id().getStatus());
//                return vehicleMap;
//            }).forEachOrdered((vehicleMap) -> {
//                pdb_map_result.add(vehicleMap);
//            });
//            result_data_obj = new Gson().toJson(pdb_map_result);
//            System.err.println("result_data_obj " + result_data_obj);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
        try {
            vehicleversion_result = PDBOwnerDB.loadVehicleVersion();
            

//            maps_object.put("removed_features", StringUtils.join(",", pdb_previous_data.get("removed_features")));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }
    
    public String LoadPdbSafetyLeg_version() throws IOException{
        System.out.println("LoadPdbSafetyLeg_version");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
        System.out.println("readValue" + readValue);
        Map<String, Object> result_data = FeatureversionDB.GetPdbSafetyLeg_version(readValue.get("vehicle_id").asInt());
        System.out.println("result_data" + result_data.get("pdb_results"));
//        JSONArray version_results = new JSONArray();
//        result_data.get("pdb_results").stream().map((ver) -> {
//            JSONObject vr = new JSONObject();
//            vr.put("id", ver.get("pid"));
//            vr.put("name", "PDB "+ver.get("name"));
//            vr.put("type","pdb");
//            return vr;
//        }).forEachOrdered((vr) -> {
//            version_results.add(vr);
//        });
        return "success";
    }
    
    public Map<String, Object> getMaps_object() {
        return maps_object;
    }

    public void setMaps_object(Map<String, Object> maps_object) {
        this.maps_object = maps_object;
    }

    public List<Vehicle> getVehicleversion_result() {
        return vehicleversion_result;
    }

    public void setVehicleversion_result(List<Vehicle> vehicleversion_result) {
        this.vehicleversion_result = vehicleversion_result;
    }

    public Map<String, String> getMaps_string() {
        return maps_string;
    }

    public void setMaps_string(Map<String, String> maps_string) {
        this.maps_string = maps_string;
    }

    public String getResult_data_obj() {
        return result_data_obj;
    }

    public void setResult_data_obj(String result_data_obj) {
        this.result_data_obj = result_data_obj;
    }
}
