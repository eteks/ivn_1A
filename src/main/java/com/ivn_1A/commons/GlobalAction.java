/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.commons;

import com.ivn_1A.configs.VersionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;

/**
 *
 * @author ETS-05
 */
public class GlobalAction {

    public String count;
    private List<Map<String, Object>> group_result = new ArrayList<>();
    private List<Tuple> tempTuples = new ArrayList<>();
    private Map<String, Integer> count_result = new HashMap<>();

    public String GetDashboardData() {
        System.out.println("GetDashboardData");
        try {
            count_result = GlobalDBActivities.GetModuleCount();
            System.out.println("count_result" + count_result);
            //Get user groupss data
            tempTuples = GlobalDBActivities.GetUserGroups();
            tempTuples.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tuple.get("id"));
                columns.put("group_name", tuple.get("group_name"));
                columns.put("status", tuple.get("status"));
                columns.put("route_pages", tuple.get("route_pages"));
                columns.put("is_superadmin", tuple.get("is_superadmin"));
                return columns;
            }).map((columns) -> {
                group_result.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums___________" + columns);
            });
            System.out.println("group_result" + group_result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "success";
    }

    public List<Map<String, Object>> getGroup_result() {
        return group_result;
    }

    public void setGroup_result(List<Map<String, Object>> group_result) {
        this.group_result = group_result;
    }

    public Map<String, Integer> getCount_result() {
        return count_result;
    }

    public void setCount_result(Map<String, Integer> count_result) {
        this.count_result = count_result;
    }

}
