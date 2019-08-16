/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.pdbowner;

import com.ivn_1A.configs.JSONConfigure;
import static com.ivn_1A.controllers.pdbowner.Vehicle_Version_Group.vehicle_Repository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ivn_1A.models.pdbowner.*;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ets-poc
 */
public class Pdbversion_Group {
    public static PDBOwnerDB pdbownerdb = new PDBOwnerDB();
    private Map<String, String> maps = new HashMap<String, String>();
    
    public String CreatePDBVersion() {
        System.out.println("CreatePDBVersion");
        JSONParser parser = new JSONParser();
        String jsondata = JSONConfigure.getAngularJSONFile();
//        String button_type = (String) json.get("button_type");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
        boolean status = (boolean) false;
        int pdbversion_id = 0;
        float version_name = 1.0f;
        String previousversion_status = null;
        String previousversion_flag = null;
        boolean flag;
        try {
            Object obj = parser.parse(jsondata);
            JSONObject json = (JSONObject) obj;
            System.out.println("pdbdata" + json);
            JSONObject pdbversion_value = (JSONObject) json.get("pdbversion");
            JSONArray pdbdata_list = (JSONArray) json.get("pdbdata_list");
            System.out.println("pdbdata_list" + pdbdata_list);
            String button_type = (String) json.get("button_type");
            String notification_to = (String) json.get("notification_to");
            if (button_type.equals("save")) {
                flag = false;
            } else {
                flag = true;
            }
            if (pdbversion_value != null && pdbversion_value.containsKey("pdbversion")) {
                pdbversion_id = Integer.parseInt((String) pdbversion_value.get("pdbversion"));
            }

            if (pdbversion_value != null && pdbversion_value.containsKey("status") && button_type.equals("submit")) {
                status = (boolean) pdbversion_value.get("status");
            }

//            if (pdbversion_id != 0) {
//                //Get the data of previous vehicle version by id
//                int pdbver_id = pdbversion_id;
//                PDBversion pver = new PDBversion(pdbver_id);
////                private List<Map<String, Object>> vehmod_map_result = new ArrayList<Map<String, Object>>();
//                List<Map<String, Object>> pdb_previous_result = PDBOwnerDB.LoadPDBPreviousVehicleversionStatus(pver);
//                System.out.println("pdb_previous_status" + pdb_previous_result);
//                previousversion_status = String.valueOf(pdb_previous_result.get(0).get("status"));
//                previousversion_flag = String.valueOf(pdb_previous_result.get(0).get("flag"));
//            }
//            System.out.println(previousversion_status);
//            System.out.println(button_type);
//            System.out.println(pdbversion_id);
////            if(previousversion_status != null && button_type.equals("save") && pdbversion_id != 0){
//            if (previousversion_status == "false" && pdbversion_id != 0) {
////                System.out.println("Ready to update");
//                maps.put("status", "Ready to update");
//                PDBversion pv = new PDBversion(pdbversion_id, status, flag, dtf.format(now), "update");
//                System.out.println("pdbversion_id" + pdbversion_id);
//                Object[] id_version = PDBOwnerDB.insertPDBVersion(pv);
//                int pdb_id = (int) id_version[0];
//                version_name = (float) id_version[1];
//                int i = 0;
//                for (Object o : pdbdata_list) {
//                    JSONObject pdbdata = (JSONObject) o;
//                    System.out.println("pdbdata" + pdbdata);
//                    int vmm_id = Integer.parseInt((String) pdbdata.get("vmm_id"));
//                    int dfm_id = Integer.parseInt((String) pdbdata.get("dfm_id"));
//                    String av_status = (String) pdbdata.get("status");
//                    PDBVersionGroup pvg = new PDBVersionGroup(pdb_id, vmm_id, dfm_id, av_status, button_type, "update");
//                    int pdbversiongroup_result = PDBOwnerDB.insertPDBVersionGroup(pvg);
//                    if (i++ == pdbdata_list.size() - 1) {
//                        if (button_type.equals("save")) {
//                            if (previousversion_flag == "true") {
//                                maps.put("status", "Record updated in same version and stored as Temporary");
//                            } else {
//                                maps.put("status", "Record updated successfully in same Temporary version");
//                            }
//                        } else {
//                            System.out.println("previousversion_flag" + previousversion_flag);
//                            if (status) {
//                                new NotificationController().createNotification(VersionType.pdbVersion.getVersionCode(), version_name, dtf.format(now), notification_to);
//                            }
//                            if (previousversion_flag == "false") {
//                                maps.put("status", "Record updated in same version and stored as permanent");
//                            } else {
//                                maps.put("status", "Record updated successfully in same Permanent version");
//                            }
//                        }
//                    }
//                }
//                PDBOwnerDB.deletePDBVersion_Group(pdb_id, "update");
//            } else {
//                PDBversion pv = new PDBversion((float) 1.0, status, flag, dtf.format(now), "create");
                //Create PDB version
                
                List<Pdbversion> version_data = pdbownerdb.GetVersionname();
                System.out.println("version_data"+version_data);
//                if(!version_data.isEmpty())
//                    version_name = (float) 1.0 + version_data..get(0get("pdb_versionname");
//                Pdbversion pdbversion = new Pdbversion();
//                pdbversion.setPdb_versionname();
//                pdbversion.setStatus(status);
//                pdbversion.setFlag(flag);
//                pdbversion.setCreated_date(new Date());
//                pdbversion.setModified_date(new Date());
//                pdbversion.setCreated_or_updated_by(vehicle_Repository.getUser(1));
//                pdbownerdb.insertPDBVersion(pdbversion);
                
//                Object[] id_version = PDBOwnerDB.insertPDBVersion(pv);
//                int pdb_id = (int) id_version[0];
//                version_name = (float) id_version[1];
//                int i = 0;
//                for (Object o : pdbdata_list) {
//                    JSONObject pdbdata = (JSONObject) o;
//                    System.out.println("pdbdata" + pdbdata);
//                    int vmm_id = Integer.parseInt((String) pdbdata.get("vmm_id"));
//                    int dfm_id = Integer.parseInt((String) pdbdata.get("dfm_id"));
//                    String av_status = (String) pdbdata.get("status");
//                    PDBVersionGroup pvg = new PDBVersionGroup(pdb_id, vmm_id, dfm_id, av_status, button_type, "create");
//                    int pdbversiongroup_result = PDBOwnerDB.insertPDBVersionGroup(pvg);
//                    if (i++ == pdbdata_list.size() - 1) {
//                        if (status) {
//                            new NotificationController().createNotification(VersionType.pdbVersion.getVersionCode(), version_name, dtf.format(now), notification_to);
//                        }
//                        if (pdbversiongroup_result == 0) {
//                            maps.put("status", "New Temporary PDB Version Created Successfully");
//                        } else {
//                            maps.put("status", "New Permanent PDB Version Created Successfully");
//                        }
//                    }
//                }
//            }
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
            maps.put("status", "Some error occurred !!");
        }
        return "success";
    }
}
