/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.legislation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ETS-05
 */
public class Legislation_Combination {

    private Map<String, String> maps = new HashMap<String, String>();
    private List<Map<String, Object>> legcomb_result = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> result_data = new ArrayList<Map<String, Object>>();
    public String result_data_obj;
    static HttpServletRequest request;

    public String CreateLegComb() {

        return "success";
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public List<Map<String, Object>> getResult_data() {
        return result_data;
    }

    public void setResult_data(List<Map<String, Object>> result_data) {
        this.result_data = result_data;
    }

    public String getResult_data_obj() {
        return result_data_obj;
    }

    public void setResult_data_obj(String result_data_obj) {
        this.result_data_obj = result_data_obj;
    }

}
