/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.pdbowner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.configs.JSONConfigure;

import static com.ivn_1A.controllers.pdbowner.Vehicle_Version_Group.vehicle_Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ivn_1A.models.pdbowner.*;
import com.opensymphony.xwork2.ActionContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author ets-poc
 */
public class Pdbversion_Group {

    public static PDBOwnerDB pdbownerdb = new PDBOwnerDB();
    private Map<String, String> maps_string = new HashMap<String, String>();
    private Map<String, Object> maps_object = new HashMap<String, Object>();
    Session session = HibernateUtil.getThreadLocalSession();
    private List<Vehicle> vehicleversion_result;
    private List<Pdbversion_group> pdbversion_group_result = new ArrayList<>();

    public String PDBAssignPage() {
        System.out.println("Entered");
        System.out.println("PDBAssignPage");
        //This will execute if url contains parameter(id and action-edit, view)
//        try {
//            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
//                    .get(ServletActionContext.HTTP_REQUEST);
//            System.out.println("request" + request);
//            System.out.println("id_value" + request.getParameter("id"));
//            System.out.println("action_value" + request.getParameter("action"));
//            PDBversion pdbver = new PDBversion(Integer.parseInt(request.getParameter("id")));
//            pdb_map_result = PDBVersionDB.LoadPDBPreviousVehicleversionData(pdbver);
//            result_data_obj = new Gson().toJson(pdb_map_result);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
        try {
            PDBOwnerDB pdbownerdb = new PDBOwnerDB();
            List<Domain_and_Features_Mapping> featureslist = pdbownerdb.LoadFeaturesList();

            JSONArray featureslist_result = new JSONArray();
            for (Domain_and_Features_Mapping fea : featureslist) {
                JSONObject fr = new JSONObject();
                fr.put("fid", fea.getId());
                fr.put("fea", fea.getDomain_id().getDomain_name());
                fr.put("domain", fea.getFeature_id().getFeature_name());
                featureslist_result.add(fr);
            }
            vehicleversion_result = pdbownerdb.LoadVehicleVersion();
            System.out.println("featureslist_result result" + featureslist_result);
            
            List<Pdbversion_group> pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(1);
            System.out.println("pdb_previous_data" + pdb_previous_data.get(0).getDomain_and_features_mapping_id().getId());
            JSONArray dfm_set = new JSONArray();
            dfm_set.add("1");
            dfm_set.add("2");
//            dfm_set.add("3");
            dfm_set.add("4");
//            System.out.println("dfm_set"+dfm_set);
            
            JSONArray pvg_result = new JSONArray();
            for (Pdbversion_group pvg : pdb_previous_data) {
                pvg_result.add(String.valueOf(pvg.getDomain_and_features_mapping_id().getId()));
            }
            
//            System.out.println("pvg_result"+pvg_result);         

            System.out.println("Original dfm_set"+dfm_set);
            System.out.println("Original pvg_result"+pvg_result);   
            
            
            JSONArray tmp_dfm_set = dfm_set;
            JSONArray tmp_pvg_result = pvg_result;
            
            tmp_dfm_set.removeAll(tmp_pvg_result);
            tmp_pvg_result.removeAll(tmp_dfm_set);

            System.out.println("Original dfm_set"+dfm_set);
            System.out.println("Original pvg_result"+pvg_result);   
            
            System.out.println("Newly inserted"+tmp_dfm_set);                        
            System.out.println("Removed"+tmp_pvg_result);
            
            
            
                
            maps_object.put("features", featureslist_result);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String CreatePDBVersion() {
        System.out.println("CreatePDBVersion");
        JSONParser parser = new JSONParser();
        String jsondata = JSONConfigure.getAngularJSONFile();
//        String button_type = (String) json.get("button_type");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
        boolean status = (boolean) false;
//        int pdbversion_id = 0;
        float version_name = 1.0f;
//        String previousversion_status = null;
//        String previousversion_flag = null;
        boolean flag;
        try {
            Object obj = parser.parse(jsondata);
            JSONObject json = (JSONObject) obj;
            System.out.println("pdbdata" + json);
            JSONObject pdbversion_value = (JSONObject) json.get("pdbversion");
            JSONArray pdbdata_list = (JSONArray) json.get("pdbdata_list");
            System.out.println("pdbdata_list" + pdbdata_list);
            String button_type = (String) json.get("button_type");
//            String notification_to = (String) json.get("notification_to");
            if (button_type.equals("save")) {
                flag = false;
            } else {
                flag = true;
            }
            if (pdbversion_value.containsKey("status")) {
                status = (boolean) pdbversion_value.get("status");
            }

            if (pdbversion_value.containsKey("pdbversion_id") && status == false) {
                System.out.println("Ready to update in same version");
            } else {
                System.out.println("Ready to create");
                //Create PDB version               
                List<Pdbversion> version_data = pdbownerdb.GetVersionname();
                Pdbversion pdbversion = new Pdbversion();
//                if(!version_data.isEmpty() && !pdbversion_value.containsKey("pdbversion_id")){
                if (!version_data.isEmpty()) {
                    if (!pdbversion_value.containsKey("pdbversion_id")) {
                        version_name = (float) 1.0 + version_data.get(0).getPdb_versionname();
                    } else {
                        version_name = (float) 0.1 + Float.valueOf((String) pdbversion_value.get("pdbversion_name"));
                        pdbversion.setPdb_reference_version(Float.valueOf((String) pdbversion_value.get("pdbversion_name")));
                    }
                }
                System.out.println("id" + Integer.parseInt((String) pdbversion_value.get("pdbversion_id")));
                //To find and store removed id's and new feature'ids 
                List pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(Integer.parseInt((String) pdbversion_value.get("pdbversion_id")));
                System.out.println("pdb_previous_data" + pdb_previous_data);
                JSONArray dfm_set = (JSONArray) json.get("dfm_set");

                pdbversion.setPdb_versionname(version_name);
                pdbversion.setPdb_manual_comment((String) pdbversion_value.get("pdb_manual_comment"));
                pdbversion.setStatus(status);
                pdbversion.setFlag(flag);
                pdbversion.setCreated_date(new Date());
                pdbversion.setModified_date(new Date());
                pdbversion.setCreated_or_updated_by(vehicle_Repository.getUser(1));
                Pdbversion pdbinserted_id = pdbownerdb.insertPDBVersion(pdbversion);
                //Insert data into PDB Version Group
                int i = 0;
                for (Object o : pdbdata_list) {
                    JSONObject pdbdata = (JSONObject) o;
                    System.out.println("pdbdata" + pdbdata);
                    Pdbversion_group pvg = new Pdbversion_group();
                    pvg.setPdbversion_id(pdbinserted_id);
                    pvg.setVehicle_id((Vehicle) session.get(Vehicle.class, Integer.parseInt((String) pdbversion_value.get("vehicle_id"))));
                    pvg.setVehiclemodel_id((Vehiclemodel) session.get(Vehiclemodel.class, Integer.parseInt((String) pdbdata.get("model_id"))));
                    pvg.setDomain_and_features_mapping_id((Domain_and_Features_Mapping) session.get(Domain_and_Features_Mapping.class, Integer.parseInt((String) pdbdata.get("dfm_id"))));
                    pvg.setAvailable_status((String) pdbdata.get("status"));
                    Pdbversion_group pvg_id = pdbownerdb.insertPDBVersionGroup(pvg);
                }
            }
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String LoadPdbversionData() {
        try {
            System.out.println("LoadPdbversionData");

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            int vehver_id = readValue.get("vehicleversion_id").asInt();
            System.out.println(vehver_id);

            pdbversion_group_result = (List<Pdbversion_group>)PDBOwnerDB.LoadPdbversion_groupByVehicleId(vehver_id);
//            JSONArray pdbvers_group_result = new JSONArray();
//            for (Pdbversion_group fea : pdbversion_group_result) {
//                System.err.println(fea.getVehicle_id());
//                JSONObject fr = new JSONObject();
//                fr.put("pid", fea.getId());
//                fr.put("vid", fea.getVehicle_id().getId());
//                fr.put("mid", fea.getVehiclemodel_id().getId());
//                pdbvers_group_result.add(fr);
//                System.out.println(fr);
//            }
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String pdbvers_group_result = mapper.writeValueAsString(pdbversion_group_result);
            maps_object.put("pdbversion", pdbvers_group_result);
            System.out.println(pdbvers_group_result);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
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
}
