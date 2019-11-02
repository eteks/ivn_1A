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
import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.FeatureversionDB;
import com.ivn_1A.models.pdbowner.Legislationversion;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Pdbversion;
import com.ivn_1A.models.pdbowner.SafetyLegDB;
import com.ivn_1A.models.pdbowner.Safetyversion;
import com.ivn_1A.models.pdbowner.Vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author ets-poc
 */
public class Featureversion_Group {

    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
    //    Session session = HibernateUtil.getThreadLocalSession();
    private List<Vehicle> vehicleversion_result;
    private List<Tuple> tuple_result = new ArrayList<>();
    private List<Map<String, Object>> result_data = new ArrayList<>();
    Gson gson = new Gson();
    private String result_data_obj;
    private HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);

    public String FeatureversionPage() {

        System.out.println("Entered");
        System.out.println("FeatureversionPage");

//        This will execute if url contains parameter(id and action-edit, view)
        try {
            System.out.println("request" + request);
            System.out.println("id_value" + request.getParameter("id"));
            System.out.println("action_value" + request.getParameter("action"));
            List<Featureversion> featureversionList = FeatureversionDB.loadFeatureversionData(Integer.parseInt(request.getParameter("id")), request.getParameter("action"));
            featureversionList.stream().map((featureversion) -> {

                System.out.println("featureversion " + featureversion.getId());
                if (featureversion.getPdbversion_id() != null) {
                    
                    Map<String, Object> pdb_result = new HashMap<>();
                    System.out.println("featureversion.getPdbversion_id().getId() " + featureversion.getPdbversion_id().getId());
                    pdb_result.put("id", featureversion.getPdbversion_id().getId());
                    pdb_result.put("name", "PDB " + featureversion.getPdbversion_id().getPdb_versionname());
                    pdb_result.put("type", "pdb");
                    result_data.add(pdb_result);
                }
                if (featureversion.getLegislationversion_id() != null) {
                    
                    Map<String, Object> leg_result = new HashMap<>();
                    System.out.println("featureversion.getLegislationversion_id().getId() " + featureversion.getLegislationversion_id().getId());
                    leg_result.put("id", featureversion.getLegislationversion_id().getId());
                    leg_result.put("name", "Legislation " + featureversion.getLegislationversion_id().getLegislation_versionname());
                    leg_result.put("type", "legislation");
                    result_data.add(leg_result);
                }
                if (featureversion.getSafetyversion_id() != null) {
                    
                    Map<String, Object> saf_result = new HashMap<>();
                    System.out.println("featureversion.getSafetyversion_id().getId() " + featureversion.getSafetyversion_id().getId());
                    saf_result.put("id", featureversion.getSafetyversion_id().getId());
                    saf_result.put("name", "Safety " + featureversion.getSafetyversion_id().getSafety_versionname());
                    saf_result.put("type", "safety");
                    result_data.add(saf_result);
                }
                if (featureversion != null) {

                    Map<String, Object> fea_result = new HashMap<>();
                    System.out.println("featureversion.getId() " + featureversion.getId());
                    fea_result.put("id", featureversion.getId());
                    fea_result.put("name", String.format("%.1f", featureversion.getFeature_versionname()));
                    fea_result.put("type", "feature");
                    fea_result.put("status", featureversion.getStatus());
                    result_data.add(fea_result);
                    
                    Map<String, Object> veh_result = new HashMap<>();
                    veh_result.put("id", featureversion.getVehicle_id().getId());
                    veh_result.put("name", featureversion.getVehicle_id().getVehiclename());
                    veh_result.put("type", "vehicle");
                    result_data.add(veh_result);
                }
                return result_data;
            }).forEachOrdered((result_data) -> {
                System.out.println(result_data);
            });
            result_data_obj = new Gson().toJson(result_data);
            maps_string.put("success", "Work is Done");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("error", "Some error occurred !!");
        }
        try {
            vehicleversion_result = PDBOwnerDB.loadVehicleVersion();

//            maps_object.put("removed_features", StringUtils.join(",", pdb_previous_data.get("removed_features")));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String LoadPdbSafetyLeg_version() throws IOException {

        try {

            System.out.println("LoadPdbSafetyLeg_version");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
//        System.out.println("readValue" + readValue);

            Pdbversion pdbversion = FeatureversionDB.GetPdbversionByVehicleId(readValue.get("vehicle_id").asInt());
            if (pdbversion != null) {
                JSONArray pdb_results = new JSONArray();
                JSONObject pdb_result = new JSONObject();
                pdb_result.put("id", pdbversion.getId());
                pdb_result.put("name", "PDB " + pdbversion.getPdb_versionname());
                pdb_result.put("type", "pdb");
                pdb_results.add(pdb_result);
                maps_object.put("pdb_results", pdb_results);
            }

            Legislationversion legislationversion = FeatureversionDB.GetLegversionByVehicleId(readValue.get("vehicle_id").asInt());
            if (legislationversion != null) {
                JSONArray leg_results = new JSONArray();
                JSONObject leg_result = new JSONObject();
                leg_result.put("id", legislationversion.getId());
                leg_result.put("name", "Legislation " + legislationversion.getLegislation_versionname());
                leg_result.put("type", "legislation");
                leg_results.add(leg_result);
                maps_object.put("leg_results", leg_results);
            }

            Safetyversion safetyversion = FeatureversionDB.GetSafetyversionByVehicleId(readValue.get("vehicle_id").asInt());
            if (safetyversion != null) {
                JSONArray saf_results = new JSONArray();
                JSONObject saf_result = new JSONObject();
                saf_result.put("id", safetyversion.getId());
                saf_result.put("name", "Safety " + safetyversion.getSafety_versionname());
                saf_result.put("type", "safety");
                saf_results.add(saf_result);
                maps_object.put("saf_results", saf_results);
            }

            Featureversion featureversion = FeatureversionDB.GetFeatureversionByVehicleId(readValue.get("vehicle_id").asInt());
            if (featureversion != null) {

                JSONArray fea_results = new JSONArray();
                JSONObject fea_result = new JSONObject();
                fea_result.put("id", featureversion.getId());
                fea_result.put("name", String.format("%.1f", featureversion.getFeature_versionname()));
                fea_result.put("type", "feature");
                fea_result.put("status", featureversion.getStatus());
                fea_results.add(fea_result);
                maps_object.put("fea_results", fea_results);
            }

            maps_string.put("success", "Work is Done");
        } catch (Exception e) {
            System.err.println("Error in  \"Featureversion_Group\" \'LoadPdbSafetyLeg_version\' : " + e);
            maps_string.put("error", "Work isn't Done");
        }
        return "success";
    }

    public String CreateFeatureVersion() throws IOException {

        NotificationController notificationController = new NotificationController();
        System.out.println("CreateFeatureVersion");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
        System.out.println("readValue" + readValue);
        boolean status = (boolean) false;
        float version_name = 1.0f;
        String version_type = "new";
        boolean flag;
        int prevfea_id = 0;
        try {
            JsonNode featureversion_value = (JsonNode) readValue.get("featureversion");
            System.out.println("featureversion_value" + featureversion_value);
            String notification_to = readValue.get("notification_to").asText();
            System.out.println("notification_to" + notification_to);
            ArrayNode featuredata_list = (ArrayNode) readValue.get("featuredata_list");
            System.out.println("featuredata_list" + featuredata_list);
            String button_type = readValue.get("button_type").asText();
            System.out.println("button_type" + button_type);
//            String notification_to = (String) json.get("notification_to");
            flag = !button_type.equals("save");
            if (featureversion_value.has("status")) {
                status = featureversion_value.get("status").asBoolean();
            }
            System.out.println("before if");
            if (featureversion_value.has("featureversion") && featureversion_value.get("featureversion").get("status").asBoolean() == false) {
                System.out.println("Ready to update in same version");
            } else {
                System.out.println("Ready to create");
                //Create PDB version               
                Featureversion version_data = FeatureversionDB.GetVersionname();

                if (featureversion_value.has("featureversion")) {
                    System.out.println("enter featureversion");
                    if (featureversion_value.has("version_change")) {
                        System.out.println("enter version change");
                        System.out.println("enter version change1" + featureversion_value.get("version_change").asText());
                        if (featureversion_value.get("version_change").asText().equals("major")) {
                            System.out.println("major");
                            System.out.println("vehicle_id" + featureversion_value.get("vehicle").asInt());
                            Featureversion fea_version_data = FeatureversionDB.GetVersionname(featureversion_value.get("vehicle").asInt(), version_type);
                            version_name = (float) 1.0 + fea_version_data.getFeature_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(version_data.get(0).getPdb_versionname()));
                            prevfea_id = fea_version_data.getId();

                        } //else if minor changes
                        else {
                            version_type = "minor_changes";
                            System.out.println("minor");
                            Featureversion fea_version_data = FeatureversionDB.GetVersionname(featureversion_value.get("vehicle").asInt(), version_type);
                            version_name = (float) 0.1 + fea_version_data.getFeature_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText()));
                            prevfea_id = featureversion_value.get("featureversion").get("id").asInt();
                        }
                        version_data.setFeature_reference_version(Float.valueOf(featureversion_value.get("featureversion").get("name").asText()));
                    }
                }
                System.out.println("version_name" + version_name);
                System.out.println("prevleg_id" + prevfea_id);
                version_data.setFeature_versionname(version_name);
                version_data.setFeature_manual_comment(featureversion_value.get("featureversion_manual_comment").asText());
                version_data.setVehicle_id(PDBOwnerDB.getVehicle(featureversion_value.get("vehicle").asInt()));
                version_data.setPdbversion_id(PDBOwnerDB.getPdbversion(featuredata_list.get(2).get("id").asInt()));
                version_data.setSafetyversion_id(SafetyLegDB.getSafetyversion(featuredata_list.get(0).get("id").asInt()));
                version_data.setLegislationversion_id(SafetyLegDB.getLegislationversion(featuredata_list.get(1).get("id").asInt()));
                version_data.setStatus(status);
                version_data.setFlag(flag);
                version_data.setCreated_date(new Date());
                version_data.setModified_date(new Date());
                version_data.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
                Featureversion curfea_id = FeatureversionDB.insertFeatureVersion(version_data);

                //we can use this code to find comparision of previous id and current id
                if (prevfea_id != 0) {
                    Map<String, Object> leg_previous_data = FeatureversionDB.GetFeaPreviousVersion_LegSafPdb(prevfea_id, curfea_id.getId());
                    System.out.println("fea_previous_data result" + leg_previous_data);

                    JSONObject fea_previous_data_result = new JSONObject();
                    fea_previous_data_result.put("removed_Legislation", leg_previous_data.get("removed_Legislation"));
                    fea_previous_data_result.put("added_Legislation", leg_previous_data.get("added_Legislation"));
                    fea_previous_data_result.put("removed_Safety", leg_previous_data.get("removed_Safety"));
                    fea_previous_data_result.put("added_Safety", leg_previous_data.get("added_Safety"));
                    fea_previous_data_result.put("removed_pdb", leg_previous_data.get("removed_pdb"));
                    fea_previous_data_result.put("added_pdb", leg_previous_data.get("added_pdb"));
                    fea_previous_data_result.put("current_version", String.format("%.1f", curfea_id.getFeature_versionname()));
                    fea_previous_data_result.put("reference_version", featureversion_value.get("featureversion").get("name").asDouble());

                    maps_object.put("fea_previous_data_result", fea_previous_data_result);
                }

                maps_string.put("fea_version", mapper.writeValueAsString(curfea_id));
                if (button_type.equals("save")) {
                    maps_string.put("status", "New Temporary Feature Version Created Successfully");
                } else {
                    notificationController.createNotification(VersionType.Featureversion.getVersionCode(), version_name, new Date().toString(), notification_to, curfea_id.getId());
                    maps_string.put("status", "New Permanent Feature Version Created Successfully");
                }
                maps_string.put("feature", mapper.writeValueAsString(curfea_id));
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

    public String GetFeaturesListing() {
        System.out.println("GetFeaturesListing");
        try {
            tuple_result = FeatureversionDB.GetFeatureversionListing();
            tuple_result.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tuple.get("fea_id"));
                columns.put("feature_versionname", String.format("%.1f", tuple.get("feature_versionname")));
                columns.put("vehiclename", tuple.get("vehiclename"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
//                columns.put("combine", "PDB_"+tuple.get("pdb_versionname")+"\n"+"LEG_"+tuple.get("legislation_versionname")+"\n"+"SAF_"+tuple.get("safety_versionname"));
                columns.put("pdb_versionname", String.format("%.1f", tuple.get("pdb_versionname")));
                columns.put("legislation_versionname", String.format("%.1f", tuple.get("legislation_versionname")));
                columns.put("safety_versionname", String.format("%.1f", tuple.get("safety_versionname")));
                columns.put("flag", tuple.get("flag"));
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
            System.out.println("Error in \"Featureversion_Group\" \'GetFeaturesListing\' : " + ex);
            maps_object.put("status", "Some error occurred !!");
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
