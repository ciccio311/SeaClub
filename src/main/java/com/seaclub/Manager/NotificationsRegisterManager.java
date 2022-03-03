package com.seaclub.Manager;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Notification;
import com.seaclub.Model.NotificationsRegister;
import com.seaclub.Model.StorageRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a model for managing a list of notification register
 */
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

    /**
     * Method used to return the list of notification register
     * @return notification register lists
     */
    public List<NotificationsRegister> getRegisters() {
        return registers;
    }

    /**
     * Method used to set the registers
     * @param registers list of notification register to set
     */
    public void setRegisters(List<NotificationsRegister> registers) {
        this.registers = registers;
    }

    /**
     * Method used to update the list of notification register
     */
    public void updateList(){
        try {
            DB.getInstance().getNotificatioinRegister();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Used add new Notification register to database.
     * @param notificationsRegister Represents the new Notification register to add.
     * @return returns true if the executive was successful.
     **/
    public boolean addNewNotificationRegister(NotificationsRegister notificationsRegister){
        try {
            //l utente 1 è un utente X che usiamo per associare le barche elliminate dagli utenti
            if(notificationsRegister.getIdMember()!=1) {
                if (DB.getInstance().addNotificationRegister(notificationsRegister)) {
                    updateList();
                    return true;
                } else
                    return false;
            }else
                return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Used for getting the club member's notification
     * @param id Represents clubMember's id
     * @return if exist returns the notification of specific clubMember otherwise return null
     **/
    public String getNotificationById(int id){
        try {
            updateList();
            String notify1 = "";
            String notify2 = "";
            for (var x : registers) {
                if (x.getIdMember() == id) {
                    if(DB.getInstance().getNotification(x.getIdNotification())) {
                        if (notification.getId() == 1) {
                            notify1 = x.getDateSender().toString() + " " + notification.getName() + " ";
                        }
                        if (notification.getId() == 2) {
                            notify2 = x.getDateSender().toString() + " " + notification.getName() + " " +
                                    x.getInfo();
                        }
                    }
                    else
                        return null;
                }
            }
            if (notify1.equals("") && notify2.equals(""))
                return null;
            return notify1 + "\n" + notify2;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * Method used to return the notification
     * @return the notification
     */
    public Notification getNotification() {
        return notification;
    }

    /**
     * Method used to set the notification
     * @param notification is the notification to set
     */
    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    /**
     * Method used for updating notification if a club member pays an expired storage quote or
     * remove a boat with storage quote expired
     * @param clubMember the club member that pays the expired storage quote or remove the boat with expired quote
     * @return true if the operation was successfully otherwise false
     */
    public Boolean updateNotificationStorage(ClubMember clubMember){
        try {
            updateList();
            String info = "";
            int idSocio = clubMember.getId();
            for (var x : registers) {
                if (x.getIdMember() == idSocio && x.getIdNotification() == 2) {
                    info = x.getInfo();
                    String boats[] = info.split(" - ");
                    //If there are some boats with storage expired, it update the info, else it delete the notification
                    if(boats.length>1){
                        for (var id : boats) {
                            clubMember.getBoatExpired().removeIf(n -> n.getId() == Integer.valueOf(id));
                        }
                    }
                    else{
                        DB.getInstance().deleteNotificationRegisterStorage(idSocio);
                        return true;
                    }
                }
            }
            info = "";
            for (var x : clubMember.getBoatExpired()) {
                info = info + x.getId() + " - ";
            }
            info = info.replaceFirst(".$", "");
            info = info.replaceFirst(".$", "");
            info = info.replaceFirst(".$", "");
            DB.getInstance().updateNotificationRegisterStorage(clubMember.getId(), info);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    /**
     * Method used for deleting the notification of membership quote expired of specific club member
     * @param clubMember the specific club member
     * @return true if the operation was successfully otherwise false
     */
    public Boolean deleteNotificationMembership(ClubMember clubMember){
        try {
            updateList();
            int idSocio = clubMember.getId();
            for (var x : registers) {
                if (x.getIdMember() == idSocio && x.getIdNotification() == 1) {
                    DB.getInstance().deleteNotificationRegisterMembership(idSocio);
                    return true;
                }
            }
            return false;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}