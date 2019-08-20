package com.ivn_1A.configs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;


public class JSONConfigure {
    static HttpServletRequest request;


    public static String getAngularJSONFile() {
        try {
            request = ServletActionContext.getRequest();
            String jsonValues = IOUtils.toString(request.getInputStream(), "UTF-8");
//				System.out.println("fileee "+filename);

            return jsonValues;


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }

    }

}
