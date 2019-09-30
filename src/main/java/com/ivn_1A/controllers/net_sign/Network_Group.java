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
}
