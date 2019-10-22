/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.notification;

import com.ivn_1A.configs.CookieRead;
import com.ivn_1A.configs.VersionType;
import com.ivn_1A.configs.VersionViewPage;
import com.ivn_1A.models.admin.User;
import com.ivn_1A.models.notification.Notification;
import com.ivn_1A.models.notification.NotificationDB;
import com.ivn_1A.models.notification.StatusNotification;
import com.ivn_1A.models.notification.StatusNotificationDB;
import com.ivn_1A.models.pdbowner.PDBOwnerDB;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 * @author ETS-05
 */
public class NotificationController implements SessionAware {

    private Map<String, String> maps_string = new HashMap<>();
    private Map<String, Object> maps_object = new HashMap<>();
    private List<Map<String, Object>> notification_result = new ArrayList<>();
    private List<Map<String, Object>> view_notification = new ArrayList<>();
    private List<Tuple> tempTuples = new ArrayList<>();
    private int notification_id;
    private SessionMap<String, Object> sessionMap;

    private static String getURLPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        String ctxPath = request.getContextPath();
        url = url.replaceFirst(uri, "");
        return url + ctxPath;
    }

    public void createNotification(int version_type_id, float version_name, String creation_date, String receiverId, int version_id) throws UnsupportedEncodingException {

        try {
            System.err.println("createNotification");
//            int senderId = CookieRead.getUserIdFromSession();
//            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println(PDBOwnerDB.getUser(1) + " " + receiverId + " " + version_type_id + " " + PDBOwnerDB.getPdbversionByName(version_name) + " " + creation_date);
            System.err.println(PDBOwnerDB.getUser(1) + " " + receiverId + " " + version_type_id + " " + version_name + " " + creation_date + " " + version_id);
            Notification notification = new Notification();
            notification.setSender_id(PDBOwnerDB.getUser(1));
            notification.setReceiver_id(receiverId);
            notification.setVersion_type_id(version_type_id);
            notification.setCreated_date(new Date());
            notification.setVersion_name(version_name);
            notification.setVersion_id(version_id);
            int a = NotificationDB.insertNotification(notification);
            System.err.println("createdNotification res : " + a);
            maps_string.put("success", "Notification Created");
        } catch (Exception e) {
            System.err.println("Error in \"createNotification\" : " + e);
            maps_string.put("failed", "Error Notification not Created");
        }
//        List<String> emailList = UserDB.getEmailListforNotification(senderId, receiverId);
//        String versionLink = NotificationController.getURLPath(request) + "/" + VersionViewPage.fromId(version_type_id) + ".action?id=" + NotificationDB.getVersionId(VersionType.fromId(version_type_id), version_name) + "&action=view";
//        if (!emailList.isEmpty()) {
//            MailUtil.sendNotificationMail(emailList, "Notification Email", notification, versionLink);
//        }
    }

    public String unreadNotification() {

        try {
            System.err.println("unreadNotification");
//            int userid = CookieRead.getUserIdFromSession();
            User user = PDBOwnerDB.getUser(1);
            sessionMap.put("user", user);
            System.out.println("User added in session");
            int userid = user.getId();
            System.err.println("Notification Area " + userid);
            tempTuples = NotificationDB.getNotificationList(userid);
            tempTuples.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                columns.put("id", tuple.get("id"));
                columns.put("status", NotificationDB.getReadStatusForNotification(userid, (int) tuple.get("id")));
                columns.put("firstname", tuple.get("firstname"));
                columns.put("version_type_id", VersionType.fromId(Integer.parseInt(tuple.get("version_type_id").toString())));
                columns.put("version_id", tuple.get("version_id"));
                columns.put("created_date", tuple.get("created_date"));
                return columns;
            }).map((columns) -> {
                notification_result.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums___________" + columns);
            });
            maps_string.put("success", "Unread notification fetched");
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
            maps_string.put("failed", "Error Unread notification fetched");
        }
        return "success";
    }

    public String readNotification() {

        try {
            System.err.println("readNotification");
//            int userid = CookieRead.getUserIdFromSession();
            tempTuples = NotificationDB.readNotification(getNotification_id());
            tempTuples.stream().map((tuple) -> {
                Map<String, Object> columns = new HashMap<>();
                String vType = VersionType.fromId(Integer.parseInt(tuple.get("version_type_id").toString())).toString();
                switch (vType) {
                    case "Pdbversion":
                        columns.put("version_type", "create_pdb");
                        break;
                    case "Legislationversion":
                        columns.put("version_type", "legi_ver_create");
                        break;
                    case "Safetyversion":
                        columns.put("version_type", "safety_ver_create");
                        break;
                    case "Featureversion":
                        columns.put("version_type", "feature_version_create");
                        break;
                    default:
                        columns.put("version_type", vType);
                        break;
                }
                columns.put("version_id", tuple.get("version_id"));
                return columns;
            }).map((columns) -> {
                view_notification.add(columns);
                return columns;
            }).forEachOrdered((columns) -> {
                System.out.println("colums___________" + columns);
            });
            StatusNotification sn = new StatusNotification();
            sn.setNotification_id(NotificationDB.getNotification(getNotification_id()));
            sn.setReceiver_id(1);
            StatusNotificationDB.insertStatus(sn);
            maps_string.put("success", "Read notification fetched");
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
            maps_string.put("failed", "Error Read notification fetched");
        }
        return "success";
    }

    public List<Map<String, Object>> getNotification_result() {
        return notification_result;
    }

    public void setNotification_result(List<Map<String, Object>> notification_result) {
        this.notification_result = notification_result;
    }

    public List<Map<String, Object>> getView_notification() {
        return view_notification;
    }

    public void setView_notification(List<Map<String, Object>> view_notification) {
        this.view_notification = view_notification;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
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

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }
}
