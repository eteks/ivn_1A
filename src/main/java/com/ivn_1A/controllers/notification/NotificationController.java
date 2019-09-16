/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.controllers.notification;

import com.ivn_1A.configs.CookieRead;
import com.ivn_1A.configs.VersionType;
import com.ivn_1A.configs.VersionViewPage;
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

/**
 *
 * @author ETS-05
 */
public class NotificationController {

    private List<Map<String, Object>> notification_result = new ArrayList<>();
    private List<Map<String, Object>> view_notification = new ArrayList<>();
    private List<Tuple> tempTuples = new ArrayList<>();
    private int notification_id;

    private static String getURLPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        String ctxPath = request.getContextPath();
        url = url.replaceFirst(uri, "");
        return url + ctxPath;
    }

    public void createNotification(int version_type_id, float version_name, String creation_date, String receiverId) throws UnsupportedEncodingException {

        try {
            System.err.println("createNotification");
//            int senderId = CookieRead.getUserIdFromSession();
//            HttpServletRequest request = ServletActionContext.getRequest();
            System.err.println(PDBOwnerDB.getUser(1)+" "+receiverId+" "+version_type_id+" "+PDBOwnerDB.getPdbversionByName(version_name)+" "+creation_date);
            Notification notification = new Notification();
            notification.setSender_id(PDBOwnerDB.getUser(1));
            notification.setReceiver_id(receiverId);
            notification.setVersion_type_id(version_type_id);
            notification.setVersion_id(PDBOwnerDB.getPdbversionByName(version_name));
            notification.setCreated_date(new Date());
            int a = NotificationDB.insertNotification(notification);
            System.err.println("createdNotification res : " + a);
        } catch (Exception e) {
            System.err.println("Error in \"createNotification\" : " + e);
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
            int userid = PDBOwnerDB.getUser(1).getId();
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
        } catch (Exception ex) {
            System.out.println("entered into catch");
            System.out.println(ex.getMessage());
        }
        return "success";
    }

    public String readNotification() {

        System.err.println("readNotification");
        int userid = CookieRead.getUserIdFromSession();
        tempTuples = NotificationDB.readNotification(getNotification_id());
        tempTuples.stream().map((tuple) -> {
            Map<String, Object> columns = new HashMap<>();
            columns.put("version_type", VersionType.fromId(Integer.parseInt(tuple.get("version_type_id").toString())));
            columns.put("version_id", tuple.get("version_id"));
            return columns;
        }).map((columns) -> {
            notification_result.add(columns);
            return columns;
        }).forEachOrdered((columns) -> {
            System.out.println("colums___________" + columns);
        });
        StatusNotification sn = new StatusNotification();
        sn.setNotification_id(NotificationDB.getNotification(getNotification_id()));
        sn.setReceiver_id(userid);
        StatusNotificationDB.insertStatus(sn);
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

}
