/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.task;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ivn_1A.configs.JSONConfigure;
import com.ivn_1A.configs.VersionType;
import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;
import com.ivn_1A.models.task.Tasks;
import com.ivn_1A.models.task.TasksDB;
import com.ivn_1A.models.task.Tasks_Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;

/**
 * @author ETS06
 */
public class TasksController {

    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
    private List<Map<String, Object>> list_object = new ArrayList<>();
    private List<Tuple> tupleObjects = new ArrayList<>();
    Gson gson = new Gson();

    public String CreateTasks() {

        try {

            System.out.println("CreateTasks");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            Map<String, Object> obj = new HashMap<>();
            JsonNode pdbv = (JsonNode) readValue.get("pdbv");

            Tasks tasks = TasksDB.insertTasks(new Tasks("myTask", PDBOwnerDB.getVehicle(pdbv.get("vehicle_id").get("id").asInt()), VersionType.Pdbversion.name(), new Date()));
            if (tasks != null) {

                obj.put("pdbv", tasks);
                User user = PDBOwnerDB.getUser(1);
                String acceptedBy = user.getUsername() + "(" + pdbv.get("froms") + ")";
                String completedBy = user.getUsername() + "(" + pdbv.get("froms") + ")";
                String version = pdbv.get("froms").asText() + " version " + pdbv.get("pdb_versionname").asText();
                Tasks_Group tg = TasksDB.insertTasks_Group(new Tasks_Group(
                        tasks, pdbv.get("id").asInt(), version, true, acceptedBy, new Date(), true,
                        completedBy, new Date(), user, pdbv.get("froms").asText(), VersionType.Legislationversion.name())
                );

                if (tg != null) {

                    obj.put("pdbvg", tg);
                    maps_object.put("success", "Tasks_Group Work is Done");
                } else {
                    maps_object.put("failed", "Tasks_Group Work is Failed");
                }
            } else {
                maps_object.put("failed", "Tasks Work is Failed");
            }
            list_object.add(obj);
        } catch (Exception e) {
            System.err.println("Error in \"TasksController\" \'CreateTasks\' : " + e);
            maps_object.put("error", "Error in \"TasksController\" \'CreateTasks\' : " + e);
        }
        return "success";
    }

    public String getTasks() {

        try {

            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);
            String froms = readValue.get("froms").asText();
            System.out.println("Froms__________________________" + froms);
            List<Tasks_Group> list = TasksDB.getTasks(froms);
            list.stream().map((tg) -> {
                Map<String, Object> columns = new HashMap<>();

                if (tg.getTask_id().getCreated_name().equals(VersionType.Pdbversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());

                    columns.put("pdb", column);
                } else if (tg.getTask_id().getCreated_name().equals(VersionType.Legislationversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());

                    columns.put("legislation", column);
                } else if (tg.getTask_id().getCreated_name().equals(VersionType.Safetyversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());

                    columns.put("safety", column);
                } else if (tg.getTask_id().getCreated_name().equals(VersionType.Featureversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());

                    columns.put("feature", column);
                } else if (tg.getTask_id().getCreated_name().equals(VersionType.IVN_Version.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());

                    columns.put("ivn", column);
                } else {
                    columns.put("none", "Empty");
                }
                return columns;
            }).map((columns) -> {
                list_object.add(columns);
//                maps_object.put("tasks", columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums" + columns);
            });
            maps_string.put("success", "Work is done");
        } catch (Exception e) {
            System.err.println("Error in \"TasksController\" \'getTasks\' : " + e);
            maps_string.put("error", "Error in \"TasksController\" \'getTasks\' : " + e);
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

    public List<Map<String, Object>> getList_object() {
        return list_object;
    }

    public void setList_object(List<Map<String, Object>> list_object) {
        this.list_object = list_object;
    }

}
