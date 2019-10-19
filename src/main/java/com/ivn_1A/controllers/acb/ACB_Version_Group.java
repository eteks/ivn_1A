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
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.json.simple.parser.JSONParser;

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
//            tupleObjects = ACB_DB.loadFeaturesByPdbId(pdbID);
            Pdbversion_group pdbversion_group = new Pdbversion_group();
            pdbversion_group.setId(pdbID);
            Map<String, Object> col = new HashMap<>();
            Map<String, Object> testMap = ACB_DB.getFeaturesByPdbId(pdbversion_group);
            testMap.forEach((k, v) -> {
                switch (k) {
                    case "vehicledetail_list":
                        List<Map<String, Object>> row1 = new ArrayList<>();
                        tupleObjects = (List<Tuple>) v;
                        tupleObjects.stream().map((tuple) -> {

                            Map<String, Object> columns = new HashMap<>();
                            System.err.println("WElcome");
                            columns.put("pdb_id", tuple.get("pdb_id"));
                            columns.put("vehicle_id", tuple.get("vehicle_id"));
                            columns.put("modelname", tuple.get("modelname"));
                            columns.put("vmm_id", tuple.get("vmm_id"));
                            return columns;
                        }).map((columns) -> {
                            row1.add(columns);
                            return columns;
                        }).forEachOrdered((columns) -> {
                            System.out.println("colums_______________" + columns);
                        });
                        col.put("vehicledetail_list", row1);
                        break;
                    case "featuredetail_list":
                        List<Map<String, Object>> row2 = new ArrayList<>();
                        tupleObjects = (List<Tuple>) v;
                        tupleObjects.stream().map((tuple) -> {

                            Map<String, Object> columns = new HashMap<>();
                            System.err.println("WElcome");
                            columns.put("pdbgroup_id", tuple.get("pdbgroup_id"));
                            columns.put("vmm_id", tuple.get("vmm_id"));
                            columns.put("fid", tuple.get("fid"));
                            columns.put("featurename", tuple.get("featurename"));
                            columns.put("domainname", tuple.get("domainname"));
                            columns.put("status", tuple.get("status"));
                            columns.put("touch", "No");
                            return columns;
                        }).map((columns) -> {
                            row2.add(columns);
                            return columns;
                        }).forEachOrdered((columns) -> {
                            System.out.println("colums_______________" + columns);
                        });
                        col.put("featuredetail_list", row2);
                        break;
                    case "pdbversion_status":
                        List<Map<String, Object>> row3 = new ArrayList<>();
                        tupleObjects = (List<Tuple>) v;
                        tupleObjects.stream().map((tuple) -> {

                            Map<String, Object> columns = new HashMap<>();
                            System.err.println("WElcome");
                            columns.put("status", tuple.get("status"));
                            return columns;
                        }).map((columns) -> {
                            row3.add(columns);
                            return columns;
                        }).forEachOrdered((columns) -> {
                            System.out.println("colums____________" + columns);
                        });
                        col.put("pdbversion_status", row3);
                        break;
                    default:
                        System.err.println("+++++++++++++++++++++++++++ Error");
                        break;
                }
            });
            result_data_obj = new Gson().toJson(col);
            maps_string.put("success", "work is done");

        } catch (Exception e) {
            System.out.println("Error in \"ACB_Version_Group\" \'getFeaturesByPdb\' : " + e);
            maps_string.put("error", "Some error occurred !!");
        }
        return "success";
    }

    public String getSignalsAndECU() {

        try {

            Map<String, Object> testMap = ACB_DB.getSignalsAndECU();

            result_data_obj = new Gson().toJson(testMap);
            System.out.println("result_data_obj : " + result_data_obj);
            maps_string.put("success", "work is done");
        } catch (Exception e) {
            System.out.println("Error in \"ACB_Version_Group\" \'getSignalsAndECU\' : " + e);
            maps_string.put("error", "Some error occurred !!");
        }
        return "success";
    }

    public String CreateACBVersion() {

        try {

            System.out.println("CreateACBVersion");
            JSONParser parser = new JSONParser();
            String jsondata = JSONConfigure.getAngularJSONFile();
            return null;
        } catch (Exception e) {
            return null;
        }
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
