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
            JsonNode val = (JsonNode) readValue.get("val");

            if (val.get("froms").asText().equals("PDB")) {

                System.out.println("CreateTasks");
                Tasks tasks = TasksDB.insertTasks(new Tasks("myTask", PDBOwnerDB.getVehicle(val.get("vehicle_id").get("id").asInt()), VersionType.Pdbversion.name(), new Date()));
                if (tasks != null) {

                    obj.put("pdbv", tasks);
                    User user = PDBOwnerDB.getUser(1);
                    String acceptedBy = user.getUsername() + "(" + val.get("froms").asText() + ")";
                    String completedBy = user.getUsername() + "(" + val.get("froms").asText() + ")";
                    String version = val.get("froms").asText() + " version " + val.get("pdb_versionname").asDouble();
                    Tasks_Group tg = TasksDB.insertTasks_Group(new Tasks_Group(
                            tasks, val.get("id").asInt(), version, true, acceptedBy, new Date(), true,
                            completedBy, new Date(), user, VersionType.Pdbversion.name(), VersionType.Legislationversion.name())
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
            } else {

                System.out.println("UpdateTasks");
                int tg_id = val.get("tg_id").asInt();

                Tasks_Group tasks_group = TasksDB.getTasks_GroupById(tg_id);
                tasks_group.setCompleted_by(PDBOwnerDB.getUser(1).getUsername() + "(" + val.get("froms").asText() + ")");
                tasks_group.setCompleted_date(new Date());
                tasks_group.setCompleted_status(true);
                tasks_group.setVersion_id(val.get("id").asInt());
                if (val.get("froms").asText().equals("Legislation"))
                    tasks_group.setVersion_name(val.get("froms").asText() + " version " + val.get("legislation_versionname").asDouble());
                else if (val.get("froms").asText().equals("Safety"))
                    tasks_group.setVersion_name(val.get("froms").asText() + " version " + val.get("safety_versionname").asDouble());
                else if (val.get("froms").asText().equals("Feature"))
                    tasks_group.setVersion_name(val.get("froms").asText() + " version " + val.get("feature_versionname").asDouble());
                else if (val.get("froms").asText().equals("IVN"))
                    tasks_group.setVersion_name(val.get("froms").asText() + " version " + val.get("ivn_versionname").asDouble());

                if (tasks_group != null) {
                    tasks_group = TasksDB.updateTasks_Group(tasks_group);
                    if (tasks_group != null) {

                        obj.put("legv", tasks_group.getTask_id());
                        obj.put("legvg", tasks_group);
                        maps_object.put("success", "Tasks_Group Work is Done");
                    } else {
                        maps_object.put("failed", "Tasks_Group Work is Failed");
                    }
                }
            }
            list_object.add(obj);
        } catch (Exception e) {
            System.err.println("Error in \"TasksController\" \'CreateTasks\' : " + e);
            maps_object.put("error", "Error in \"TasksController\" \'CreateTasks\' : " + e);
        }
        return "success";
    }

    public String createFirstLevelTask() {

        try {

            System.out.println("createFirstLevelTask");
            final ObjectMapper mapper = new ObjectMapper();
            String jsonValues = JSONConfigure.getAngularJSONFile();
            final JsonNode readValue = mapper.readValue(jsonValues, JsonNode.class);

            int t_id = readValue.get("t_id").asInt();
            String froms = readValue.get("froms").asText();
            boolean stt = readValue.get("stt").asText().equals("accept") ? true : false;
            String receiver = "";
            if (readValue.get("froms").asText().equals("Legislationversion"))
                receiver = VersionType.Safetyversion.name();
            else if (readValue.get("froms").asText().equals("Safetyversion"))
                receiver = VersionType.Featureversion.name();
            else if (readValue.get("froms").asText().equals("Featureversion"))
                receiver = VersionType.IVN_Version.name();
            else if (readValue.get("froms").asText().equals("IVN_Version"))
                receiver = VersionType.ACBversion.name();
            else
                receiver = "None";

            Tasks tasks = TasksDB.getTasksById(t_id);
            if (tasks != null) {
                User user = PDBOwnerDB.getUser(readValue.get("uid").asInt());
                String acceptedBy = user.getUsername() + "(" + froms + ")";
                Tasks_Group tg = TasksDB.insertTasks_Group(new Tasks_Group(
                        tasks, 0, "0.0", stt, acceptedBy, new Date(), false,
                        "none", new Date(), user, froms, receiver)
                );

                if (tg != null) {
                    maps_object.put("vals", mapper.writeValueAsString(tg));
                    maps_object.put("success", "Accepted or Rejected is Done");
                } else {
                    maps_object.put("failed", "Accepted or Rejected is Failed");
                }
            } else {
                maps_object.put("failed", "Accepted or Rejected init is Failed");
            }
        } catch (Exception e) {
            System.err.println("Error in \"TasksController\" \'createFirstLevelTask\' : " + e);
            maps_object.put("error", "Error in \"TasksController\" \'createFirstLevelTask\' : " + e);
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
            Map<String, Object> columns = new HashMap<>();
            ;
            List<Tasks_Group> list = TasksDB.getTasks(froms);
            list.stream().map((tg) -> {

                if (tg.getSender_id().equals(VersionType.Pdbversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());
                    column.put("task_id", tg.getTask_id().getId());
                    column.put("tg_id", tg.getId());

                    columns.put("pdb", column);
                } else if (tg.getSender_id().equals(VersionType.Legislationversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());
                    column.put("task_id", tg.getTask_id().getId());
                    column.put("tg_id", tg.getId());

                    columns.put("legislation", column);
                } else if (tg.getSender_id().equals(VersionType.Safetyversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());
                    column.put("task_id", tg.getTask_id().getId());
                    column.put("tg_id", tg.getId());

                    columns.put("safety", column);
                } else if (tg.getSender_id().equals(VersionType.Featureversion.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());
                    column.put("task_id", tg.getTask_id().getId());
                    column.put("tg_id", tg.getId());

                    columns.put("feature", column);
                } else if (tg.getSender_id().equals(VersionType.IVN_Version.name())) {

                    Map<String, Object> column = new HashMap<>();
                    column.put("name", tg.getVersion_name());
                    column.put("accepted_date", tg.getAccepted_date());
                    column.put("created_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("accepted_by", tg.getCreated_or_updated_by().getUsername());
                    column.put("acceptance_status", tg.isAccepted_status());
                    column.put("completion_status", tg.isCompleted_status());
                    column.put("completion_date", tg.getCompleted_date());
                    column.put("task_id", tg.getTask_id().getId());
                    column.put("tg_id", tg.getId());

                    columns.put("ivn", column);
                } else {
                    columns.put("none", "Empty");
                }
                return columns;
            }).map((col) -> {
                list_object.add(col);
                return col;
            }).forEachOrdered((col) -> {
                System.out.println("colums  " + col);
            });
            maps_object.put("tasks", columns);
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
