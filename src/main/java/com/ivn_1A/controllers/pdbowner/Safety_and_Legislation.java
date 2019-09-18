/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.pdbowner;

import com.google.gson.Gson;
import com.ivn_1A.models.pdbowner.Domain_and_Features_Mapping;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import com.ivn_1A.models.pdbowner.Querybuilder;
import com.ivn_1A.models.pdbowner.SafetyLegDB;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.opensymphony.xwork2.ActionContext;
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
public class Safety_and_Legislation {
    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
//    Session session = HibernateUtil.getThreadLocalSession();
    private List<Vehicle> vehicle_result;
    private List<Tuple> tupleObjects = new ArrayList<>();
    Gson gson = new Gson();
    private String result_data_obj;

    public String LegislationPage() {

        System.out.println("Entered");
        System.out.println("LegislationPage");

//        This will execute if url contains parameter(id and action-edit, view)
        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            System.out.println("request" + request);
            System.out.println("id_value" + request.getParameter("id"));
            System.out.println("action_value" + request.getParameter("action"));
            List<Pdbversion_group> pdbversion_group_List = PDBOwnerDB.LoadPDBPreviousVehicleversionData(Integer.parseInt(request.getParameter("id")));
            List<Map<String, Object>> pdb_map_result = new ArrayList<>();
            pdbversion_group_List.stream().map((pdbversion_group) -> {
                Map<String, Object> vehicleMap = new HashMap<>();
//                vehicleMap.put("vehver_id", pdbversion_group.getVehicle_id().getId());
//                vehicleMap.put("vehiclename", pdbversion_group.getVehicle_id().getVehiclename());
                vehicleMap.put("modelname", pdbversion_group.getVehiclemodel_id().getModelname());
                vehicleMap.put("pdbversion_group_id", pdbversion_group.getId());
                vehicleMap.put("pdbversion_id", pdbversion_group.getPdbversion_id().getId());
                vehicleMap.put("pdbversion_name", pdbversion_group.getPdbversion_id().getPdb_versionname());
                vehicleMap.put("status", pdbversion_group.getPdbversion_id().getStatus());
                return vehicleMap;
            }).forEachOrdered((vehicleMap) -> {
                pdb_map_result.add(vehicleMap);
            });
            result_data_obj = new Gson().toJson(pdb_map_result);
            System.err.println("result_data_obj " + result_data_obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            List<Querybuilder> legcomb_list = SafetyLegDB.LoadCombinationList("legislation");

            JSONArray legcomb_list_res = new JSONArray();
            legcomb_list.stream().map((leg) -> {
                JSONObject fr = new JSONObject();
                fr.put("lid", leg.getId());
                fr.put("leg", leg.getQuerybuilder_name());
                return fr;
            }).forEachOrdered((fr) -> {
                legcomb_list_res.add(fr);
            });
            maps_object.put("legcomb_list_res", legcomb_list_res);
            vehicle_result = PDBOwnerDB.loadVehicleVersion();
            System.out.println("vehicle_result" + vehicle_result);
            

//            Map<String, Object> pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(1, 2);
//            System.out.println("pdb_previous_data result" + pdb_previous_data);
//            
//            JSONObject pdb_previous_data_result = new JSONObject();
//            pdb_previous_data_result.put("removed_features", pdb_previous_data.get("removed_features"));
//            pdb_previous_data_result.put("added_features",pdb_previous_data.get("added_features"));
//            pdb_previous_data_result.put("removed_models",pdb_previous_data.get("removed_models"));
//            pdb_previous_data_result.put("added_models",pdb_previous_data.get("added_models"));            
//            pdb_previous_data_result.put("current_version","1.1");
//            pdb_previous_data_result.put("previous_models","1.0");
//            
//            maps_object.put("pdb_previous_data_result", pdb_previous_data_result);
//            maps_object.put("features", featureslist_result);

//            maps_object.put("removed_features", StringUtils.join(",", pdb_previous_data.get("removed_features")));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }
    
    public Map<String, Object> getMaps_object() {
        return maps_object;
    }

    public void setMaps_object(Map<String, Object> maps_object) {
        this.maps_object = maps_object;
    }
    public List<Vehicle> getVehicle_result() {
        return vehicle_result;
    }

    public void setVehicleversion_result(List<Vehicle> vehicle_result) {
        this.vehicle_result = vehicle_result;
    }
}
