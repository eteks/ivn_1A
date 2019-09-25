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
import com.ivn_1A.controllers.notification.NotificationController;
import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.FeatureversionDB;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.SafetyLegDB;
import com.ivn_1A.models.pdbowner.Vehicle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
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
    private List<Tuple> tuple_result = new ArrayList<>();
    private List<Map<String, Object>> result_data = new ArrayList<>();
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

    public String LoadPdbSafetyLeg_version() throws IOException {
        System.out.println("LoadPdbSafetyLeg_version");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
//        System.out.println("readValue" + readValue);

        List<Tuple> pdb_result_data = FeatureversionDB.GetPdbversion(readValue.get("vehicle_id").asInt());
        System.out.println("pdb_result_data" + pdb_result_data);
        JSONArray pdb_results = new JSONArray();
        pdb_result_data.stream().map((ver) -> {
            JSONObject vr = new JSONObject();
            vr.put("id", ver.get("pid"));
            vr.put("name", "PDB " + ver.get("name"));
            vr.put("type", "pdb");
            return vr;
        }).forEachOrdered((vr) -> {
            pdb_results.add(vr);
        });
        maps_object.put("pdb_results", pdb_results);

        List<Tuple> leg_result_data = FeatureversionDB.GetLegversion(readValue.get("vehicle_id").asInt());
        System.out.println("pdb_result_data" + leg_result_data);
        JSONArray leg_results = new JSONArray();
        leg_result_data.stream().map((ver) -> {
            JSONObject vr = new JSONObject();
            vr.put("id", ver.get("lid"));
            vr.put("name", "Legislation " + ver.get("name"));
            vr.put("type", "legislation");
            return vr;
        }).forEachOrdered((vr) -> {
            leg_results.add(vr);
        });
        maps_object.put("leg_results", leg_results);

        List<Tuple> saf_result_data = FeatureversionDB.GetSafetyversion(readValue.get("vehicle_id").asInt());
        System.out.println("saf_result_data" + saf_result_data);
        JSONArray saf_results = new JSONArray();
        saf_result_data.stream().map((ver) -> {
            JSONObject vr = new JSONObject();
            vr.put("id", ver.get("sid"));
            vr.put("name", "Safety " + ver.get("name"));
            vr.put("type", "safety");
            return vr;
        }).forEachOrdered((vr) -> {
            saf_results.add(vr);
        });
        maps_object.put("saf_results", saf_results);

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

            if (button_type.equals("save")) {
                flag = false;
            } else {
                flag = true;
            }
            if (featureversion_value.has("status")) {
                status = featureversion_value.get("status").asBoolean();
            }
            System.out.println("before if");
            if (featureversion_value.has("legislationversion") && featureversion_value.get("legislationversion").get("status").asBoolean() == false) {
                System.out.println("Ready to update in same version");
            } else {
                System.out.println("Ready to create");
                //Create PDB version               
                List<Featureversion> version_data = FeatureversionDB.GetVersionname();
                if (!version_data.isEmpty()) {
                    version_name = (float) 1.0 + version_data.get(0).getFeature_versionname();
                }
                Featureversion featureversion = new Featureversion();
                featureversion.setFeature_versionname(version_name);
                featureversion.setFeature_manual_comment(featureversion_value.get("featureversion_manual_comment").asText());
                featureversion.setVehicle_id(PDBOwnerDB.getVehicle(featureversion_value.get("vehicle").asInt()));
                featureversion.setPdbversion_id(PDBOwnerDB.getPdbversion(featuredata_list.get(2).get("id").asInt()));
                featureversion.setSafetyversion_id(SafetyLegDB.getSafetyversion(featuredata_list.get(0).get("id").asInt()));
                featureversion.setLegislationversion_id(SafetyLegDB.getLegislationversion(featuredata_list.get(1).get("id").asInt()));
                featureversion.setStatus(status);
                featureversion.setFlag(flag);
                featureversion.setCreated_date(new Date());
                featureversion.setModified_date(new Date());
                featureversion.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
                Featureversion curfea_id = FeatureversionDB.insertFeatureVersion(featureversion);
                if (button_type.equals("save")) {
                    maps_string.put("status", "New Temporary Feature Version Created Successfully");
                } else {
//                    notificationController.createNotification(VersionType.Pdbversion.getVersionCode(), curleg_id.getLegislation_versionname(), new Date().toString(), notification_to);
                    maps_string.put("status", "New Permanent Feature Version Created Successfully");
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

    public String GetFeaturesListing() {
        System.out.println("GetFeaturesListing");
        try {
            tuple_result = FeatureversionDB.GetFeatureversionListing();
            tuple_result.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
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
            System.out.println("Error in \"Featureversion_Group\" \'GetFeaturesListing\' : "+ ex);
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
