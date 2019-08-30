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

import static com.ivn_1A.controllers.pdbowner.Vehicle_Version_Group.vehicle_Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ivn_1A.models.pdbowner.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.parser.ParseException;

/**
 * @author ets-poc
 */
public class Pdbversion_Group {

    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
    Session session = HibernateUtil.getThreadLocalSession();
    private List<Vehicle> vehicleversion_result;
    private String resultValues;
    private List<Pdbversion_group> pdbversion_group_result = new ArrayList<>();
    private List<Object[]> listObjects = new ArrayList<>();
    private List<Map<String, Object>> domainfeatures_result = new ArrayList<>();
    private HashMap<String, Object> domainfeatures_result1 = new HashMap<>();
    Gson gson = new Gson();

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
            List<Domain_and_Features_Mapping> featureslist = PDBOwnerDB.LoadFeaturesList();

            JSONArray featureslist_result = new JSONArray();
            for (Domain_and_Features_Mapping fea : featureslist) {
                JSONObject fr = new JSONObject();
                fr.put("fid", fea.getId());
                fr.put("fea", fea.getDomain_id().getDomain_name());
                fr.put("domain", fea.getFeature_id().getFeature_name());
                featureslist_result.add(fr);
            }
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

        System.out.println("CreatePDBVersion");
        final ObjectMapper mapper = new ObjectMapper();
        String jsonValues = JSONConfigure.getAngularJSONFile();
        final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
        System.out.println("readValue" + readValue);
        boolean status = (boolean) false;
//        int pdbversion_id = 0;
        float version_name = 1.0f;
//        String previousversion_status = null;
//        String previousversion_flag = null;
        boolean flag;
        int prevpdb_id = 0;
        try {
//            Object obj = parser.parse(jsondata);
//            JSONObject json = (JSONObject) obj;
//            System.out.println("pdbdata" + json);
            JsonNode pdbversion_value = (JsonNode) readValue.get("pdbversion");
            System.out.println("pdbversion_value" + pdbversion_value);
            ArrayNode pdbdata_list = (ArrayNode) readValue.get("pdbdata_list");
            System.out.println("pdbdata_list" + pdbdata_list);
            String button_type = readValue.get("button_type").asText();
//            String notification_to = (String) json.get("notification_to");

            if (button_type.equals("save")) {
                flag = false;
            } else {
                flag = true;
            }
            if (pdbversion_value.has("status")) {
                status = pdbversion_value.get("status").asBoolean();
            }

            if (pdbversion_value.has("pdbversion") && status == false) {
                System.out.println("Ready to update in same version");
            } else {
                System.out.println("Ready to create");
                //Create PDB version               
                List<Pdbversion> version_data = PDBOwnerDB.GetVersionname();
                Pdbversion pdbversion = new Pdbversion();
//                if(!version_data.isEmpty() && !pdbversion_value.containsKey("pdbversion_id")){
                if (!version_data.isEmpty()) {
                    if (!pdbversion_value.has("pdbversion")) {
                        version_name = (float) 1.0 + version_data.get(0).getPdb_versionname();
                    } else {
                        version_name = (float) 0.1 + Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText());
                        pdbversion.setPdb_reference_version(Float.valueOf(pdbversion_value.get("pdbversion").get("pdbversion_name").asText()));
                        prevpdb_id = pdbversion_value.get("pdbversion").get("pdbid").asInt();
                    }
                }
                //To find and store removed id's and new feature'ids 
//                List pdb_previous_data = pdbownerdb.GetPDBPreviousVersion_DomFea(Integer.parseInt((String) pdbversion_value.get("pdbversion_id")));
//                System.out.println("pdb_previous_data" + pdb_previous_data);
                ArrayNode dfm_set = (ArrayNode) readValue.get("dfm_set");

                pdbversion.setPdb_versionname(version_name);
                pdbversion.setPdb_manual_comment(pdbversion_value.get("pdb_manual_comment").asText());
                pdbversion.setStatus(status);
                pdbversion.setFlag(flag);
                pdbversion.setCreated_date(new Date());
                pdbversion.setModified_date(new Date());
                pdbversion.setCreated_or_updated_by(vehicle_Repository.getUser(1));
                Pdbversion curpdb_id = PDBOwnerDB.insertPDBVersion(pdbversion);
                //Insert data into PDB Version Group
                int i = 0;
                for (Object o : pdbdata_list) {
                    JsonNode pdbdata = (JsonNode) o;
                    System.out.println("pdbdata" + pdbdata);
                    Pdbversion_group pvg = new Pdbversion_group();
                    pvg.setPdbversion_id(curpdb_id);
                    pvg.setVehicle_id((Vehicle) session.get(Vehicle.class, pdbversion_value.get("vehicle_id").asInt()));
                    pvg.setVehiclemodel_id((Vehiclemodel) session.get(Vehiclemodel.class, pdbdata.get("model_id").asInt()));
                    pvg.setDomain_and_features_mapping_id((Domain_and_Features_Mapping) session.get(Domain_and_Features_Mapping.class, pdbdata.get("dfm_id").asInt()));
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
                    pdb_previous_data_result.put("current_version", "1.1");
                    pdb_previous_data_result.put("previous_models", "1.0");

                    maps_object.put("pdb_previous_data_result", pdb_previous_data_result);
                }
            }
            maps_string.put("status", "Process Done");
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

            List<Object[]> pdbversion_group_result = (List<Object[]>) PDBOwnerDB.loadPdbversion_groupByVehicleId(vehver_id);
            JSONArray pdbvers_group_result = new JSONArray();

            for (Object[] fea : pdbversion_group_result) {

                System.err.println(fea[0] + " " + fea[1]);
                JSONObject fr = new JSONObject();
                fr.put("pid", fea[0]);
                fr.put("pversion", fea[1]);
                pdbvers_group_result.add(fr);
                System.out.println("JSON ARRAY : " + fr);
            }
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
            System.out.println(vehver_id);
            List<Object[]> reObjects = (List<Object[]>) PDBOwnerDB.loadVehicleAndModelByVehicleId(vehver_id);
            Map<String, Object> m = new HashMap<>();
//            JSONArray pdbvers_group_result = new JSONArray();
//            for (Object[] reObject : reObjects) {
//
//                JSONObject fr = new JSONObject();
//                fr.put("versionname", reObject[0]);
//                fr.put("status", reObject[1]);
//                fr.put("vehicle_id", reObject[2]);
//                fr.put("vehiclename", reObject[3]);
//                fr.put("modelid", reObject[4]);
//                fr.put("modelname", reObject[4]);
//                System.out.println("JSON ARRAY : " + fr);
//                pdbvers_group_result.add(fr);
//            }
            for (Object[] reObject : reObjects) {

                m.put("versionname", reObject[0]);
                m.put("status", reObject[1]);
                m.put("vehicle_id", reObject[2]);
                m.put("vehiclename", reObject[3]);
                m.put("modelid", reObject[4]);
                m.put("modelname", reObject[5]);
                System.out.println("JSON ARRAY : " + m);
                domainfeatures_result.add(m);
            }
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
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            boolean status = (boolean) false;
            int vehicleversion_id = 0;
            String previousversion_status = null;

            String domain_name = readValue.get("domain_name").asText();
            ArrayNode features_and_description = (ArrayNode) readValue.get("features_and_description");
            System.out.println("vehiclename" + domain_name);
            System.out.println("vehicle_and_model_value" + features_and_description);

            Domain domain = new Domain(domain_name, false, new Date(), new Date(), PDBOwnerDB.getUser(1));
            Domain domainId = PDBOwnerDB.saveDomain(domain);
            List<Map<String, Object>> row = new ArrayList<Map<String, Object>>();

            //Insert Data in Features table
            for (Object o : features_and_description) {

                Map<String, Object> columns = new HashMap<String, Object>();
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
                System.out.println("domainfeatures_result" + domainfeatures_result);
            }
            maps_string.put("status", "Process Done");
        } catch (Exception e) {
            System.out.println("Error : " + e);
            maps_object.put("status", "Error in the Inserion : " + e);
        }
        return "success";
    }

    public String checkVehicleAndModel() {

        try {

            System.out.println("checkVehicleAndModel");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);

            String vehiclename = readValue.get("vehiclename").asText();
            ArrayNode models = (ArrayNode) readValue.get("models");

            Vehicle vehicle = new Vehicle(vehiclename, true, new Date(), new Date(), PDBOwnerDB.getUser(1));
            Vehicle vehicleId = PDBOwnerDB.saveVehicles(vehicle);
            List<Map<String, Object>> row = new ArrayList<Map<String, Object>>();
            domainfeatures_result1.put("vehicle_id", vehicleId.getId());
            domainfeatures_result1.put("vehiclename", vehicleId.getVehiclename());
            for (Object o : models) {

                Map<String, Object> column = new HashMap<String, Object>();
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
            domainfeatures_result1.put("models", row);
            System.out.println("domainfeatures_result" + domainfeatures_result1);
        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }
        return "success";
    }

    public String GetVehicleModel_Listing() {

        try {
            listObjects = PDBOwnerDB.GetVehicleModel_Listing();
            List<Map<String, Object>> row = new ArrayList<>();
            for (Object[] reObject : listObjects) {

                Map<String, Object> columns = new HashMap<String, Object>();

                String modelname = (String) reObject[0];
                System.out.println(modelname);
                columns.put("modelname", modelname);

                boolean status = (boolean) reObject[1];
                System.out.println(status);
                columns.put("status", status);

                Date created_date = (Date) reObject[2];
                System.out.println(created_date);
                columns.put("created_date", created_date);

                Date modified_date = (Date) reObject[3];
                System.out.println(modified_date);
                columns.put("modified_date", modified_date);

                String vehiclename = (String) reObject[4];
                System.out.println(vehiclename);
                columns.put("vehiclename", vehiclename);

                String versionname = (String) reObject[5];
                System.out.println(versionname);
                columns.put("versionname", versionname);

                row.add(columns);
                System.out.println("colums" + columns);
            }
            domainfeatures_result = row;
            maps_string.put("status", "Listed Done");
            System.out.println("Json Values : " + domainfeatures_result);

        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }

        return "success";
    }

    public String GetVehicle_Listing() {

        try {
            listObjects = PDBOwnerDB.getVehicle_Listing();
            List<Map<String, Object>> row = new ArrayList<>();
            listObjects.stream().map((reObject) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("pdb_version", reObject[0]);
                columns.put("vehiclename", reObject[1]);
                columns.put("status", reObject[2]);
                columns.put("created_date", reObject[3]);
                columns.put("modified_date", reObject[4]);
                columns.put("versionname", reObject[5]);
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            domainfeatures_result = row;
            maps_string.put("status", "Listed Done");
            System.out.println("Json Values : " + domainfeatures_result);

        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }

        return "success";
    }

    public String GetPDBVersion_Listing() {

        System.out.println("GetVehicleVersion_Listing");
        try {
            listObjects = PDBOwnerDB.GetPDBVersion_Listing();

            List<Map<String, Object>> row = new ArrayList<>();
            listObjects.stream().map((reObject) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("pdb_id", reObject[0]);
                columns.put("pdb_versionname", reObject[1]);
                columns.put("vehicle_id", reObject[2]);
                columns.put("vehiclename", reObject[3]);
                columns.put("modelname", reObject[4]);
                columns.put("status", reObject[5]);
                columns.put("flag", reObject[6]);
                columns.put("created_date", reObject[7]);
                columns.put("modified_date", reObject[8]);
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            domainfeatures_result = row;
            maps_string.put("status", "Listed Done");
            System.out.println("Json Values : " + domainfeatures_result);

        } catch (Exception e) {
            maps_object.put("status", e);
            System.err.println("Error : " + e);
        }

        return "success";
    }

    public String GetDomainFeaturesListing() {

        System.out.println("GetFeaturesListing controller");
        try {
            listObjects = PDBOwnerDB.GetDomainFeaturesListing();

            List<Map<String, Object>> row = new ArrayList<>();
            listObjects.stream().map((reObject) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("dfm_id", reObject[0]);
                columns.put("domain_name", reObject[1]);
                columns.put("feature_name", reObject[2]);
                columns.put("created_date", reObject[3]);
                columns.put("modified_date", reObject[4]);
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            domainfeatures_result = row;
            maps_string.put("status", "Listed Done");
            System.out.println("Json Values : " + domainfeatures_result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !! " + ex);
        }
        return "success";
    }

    public String GetDomainFeaturesListing1() {

        System.out.println("GetFeaturesListing controller");
        try {
            listObjects = PDBOwnerDB.GetDomainFeaturesListing1();

            List<Map<String, Object>> row = new ArrayList<>();
            listObjects.stream().map((reObject) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("dfm_id", reObject[0]);
                columns.put("domain_name", reObject[1]);
                columns.put("feature_name", reObject[2]);
                columns.put("created_date", reObject[3]);
                columns.put("modified_date", reObject[4]);
                return columns;
            }).map((columns) -> {
                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            domainfeatures_result = row;
            maps_string.put("status", "Listed Done");
            System.out.println("Json Values : " + domainfeatures_result);
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
            if (!vehicle.getVehiclename().equals(vehicleName)) {
                maps_string.put("status", "No Vehicle Found You can Continue");
            } else {
                maps_string.put("status", "Your Entered Vehicle Already Exisit");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "You can Continue");
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
            if (!domain.getDomain_name().equals(domainname)) {
                maps_string.put("status", "No Vehicle Found You can Continue");
                maps_string.put("res", "success");
            } else {
                maps_string.put("status", "Your Entered Vehicle Already Exisit");
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
            List<Pdbversion_group> pdb_map_result = PDBOwnerDB.LoadPDBDomainFeatures(readValue.get("pdbversion_id").asInt());
            System.out.println("pdb_map_result" + pdb_map_result);          
            String pdb_results = gson.toJson(pdb_map_result);
            System.out.println("pdb_results"+pdb_results);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            maps_string.put("status", "Some error occurred !!");
        }
//            return vehmod_map_result;
//            System.out.println("Result"+vehmod_map_result);
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

    public List<Pdbversion_group> getPdbversion_group_result() {
        return pdbversion_group_result;
    }

    public void setPdbversion_group_result(List<Pdbversion_group> pdbversion_group_result) {
        this.pdbversion_group_result = pdbversion_group_result;
    }

    public List<Map<String, Object>> getDomainfeatures_result() {
        return domainfeatures_result;
    }

    public void setDomainfeatures_result(List<Map<String, Object>> domainfeatures_result) {
        this.domainfeatures_result = domainfeatures_result;
    }

    public HashMap<String, Object> getDomainfeatures_result1() {
        return domainfeatures_result1;
    }

    public void setDomainfeatures_result1(HashMap<String, Object> domainfeatures_result1) {
        this.domainfeatures_result1 = domainfeatures_result1;
    }

    public String getResultValues() {
        return resultValues;
    }

    public void setResultValues(String resultValues) {
        this.resultValues = resultValues;
    }

}
