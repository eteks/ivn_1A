/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.legislation_safty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.models.legislation_safty.SafetyDB;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Querybuilder;
import com.ivn_1A.models.pdbowner.SafetyLegDB;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;

/**
 *
 * @author ETS-05
 */
public class Safty_Combination {

    private Map<String, String> maps = new HashMap<>();
    private Map<String, Object> maps_obj = new HashMap<>();
    private List<Tuple> tuple_result = new ArrayList<>();
    private List<Map<String, Object>> safcomb_result = new ArrayList<>();
    private List<Map<String, Object>> result_data = new ArrayList<>();
    public String result_data_obj, result_data_str;
//    static HttpServletRequest request;

    final ObjectMapper mapper = new ObjectMapper();

    public String CreateSafComb() {

        try {
            System.out.println("CreateSafComb");
            System.out.println("entered try");
            long comb_id = 0;
            String previousversion_status = "true";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            System.out.println("readValue" + readValue);
//            request = ServletActionContext.getRequest();
//            System.out.println(request.getParameter("sql"));

            if (readValue.has("cid")) {
                System.out.println("cid" + readValue.has("cid"));
                comb_id = readValue.get("cid").asInt();
            }
            System.out.println("comb_id" + comb_id);
            if (comb_id != 0) {

                System.out.println("comb_id" + comb_id);
                //Get the data of previous vehicle version by id
                long c_id = comb_id;
                Querybuilder lc = new Querybuilder(c_id);
                tuple_result = SafetyDB.LoadPreviousSafetyCombinationData(lc);
                tuple_result.stream().map((tuple) -> {
                    Map<String, Object> columns = new HashMap<>();
                    columns.put("querybuilder_name", tuple.get("querybuilder_name"));
                    columns.put("querybuilder_type", tuple.get("querybuilder_type"));
                    columns.put("querybuilder_condition", tuple.get("querybuilder_condition"));
                    columns.put("querybuilder_status", tuple.get("querybuilder_status"));
                    return columns;
                }).map((columns) -> {
                    safcomb_result.add(columns);
                    return columns;
                }).forEachOrdered((columns) -> {
                    System.out.println("colums" + columns);
                });
                System.out.println("safcomb_result" + safcomb_result);
                previousversion_status = String.valueOf(tuple_result.get(0).get("querybuilder_status"));
                System.out.println("previousversion_status" + previousversion_status);

            } else if (previousversion_status.equals("false") && comb_id != 0) {

                System.out.println("Ready to update");
                boolean r_status = readValue.get("qb_status").asBoolean();
                Querybuilder lc = new Querybuilder(comb_id, readValue.get("qb_name").asText(), readValue.get("ctype").asText(), readValue.get("sql").toString(), r_status, new Date(), new Date(), PDBOwnerDB.getUser(1));
                //int result = VehicleversionDB.insertVehicleVersion(v);
                long lc_res = SafetyDB.insertSafetyCombination(lc);
                if (lc_res > 0) {
                    maps.put("status", "Safty Combination Updated Successfully");
                } else {
                    maps.put("status", "Safty Combination Updated Failed");
                }
            } else {

                System.out.println("Ready to insert");
                boolean r_status = readValue.get("qb_status").asBoolean();
                Querybuilder lc = new Querybuilder(comb_id, readValue.get("qb_name").asText(), readValue.get("ctype").asText(), readValue.get("sql").toString(), r_status, new Date(), new Date(), PDBOwnerDB.getUser(1));
                //int result = VehicleversionDB.insertVehicleVersion(v);
                long lc_res = SafetyDB.insertSafetyCombination(lc);
                if (lc_res > 0) {
                    maps.put("status", "Safty Combination Created Successfully");
                } else {
                    maps.put("status", "Safty Combination Created Failed");
                }
            }
            System.out.println("maps" + maps);
        } catch (Exception e) {
            System.out.println("entered into catch \'CreateSafComb\': " + e);
            maps.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String GetSafetyListing() {

        System.out.println("GetLegislationListing");
        Querybuilder lc = new Querybuilder();
        try {
            tuple_result = SafetyDB.GetSafetyListing();
            tuple_result.stream().map((legislationversion_group) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("leg_id", legislationversion_group.get("leg_id"));
                columns.put("leg", String.format("%.1f", legislationversion_group.get("leg")));
                columns.put("created_date", legislationversion_group.get("created_date"));
                columns.put("modified_date", legislationversion_group.get("modified_date"));
                columns.put("model", legislationversion_group.get("modelname"));
                columns.put("vehicle", legislationversion_group.get("vehiclename"));
                columns.put("version", String.format("%.1f", legislationversion_group.get("pdb_versionname")));
                columns.put("status", legislationversion_group.get("status"));
                columns.put("flag", legislationversion_group.get("flag"));
                return columns;
            }).map((columns) -> {
                result_data.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            result_data_obj = new Gson().toJson(result_data);
//            vehmod_map_result_obj = new Gson().toJson(vehmod_map_result);
//                vehmod_map_result_obj =  Gson().toJSON(vehmod_map_result);
            System.out.println("oject" + result_data_obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps.put("status", "Some error occurred !!");
        }
//            return vehmod_map_result;
//            System.out.println("Result"+vehmod_map_result);
        return "success";
    }

    public String GetSafetyCombinationListingPage() {
        System.out.println("GetSaftyCombinationListing");
//        try {
//            tuple_result = SafetyLegDB.GetSafetyCombinationListing();
//            tuple_result.stream().map((tuple) -> {
//                try {
//                    Map<String, Object> columns = new HashMap<>();
//                    columns.put("saf_id", tuple.get("saf_id"));
//                    columns.put("saf", tuple.get("saf"));
//                    columns.put("created_date", tuple.get("created_date"));
//                    columns.put("modified_date", tuple.get("modified_date"));
//                    System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + tuple.get("combination"));
//                    columns.put("combination", tuple.get("combination"));
//                    maps_obj.put("combination", mapper.readValue(tuple.get("combination").toString(), JsonNode.class));
//                    columns.put("status", tuple.get("status"));
//                    return columns;
//                } catch (Exception ex) {
//                    maps.put("status", "Parsing Error");
//                    return null;
//                }
//            }).map((columns) -> {
//                result_data.add(columns);
//                return columns;
//            }).forEachOrdered((columns) -> {
//                System.out.println("colums" + columns);
//            });
//            result_data_obj = new Gson().toJson(result_data);
//            System.out.println("oject" + result_data_obj);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            maps.put("status", "Some error occurred !!");
//        }
        return "success";
    }

    public String GetSafetyCombinationListing() {
        System.out.println("GetSaftyCombinationListing");
        try {
            tuple_result = SafetyLegDB.GetSafetyCombinationListing();
            tuple_result.stream().map((tuple) -> {
                try {
                    Map<String, Object> columns = new HashMap<>();
                    columns.put("saf_id", tuple.get("saf_id"));
                    columns.put("saf", tuple.get("saf"));
                    columns.put("created_date", tuple.get("created_date"));
                    columns.put("modified_date", tuple.get("modified_date"));
//                    System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + tuple.get("combination"));
                    columns.put("combination", tuple.get("combination").toString());
                    columns.put("status", tuple.get("status"));
                    return columns;
                } catch (Exception ex) {
                    maps.put("status", "Parsing Error");
                    return null;
                }
            }).map((columns) -> {
                result_data.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            System.out.println("oject" + result_data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public List<Map<String, Object>> getResult_data() {
        return result_data;
    }

    public void setResult_data(List<Map<String, Object>> result_data) {
        this.result_data = result_data;
    }

    public String getResult_data_obj() {
        return result_data_obj;
    }

    public void setResult_data_obj(String result_data_obj) {
        this.result_data_obj = result_data_obj;
    }

    public String getResult_data_str() {
        return result_data_str;
    }

    public void setResult_data_str(String result_data_str) {
        this.result_data_str = result_data_str;
    }

    public Map<String, Object> getMaps_obj() {
        return maps_obj;
    }

    public void setMaps_obj(Map<String, Object> maps_obj) {
        this.maps_obj = maps_obj;
    }

}
