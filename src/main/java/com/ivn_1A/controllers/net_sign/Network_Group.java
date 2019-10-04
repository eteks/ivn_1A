/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.net_sign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.configs.VersionType;
import com.ivn_1A.controllers.notification.NotificationController;
import com.ivn_1A.models.pdbowner.Featureversion;
import com.ivn_1A.models.pdbowner.FeatureversionDB;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.SafetyLegDB;
import com.ivn_1A.models.net_sign.ECU;
import com.ivn_1A.models.net_sign.IVNEngineerDB;
import static com.ivn_1A.models.net_sign.IVNEngineerDB.getSignalTagsByName;
import com.ivn_1A.models.net_sign.IVN_Version;
import com.ivn_1A.models.net_sign.IVN_Version_Group;
import com.ivn_1A.models.net_sign.Network;
import com.ivn_1A.models.net_sign.SignalTags;
import com.ivn_1A.models.net_sign.SignalTags_Mapping;
import com.ivn_1A.models.net_sign.Signals;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

/**
 *
 * @author ETS06
 */
public class Network_Group {

    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
    private List<Tuple> tupleObjects = new ArrayList<>();
    Gson gson = new Gson();
    private String result_data_obj;
    public String eculist_result_obj;
    public String signallist_result_obj;
    public String network_list_obj;

    public String IVNVersionCreationPage() {

        try {

            List<Map<String, Object>> raw = new ArrayList<>();
            tupleObjects = IVNEngineerDB.LoadFeatureVersion("active");
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tuple.get("id"));
                columns.put("versionname", String.format("%.1f", tuple.get("versionname")));
                columns.put("status", tuple.get("status"));
                return columns;
            }).map((columns) -> {
                raw.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            result_data_obj = new Gson().toJson(raw);
            System.out.println("result_data_obj  " + result_data_obj);

            List<Map<String, Object>> row = new ArrayList<>(), row1 = new ArrayList<>(), row2 = new ArrayList<>();
            Map<String, Object> column = new HashMap<>();

            tupleObjects = IVNEngineerDB.LoadNetwork();
            for (Tuple tupleObject : tupleObjects) {

                Map<String, Object> columns = new HashMap<>(), columns1 = new HashMap<>(), columns2 = new HashMap<>();

                if (tupleObject.get("ntype").equals("can")) {
                    columns.put("cid", tupleObject.get("id"));
                    columns.put("listitem", tupleObject.get("listitem"));
                    row.add(columns);
                } else if (tupleObject.get("ntype").equals("lin")) {
                    columns1.put("lid", tupleObject.get("id"));
                    columns1.put("listitem", tupleObject.get("listitem"));
                    row1.add(columns1);
                } else if (tupleObject.get("ntype").equals("hardware")) {
                    columns2.put("hid", tupleObject.get("id"));
                    columns2.put("listitem", tupleObject.get("listitem"));
                    row2.add(columns2);
                }
            }
            column.put("can_list", row);
            column.put("lin_list", row1);
            column.put("hardware_list", row2);
            network_list_obj = new Gson().toJson(column);
            System.out.println("network_list_obj  " + network_list_obj);

            List<Map<String, Object>> rows = new ArrayList<>();
            tupleObjects = IVNEngineerDB.LoadECU();
            for (Tuple tupleObject : tupleObjects) {

                Map<String, Object> columns = new HashMap<>();
                columns.put("eid", tupleObject.get("id"));
                columns.put("listitem", tupleObject.get("listitem"));
                columns.put("description", tupleObject.get("description"));
                rows.add(columns);
            }
            eculist_result_obj = new Gson().toJson(rows);

            List<Map<String, Object>> rows1 = new ArrayList<>();
            tupleObjects = IVNEngineerDB.LoadSignals();
            for (Tuple tupleObject : tupleObjects) {

                Map<String, Object> columns = new HashMap<>();
                columns.put("sid", tupleObject.get("id"));
                columns.put("listitem", tupleObject.get("listitem"));
                columns.put("description", tupleObject.get("description"));
                rows1.add(columns);
            }
            signallist_result_obj = new Gson().toJson(rows1);
        } catch (Exception e) {
            System.err.println("Error in \"Network_Group\" \'IVNVersionCreationPage\' " + e);
        }
        return "success";
    }

    public String CreateIVNVersion_Attributes() {

        try {
            System.err.println("CreateIVNVersion_Attributes");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            List<Map<String, Object>> networkName_Result = new ArrayList<>();
            Map<String, Object> columns = new HashMap<>();

            String nw_type = readValue.get("network").asText();
            System.out.println("nw_type " + nw_type);
            if (!nw_type.equals("signals")) {

                ArrayNode ivn_attribute_data = (ArrayNode) readValue.get("ivn_attribute_data");
                System.out.println("ivn_attribute_data " + ivn_attribute_data);
                if (!nw_type.equals("ecu")) {

                    for (Object o : ivn_attribute_data) {
                        JsonNode item = (JsonNode) o;
                        String nw_name = item.get("name").asText();
                        String nw_description = item.get("description").asText();
                        Network network = new Network(nw_name, nw_description, nw_type, new Date(), new Date(), PDBOwnerDB.getUser(1), true);
//                        n.setNetwork_name(nw_name);
//                        n.setNetwork_description(nw_description);
//                        n.setNetwork_type(nw_type);
//                        n.setCreated_date(new Date());
//                        n.setModified_date(new Date());
//                        n.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
//                        n.setStatus(true);
                        Network nt_data = IVNEngineerDB.insertNetworkData(network);
                        if (nt_data != null) {
                            int last_inserted_id = nt_data.getId();
                            //                    return last_inserted_id;
                            if (nt_data.getNetwork_type().equals("can")) {
                                columns.put("cid", Integer.toString(last_inserted_id));
                            } else if (nt_data.getNetwork_type().equals("lin")) {
                                columns.put("lid", Integer.toString(last_inserted_id));
                            } else if (nt_data.getNetwork_type().equals("hardware")) {
                                columns.put("hid", Integer.toString(last_inserted_id));
                            }
                            networkName_Result.add(columns);
                        }
                    }
                    result_data_obj = new Gson().toJson(networkName_Result);
                    System.out.println("result_data" + networkName_Result);
                } else {

                    for (Object o : ivn_attribute_data) {
                        JsonNode item = (JsonNode) o;
                        String nw_name = item.get("name").asText();
                        String nw_description = item.get("description").asText();
                        ECU ecu = new ECU(nw_name, nw_description, new Date(), new Date(), PDBOwnerDB.getUser(1), true);
                        ECU nt_data = IVNEngineerDB.insertECUData(ecu);
                        if (nt_data != null) {
                            int last_inserted_id = nt_data.getId();
                            columns.put("eid", Integer.toString(last_inserted_id));
                            columns.put("description", nt_data.getEcu_description());
                            columns.put("listitem", nt_data.getEcu_name());
                            networkName_Result.add(columns);
                        }
                    }
                    result_data_obj = new Gson().toJson(networkName_Result);
                    System.out.println("result_data  " + networkName_Result);
                }
            } else {

                System.out.println("Signals");
                JsonNode jsonNode = readValue.get("ivn_attribute_data");
                String signal_name = jsonNode.get("name").asText();
                String signal_alias = jsonNode.get("alias").asText();
                String signal_description = jsonNode.get("description").asText();
                String signal_byteorder = jsonNode.get("byteorder").asText();
                String signal_unit = jsonNode.get("unit").asText();
                String signal_valuetype = jsonNode.get("valuetype").asText();
                String signal_valuetable = jsonNode.get("valuetable").asText();
                String signal_can_id = jsonNode.get("can").asText();
                String signal_lin_id = jsonNode.get("lin").asText();
                String signal_hw_id = jsonNode.get("hardware").asText();
                ArrayNode signal_tags = (ArrayNode) jsonNode.get("tags");
                System.out.println("signal_tags" + signal_tags);
                System.out.println("int value started");
                int signal_length = (jsonNode.get("length") != null) ? Integer.parseInt(jsonNode.get("length").asText()) : 0;
                System.out.println("signal_length" + signal_length);
                int signal_initvalue = (jsonNode.get("initvalue") != null) ? Integer.parseInt(jsonNode.get("initvalue").asText()) : 0;
                System.out.println("signal_initvalue" + signal_initvalue);
                double signal_factor = (jsonNode.get("factor") != null) ? Double.parseDouble(jsonNode.get("factor").asText()) : 0;
                System.out.println("signal_factor" + signal_factor);
                int signal_offset = (jsonNode.get("offset") != null) ? Integer.parseInt(jsonNode.get("offset").asText()) : 0;
                System.out.println("signal_offset" + signal_offset);
                int signal_minimum = (jsonNode.get("minimum") != null) ? Integer.parseInt(jsonNode.get("minimum").asText()) : 0;
                System.out.println("signal_minimum" + signal_minimum);
                int signal_maximum = (jsonNode.get("minimum") != null) ? Integer.parseInt(jsonNode.get("maximum").asText()) : 0;
                System.out.println("signal_maximum" + signal_maximum);

                int last_inserted_id = 0;
                int tag_id = 0;
                Signals s1 = IVNEngineerDB.getSignalByName(signal_name);
                if (s1 == null) {
                    s1 = IVNEngineerDB.insertSignalData(new Signals(signal_name, signal_alias, signal_description, signal_length,
                            signal_byteorder, signal_unit, signal_valuetype, signal_initvalue,
                            signal_factor, signal_offset, signal_minimum, signal_maximum,
                            signal_valuetable, IVNEngineerDB.getNetworkById(Integer.parseInt(signal_can_id)), IVNEngineerDB.getNetworkById(Integer.parseInt(signal_lin_id)),
                            IVNEngineerDB.getNetworkById(Integer.parseInt(signal_hw_id)), new Date(), new Date(), PDBOwnerDB.getUser(1), true));
                    last_inserted_id = s1.getId();
                    System.err.println("Singnal Id    " + last_inserted_id);
                }
                for (JsonNode signal_tag : signal_tags) {

                    String tagname = signal_tag.asText();
                    System.err.println("tagname   " + tagname);
                    SignalTags st = getSignalTagsByName(tagname);
                    if (st == null) {
                        st = IVNEngineerDB.insertSignalTagsData(new SignalTags(tagname, new Date(), new Date(), PDBOwnerDB.getUser(1), true));
                        tag_id = st.getId();
                        System.err.println("tag_id   " + tag_id);
                    } else {
                        tag_id = st.getId();
                        System.err.println("tag_id   " + tag_id);
                    }
                    SignalTags_Mapping stm = IVNEngineerDB.insertsignalTags_MappingData(new SignalTags_Mapping(s1, st, new Date()));
                    tag_id = stm.getId();
                    System.err.println("tag_id   " + tag_id);
                }

                List<Map<String, Object>> row = new ArrayList<>();
                Map<String, Object> col = new HashMap<>();
                Signals signals = IVNEngineerDB.getSignalDataByID(last_inserted_id);
                if (signals != null) {
                    col.put("listitem", signals.getSignal_name());
                    col.put("sid", Integer.toString(signals.getId()));
                    col.put("description", signals.getSignal_description());
                    col.put("salias", signals.getSignal_alias());
                    col.put("can", signals.getCan_id_group().getId());
                    col.put("lin", signals.getLin_id_group().getId());
                    col.put("hardware", signals.getHw_id_group().getId());
                    row.add(col);
                }
                result_data_obj = new Gson().toJson(row);
            }
            maps_object.put("status", "Work is done");
        } catch (Exception e) {
            System.err.println("Error in \"Network_Group\" \'CreateIVNVersion_Attributes\' " + e);
            maps_object.put("status", "Error in \"Network_Group\" \'CreateIVNVersion_Attributes\' " + e);
        }
        return "success";
    }

    public String loadSelectedFeatureVersionData() {

        try {

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            int featureID = readValue.get("id").asInt();

            List<Map<String, Object>> features_result = new ArrayList<>();
            System.out.println(featureID);
            tupleObjects = IVNEngineerDB.LoadFeatureVersionById(featureID);
            tupleObjects.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
//                columns.put("id", tuple.get("id"));
//                columns.put("pdbversionname", String.format("%.1f", tuple.get("pdbversionname")));
                columns.put("vid", tuple.get("vid"));
                columns.put("vname", tuple.get("vname"));
                columns.put("status", tuple.get("status"));
                columns.put("flag", tuple.get("flag"));
                return columns;
            }).map((columns) -> {
                features_result.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            result_data_obj = new Gson().toJson(features_result);
            System.out.println("result_data_obj  " + result_data_obj);
        } catch (Exception e) {
            System.err.println("Error in \"Network_Group\" \'loadSelectedFeatureVersionData\' " + e);
            maps_object.put("status", "Error in \"Network_Group\" \'loadSelectedFeatureVersionData\' " + e);
        }
        return "success";
    }

    public String CreateIVNVersion() {

        try {

            System.out.println("CreateIVNVersion");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            boolean status = false;
            int ivnversion_id = 0;
            float version_name = 1.0f;
            String version_type = "new";
            String previousversion_status = "none";
            String previousversion_flag = "none";
            boolean flag;
            JsonNode ivnversion = readValue.get("ivnversion");
            JsonNode ivndata_list = readValue.get("ivndata_list");
            String button_type = readValue.get("button_type").asText();
            String notification_to = readValue.get("notification_to").asText();

            if (button_type.equals("save")) {
                flag = false;
            } else {
                flag = true;
            }
            if (ivnversion != null && ivnversion.has("vername")) {
                ivnversion_id = ivnversion.get("vername").get("id").asInt();
            }
            if (ivnversion != null && ivnversion.has("status")) {
                status = ivnversion.get("status").asBoolean();
            }
            System.out.println("before if");
            if (ivnversion_id != 0) {

                IVN_Version iVN_Version = IVNEngineerDB.LoadIVNPreviousVehicleversionStatus(ivnversion_id);
                if (iVN_Version != null) {
                    version_name = iVN_Version.getIvn_version();
                    previousversion_status = String.valueOf(iVN_Version.isStatus());
                    previousversion_flag = String.valueOf(iVN_Version.isFlag());
                }
            }
            System.out.println("__________" + previousversion_status);
            System.out.println("__________" + previousversion_status);
            System.out.println("__________" + button_type);
            System.out.println("__________" + ivnversion_id);
            System.out.println("__________" + version_name);

            if (previousversion_status.equals("false") && ivnversion_id != 0) {

                maps_object.put("status", "Ready to update");
//                IVN_Version iVN_Version = IVNEngineerDB.insertIVNVersion(new IVN_Version(version_name, new Date(), new Date(), ivnversion.get("version_name").asText(), ivnversion.get("pdb_manual_comment").asText(), flag, status), "update");
//                if (iVN_Version != null) {
//
//                    int ivn_id = iVN_Version.getId();
//                    version_name = iVN_Version.getIvn_version();
//                    IVN_Version_Group iVN_Version_Group = IVNEngineerDB.insertIVNVersionGroup(new IVN_Version_Group(iVN_Version, PDBOwnerDB.getVehicle(ivnversion.get("vehiclename").get("vid").asInt()), new Featureversion(), ivnversion.get("ivndata_list").get("signal").asText(), ivnversion.get("ivndata_list").get("ecu").asText(), new Date()));
//                    if (iVN_Version_Group != null && button_type.equals("save")) {
//                        if (previousversion_flag.equals("true")) {
//                            maps_object.put("status", "Record updated in same version and stored as Temporary");
//                        } else {
//                            maps_object.put("status", "Record updated successfully in same Temporary version");
//                        }
//                    } else {
//                        System.out.println("previousversion_flag" + previousversion_flag);
//                        if (status) {
//                            new NotificationController().createNotification(VersionType.IVN_Version.getVersionCode().intValue(), version_name, new Date().toString(), notification_to, ivn_id);
//                        }
//                        if (previousversion_flag.equals("false")) {
//                            maps_object.put("status", "Record updated in same version and stored as permanent");
//                        } else {
//                            maps_object.put("status", "Record updated successfully in same Permanent version");
//                        }
//                    }
//                }
            } else {

                System.out.println("else");
                IVN_Version iVN_Version = new IVN_Version();
                if (ivnversion.has("vername")) {
                    System.out.println("enter pdbversion");
                    if (ivnversion.has("version_change")) {

                        System.out.println("enter version change");
                        System.out.println("enter version change1  " + ivnversion.get("version_change").asText());
                        if (ivnversion.get("version_change").asText().equals("major")) { //for major changes

//                            version_type = "major_changes";
                            System.out.println("major");
                            System.out.println("vehicle_id" + ivnversion.get("vehiclename").get("vid").asInt());
                            IVN_Version ivnv = IVNEngineerDB.GetVersionname(ivnversion.get("vehiclename").get("vid").asInt(), version_type);
                            version_name = (float) 1.0 + ivnv.getIvn_version();
                            ivnversion_id = ivnv.getId();

                        } else { //for minor changes

                            version_type = "minor_changes";
                            System.out.println("minor");
                            System.out.println("vehicle_id" + ivnversion.get("vehiclename").get("vid").asInt());
                            IVN_Version ivnv = IVNEngineerDB.GetVersionname(ivnversion.get("vehiclename").get("vid").asInt(), version_type);
                            version_name = (float) 0.1 + ivnv.getIvn_version();
                            ivnversion_id = ivnv.getId();
                        }
                        iVN_Version.setIvn_reference_version(Float.valueOf(ivnversion.get("vername").get("ivn_version").asText()));
                    }

                    System.out.println("version_name " + version_name);
                    System.out.println("prevpdb_id " + ivnversion_id);

                    iVN_Version.setCreated_date(new Date());
                    iVN_Version.setModified_date(new Date());
                    iVN_Version.setIvn_version(version_name);
                    iVN_Version.setIvn_manual_comment(ivnversion.get("pdb_manual_comment").asText());
                    iVN_Version.setVersion_type(version_type);
                    iVN_Version.setVersion_name(ivnversion.get("version_name").asText());
                    iVN_Version.setStatus(status);
                    iVN_Version.setFlag(flag);
                    iVN_Version.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
                    iVN_Version.setVehicleId(PDBOwnerDB.getVehicle(ivnversion.get("vehiclename").get("vid").asInt()));
                    iVN_Version.setFeatureVersionId(FeatureversionDB.GetVersionnameById(ivnversion.get("featureversion").get("id").asInt()));

                    IVN_Version iVN_Versions = IVNEngineerDB.insertIVNVersion(iVN_Version);
                    if (iVN_Versions != null) {

                        ArrayNode sigNode = (ArrayNode) ivndata_list.get("signal");
                        ArrayNode ecuNode = (ArrayNode) ivndata_list.get("ecu");

//                        String sigVal = sigNode.size() > 1 ? String.join(",", mapper.convertValue(sigNode, String[].class)) : sigNode.get(0).asText();
//                        String ecuVal = ecuNode.size() > 1 ? String.join(",", mapper.convertValue(ecuNode, String[].class)) : ecuNode.get(0).asText();
                        IVN_Version_Group iVN_Version_Groups = new IVN_Version_Group();
                        //loop for different size arraynode value.
                        int max = Math.max(sigNode.size(), ecuNode.size());
                        for (int i = 0; i < max; i++) {
                            
                            iVN_Version_Groups.setIvnVersionId(iVN_Versions);
                            if (sigNode.size() > i) {
                                iVN_Version_Groups.setSignalsId(IVNEngineerDB.getSignalDataByID(sigNode.get(i).asInt()));
                            } else {
                                iVN_Version_Groups.setSignalsId(null);
                            }
                            if (ecuNode.size() > i) {
                                iVN_Version_Groups.setEcuId(IVNEngineerDB.getECUById(ecuNode.get(i).asInt()));
                            } else {
                                iVN_Version_Groups.setEcuId(null);
                            }
                            iVN_Version_Groups.setCreated_date(new Date());

                            iVN_Version_Groups = IVNEngineerDB.insertIVNVersionGroup(iVN_Version_Groups);
                        }
                        if (ivnversion_id != 0) {
                            Map<String, Object> ivn_previous_data = IVNEngineerDB.GetIVNPreviousVersion_SigEcu(ivnversion_id, iVN_Version.getId());
                            System.out.println("ivn_previous_data result    " + ivn_previous_data);

                            JSONObject ivn_previous_data_result = new JSONObject();
                            ivn_previous_data_result.put("removed_signals", ivn_previous_data.get("removed_signals"));
                            ivn_previous_data_result.put("added_signals", ivn_previous_data.get("added_signals"));
                            ivn_previous_data_result.put("removed_ecus", ivn_previous_data.get("removed_ecus"));
                            ivn_previous_data_result.put("added_ecus", ivn_previous_data.get("added_ecus"));
                            ivn_previous_data_result.put("current_version", String.format("%.1f", iVN_Version.getIvn_version()));
                            ivn_previous_data_result.put("reference_version", ivnversion.get("vername").get("ivn_version").asDouble());

                            maps_object.put("ivn_previous_data_result", ivn_previous_data_result);
                        }
                        if (iVN_Version_Groups != null && button_type.equals("save")) {
                            maps_object.put("status", "New Temporary IVN Version Created Successfully");
                        } else {
                            System.out.println("previousversion_flag" + previousversion_flag);
                            if (status) {
                                new NotificationController().createNotification(VersionType.IVN_Version.getVersionCode().intValue(), version_name, new Date().toString(), notification_to, iVN_Versions.getId());
                            }
                            maps_object.put("status", "New Permanent IVN Version Created Successfully");
                        }
                    }
                    maps_string.put("status_code", "1");
                    
                } else {
                    System.out.println("version_name " + version_name);
                    System.out.println("prevpdb_id " + ivnversion_id);

                    iVN_Version.setCreated_date(new Date());
                    iVN_Version.setModified_date(new Date());
                    iVN_Version.setIvn_version(version_name);
                    iVN_Version.setIvn_manual_comment(ivnversion.get("pdb_manual_comment").asText());
                    iVN_Version.setVersion_type(version_type);
                    iVN_Version.setVersion_name(ivnversion.get("version_name").asText());
                    iVN_Version.setStatus(status);
                    iVN_Version.setFlag(flag);
                    iVN_Version.setCreated_or_updated_by(PDBOwnerDB.getUser(1));
                    iVN_Version.setVehicleId(PDBOwnerDB.getVehicle(ivnversion.get("vehiclename").get("vid").asInt()));
                    iVN_Version.setFeatureVersionId(FeatureversionDB.GetVersionnameById(ivnversion.get("featureversion").get("id").asInt()));

                    IVN_Version iVN_Versions = IVNEngineerDB.insertIVNVersion(iVN_Version);
                    if (iVN_Versions != null) {

                        ArrayNode sigNode = (ArrayNode) ivndata_list.get("signal");
                        ArrayNode ecuNode = (ArrayNode) ivndata_list.get("ecu");
                        
                        IVN_Version_Group iVN_Version_Groups = new IVN_Version_Group();
                        int max = Math.max(sigNode.size(), ecuNode.size());
                        for (int i = 0; i < max; i++) {
                            iVN_Version_Groups.setIvnVersionId(iVN_Versions);
                            if (sigNode.size() > i) {
                                iVN_Version_Groups.setSignalsId(IVNEngineerDB.getSignalDataByID(sigNode.get(i).asInt()));
                            } else {
                                iVN_Version_Groups.setSignalsId(null);
                            }
                            if (ecuNode.size() > i) {
                                iVN_Version_Groups.setEcuId(IVNEngineerDB.getECUById(ecuNode.get(i).asInt()));
                            } else {
                                iVN_Version_Groups.setEcuId(null);
                            }
                            iVN_Version_Groups.setCreated_date(new Date());

                            iVN_Version_Groups = IVNEngineerDB.insertIVNVersionGroup(iVN_Version_Groups);
                        }
                        if (ivnversion_id != 0) {
                            Map<String, Object> ivn_previous_data = IVNEngineerDB.GetIVNPreviousVersion_SigEcu(ivnversion_id, iVN_Version.getId());
                            System.out.println("ivn_previous_data result    " + ivn_previous_data);

                            JSONObject ivn_previous_data_result = new JSONObject();
                            ivn_previous_data_result.put("removed_signals", ivn_previous_data.get("removed_signals"));
                            ivn_previous_data_result.put("added_signals", ivn_previous_data.get("added_signals"));
                            ivn_previous_data_result.put("removed_ecus", ivn_previous_data.get("removed_ecus"));
                            ivn_previous_data_result.put("added_ecus", ivn_previous_data.get("added_ecus"));
                            ivn_previous_data_result.put("current_version", String.format("%.1f", iVN_Version.getIvn_version()));
                            ivn_previous_data_result.put("reference_version", ivnversion.get("vername").get("ivn_version").asDouble());

                            maps_object.put("ivn_previous_data_result", ivn_previous_data_result);
                        }
                        if (iVN_Version_Groups != null && button_type.equals("save")) {
                            maps_object.put("status", "New Temporary IVN Version Created Successfully");
                        } else {
                            System.out.println("previousversion_flag" + previousversion_flag);
                            if (status) {
                                new NotificationController().createNotification(VersionType.IVN_Version.getVersionCode().intValue(), version_name, new Date().toString(), notification_to, iVN_Versions.getId());
                            }
                            maps_object.put("status", "New Permanent IVN Version Created Successfully");
                        }
                    }
                }
            }
            maps_string.put("status_code", "1");
            
        } catch (Exception e) {
            System.err.println("Error in \"Network_Group\" \'CreateIVNVersion\' " + e);
            maps_object.put("status", "Error in \"Network_Group\" \'CreateIVNVersion\' " + e);
            maps_string.put("status", "Some error occurred !!");
            maps_string.put("status_code", "0");
        }
        return "success";
    }

    public String GetIVNVersion_Listing() {

        try {

            System.err.println("GetIVNVersion_Listing");
            List<Map<String, Object>> row = new ArrayList<>();
            List<IVN_Version_Group> iVN_Version_Groups = IVNEngineerDB.GetIVNVersion_Listing();

            iVN_Version_Groups.stream().map((iVN_Version_Group) -> {

                Map<String, Object> columns = new HashMap<>();
                columns.put("id", iVN_Version_Group.getIvnVersionId().getId());
                columns.put("ivn_version", String.format("%.1f", iVN_Version_Group.getIvnVersionId().getIvn_version()));
                columns.put("alias_version", iVN_Version_Group.getIvnVersionId().getVersion_name());
                columns.put("vehicle", iVN_Version_Group.getIvnVersionId().getVehicleId().getVehiclename());
                columns.put("fea_version", String.format("%.1f", iVN_Version_Group.getIvnVersionId().getFeatureVersionId().getFeature_versionname()));
                columns.put("model", iVN_Version_Group.getIvnVersionId().getFeatureVersionId().getLegislationversion_id().getId());
                columns.put("created_date", iVN_Version_Group.getIvnVersionId().getCreated_date());
                columns.put("modified_date", iVN_Version_Group.getIvnVersionId().getModified_date());
                columns.put("status", iVN_Version_Group.getIvnVersionId().isStatus());
                columns.put("flag", iVN_Version_Group.getIvnVersionId().isFlag());
                return columns;
            }).map((columns) -> {

                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            result_data_obj = new Gson().toJson(row);
            System.out.println("result_data_obj  " + result_data_obj);

        } catch (Exception e) {
            System.err.println("Error in \"Network_Group\" \'GetIVNVersion_Listing\' " + e);
            maps_object.put("status", "Error in \"Network_Group\" \'GetIVNVersion_Listing\' " + e);
        }
        return "success";
    }

    public String LoadIVNVersion() {

        try {
            System.err.println("LoadIVNVersion");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);

            List<Map<String, Object>> row = new ArrayList<>();
            tupleObjects = IVNEngineerDB.loadIVNVersion_ListingByVehicleId(readValue.get("vid").asInt());

            tupleObjects.stream().map((tupleObject) -> {

                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tupleObject.get("id"));
                columns.put("ivn_version", tupleObject.get("ivn_version"));
                return columns;
            }).map((columns) -> {

                row.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            result_data_obj = new Gson().toJson(row);
            System.out.println("result_data_obj  " + result_data_obj);
            maps_object.put("status", "Work done");
        } catch (Exception e) {
            System.err.println("Error in \"Network_Group\" \'LoadIVNVersion\' " + e);
            maps_object.put("status", "Error in \"Network_Group\" \'LoadIVNVersion\' " + e);
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

    public String getEculist_result_obj() {
        return eculist_result_obj;
    }

    public void setEculist_result_obj(String eculist_result_obj) {
        this.eculist_result_obj = eculist_result_obj;
    }

    public String getSignallist_result_obj() {
        return signallist_result_obj;
    }

    public void setSignallist_result_obj(String signallist_result_obj) {
        this.signallist_result_obj = signallist_result_obj;
    }

    public String getNetwork_list_obj() {
        return network_list_obj;
    }

    public void setNetwork_list_obj(String network_list_obj) {
        this.network_list_obj = network_list_obj;
    }

}
