package com.seaclub.Model;

import java.util.Date;

public class NotificationsRegister {

    private int id;
    private int idMember;
    private int idNotification;
    private Date dateSender;
    private String info;

    public NotificationsRegister(int id, String info, Date dates,int idmember,int idnotify){
        this.id=id;
        this.idMember = idmember;
        this.dateSender = dates;
        this.info = info;
        this.idNotification = idnotify;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDateSender() {
        return dateSender;
    }

    public int getIdMember() {
        return idMember;
    }

    public String getInfo() {
        return info;
    }

    public void setDateSender(Date dateSender) {
        this.dateSender = dateSender;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }
}
