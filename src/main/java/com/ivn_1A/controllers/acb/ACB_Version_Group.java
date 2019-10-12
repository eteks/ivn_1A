/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.acb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.models.acb.ACB_DB;
import com.ivn_1A.models.net_sign.IVNEngineerDB;
import com.ivn_1A.models.pdbowner.FeatureversionDB;
import com.ivn_1A.models.pdbowner.Vehicle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author root
 */
public class ACB_Version_Group {

    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
    private List<Tuple> tupleObjects = new ArrayList<>();
    private List<Map<String, Object>> result_data = new ArrayList<>();
    Gson gson = new Gson();
    private String result_data_obj;
    HttpSession session = ServletActionContext.getRequest().getSession(false);
    final ObjectMapper mapper = new ObjectMapper();

    public String ACBVersionPage() {

        try {

            System.out.println("ACBVersionPage");
            IVNEngineerDB.LoadFeatureVersion("active").stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tuple.get("id"));
                columns.put("versionname", String.format("%.1f", tuple.get("versionname")));
                columns.put("status", tuple.get("status"));
                return columns;
            }).map((columns) -> {
                result_data.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            result_data_obj = new Gson().toJson(result_data);
            maps_string.put("success", "work is done");
        } catch (Exception e) {
            System.out.println("Error in \"ACB_Version_Group\" \'ACBVersionPage\' : " + e);
            maps_string.put("error", "Some error occurred !!");
        }
        return "success";
    }

    public String getPdbVersionFromFeatureVersion() {

        try {

            System.out.println("getPdbVersionFromFeatureVersion");

//            ACB_DB.getPdbVersionFromFeatureVersion().stream().map((tuple) -> {
//                Map<String, Object> columns = new HashMap<>();
//                columns.put("pdbid", tuple.get("pdbid"));
//                columns.put("pdbversionname", String.format("%.1f", tuple.get("pdb_versionname")));
//                return columns;
//            }).map((columns) -> {
//               result_data.add(columns);
//                return columns;
//            }).forEachOrdered((columns) -> {
//                System.out.println("getPdbVersionFromFeatureVersion " + columns);
//            });
            IVNEngineerDB.LoadFeatureVersion("active").stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tuple.get("id"));
                columns.put("versionname", String.format("%.1f", tuple.get("versionname")));
                columns.put("status", tuple.get("status"));
                return columns;
            }).map((columns) -> {
                result_data.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            maps_string.put("success", "work is done");

        } catch (Exception e) {
            System.out.println("Error in \"ACB_Version_Group\" \'getPdbVersionFromFeatureVersion\' : " + e);
            maps_string.put("error", "Some error occurred !!");
        }
        return "success";
    }
    
    public String getFeaturesByPdb() {
        
        try {

            System.out.println("getFeaturesByPdb");
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            int pdbID = readValue.get("pdbid").asInt();
            tupleObjects = ACB_DB.loadFeaturesByPdbId(pdbID);
            tupleObjects.stream().map((tuple) -> {
                
                Map<String, Object> columns = new HashMap<>();
                System.err.println("WElcome");
                columns.put("fid", tuple.get("fid"));
                columns.put("featurename", tuple.get("fname"));
                columns.put("stt", tuple.get("stt").toString().split(","));
                columns.put("touch", "No");
                columns.put("vmm_id", tuple.get("mid"));
                columns.put("modelname", tuple.get("mname"));
                return columns;
            }).map((columns) -> {
                result_data.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            result_data_obj = new Gson().toJson(result_data);
            maps_string.put("success", "work is done");

        } catch (Exception e) {
            System.out.println("Error in \"ACB_Version_Group\" \'getFeaturesByPdb\' : " + e);
            maps_string.put("error", "Some error occurred !!");
        }
        return "success";
    }

    public Map<String, String> getMaps_string() {
        return maps_string;
    }

    public void setMaps_string(Map<String, String> maps_string) {
        this.maps_string = maps_string;
    }

    public Map<String, Object> getMaps_object() {
        return maps_object;
    }

    public void setMaps_object(Map<String, Object> maps_object) {
        this.maps_object = maps_object;
    }

    public String getResult_data_obj() {
        return result_data_obj;
    }

    public void setResult_data_obj(String result_data_obj) {
        this.result_data_obj = result_data_obj;
    }

    public List<Map<String, Object>> getResult_data() {
        return result_data;
    }

    public void setResult_data(List<Map<String, Object>> result_data) {
        this.result_data = result_data;
    }

}
