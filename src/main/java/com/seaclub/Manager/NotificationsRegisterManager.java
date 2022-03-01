package com.seaclub.Manager;

import com.seaclub.Model.Notification;
import com.seaclub.Model.NotificationsRegister;
import com.seaclub.Model.StorageRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

public class NotificationsRegisterManager {

    private List<NotificationsRegister> registers;

    private Notification notification;

    private static NotificationsRegisterManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private NotificationsRegisterManager(){
        registers = new ArrayList<NotificationsRegister>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static NotificationsRegisterManager getInstance() {
        if (instance == null)
            instance = new NotificationsRegisterManager();
        return instance;
    }

    public List<NotificationsRegister> getRegisters() {
        return registers;
    }

    public void setRegisters(List<NotificationsRegister> registers) {
        this.registers = registers;
    }

    public void updateList(){
        try {
            DB.getInstance().getNotificatioinRegister();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addNewNotificationRegister(NotificationsRegister notificationsRegister){
        DB.getInstance().addNotificationRegister(notificationsRegister);
        updateList();
    }

    public String getNotificationById(int id){
        updateList();
        String notify1="";
        String notify2="";
        for(var x:registers){
            if(x.getIdMember()==id){
                DB.getInstance().getNotification(x.getIdNotification());
                if(notification.getId()==1){
                     notify1 = x.getDateSender().toString()+" "+notification.getName()+" ";
                }
                if(notification.getId()==2){
                     notify2 = x.getDateSender().toString()+" "+notification.getName()+" "+
                            x.getInfo();
                }
            }
        }

        return notify1+"\n"+notify2;

    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
