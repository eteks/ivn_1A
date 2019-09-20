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
import com.ivn_1A.configs.HibernateUtil;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.configs.VersionType;
import com.ivn_1A.controllers.notification.NotificationController;

import static com.ivn_1A.controllers.pdbowner.Vehicle_Version_Group.vehicle_Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ivn_1A.models.pdbowner.*;
import com.opensymphony.xwork2.ActionContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
//import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.parser.ParseException;

/**
 * @author ets-poc
 */
public class Pdbversion_Group {

    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
//    Session session = HibernateUtil.getThreadLocalSession();
    private List<Vehicle> vehicleversion_result;
    private List<Tuple> tupleObjects = new ArrayList<>();
    Gson gson = new Gson();
    private String result_data_obj;

    public String PDBAssignPage() {

        System.out.println("Entered");
        System.out.println("PDBAssignPage");

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
                System.out.println("vehver_id"+pdbversion_group.getPdbversion_id().getVehicle_id().getId());
//                vehicleMap.put("vehver_id", pdbversion_group.getVehicle_id().getId());
//                vehicleMap.put("vehiclename", pdbversion_group.getVehicle_id().getVehiclename());
                vehicleMap.put("vehver_id", pdbversion_group.getPdbversion_id().getVehicle_id().getId());
                System.out.println("vehiclename"+pdbversion_group.getPdbversion_id().getVehicle_id().getVehiclename());
                vehicleMap.put("vehiclename", pdbversion_group.getPdbversion_id().getVehicle_id().getVehiclename());
                System.out.println("modelname"+pdbversion_group.getVehiclemodel_id().getModelname());
                vehicleMap.put("modelname", pdbversion_group.getVehiclemodel_id().getModelname());
                System.out.println("pdbversion_group_id"+pdbversion_group.getId());
                vehicleMap.put("pdbversion_group_id", pdbversion_group.getId());
                System.out.println("pdbversion_id"+pdbversion_group.getPdbversion_id().getId());
                vehicleMap.put("pdbversion_id", pdbversion_group.getPdbversion_id().getId());
                System.out.println("pdbversion_name"+pdbversion_group.getPdbversion_id().getPdb_versionname());
                vehicleMap.put("pdbversion_name", pdbversion_group.getPdbversion_id().getPdb_versionname());
                System.out.println("status"+pdbversion_group.getPdbversion_id().getStatus());
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
            List<Domain_and_Features_Mapping> featureslist = PDBOwnerDB.LoadFeaturesList();

            JSONArray featureslist_result = new JSONArray();
            featureslist.stream().map((fea) -> {
                JSONObject fr = new JSONObject();
                fr.put("fid", fea.getId());
                fr.put("fea", fea.getFeature_id().getFeature_name());
                fr.put("domain",fea.getDomain_id().getDomain_name() );
                return fr;
            }).forEachOrdered((fr) -> {
                featureslist_result.add(fr);
            });
            vehicleversion_result = PDBOwnerDB.loadVehicleVersion();
            System.out.println("featureslist_result result" + featureslist_result);

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
            maps_object.put("features", featureslist_result);

//            maps_object.put("removed_features", StringUtils.join(",", pdb_previous_data.get("removed_features")));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
        return "success";
    }

    public String CreatePDBVersion() throws IOException {

        NotificationController notificationController = new NotificationController();
        System.out.println("CreatePDBVersion");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
        System.out.println("readValue" + readValue);
        boolean status = (boolean) false;
//        int pdbversion_id = 0;
        float version_name = 1.0f;
        String version_type = "new";
//        String previousversion_status = null;
//        String previousversion_flag = null;
        boolean flag;
        int prevpdb_id = 0;
        try {
//            Transaction tx = session.beginTransaction();
//            Object obj = parser.parse(jsondata);
//            JSONObject json = (JSONObject) obj;
//            System.out.println("pdbdata" + json);
            JsonNode pdbversion_value = (JsonNode) readValue.get("pdbversion");
            System.out.println("pdbversion_value" + pdbversion_value);
            String notification_to = readValue.get("notification_to").asText();
            System.out.println("notification_to" + notification_to);
            ArrayNode pdbdata_list = (ArrayNode) readValue.get("pdbdata_list");
            System.out.println("pdbdata_list" + pdbdata_list);
            String button_type = readValue.get("button_type").asText();
            System.out.println("button_type" + button_type);
//            String notification_to = (String) json.get("notification_to");

            if (button_type.equals("save")) {
                flag = false;
            } else {
                flag = true;
            }
            if (pdbversion_value.has("status")) {
                status = pdbversion_value.get("status").asBoolean();
            }
            System.out.println("before if");
            if (pdbversion_value.has("pdbversion") && pdbversion_value.get("pdbversion").get("status").asBoolean() == false) {
                System.out.println("Ready to update in same version");
            } else {
                System.out.println("Ready to create");
                //Create PDB version               
//                List<Pdbversion> version_data = PDBOwnerDB.GetVersionname();
                Pdbversion pdbversion = new Pdbversion();
//                if(!version_data.isEmpty() && !pdbversion_value.containsKey("pdbversion_id")){
                if (pdbversion_value.has("pdbversion")) {
                    System.out.println("enter pdbversion");
                    if (pdbversion_value.has("version_change")) {
                        System.out.println("enter version change");
                        System.out.println("enter version change1"+pdbversion_value.get("version_change").asText());
                        if(pdbversion_value.get("version_change").asText().equals("major")){
                            System.out.println("major");
                            System.out.println("vehicle_id"+pdbversion_value.get("vehicle_id").asInt());
                            List<Pdbversion> version_data = PDBOwnerDB.GetVersionname(pdbversion_value.get("vehicle_id").asInt(),version_type);
                            version_name = (float) 1.0 + version_data.get(0).getPdb_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(version_data.get(0).getPdb_versionname()));
                            prevpdb_id = version_data.get(0).getId();
                            
                        }
                        //else if minor changes
                        else{
                            version_type = "minor_changes";
                            System.out.println("minor");
                            List<Pdbversion> version_data = PDBOwnerDB.GetVersionname(pdbversion_value.get("vehicle_id").asInt(),version_type);
                            version_name = (float) 0.1 + version_data.get(0).getPdb_versionname();
//                            pdbversion.setPdb_reference_version(Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText()));
                            prevpdb_id = pdbversion_value.get("pdbversion").get("pdbid").asInt();                           
                        }
                        pdbversion.setPdb_reference_version(Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText()));
                    }
                }                
                System.out.println("version_name"+version_name);
                System.out.println("prevpdb_id"+prevpdb_id);
                //To find and store removed id's and new feature'ids 
//                List pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(Integer.parseInt((String) pdbversion_value.get("pdbversion_id")));
//                System.out.println("pdb_previous_data" + pdb_previous_data);
//                ArrayNode dfm_set = (ArrayNode) readValue.get("dfm_set");
                pdbversion.setVehicle_id(PDBOwnerDB.getVehicle(pdbversion_value.get("vehicle_id").asInt()));
                pdbversion.setPdb_versionname(version_name);
                pdbversion.setPdb_manual_comment(pdbversion_value.get("pdb_manual_comment").asText());
                pdbversion.setVersion_type(version_type);
                pdbversion.setStatus(status);
                pdbversion.setFlag(flag);
                pdbversion.setCreated_date(new Date());
                pdbversion.setModified_date(new Date());
                pdbversion.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
                Pdbversion curpdb_id = PDBOwnerDB.insertPDBVersion(pdbversion);
                //Insert data into PDB Version Group
                int i = 0;
                for (Object o : pdbdata_list) {
                    JsonNode pdbdata = (JsonNode) o;
                    System.out.println("pdbdata" + pdbdata);
                    Pdbversion_group pvg = new Pdbversion_group();
                    pvg.setPdbversion_id(curpdb_id);
//                    pvg.setVehicle_id((Vehicle) session.get(Vehicle.class, pdbversion_value.get("vehicle_id").asInt()));
//                    pvg.setVehiclemodel_id((Vehiclemodel) session.get(Vehiclemodel.class, pdbdata.get("model_id").asInt()));
//                    pvg.setDomain_and_features_mapping_id((Domain_and_Features_Mapping) session.get(Domain_and_Features_Mapping.class, pdbdata.get("dfm_id").asInt()));

//                    pvg.setVehicle_id(PDBOwnerDB.getVehicle(pdbversion_value.get("vehicle_id").asInt()));
                    pvg.setVehiclemodel_id(PDBOwnerDB.getVehiclemodel(pdbdata.get("model_id").asInt()));
                    pvg.setDomain_and_features_mapping_id(PDBOwnerDB.getDomain_and_Features_Mapping(pdbdata.get("dfm_id").asInt()));

                    pvg.setAvailable_status(pdbdata.get("status").asText());
                    Pdbversion_group pvg_id = PDBOwnerDB.insertPDBVersionGroup(pvg);
                }
                if (prevpdb_id != 0) {
                    Map<String, Object> pdb_previous_data = PDBOwnerDB.GetPDBPreviousVersion_DomFea(prevpdb_id, curpdb_id.getId());
                    System.out.println("pdb_previous_data result" + pdb_previous_data);

                    JSONObject pdb_previous_data_result = new JSONObject();
                    pdb_previous_data_result.put("removed_features", pdb_previous_data.get("removed_features"));
                    pdb_previous_data_result.put("added_features", pdb_previous_data.get("added_features"));
                    pdb_previous_data_result.put("removed_models", pdb_previous_data.get("removed_models"));
                    pdb_previous_data_result.put("added_models", pdb_previous_data.get("added_models"));
                    pdb_previous_data_result.put("current_version", String.format("%.1f", curpdb_id.getPdb_versionname()));
                    pdb_previous_data_result.put("reference_version", pdbversion_value.get("pdbversion").get("pdbversion_name").asDouble());

                    maps_object.put("pdb_previous_data_result", pdb_previous_data_result);
                }
                if (button_type.equals("save")) {
                    maps_string.put("status", "New Temporary PDB Version Created Successfully");
                } else {
                    notificationController.createNotification(VersionType.Pdbversion.getVersionCode(), curpdb_id.getPdb_versionname(), new Date().toString(), notification_to);
                    maps_string.put("status", "New Permanent PDB Version Created Successfully");
                }
            }
            maps_string.put("status_code", "1");
            
//            //Old code
////            if (pdbversion_value.has("pdbversion") && status == false) {
//            if (pdbversion_value.has("pdbversion") && pdbversion_value.get("pdbversion").get("status").asBoolean() == false) {
//                System.out.println("Ready to update in same version");
//            } else {
//                System.out.println("Ready to create");
//                //Create PDB version               
//                List<Pdbversion> version_data = PDBOwnerDB.GetVersionname();
//                Pdbversion pdbversion = new Pdbversion();
////                if(!version_data.isEmpty() && !pdbversion_value.containsKey("pdbversion_id")){
//                if (!version_data.isEmpty()) {
//                    if (!pdbversion_value.has("pdbversion")) {
//                        version_name = (float) 1.0 + version_data.get(0).getPdb_versionname();
//                    } else {
//                        version_name = (float) 0.1 + Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText());
//                        pdbversion.setPdb_reference_version(Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText()));
//                        prevpdb_id = pdbversion_value.get("pdbversion").get("pdbid").asInt();
//                    }
//                }
//                //To find and store removed id's and new feature'ids 
////                List pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(Integer.parseInt((String) pdbversion_value.get("pdbversion_id")));
////                System.out.println("pdb_previous_data" + pdb_previous_data);
////                ArrayNode dfm_set = (ArrayNode) readValue.get("dfm_set");
//                pdbversion.setVehicle_id(PDBOwnerDB.getVehicle(pdbversion_value.get("vehicle_id").asInt()));
//                pdbversion.setPdb_versionname(version_name);
//                pdbversion.setPdb_manual_comment(pdbversion_value.get("pdb_manual_comment").asText());
//                pdbversion.setStatus(status);
//                pdbversion.setFlag(flag);
//                pdbversion.setCreated_date(new Date());
//                pdbversion.setModified_date(new Date());
//                pdbversion.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
//                Pdbversion curpdb_id = PDBOwnerDB.insertPDBVersion(pdbversion);
//                //Insert data into PDB Version Group
//                int i = 0;
//                for (Object o : pdbdata_list) {
//                    JsonNode pdbdata = (JsonNode) o;
//                    System.out.println("pdbdata" + pdbdata);
//                    Pdbversion_group pvg = new Pdbversion_group();
//                    pvg.setPdbversion_id(curpdb_id);
////                    pvg.setVehicle_id((Vehicle) session.get(Vehicle.class, pdbversion_value.get("vehicle_id").asInt()));
////                    pvg.setVehiclemodel_id((Vehiclemodel) session.get(Vehiclemodel.class, pdbdata.get("model_id").asInt()));
////                    pvg.setDomain_and_features_mapping_id((Domain_and_Features_Mapping) session.get(Domain_and_Features_Mapping.class, pdbdata.get("dfm_id").asInt()));
//
////                    pvg.setVehicle_id(PDBOwnerDB.getVehicle(pdbversion_value.get("vehicle_id").asInt()));
//                    pvg.setVehiclemodel_id(PDBOwnerDB.getVehiclemodel(pdbdata.get("model_id").asInt()));
//                    pvg.setDomain_and_features_mapping_id(PDBOwnerDB.getDomain_and_Features_Mapping(pdbdata.get("dfm_id").asInt()));
//
//                    pvg.setAvailable_status(pdbdata.get("status").asText());
//                    Pdbversion_group pvg_id = PDBOwnerDB.insertPDBVersionGroup(pvg);
//                }
//                if (prevpdb_id != 0) {
//                    Map<String, Object> pdb_previous_data = PDBOwnerDB.GetPDBPreviousVersion_DomFea(prevpdb_id, curpdb_id.getId());
//                    System.out.println("pdb_previous_data result" + pdb_previous_data);
//
//                    JSONObject pdb_previous_data_result = new JSONObject();
//                    pdb_previous_data_result.put("removed_features", pdb_previous_data.get("removed_features"));
//                    pdb_previous_data_result.put("added_features", pdb_previous_data.get("added_features"));
//                    pdb_previous_data_result.put("removed_models", pdb_previous_data.get("removed_models"));
//                    pdb_previous_data_result.put("added_models", pdb_previous_data.get("added_models"));
//                    pdb_previous_data_result.put("current_version", String.format("%.1f", curpdb_id.getPdb_versionname()));
//                    pdb_previous_data_result.put("reference_version", pdbversion_value.get("pdbversion").get("pdbversion_name").asDouble());
//
//                    maps_object.put("pdb_previous_data_result", pdb_previous_data_result);
//                }
//                if (button_type.equals("save")) {
//                    maps_string.put("status", "New Temporary PDB Version Created Successfully");
//                } else {
//                    notificationController.createNotification(VersionType.Pdbversion.getVersionCode(), curpdb_id.getPdb_versionname(), new Date().toString(), notification_to);
//                    maps_string.put("status", "New Permanent PDB Version Created Successfully");
//                }
//            }
////            tx.commit();
////            session.clear();
////            maps_string.put("status", "Process Done");
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
            maps_string.put("status_code", "0");
        }
        return "success";
    }

    public String LoadPdbversionData() {
        try {
            System.out.println("LoadPdbversionData");

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            System.out.println("LoadPdbversionData1");
            int vehicle_id = readValue.get("vehicle_id").asInt();
            System.out.println("LoadPdbversionData2");
            String action = readValue.get("action").asText();
            System.out.println(vehicle_id+"$$$$$$$$$$$$$$$$$$$$$$$$44"+action);

            tupleObjects = PDBOwnerDB.loadPdbversion_groupByVehicleId(vehicle_id, action);
            JSONArray pdbvers_group_result = new JSONArray();

            tupleObjects.stream().map((tuple) -> {
                System.err.println("*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*****************" + tuple.get("pid") + " " + tuple.get("pversion"));
                return tuple;
            }).map((tuple) -> {
                JSONObject fr = new JSONObject();
                fr.put("pid", tuple.get("pid"));
                fr.put("pversion", tuple.get("pversion"));
                fr.put("status", tuple.get("status"));
                return fr;
            }).map((fr) -> {
                pdbvers_group_result.add(fr);
                return fr;
            }).forEachOrdered((fr) -> {
                System.out.println("JSON ARRAY : " + fr);
            });
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            String pdbvers_group_result = mapper.writeValueAsString(pdbversion_group_result);
            maps_object.put("pdbversion", pdbvers_group_result);
            System.out.println(pdbvers_group_result);
        } catch (Exception e) {
            maps_object.put("status", e);
            System.out.println("Error : " + e);
        }
        return "success";
    }

    public String loadVehicleAndModelNames() {
        try {
            System.out.println("loadVehicleAndModelNames");

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            int vehver_id = readValue.get("pdb_id").asInt();
            List<Map<String, Object>> domainfeatures_result = new ArrayList<>();
            System.out.println(vehver_id);
            tupleObjects = PDBOwnerDB.loadVehicleAndModelByVehicleId(vehver_id);
            Map<String, Object> m = new HashMap<>();
            tupleObjects.stream().map((tuple) -> {
                m.put("versionname", tuple.get("versionname"));
                return tuple;
            }).map((tuple) -> {
                m.put("status", tuple.get("status"));
                return tuple;
            }).map((tuple) -> {
                m.put("vehicle_id", tuple.get("vehicle_id"));
                return tuple;
            }).map((tuple) -> {
                m.put("vehiclename", tuple.get("vehiclename"));
                return tuple;
            }).map((tuple) -> {
                m.put("modelid", tuple.get("modelid"));
                return tuple;
            }).map((tuple) -> {
                m.put("modelname", tuple.get("modelname"));
                return tuple;
            }).map((_item) -> {
                System.out.println("JSON ARRAY : " + m);
                return _item;
            }).forEachOrdered((_item) -> {
                domainfeatures_result.add(m);
            });
            maps_object.put("pdbversion", domainfeatures_result);
            System.out.println(domainfeatures_result);
        } catch (Exception e) {
            maps_object.put("status", e);
            System.out.println("Error : " + e);
        }
        return "success";
    }

    public String CreateDomain_and_Features() {

        try {

            System.out.println("CreateDomain_and_Features");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            List<Map<String, Object>> domainfeatures_result = new ArrayList<>();

            String domain_name = readValue.get("domain_name").asText();
            ArrayNode features_and_description = (ArrayNode) readValue.get("features_and_description");
            System.out.println("vehiclename" + domain_name);
            System.out.println("vehicle_and_model_value" + features_and_description);

            Domain domainId = PDBOwnerDB.getDomainByName(domain_name);
            if (domainId == null) {
                domainId = PDBOwnerDB.saveDomain(new Domain(domain_name, false, new Date(), new Date(), PDBOwnerDB.getUser(1)));
            }
            List<Map<String, Object>> row = new ArrayList<>();

            //Insert Data in Features table
            for (Object o : features_and_description) {

                Map<String, Object> columns = new HashMap<>();
                JsonNode jn = (JsonNode) o;
                String feature_name = jn.get("feature").asText();
                String feature_description = jn.get("description").asText();

                Features features = new Features(feature_name, feature_description, "Electrical", false, new Date(), new Date(), PDBOwnerDB.getUser(1));
                Features featureId = PDBOwnerDB.saveFeatures(features);

                Domain_and_Features_Mapping domain_and_Features_Mapping = new Domain_and_Features_Mapping(domainId, featureId);
                Domain_and_Features_Mapping domain_and_Features_MappingId = PDBOwnerDB.saveDomain_and_Features_Mapping(domain_and_Features_Mapping);
                columns.put("domain", domainId.getDomain_name());
                columns.put("fid", featureId.getId());
                columns.put("fea", featureId.getFeature_name());
                columns.put("fid", featureId.getId());
                columns.put("did", domain_and_Features_MappingId.getId());
                domainfeatures_result.add(columns);
                row.add(columns);
            }
            maps_string.put("status", "Success");
            maps_object.put("domainfeatures_result", domainfeatures_result);
            System.out.println("domainfeatures_result" + domainfeatures_result);
        } catch (Exception e) {
            System.out.println("Error : " + e);
            maps_string.put("status", "Error");
        }
        return "success";
    }

    public String checkVehicleAndModel() {

        try {

            System.out.println("checkVehicleAndModel");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            Map<String, Object> vehicleAndModel = new HashMap<>();

            String vehiclename = readValue.get("vehiclename").asText();
            ArrayNode models = (ArrayNode) readValue.get("models");

            Vehicle vehicleId = PDBOwnerDB.getVehicleByName(vehiclename);
            if (vehicleId == null) {
                vehicleId = PDBOwnerDB.saveVehicles(new Vehicle(vehiclename, true, new Date(), new Date(), PDBOwnerDB.getUser(1)));
            }
            List<Map<String, Object>> row = new ArrayList<>();
            vehicleAndModel.put("vehicle_id", vehicleId.getId());
            vehicleAndModel.put("vehiclename", vehicleId.getVehiclename());
            for (Object o : models) {

                Map<String, Object> column = new HashMap<>();
                JsonNode jn = (JsonNode) o;
                String modelString = jn.get("modelname").asText();

                Vehiclemodel vehiclemodel = new Vehiclemodel(modelString, true, new Date(), new Date(), PDBOwnerDB.getUser(1));
                Vehiclemodel vehiclemodelId = PDBOwnerDB.saveVehicleModel(vehiclemodel);

                column.put("model_id", vehiclemodelId.getId());
                column.put("modelname", vehiclemodelId.getModelname());
                column.put("status", vehiclemodelId.getStatus());

//                domainfeatures_result.add(column2);
                row.add(column);

            }
            maps_string.put("status", "Process Done");
            maps_object.put("vehicleAndModel", vehicleAndModel);
            vehicleAndModel.put("models", row);
            System.out.println("vehicleAndModel" + vehicleAndModel);
        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }
        return "success";
    }

    public String GetVehicleModel_Listing() {

        try {
            tupleObjects = PDBOwnerDB.GetVehicleModel_Listing();
            List<Map<String, Object>> row = new ArrayList<>();
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("modelname", tuple.get("modelname"));
                columns.put("status", tuple.get("status"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
                columns.put("vehiclename", tuple.get("vehiclename"));
                columns.put("versionname", tuple.get("versionname"));
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            maps_string.put("status", "Listed Done");
            maps_object.put("domainfeatures_result", row);
            System.out.println("Json Values : " + row);

        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }

        return "success";
    }

    public String GetVehicle_Listing() {

        try {
            tupleObjects = PDBOwnerDB.getVehicle_Listing();
            List<Map<String, Object>> row = new ArrayList<>();
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("pdb_version_id", tuple.get("pdb_version_id"));
                columns.put("vehiclename", tuple.get("vehiclename"));
                columns.put("status", tuple.get("status"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
                columns.put("pdb_version_name", tuple.get("pdb_version_name"));
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            maps_string.put("status", "Listed Done");
            maps_object.put("domainfeatures_result", row);
            System.out.println("Json Values : " + row);

        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }

        return "success";
    }

    public String GetPDBVersion_Listing() {

        System.out.println("GetVehicleVersion_Listing");
        try {
            ObjectMapper mapper = new ObjectMapper();
            tupleObjects = PDBOwnerDB.GetPDBVersion_Listing();

            List<Map<String, Object>> row = new ArrayList<>();
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tuple.get("pdb_id"));
                columns.put("pdb_version", String.format("%.1f", tuple.get("pdb_versionname")));
//                columns.put("pdb_version", BigDecimal.valueOf((float)tuple.get("pdb_versionname")).setScale(1, RoundingMode.HALF_UP));
                columns.put("vehicle_id", tuple.get("vehicle_id"));
                columns.put("vehicle", tuple.get("vehiclename"));
                columns.put("model", tuple.get("modelname"));
                columns.put("status", tuple.get("status"));
                columns.put("flag", tuple.get("flag"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            maps_string.put("status", "Listed Done");
            result_data_obj = gson.toJson(row);
            maps_object.put("pdb_version_list", mapper.writeValueAsString(row));
            System.out.println("Json Values : " + row);

        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }

        return "success";
    }

    public String GetDomainFeaturesListing() {

        System.out.println("GetFeaturesListing controller");
        try {
            ObjectMapper mapper = new ObjectMapper();
            tupleObjects = PDBOwnerDB.GetDomainFeaturesListing();

            List<Map<String, Object>> row = new ArrayList<>();
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("dfm_id", tuple.get("dfm_id"));
                columns.put("domain", tuple.get("domain_name"));
                columns.put("fid", tuple.get("fid"));
                columns.put("fea", tuple.get("feature_name"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            maps_string.put("status", "Listed Done");            
            result_data_obj = gson.toJson(row);
            maps_object.put("domainfeatures_result", mapper.writeValueAsString(row));
            System.out.println("Json Values : " + row);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !! " + ex);
        }
        return "success";
    }

    public String GetDomainFeaturesListing1() {

        System.out.println("GetFeaturesListing controller");
        try {
            tupleObjects = PDBOwnerDB.GetDomainFeaturesListing1();

            List<Map<String, Object>> row = new ArrayList<>();
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("dfm_id", tuple.get("dfm_id"));
                columns.put("domain_name", tuple.get("domain_name"));
                columns.put("feature_name", tuple.get("feature_name"));
                columns.put("created_date", tuple.get("created_date"));
                columns.put("modified_date", tuple.get("modified_date"));
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            maps_string.put("status", "Listed Done");
            maps_object.put("domainfeatures_result", row);
            System.out.println("Json Values : " + row);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !! " + ex);
        }
        return "success";
    }

    public String verifyVehicles() {

        System.out.println("verifyVehicles controller");
        try {

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            String vehicleName = readValue.get("vehiclename").asText();

            Vehicle vehicle = PDBOwnerDB.getVehicleByName(vehicleName);
            if (vehicle == null) {
                maps_string.put("success", "No Vehicle Found You can Continue");
            } else {
                maps_string.put("failed", "Your Entered Vehicle Already Exisit");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("error", "You can Continue");
        }
        return "success";
    }

    public String validateDomain() {

        System.out.println("validateDomain controller");
        try {

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            String domainname = readValue.get("domainname").asText();

            Domain domain = PDBOwnerDB.getDomainByName(domainname);
            if (domain == null) {
                maps_string.put("status", "No Domain Found You can Continue");
                maps_string.put("res", "success");
            } else {
                maps_string.put("status", "Your Entered Domain Already Exisit");
                maps_string.put("res", "failed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "You can Continue");
            maps_string.put("res", "error");
        }
        return "success";
    }

    public String LoadPDBDomainFeatures() throws ParseException, IOException {
        System.out.println("LoadPDBDomainFeatures controller");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);

        try {
            List<Tuple> pdb_map_result = PDBOwnerDB.LoadPDBDomainFeatures(readValue.get("pdbversion_id").asInt());
            System.out.println("pdb_map_result" + pdb_map_result);
            JSONArray pdb_result = new JSONArray();
            pdb_map_result.stream().map((pdb) -> {
                JSONObject res = new JSONObject();
                res.put("model_id", pdb.get("model_id"));
                res.put("fid", pdb.get("fid"));
                res.put("status", pdb.get("status"));
                res.put("domainname", pdb.get("domainname"));
                res.put("featurename", pdb.get("featurename"));
                res.put("pdbstatus", pdb.get("pdbstatus"));
                return res;
            }).forEachOrdered((res) -> {
                pdb_result.add(res);
            });
            System.out.println("pdb_result" + pdb_result);
//            JSONArray pdb_result = new JSONArray();
//            for (Pdbversion_group pdb : pdb_map_result) {
//                JSONObject res = new JSONObject();
//                res.put("vm_id", pdb.getVehiclemodel_id().getId());
//                res.put("fid", pdb.getDomain_and_features_mapping_id().getId());
//                res.put("status", pdb.getAvailable_status());
//                res.put("domainname", pdb.getDomain_and_features_mapping_id().getDomain_id().getDomain_name());
//                res.put("featurename", pdb.getDomain_and_features_mapping_id().getFeature_id().getFeature_name());
//                pdb_result.add(res);
//            }
            maps_object.put("pdb_map_result", pdb_result);
//            String pdb_results = gson.toJson(pdb_map_result);
//            System.out.println("pdb_results"+pdb_results);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
//            return vehmod_map_result;
//            System.out.println("Result"+vehmod_map_result);
        return "success";
    }

    public String GetFeatures_Listing() {

        try {
            
            List<Features> featureses = PDBOwnerDB.getFeatures();
            List<Map<String, Object>> row = new ArrayList<>();
            featureses.stream().map((features) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("fid", features.getId());
                columns.put("feature_name", features.getFeature_name());
                columns.put("created_date", features.getCreated_date());
                columns.put("modified_date", features.getModified_date());
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });            
            maps_object.put("result_data_obj", row);
//            result_data_obj = new Gson().toJson(row);
            System.err.println("result_data_obj " + row);
        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
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
