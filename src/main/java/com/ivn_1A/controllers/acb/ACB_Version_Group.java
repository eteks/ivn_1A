/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.acb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.models.acb.ACB_DB;
import com.ivn_1A.models.acb.ACB_InputSignals;
import com.ivn_1A.models.acb.ACB_OutputSignals;
import com.ivn_1A.models.acb.ACB_Version;
import com.ivn_1A.models.acb.ACB_Version_Group_M;
import com.ivn_1A.models.net_sign.IVNEngineerDB;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.pdbowner.Pdbversion_group;
import java.util.ArrayList;
import java.util.Date;
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
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            boolean status = (boolean) false;
            int acbversion_id = 0;
            float version_name;
            String previousversion_status = "0";
            String previousversion_flag = "0";
            String subversion = "0";
            boolean flag;
            boolean is_acbsubversion = (boolean) false;

            JsonNode acbversion = readValue.get("acbversion");
            ArrayNode acbdata_list = (ArrayNode) readValue.get("acbdata_list");
            String button_type = readValue.get("button_type").asText();
            String notification_to = readValue.get("notification_to").asText();
            boolean fully_touchedstatus = readValue.get("features_fully_touchedstatus").asBoolean();
            flag = !button_type.equals("save");
            System.out.println("before_if");
            if (acbversion != null && acbversion.has("acbversion")) {
                System.out.println("enter_if");
                if (acbversion.get("acbsubversion") != null) {
                    acbversion_id = acbversion.get("acbsubversion").asInt();
                    is_acbsubversion = true;
                } else {
                    acbversion_id = acbversion.get("acbversion").asInt();
                }
            }

            if (acbversion != null && acbversion.has("status") && button_type.equals("submit")) {
                status = acbversion.get("status").asBoolean();
            }

            if (acbversion_id != 0) {
                //Get the data of previous vehicle version by id
                int acbver_id = acbversion_id;
                ACB_Version acbver = new ACB_Version(acbver_id);
                ACB_Version acbv = ACB_DB.loadACBPreviousVehicleversionStatus(acbver);
                System.out.println("acb_previous_result");
                previousversion_status = String.valueOf(acbv.isStatus());
                previousversion_flag = String.valueOf(acbv.isFlag());
            }
            System.out.println(previousversion_status);
            System.out.println(previousversion_flag);
            System.out.println(button_type);
            System.out.println(acbversion_id);

            if (previousversion_status.equals("false") && acbversion_id != 0) {
                System.out.println("Ready to update");
                System.out.println("if");
                ACB_Version acbv = new ACB_Version(Float.valueOf(subversion), new Date(), new Date(), PDBOwnerDB.getUser(1), status, flag, acbversion_id, fully_touchedstatus);
                System.out.println("acbversion_id" + acbversion_id);
                acbv = ACB_DB.insertACBVersion(acbv, "update", is_acbsubversion, "yes");
                int acb_id = acbv.getId();
                version_name = acbv.getAcb_versionname();
                System.out.println("acb_id  " + acb_id);

                for (JsonNode jsonNode : acbdata_list) {

                    ArrayNode feature = (ArrayNode) jsonNode.get("feature");
                    ArrayNode ipsignal = (ArrayNode) jsonNode.get("ipsignal");
                    ArrayNode opsignal = (ArrayNode) jsonNode.get("opsignal");
                    String ecu = jsonNode.get("ecu").asText();
                    int eid = jsonNode.get("eid").asInt();
                    String ecu_fea = jsonNode.get("ecu_fea").asText();

                    int max = Math.max(Math.max(ipsignal.size(), opsignal.size()), feature.size());
                    int featureId = 0;
                    ACB_InputSignals acbis = null;
                    ACB_OutputSignals acbos = null;
                    for (int i = 0; i < max; i++) {

                        if (feature.size() > i) {
                            featureId = feature.get("fid").asInt();
                        }
                        if (ipsignal.size() > i) {
                            acbis = (ACB_InputSignals) ACB_DB.insertACBSignal(new ACB_InputSignals(IVNEngineerDB.getSignalDataByID(ipsignal.get("sid").asInt()),
                                    IVNEngineerDB.getNetworkById(ipsignal.get("nw").asInt()), PDBOwnerDB.getPdbversionGroupById(ipsignal.get("pdbgp_id").asInt())));

                        }
                        if (opsignal.size() > i) {
                            acbos = (ACB_OutputSignals) ACB_DB.insertACBSignal(new ACB_OutputSignals(IVNEngineerDB.getSignalDataByID(ipsignal.get("sid").asInt()),
                                    IVNEngineerDB.getNetworkById(ipsignal.get("nw").asInt()), PDBOwnerDB.getPdbversionGroupById(ipsignal.get("pdbgp_id").asInt())));
                        }
                    }
                    System.out.println("ip_signals ### " + acbis.getInputSignalId().getSignal_name());
                    System.out.println("op_signals $$$ " + acbos.getOutputSignalId().getSignal_name());

                    ACB_Version_Group_M acbvgm = new ACB_Version_Group_M(acbv, IVNEngineerDB.getIVNVersionByIVN_ID(acbversion.get("vername").get("id").asInt()),
                            PDBOwnerDB.getPdbversion(acbversion.get("pdbversion").get("pdbid").asInt()), PDBOwnerDB.getVehicle(acbversion.get("vehiclename").get("vid").asInt()),
                            PDBOwnerDB.getDomain_and_Features_Mapping(acbversion.get("pdbversion").get("pdbid").asInt()), IVNEngineerDB.getECUById(acbversion.get("ecu_id").asInt()), fully_touchedstatus);
                    acbvgm = ACB_DB.insertACBVersionGroup(acbvgm, "update", button_type);

                    if (acbvgm != null && button_type.equals("save")) {
                        maps_object.put("status", "New Temporary ACB Version Created Successfully");
                    } else {
                        System.out.println("previousversion_flag" + previousversion_flag);
                        if (status) {
//                            new NotificationController().createNotification(VersionType.ACBversion.getVersionCode(), version_name, new Date().toString(), notification_to, acbv.getId());
                        }
                        maps_object.put("status", "New Permanent ACB Version Created Successfully");
                    }
                }
                ACB_DB.deleteACBVersion_Group(acbversion_id, "update");

            } else {

                System.out.println("else");
                if (previousversion_status.equals("true") && acbversion_id != 0) {
                    subversion = "yes";
                }
                ACB_Version acbv = new ACB_Version(Float.valueOf(subversion), new Date(), new Date(), PDBOwnerDB.getUser(1), status, flag, acbversion_id, fully_touchedstatus);
                System.out.println("acbversion_id" + acbversion_id);
                acbv = ACB_DB.insertACBVersion(acbv, "update", is_acbsubversion, "yes");
                int acb_id = acbv.getId();
                version_name = acbv.getAcb_versionname();
                System.out.println("acb_id  " + acb_id);
                int a = 0;
                for (JsonNode jsonNode : acbdata_list) {
                    ArrayNode feature = (ArrayNode) jsonNode.get("feature");
                    ArrayNode ipsignal = (ArrayNode) jsonNode.get("ipsignal");
                    ArrayNode opsignal = (ArrayNode) jsonNode.get("opsignal");
                    String ecu = jsonNode.get("ecu").asText();
                    int eid = jsonNode.get("eid").asInt();
                    String ecu_fea = jsonNode.get("ecu_fea").asText();

                    int max = Math.max(Math.max(ipsignal.size(), opsignal.size()), feature.size());
                    int featureId = 0;
                    ACB_InputSignals acbis = null;
                    ACB_OutputSignals acbos = null;
                    for (int i = 0; i < max; i++) {

                        if (feature.size() > i) {
                            JsonNode fea = feature.get(i);
                            featureId = fea.get("fid").asInt();
                        }
                        if (ipsignal.size() > i) {
                            JsonNode ips = ipsignal.get(i);
                            System.out.println("@#$%^&*()   " + ips.get("sid").asInt() + "@#$%^&*()   " + ips.get("nw").asInt() + "@#$%^&*()   " + ips.get("pdbgp_id").asInt());
                            acbis = (ACB_InputSignals) ACB_DB.insertACBSignal(new ACB_InputSignals(IVNEngineerDB.getSignalDataByID(ips.get("sid").asInt()),
                                    IVNEngineerDB.getNetworkById(ips.get("nw").asInt()), PDBOwnerDB.getPdbversionGroupById(ips.get("pdbgp_id").asInt())));

                        }
                        if (opsignal.size() > i) {
                            JsonNode ops = opsignal.get(i);
                            System.out.println("@#$%^&*()   " + ops.get("sid").asInt() + "@#$%^&*()   " + ops.get("nw").asInt() + "@#$%^&*()   " + ops.get("pdbgp_id").asInt());
                            acbos = (ACB_OutputSignals) ACB_DB.insertACBSignal(new ACB_OutputSignals(IVNEngineerDB.getSignalDataByID(ops.get("sid").asInt()),
                                    IVNEngineerDB.getNetworkById(ops.get("nw").asInt()), PDBOwnerDB.getPdbversionGroupById(ops.get("pdbgp_id").asInt())));
                        }
                    }
                    System.out.println("ip_signals ### " + acbis.getInputSignalId().getSignal_name());
                    System.out.println("op_signals $$$ " + acbos.getOutputSignalId().getSignal_name());

                    ACB_Version_Group_M acbvgm = new ACB_Version_Group_M(acbv, IVNEngineerDB.getIVNVersionByIVN_ID(acbversion.get("vername").get("id").asInt()),
                            PDBOwnerDB.getPdbversion(acbversion.get("pdbversion").get("pdbid").asInt()), PDBOwnerDB.getVehicle(acbversion.get("vehiclename").get("vid").asInt()),
                            PDBOwnerDB.getDomain_and_Features_Mapping(acbversion.get("pdbversion").get("pdbid").asInt()), IVNEngineerDB.getECUById(acbversion.get("ecu_id").asInt()), fully_touchedstatus);
                    acbvgm = ACB_DB.insertACBVersionGroup(acbvgm, "create", button_type);
                    if (acbvgm != null) {
                        
                        if (a++ == acbdata_list.size() - 1) {
                            System.out.println("final loop");
                            if (button_type.equals("save")) {
                                System.out.println("subversion_value" + subversion);
                                maps_object.put("status", "New Temporary ACB Version Created Successfully");
                            } else {
                                if (status) {
//                                new NotificationController().createNotification(VersionType.ACBversion.getVersionCode(), version_name, new Date().toString(), notification_to, acbv.getId());
                                }
                                maps_object.put("status", "New Permanent ACB Version Created Successfully");
                            }
                        }
                    } else {
                        System.out.println("ACB_Version_Group_M not inserted");
                    }
                }
            }
//            maps_object.put(jsonValues, readValue);
            maps_string.put("success", "work is done");
        } catch (Exception e) {
            System.out.println("Error in \"ACB_Version_Group\" \'CreateACBVersion\' : " + e);
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
