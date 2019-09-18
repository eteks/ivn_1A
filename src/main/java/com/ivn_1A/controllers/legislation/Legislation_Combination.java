/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.legislation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.models.legislation.LegislationDB;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Querybuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ETS-05
 */
public class Legislation_Combination {

    private Map<String, String> maps = new HashMap<>();
    private List<Tuple> tuple_result = new ArrayList<>();
    private List<Map<String, Object>> legcomb_result = new ArrayList<>();
    private List<Map<String, Object>> result_data = new ArrayList<>();
    public String result_data_obj;
//    static HttpServletRequest request;

    public String CreateLegComb() {

        try {
            System.out.println("CreateLegComb");
            System.out.println("entered try");
            long comb_id = 0;
            String previousversion_status = "true";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            final ObjectMapper mapper = new ObjectMapper();
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
                tuple_result = LegislationDB.LoadPreviousLegislationCombinationData(lc);
                tuple_result.stream().map((tuple) -> {
                    Map<String, Object> columns = new HashMap<>();
                    columns.put("querybuilder_name", tuple.get("querybuilder_name"));
                    columns.put("querybuilder_type", tuple.get("querybuilder_type"));
                    columns.put("querybuilder_condition", tuple.get("querybuilder_condition"));
                    columns.put("querybuilder_status", tuple.get("querybuilder_status"));
                    return columns;
                }).map((columns) -> {
                    legcomb_result.add(columns);
                    return columns;
                }).forEachOrdered((columns) -> {
                    System.out.println("colums" + columns);
                });
                System.out.println("legcomb_result" + legcomb_result);
                previousversion_status = String.valueOf(tuple_result.get(0).get("querybuilder_status"));
                System.out.println("previousversion_status" + previousversion_status);

            } else if (previousversion_status.equals("false") && comb_id != 0) {

                System.out.println("Ready to update");
                boolean r_status = readValue.get("qb_status").asBoolean();
                Querybuilder lc = new Querybuilder(comb_id, readValue.get("qb_name").asText(), "legislation", readValue.get("sql").asText(), r_status, new Date(), new Date(), PDBOwnerDB.getUser(1));
                //int result = VehicleversionDB.insertVehicleVersion(v);
                long lc_res = LegislationDB.insertLegislationCombination(lc);
                if (lc_res > 0) {
                    maps.put("status", "Legislation Combination Updated Successfully");
                } else {
                    maps.put("status", "Legislation Combination Updated Failed");
                }
            } else {

                System.out.println("Ready to insert");
                boolean r_status = readValue.get("qb_status").asBoolean();
                Querybuilder lc = new Querybuilder(comb_id, readValue.get("qb_name").asText(), "legislation", readValue.get("sql").asText(), r_status, new Date(), new Date(), PDBOwnerDB.getUser(1));
                //int result = VehicleversionDB.insertVehicleVersion(v);
                long lc_res = LegislationDB.insertLegislationCombination(lc);
                if (lc_res > 0) {
                    maps.put("status", "Legislation Combination Created Successfully");
                } else {
                    maps.put("status", "Legislation Combination Created Failed");
                }
            }
            System.out.println("maps" + maps);
        } catch (Exception e) {
            System.out.println("entered into catch \'CreateLegComb\': " + e);
            maps.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String GetLegislationCombinationListing() {
        System.out.println("GetLegislationCombinationListing controller");
        Querybuilder lc = new Querybuilder();
        try {
            tuple_result = LegislationDB.GetLegislationCombinationListing();
            tuple_result.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("leg_id", tuple.get("leg_id"));
                columns.put("leg", tuple.get("leg"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
                columns.put("combination", tuple.get("combination"));
                columns.put("status", tuple.get("status"));
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

}
