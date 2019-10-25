/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.pdbowner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.configs.VersionType;
import com.ivn_1A.controllers.notification.NotificationController;
import com.ivn_1A.models.pdbowner.Legislationversion;
import com.ivn_1A.models.pdbowner.Legislationversion_group;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Querybuilder;
import com.ivn_1A.models.pdbowner.SafetyLegDB;
import com.ivn_1A.models.pdbowner.Safetyversion;
import com.ivn_1A.models.pdbowner.Safetyversion_group;
import com.ivn_1A.models.pdbowner.Vehicle;
import com.opensymphony.xwork2.ActionContext;
import java.io.IOException;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ets-poc
 */
public class Safety_and_Legislation {

    private Map<String, String> maps = new HashMap<>();
    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
//    Session session = HibernateUtil.getThreadLocalSession();
    private List<Vehicle> vehicle_result;
    private List<Tuple> tupleObjects = new ArrayList<>();
    Gson gson = new Gson();
    private String result_data_obj;
    private List<Tuple> tuple_result = new ArrayList<>();
    private List<Map<String, Object>> result_data = new ArrayList<>();

    final ObjectMapper mapper = new ObjectMapper();

    public String LegislationPage() {

        System.out.println("Entered");
        System.out.println("LegislationPage");

//        This will execute if url contains parameter(id and action-edit, view)
        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            System.out.println("request" + request);
            System.out.println("id_value" + request.getParameter("id"));
            System.out.println("action_value" + request.getParameter("action"));
//            tupleObjects = SafetyLegDB.LoadLegislationversion_groupData(Integer.parseInt(request.getParameter("id")), request.getParameter("action"));
//            List<Map<String, Object>> saf_map_result = new ArrayList<>();
//            tupleObjects.stream().map((tuple) -> {
//                Map<String, Object> safetyMap = new HashMap<>();
//                safetyMap.put("qb_name", tuple.get("qb_name"));
//                safetyMap.put("qb_id", tuple.get("qb_id"));
//                safetyMap.put("pdbversion_id", tuple.get("pdb_versionid"));
//                safetyMap.put("pdbversion_name", tuple.get("pdb_versionname"));
//                safetyMap.put("modelname", tuple.get("modelname"));
//                safetyMap.put("model_id", tuple.get("model_id"));
//                safetyMap.put("vehiclename", tuple.get("vehiclename"));
//                safetyMap.put("vehicle_id", tuple.get("vehicle_id"));
//                safetyMap.put("legisversion_group_id", tuple.get("legisversion_group_id"));
//                safetyMap.put("leg_id", tuple.get("leg_id"));
//                safetyMap.put("leg", tuple.get("leg"));
//                safetyMap.put("status", tuple.get("status"));
//                safetyMap.put("available_status", tuple.get("available_status"));
//                safetyMap.put("flag", tuple.get("flag"));
//                return safetyMap;
//            }).forEachOrdered((safetyMap) -> {
//                saf_map_result.add(safetyMap);
//            });
//            result_data_obj = new Gson().toJson(saf_map_result);

            Map<String, Object> maps = SafetyLegDB.LoadSafetyVersionGroupData(Integer.parseInt(request.getParameter("id")), request.getParameter("action"));
            maps.forEach((t, u) -> {
                switch (t) {
                    case "safety":
                        ((List<Tuple>) u).stream().map((tuple) -> {
                            Map<String, Object> safetyMap = new HashMap<>();
                            safetyMap.put("pdb_id", tuple.get("pdb_id"));
                            safetyMap.put("veh_id", tuple.get("veh_id"));
                            safetyMap.put("created_date", tuple.get("created_date"));
                            safetyMap.put("modified_date", tuple.get("modified_date"));
                            safetyMap.put("modelname", tuple.get("modelname"));
                            safetyMap.put("id", tuple.get("id"));
                            return safetyMap;
                        }).forEachOrdered((safetyMap) -> {
                            result_data.add(safetyMap);
                        });
                        break;
                    case "qb":
                        ((List<Tuple>) u).stream().map((tuple) -> {
                            Map<String, Object> safetyMap = new HashMap<>();
                            safetyMap.put("id", tuple.get("id"));
                            safetyMap.put("qb_id", tuple.get("qb_id"));
                            safetyMap.put("available_status", tuple.get("available_status"));
                            safetyMap.put("qb_name", tuple.get("qb_name"));
                            safetyMap.put("querybuilder_type", tuple.get("qb_type"));
                            safetyMap.put("id", tuple.get("id"));
                            return safetyMap;
                        }).forEachOrdered((safetyMap) -> {
                            result_data.add(safetyMap);
                        });
                        break;
                    default:
                        break;
                }
            });
            result_data_obj = new Gson().toJson(result_data);
            System.err.println("result_data_obj=== " + result_data_obj);
        } catch (Exception e) {
            System.err.println("Error in \"Safety_and_Legislation\" \'LegislationPage\' : " + e);
        }
        try {
            List<Querybuilder> legcomb_list = SafetyLegDB.LoadCombinationList("legislation");

            JSONArray legcomb_list_res = new JSONArray();
            legcomb_list.stream().map((leg) -> {
                JSONObject fr = new JSONObject();
                fr.put("qb_id", leg.getId());
                fr.put("qb_name", leg.getQuerybuilder_name());
                return fr;
            }).forEachOrdered((fr) -> {
                legcomb_list_res.add(fr);
            });
            maps_object.put("legcomb_list_res", legcomb_list_res);
            vehicle_result = PDBOwnerDB.loadVehicleVersion();
            System.out.println("vehicle_result" + vehicle_result);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String SafetyPage() {

        System.out.println("Entered");
        System.out.println("SafetyPage");

        //This will execute if url contains parameter(id and action-edit, view)
        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            System.out.println("request" + request);
            System.out.println("id_value" + request.getParameter("id"));
            System.out.println("action_value" + request.getParameter("action"));
            tupleObjects = SafetyLegDB.LoadSafetyversion_groupData(Integer.parseInt(request.getParameter("id")), request.getParameter("action"));
            List<Map<String, Object>> saf_map_result = new ArrayList<>();
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> safetyMap = new HashMap<>();
                safetyMap.put("qb_name", tuple.get("qb_name"));
                safetyMap.put("qb_id", tuple.get("qb_id"));
                safetyMap.put("pdbversion_id", tuple.get("pdb_versionid"));
                safetyMap.put("pdbversion_name", tuple.get("pdb_versionname"));
                safetyMap.put("modelname", tuple.get("modelname"));
                safetyMap.put("model_id", tuple.get("model_id"));
                safetyMap.put("vehiclename", tuple.get("vehiclename"));
                safetyMap.put("vehicle_id", tuple.get("vehicle_id"));
                safetyMap.put("safetyversion_group_id", tuple.get("safetyversion_group_id"));
                safetyMap.put("saf_id", tuple.get("saf_id"));
                safetyMap.put("saf", tuple.get("saf"));
                safetyMap.put("status", tuple.get("status"));
                safetyMap.put("available_status", tuple.get("available_status"));
                safetyMap.put("flag", tuple.get("flag"));
                return safetyMap;
            }).forEachOrdered((safetyMap) -> {
                saf_map_result.add(safetyMap);
            });
            result_data_obj = new Gson().toJson(saf_map_result);
            System.err.println("result_data_obj " + result_data_obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            List<Querybuilder> safcomb_list = SafetyLegDB.LoadCombinationList("safety");

            JSONArray safcomb_list_res = new JSONArray();
            safcomb_list.stream().map((leg) -> {
                JSONObject fr = new JSONObject();
                fr.put("qb_id", leg.getId());
                fr.put("qb_name", leg.getQuerybuilder_name());
                return fr;
            }).forEachOrdered((fr) -> {
                safcomb_list_res.add(fr);
            });
            maps_object.put("safcomb_list_res", safcomb_list_res);
            System.out.println("safcomb_list_res" + safcomb_list_res);
            vehicle_result = PDBOwnerDB.loadVehicleVersion();
            System.out.println("vehicle_result" + vehicle_result);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String CreateLegislationVersion() throws IOException {

        NotificationController notificationController = new NotificationController();
        System.out.println("CreateLegislationVersion");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
        System.out.println("readValue" + readValue);
        boolean status = (boolean) false;
        float version_name = 1.0f;
        String version_type = "new";
        boolean flag;
        int prevleg_id = 0;
        try {
            JsonNode legislation_value = (JsonNode) readValue.get("legislationversion");
            System.out.println("legislation_value" + legislation_value);
            String notification_to = readValue.get("notification_to").asText();
            System.out.println("notification_to" + notification_to);
            ArrayNode legislationdata_list = (ArrayNode) readValue.get("legislationdata_list");
            System.out.println("legislationdata_list" + legislationdata_list);
            String button_type = readValue.get("button_type").asText();
            System.out.println("button_type" + button_type);
//            String notification_to = (String) json.get("notification_to");
            flag = !button_type.equals("save");
            if (legislation_value.has("status")) {
                status = legislation_value.get("status").asBoolean();
            }
            System.out.println("before if");
            if (legislation_value.has("legislationversion") && legislation_value.get("legislationversion").get("status").asBoolean() == false) {
                System.out.println("Ready to update in same version");
            } else {

                System.out.println("Ready to create");
                //Create PDB version               
//                List<Pdbversion> version_data = PDBOwnerDB.GetVersionname();
                Legislationversion legislationversion = new Legislationversion();
//                if(!version_data.isEmpty() && !pdbversion_value.containsKey("pdbversion_id")){
                if (legislation_value.has("legislationversion")) {
                    System.out.println("enter legislationversion");
                    if (legislation_value.has("version_change")) {
                        System.out.println("enter version change");
                        System.out.println("enter version change1" + legislation_value.get("version_change").asText());
                        if (legislation_value.get("version_change").asText().equals("major")) {
                            System.out.println("major");
                            System.out.println("vehicle_id" + legislation_value.get("vehicle").asInt());
                            List<Legislationversion> version_data = SafetyLegDB.GetVersionname(legislation_value.get("vehicle").asInt(), version_type);
                            version_name = (float) 1.0 + version_data.get(0).getLegislation_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(version_data.get(0).getPdb_versionname()));
                            prevleg_id = version_data.get(0).getId();

                        } //else if minor changes
                        else {
                            version_type = "minor_changes";
                            System.out.println("minor");
                            List<Legislationversion> version_data = SafetyLegDB.GetVersionname(legislation_value.get("vehicle").asInt(), version_type);
                            version_name = (float) 0.1 + version_data.get(0).getLegislation_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText()));
                            prevleg_id = legislation_value.get("legislationversion").get("legid").asInt();
                        }
                        legislationversion.setLegislation_reference_version(Float.valueOf(legislation_value.get("legislationversion").get("legversion_name").asText()));
                    }
                }
                System.out.println("version_name" + version_name);
                System.out.println("prevleg_id" + prevleg_id);
                //To find and store removed id's and new feature'ids 
//                List pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(Integer.parseInt((String) pdbversion_value.get("pdbversion_id")));
//                System.out.println("pdb_previous_data" + pdb_previous_data);
//                ArrayNode dfm_set = (ArrayNode) readValue.get("dfm_set");
                legislationversion.setVehicle_id(PDBOwnerDB.getVehicle(legislation_value.get("vehicle").asInt()));
                legislationversion.setLegislation_versionname(version_name);
                legislationversion.setLegislation_manual_comment(legislation_value.get("legislation_manual_comment").asText());
                legislationversion.setVersion_type(version_type);
                legislationversion.setPdbversion_id(PDBOwnerDB.getPdbversion(legislation_value.get("pdbversion").get("pdbid").asInt()));
                legislationversion.setStatus(status);
                legislationversion.setFlag(flag);
                legislationversion.setCreated_date(new Date());
                legislationversion.setModified_date(new Date());
                legislationversion.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
                Legislationversion curleg_id = SafetyLegDB.insertLegilsationVersion(legislationversion);
                //Insert data into PDB Version Group
                int i = 0;
                List<Legislationversion_group> legislationversion_groups = new ArrayList<>();
                for (Object o : legislationdata_list) {
                    JsonNode legdata = (JsonNode) o;
                    System.out.println("legdata" + legdata);
                    Legislationversion_group leg_gp = new Legislationversion_group();
                    leg_gp.setLegislationversion_id(curleg_id);
                    leg_gp.setVehiclemodel_id(PDBOwnerDB.getVehiclemodel(legdata.get("model_id").asInt()));
                    leg_gp.setQuerybuilder_id(SafetyLegDB.getQuerybuilder(legdata.get("qb_id").asLong()));
                    leg_gp.setAvailable_status(legdata.get("status").asText());
                    Legislationversion_group leg_gp_id = SafetyLegDB.insertLegislationVersionGroup(leg_gp);
                    legislationversion_groups.add(leg_gp_id);
                }
                //we can use this code to find comparision of previous id and current id
                if (prevleg_id != 0) {
                    Map<String, Object> leg_previous_data = PDBOwnerDB.GetPDBPreviousVersion_DomFea(prevleg_id, curleg_id.getId());
                    System.out.println("leg_previous_data result" + leg_previous_data);

                    JSONObject leg_previous_data_result = new JSONObject();
                    leg_previous_data_result.put("removed_features", leg_previous_data.get("removed_features"));
                    leg_previous_data_result.put("added_features", leg_previous_data.get("added_features"));
                    leg_previous_data_result.put("removed_models", leg_previous_data.get("removed_models"));
                    leg_previous_data_result.put("added_models", leg_previous_data.get("added_models"));
                    leg_previous_data_result.put("current_version", String.format("%.1f", curleg_id.getLegislation_versionname()));
                    leg_previous_data_result.put("reference_version", legislation_value.get("legislationversion").get("legversion_name").asDouble());

                    maps_object.put("leg_previous_data_result", leg_previous_data_result);
                }

                maps_string.put("leg_version", mapper.writeValueAsString(curleg_id));
                maps_string.put("leg_version_group", mapper.writeValueAsString(legislationversion_groups));
                if (button_type.equals("save")) {
                    maps_string.put("status", "New Temporary Legislation Version Created Successfully");
                } else {
                    notificationController.createNotification(VersionType.Legislationversion.getVersionCode(), curleg_id.getLegislation_versionname(), new Date().toString(), notification_to, curleg_id.getId());
                    maps_string.put("status", "New Permanent Legislation Version Created Successfully");
                }
            }
            maps_string.put("status_code", "1");
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
            maps_string.put("status_code", "0");
        }
        return "success";
    }

    public String CreateSafetyVersion() throws IOException {

        NotificationController notificationController = new NotificationController();
        System.out.println("CreateSafetyVersion");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
        System.out.println("readValue" + readValue);
        boolean status = (boolean) false;
        float version_name = 1.0f;
        String version_type = "new";
        boolean flag;
        int prevsaf_id = 0;
        try {
            JsonNode safety_value = (JsonNode) readValue.get("safetyversion");
            System.out.println("safety_value" + safety_value);
            String notification_to = readValue.get("notification_to").asText();
            System.out.println("notification_to" + notification_to);
            ArrayNode safetydata_list = (ArrayNode) readValue.get("safetyversiondata_list");
            System.out.println("safetydata_list" + safetydata_list);
            String button_type = readValue.get("button_type").asText();
            System.out.println("button_type" + button_type);
//            String notification_to = (String) json.get("notification_to");

            if (button_type.equals("save")) {
                flag = false;
            } else {
                flag = true;
            }
            if (safety_value.has("status")) {
                status = safety_value.get("status").asBoolean();
            }
            System.out.println("before if");
            if (safety_value.has("safetyversion") && safety_value.get("safetyversion").get("status").asBoolean() == false) {
                System.out.println("Ready to update in same version");
            } else {
                System.out.println("Ready to create");
                //Create PDB version               
//                List<Pdbversion> version_data = PDBOwnerDB.GetVersionname();
                Safetyversion safetyversion = new Safetyversion();
//                if(!version_data.isEmpty() && !pdbversion_value.containsKey("pdbversion_id")){
                if (safety_value.has("safetyversion")) {
                    System.out.println("enter safetyversion");
                    if (safety_value.has("version_change")) {
                        System.out.println("enter version change");
                        System.out.println("enter version change1" + safety_value.get("version_change").asText());
                        if (safety_value.get("version_change").asText().equals("major")) {
                            System.out.println("major");
                            System.out.println("vehicle_id" + safety_value.get("vehicle").asInt());
                            List<Safetyversion> version_data = SafetyLegDB.GetSafetyVersionname(safety_value.get("vehicle").asInt(), version_type);
                            version_name = (float) 1.0 + version_data.get(0).getSafety_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(version_data.get(0).getPdb_versionname()));
                            prevsaf_id = version_data.get(0).getId();

                        } //else if minor changes
                        else {
                            version_type = "minor_changes";
                            System.out.println("minor");
                            List<Safetyversion> version_data = SafetyLegDB.GetSafetyVersionname(safety_value.get("vehicle").asInt(), version_type);
                            version_name = (float) 0.1 + version_data.get(0).getSafety_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText()));
                            prevsaf_id = safety_value.get("safetyversion").get("safid").asInt();
                        }
                        safetyversion.setSafety_reference_version(Float.valueOf(safety_value.get("safetyversion").get("safversion_name").asText()));
                    }
                }
                System.out.println("version_name" + version_name);
                System.out.println("prevleg_id" + prevsaf_id);
                //To find and store removed id's and new feature'ids 
//                List pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(Integer.parseInt((String) pdbversion_value.get("pdbversion_id")));
//                System.out.println("pdb_previous_data" + pdb_previous_data);
//                ArrayNode dfm_set = (ArrayNode) readValue.get("dfm_set");
                safetyversion.setVehicle_id(PDBOwnerDB.getVehicle(safety_value.get("vehicle").asInt()));
                safetyversion.setSafety_versionname(version_name);
                safetyversion.setSafety_manual_comment(safety_value.get("safety_manual_comment").asText());
                safetyversion.setVersion_type(version_type);
                safetyversion.setPdbversion_id(PDBOwnerDB.getPdbversion(safety_value.get("pdbversion").get("pdbid").asInt()));
                safetyversion.setStatus(status);
                safetyversion.setFlag(flag);
                safetyversion.setCreated_date(new Date());
                safetyversion.setModified_date(new Date());
                safetyversion.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
                Safetyversion cursaf_id = SafetyLegDB.insertSafetyVersion(safetyversion);
                //Insert data into PDB Version Group
                int i = 0;
                List<Safetyversion_group> Safetyversion_group = new ArrayList<>();
                for (Object o : safetydata_list) {
                    JsonNode safdata = (JsonNode) o;
                    System.out.println("safdata" + safdata);
                    Safetyversion_group saf_gp = new Safetyversion_group();
                    saf_gp.setSafetyversion_id(cursaf_id);
                    saf_gp.setVehiclemodel_id(PDBOwnerDB.getVehiclemodel(safdata.get("model_id").asInt()));
                    saf_gp.setQuerybuilder_id(SafetyLegDB.getQuerybuilder(safdata.get("qb_id").asLong()));
                    saf_gp.setAvailable_status(safdata.get("status").asText());
                    Safetyversion_group saf_gp_id = SafetyLegDB.insertSafetyVersionGroup(saf_gp);
                    Safetyversion_group.add(saf_gp_id);
                }
                //we can use this code to find comparision of previous id and current id
                if (prevsaf_id != 0) {
                    Map<String, Object> saf_previous_data = PDBOwnerDB.GetPDBPreviousVersion_DomFea(prevsaf_id, cursaf_id.getId());
                    System.out.println("saf_previous_data result" + saf_previous_data);

                    JSONObject saf_previous_data_result = new JSONObject();
                    saf_previous_data_result.put("removed_features", saf_previous_data.get("removed_features"));
                    saf_previous_data_result.put("added_features", saf_previous_data.get("added_features"));
                    saf_previous_data_result.put("removed_models", saf_previous_data.get("removed_models"));
                    saf_previous_data_result.put("added_models", saf_previous_data.get("added_models"));
                    saf_previous_data_result.put("current_version", String.format("%.1f", cursaf_id.getSafety_versionname()));
                    saf_previous_data_result.put("reference_version", safety_value.get("safetyversion").get("safversion_name").asDouble());

                    maps_object.put("saf_previous_data_result", saf_previous_data_result);
                }

                maps_string.put("saf_version", mapper.writeValueAsString(cursaf_id));
                maps_string.put("saf_version_group", mapper.writeValueAsString(Safetyversion_group));
                if (button_type.equals("save")) {
                    maps_string.put("status", "New Temporary Safety Version Created Successfully");
                } else {
                    notificationController.createNotification(VersionType.Safetyversion.getVersionCode(), cursaf_id.getSafety_versionname(), new Date().toString(), notification_to, cursaf_id.getId());
                    maps_string.put("status", "New Permanent Safety Version Created Successfully");
                }
            }
            maps_string.put("status_code", "1");
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
            maps_string.put("status_code", "0");
        }
        return "success";
    }

    public String LoadLegislationversionData() {

        try {
            System.out.println("LoadLegislationversionData");

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            int vehicle_id = readValue.get("vehicle_id").asInt();
            String action = readValue.get("action").asText();

            tupleObjects = SafetyLegDB.loadLegislationversion_groupByVehicleId(vehicle_id, action);
            JSONArray legvers_group_result = new JSONArray();

            tupleObjects.stream().map((tuple) -> {
                return tuple;
            }).map((tuple) -> {
                JSONObject fr = new JSONObject();
                fr.put("lid", tuple.get("lid"));
                fr.put("lversion", tuple.get("lversion"));
                fr.put("status", tuple.get("status"));
                return fr;
            }).map((fr) -> {
                legvers_group_result.add(fr);
                return fr;
            }).forEachOrdered((fr) -> {
                System.out.println("JSON ARRAY : " + fr);
            });
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            String pdbvers_group_result = mapper.writeValueAsString(pdbversion_group_result);
            maps_object.put("legversion", legvers_group_result);
            System.out.println(legvers_group_result);
            maps_string.put("success", "Work is Done");
        } catch (Exception e) {
            System.err.println("Error in  \"Safety_and_Legislation\" \'LoadLegislationversionData\' : " + e);
            maps_string.put("error", "Work isn't Done");
        }
        return "success";
    }

    public String LoadSafetyversionData() {
        try {
            System.out.println("LoadSafetyversionData");

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            int vehicle_id = readValue.get("vehicle_id").asInt();
            String action = readValue.get("action").asText();

            tupleObjects = SafetyLegDB.loadSafetyversion_groupByVehicleId(vehicle_id, action);
            JSONArray safvers_group_result = new JSONArray();

            tupleObjects.stream().map((tuple) -> {
                return tuple;
            }).map((tuple) -> {
                JSONObject fr = new JSONObject();
                fr.put("sid", tuple.get("sid"));
                fr.put("sversion", tuple.get("sversion"));
                fr.put("status", tuple.get("status"));
                return fr;
            }).map((fr) -> {
                safvers_group_result.add(fr);
                return fr;
            }).forEachOrdered((fr) -> {
                System.out.println("JSON ARRAY : " + fr);
            });
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            String pdbvers_group_result = mapper.writeValueAsString(pdbversion_group_result);
            maps_object.put("safversion", safvers_group_result);
            System.out.println(safvers_group_result);
        } catch (Exception e) {
            maps_object.put("status", e);
            System.out.println("Error : " + e);
        }
        return "success";
    }

    public String GetSafetyListing() {

        System.out.println("GetSafetyListing");
        Querybuilder lc = new Querybuilder();
        try {
            tuple_result = SafetyLegDB.GetSafetyListing();
            tuple_result.stream().map((safetyversion_group) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("saf_id", safetyversion_group.get("saf_id"));
                columns.put("saf", String.format("%.1f", safetyversion_group.get("saf")));
                columns.put("created_date", safetyversion_group.get("created_date"));
                columns.put("modified_date", safetyversion_group.get("modified_date"));
                columns.put("model", safetyversion_group.get("modelname"));
                columns.put("vehicle", safetyversion_group.get("vehiclename"));
                columns.put("version", String.format("%.1f", safetyversion_group.get("pdb_versionname")));
                columns.put("status", safetyversion_group.get("status"));
                columns.put("flag", safetyversion_group.get("flag"));
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
            System.out.println("oject======" + result_data_obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_object.put("status", "Some error occurred !!");
        }
//            return vehmod_map_result;
//            System.out.println("Result"+vehmod_map_result);
        return "success";
    }

    public String CreateLegComb() {

        try {
            System.out.println("CreateLegComb");
            System.out.println("entered try");
            long comb_id = 0;
            String previousversion_status = "none";
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
                tuple_result = SafetyLegDB.LoadPreviousLegislationCombinationData(lc);
                tuple_result.stream().map((tuple) -> {
                    Map<String, Object> columns = new HashMap<>();
                    columns.put("querybuilder_name", tuple.get("querybuilder_name"));
                    columns.put("querybuilder_type", tuple.get("querybuilder_type"));
                    columns.put("querybuilder_condition", tuple.get("querybuilder_condition"));
                    columns.put("querybuilder_status", tuple.get("querybuilder_status"));
                    return columns;
                }).map((columns) -> {
                    result_data.add(columns);
                    return columns;
                }).forEachOrdered((columns) -> {
                    System.out.println("colums" + columns);
                });
                System.out.println("legcomb_result" + result_data);
                previousversion_status = String.valueOf(tuple_result.get(0).get("querybuilder_status"));
                System.out.println("previousversion_status" + previousversion_status);

            } else if (previousversion_status.equals("false") && comb_id != 0) {

                System.out.println("Ready to update");
                boolean r_status = readValue.get("qb_status").asBoolean();
                Querybuilder lc = new Querybuilder(comb_id, readValue.get("qb_name").asText(), readValue.get("ctype").asText(), readValue.get("sql").toString(), r_status, new Date(), new Date(), PDBOwnerDB.getUser(1));
                //int result = VehicleversionDB.insertVehicleVersion(v);
                long lc_res = SafetyLegDB.insertLegislationCombination(lc);
                if (lc_res > 0) {
                    maps.put("status", "Legislation Combination Updated Successfully");
                } else {
                    maps.put("status", "Legislation Combination Updated Failed");
                }
            } else {

                System.out.println("Ready to insert");
                boolean r_status = readValue.get("qb_status").asBoolean();
                Querybuilder lc = new Querybuilder(comb_id, readValue.get("qb_name").asText(), readValue.get("ctype").asText(), readValue.get("sql").toString(), r_status, new Date(), new Date(), PDBOwnerDB.getUser(1));
                //int result = VehicleversionDB.insertVehicleVersion(v);
                long lc_res = SafetyLegDB.insertLegislationCombination(lc);
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

    public String GetLegislationListing() {
        System.out.println("GetLegislationListing");
        Querybuilder lc = new Querybuilder();
        try {
//            tuple_result = SafetyLegDB.GetLegislationListing();
            SafetyLegDB.GetLegislationListing().stream().map((legislationversion_group) -> {
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
                System.out.println("colums****************" + columns);
            });
            result_data.add(maps_object);
//            SafetyLegDB.GetLegislationListing().stream().map((legislationversion_group) -> {
//                Map<String, Object> columns = new HashMap<>();
//                columns.put("leg_id", legislationversion_group.getLegislationversion_id().getId());
//                columns.put("leg", String.format("%.1f", legislationversion_group.getLegislationversion_id().getLegislation_versionname()));
//                columns.put("created_date", legislationversion_group.getLegislationversion_id().getCreated_date());
//                columns.put("modified_date", legislationversion_group.getLegislationversion_id().getModified_date());
//                columns.put("model", StringUtils.join(legislationversion_group.getVehiclemodel_id().getModelname(), ","));
//                columns.put("vehicle", legislationversion_group.getLegislationversion_id().getVehicle_id().getVehiclename());
//                columns.put("version", String.format("%.1f", legislationversion_group.getLegislationversion_id().getPdbversion_id().getPdb_versionname()));
//                columns.put("status", legislationversion_group.getLegislationversion_id().getStatus());
//                columns.put("flag", legislationversion_group.getLegislationversion_id().getFlag());
//                return columns;
//            }).map((columns) -> {
//                result_data.add(columns);
//                return columns;
//            }).forEachOrdered((columns) -> {
//                System.out.println("colums" + columns);
//            });
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

    public String GetLegislationCombinationListingPage() {
        System.out.println("GetLegislationCombinationListingPage controller");
//        Querybuilder lc = new Querybuilder();
//        try {
//            tuple_result = SafetyLegDB.GetLegislationCombinationListing();
//            tuple_result.stream().map((tuple) -> {
//                Map<String, Object> columns = new HashMap<>();
//                columns.put("leg_id", tuple.get("leg_id"));
//                columns.put("leg", tuple.get("leg"));
//                columns.put("created_date", tuple.get("created_date"));
//                columns.put("modified_date", tuple.get("modified_date"));
//                columns.put("combination", tuple.get("combination").toString());
//                columns.put("status", tuple.get("status"));
//                return columns;
//            }).map((columns) -> {
//                result_data.add(columns);
//                return columns;
//            }).forEachOrdered((columns) -> {
//                System.out.println("colums" + columns);
//            });
//            result_data_obj = new Gson().toJson(result_data);
////            vehmod_map_result_obj = new Gson().toJson(vehmod_map_result);
////                vehmod_map_result_obj =  Gson().toJSON(vehmod_map_result);
//            System.out.println("oject" + result_data_obj);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            maps.put("status", "Some error occurred !!");
//        }
//            return vehmod_map_result;
//            System.out.println("Result"+vehmod_map_result);
        return "success";
    }

    public String GetLegislationCombinationListing() {
        System.out.println("GetLegislationCombinationListing controller");
        Querybuilder lc = new Querybuilder();
        try {
            tuple_result = SafetyLegDB.GetLegislationCombinationListing();
            tuple_result.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("leg_id", tuple.get("leg_id"));
                columns.put("leg", tuple.get("leg"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
                columns.put("combination", tuple.get("combination").toString());
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
                tuple_result = SafetyLegDB.LoadPreviousSafetyCombinationData(lc);
                tuple_result.stream().map((tuple) -> {
                    Map<String, Object> columns = new HashMap<>();
                    columns.put("querybuilder_name", tuple.get("querybuilder_name"));
                    columns.put("querybuilder_type", tuple.get("querybuilder_type"));
                    columns.put("querybuilder_condition", tuple.get("querybuilder_condition"));
                    columns.put("querybuilder_status", tuple.get("querybuilder_status"));
                    return columns;
                }).map((columns) -> {
                    result_data.add(columns);
                    return columns;
                }).forEachOrdered((columns) -> {
                    System.out.println("colums" + columns);
                });
                System.out.println("safcomb_result" + result_data);
                previousversion_status = String.valueOf(tuple_result.get(0).get("querybuilder_status"));
                System.out.println("previousversion_status" + previousversion_status);

            } else if (previousversion_status.equals("false") && comb_id != 0) {

                System.out.println("Ready to update");
                boolean r_status = readValue.get("qb_status").asBoolean();
                Querybuilder lc = new Querybuilder(comb_id, readValue.get("qb_name").asText(), readValue.get("ctype").asText(), readValue.get("sql").toString(), r_status, new Date(), new Date(), PDBOwnerDB.getUser(1));
                //int result = VehicleversionDB.insertVehicleVersion(v);
                long lc_res = SafetyLegDB.insertSafetyCombination(lc);
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
                long lc_res = SafetyLegDB.insertSafetyCombination(lc);
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

    public Map<String, Object> getMaps_object() {
        return maps_object;
    }

    public void setMaps_object(Map<String, Object> maps_object) {
        this.maps_object = maps_object;
    }

    public Map<String, String> getMaps_string() {
        return maps_string;
    }

    public void setMaps_string(Map<String, String> maps_string) {
        this.maps_string = maps_string;
    }

    public List<Vehicle> getVehicle_result() {
        return vehicle_result;
    }

    public void setVehicleversion_result(List<Vehicle> vehicle_result) {
        this.vehicle_result = vehicle_result;
    }

    public String getResult_data_obj() {
        return result_data_obj;
    }

    public void setResult_data_obj(String result_data_obj) {
        this.result_data_obj = result_data_obj;
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

}
