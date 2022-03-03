package com.seaclub.Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * The class {@code NotificationaRegister} provides a register of the notification.
 *
 **/
public class NotificationsRegister implements Serializable {

    private int id;
    private int idMember;
    private int idNotification;
    private Date dateSender;
    private String info;

    /**
     * First class constructor
     */
    public NotificationsRegister(){}

    /**
     * @param id the register notification's id
     * @param info the register notification's info
     * @param dates the register notification's date
     * @param idmember the club member's id
     * @param idnotify the notification's id
     */
    public NotificationsRegister(int id, String info, Date dates,int idmember,int idnotify){
        this.id=id;
        this.idMember = idmember;
        this.dateSender = dates;
        this.info = info;
        this.idNotification = idnotify;
    }

    /**
     * Sets the notification register's id
     * @param id is the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the notification register's id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the notification register's date
     * @return the date
     */
    public Date getDateSender() {
        return dateSender;
    }

    /**
     * Gets the club member's id
     * @return the club member's id
     */
    public int getIdMember() {
        return idMember;
    }

    /**
     * Gets the notification register's info
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the notification register's date
     * @param dateSender is the date
     */
    public void setDateSender(Date dateSender) {
        this.dateSender = dateSender;
    }

    /**
     * Sets the club member's id
     * @param idMember is the id
     */
    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    /**
     * Sets the notification register's info
     * @param info is the information
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Gets the notification's id
     * @return the notification's id
     */
    public int getIdNotification() {
        return idNotification;
    }

    /**
     * Sets the notification's id
     * @param idNotification is the id
     */
    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }
}
