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
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com_ivn_1A.models.net_sign.ECU;
import com_ivn_1A.models.net_sign.IVNEngineerDB;
import com_ivn_1A.models.net_sign.Network;
import com_ivn_1A.models.net_sign.Signals;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import org.apache.struts2.ServletActionContext;

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
                    ServletActionContext.getResponse().getWriter().println("<script>alert('This is Network');</script>");
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
                    System.out.println("result_data" + networkName_Result);
                    ServletActionContext.getResponse().getWriter().println("<script>alert('This is ECU');</script>");
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

                Signals s = new Signals(signal_name, signal_alias, signal_description, signal_length,
                        signal_byteorder, signal_unit, signal_valuetype, signal_initvalue,
                        signal_factor, signal_offset, signal_minimum, signal_maximum,
                        signal_valuetable, IVNEngineerDB.getNetworkById(Integer.parseInt(signal_can_id)), IVNEngineerDB.getNetworkById(Integer.parseInt(signal_lin_id)), 
                        IVNEngineerDB.getNetworkById(Integer.parseInt(signal_hw_id)), new Date(), PDBOwnerDB.getUser(1));
                
                ServletActionContext.getResponse().getWriter().println("<script>alert('This is Signal');</script>");
            }

        } catch (Exception e) {
            System.err.println("Error in \"Network_Group\" \'createNetwork\' " + e);
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
