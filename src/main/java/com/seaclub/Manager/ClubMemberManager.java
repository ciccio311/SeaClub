package com.seaclub.Manager;

import com.seaclub.Model.ClubMember;
import com.seaclub.Model.MembershipRegister;
import com.seaclub.Model.NotificationsRegister;
import com.seaclub.client.Client;
import com.seaclub.server.DB;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class implements a model for managing list of club member
 */
public class ClubMemberManager {

    private List<ClubMember> members;

    private static ClubMemberManager instance = null;

    /**
     * The constructor is private so it is accessible only within the class.
     **/
    private ClubMemberManager(){
        members = new ArrayList<ClubMember>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static ClubMemberManager getInstance() {
        if (instance == null)
            instance = new ClubMemberManager();
        return instance;
    }

    /**
     * @return the members
     */
    public List<ClubMember> getMembers() {
        return this.members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(List<ClubMember> members) {
        this.members = members;
    }


    /**
     * Method used for updating information about all club member in members list
     */
    public void updateList(){
        try {
            DB.getInstance().getClubMembers();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Method used for adding new club member to database.
     * @param member Represents the new member to add.
     * @return returns true if the executive was successful.
     **/
    public boolean addMember(ClubMember member){
        try{
            if(checkCFalreadyExist(member))
                throw new Exception("CF already exist!");
            else{
                DB.getInstance().addNewMember(member);
                updateList();
                return true;
            }
        }catch (Exception e){

            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Used for login
     * @param cm Represents the new clubMember that try to login
     * @return returns clubmember logged if the credentials are correct otherwise return null
     **/
    public ClubMember login(ClubMember cm){
        updateList();
        for(var x:this.members){
            if(x.getId()==cm.getId() && x.getPassword().equals(cm.getPassword())){
                return x;
            }
        }
        return null;
    }

    /**
     * Method used for getting a club member with the information updated
     * @param clubMember the club member with old information
     * @return the club member with the information updated
     */
    public ClubMember updateClubMember(ClubMember clubMember){
        updateList();
        for(var x:this.members){
            if(x.getId() == clubMember.getId())
                return x;
        }
        return null;
    }

    /**
     * Method used for getting the list of club member with the quote membership expired
     * @return the list of club member with the quote membership expired
     */
    public List<ClubMember> clubMemberExpired(){
        try {
            updateList();
            MembershipRegister membershipRegister = new MembershipRegister();
            List<NotificationsRegister> notificationsRegisters = new ArrayList<NotificationsRegister>();
            NotificationsRegisterManager.getInstance().updateList();
            MembershipRegisterManager.getInstance().updateList();
            List<ClubMember> expired = new ArrayList<ClubMember>();
            notificationsRegisters = NotificationsRegisterManager.getInstance().getRegisters();

            for (var x : this.members) {
                membershipRegister = x.getLastPaymentQuote(MembershipRegisterManager.getInstance().getMembershipRegisters());
                if (membershipRegister != null) {
                    //default time zone
                    ZoneId defaultZoneId = ZoneId.systemDefault();

                    LocalDate now = LocalDate.now();
                    LocalDate dateMinusYear = now.minusYears(1);
                    Date dateNow = Date.from(dateMinusYear.atStartOfDay(defaultZoneId).toInstant());

                    if (membershipRegister.getDatePayment().before(dateNow)) {
                        //expired
                        expired.add(x);
                    }
                } else {
                    expired.add(x);
                }
            }
            for (var notify : notificationsRegisters) {
                expired.removeIf(n -> (n.getId() == notify.getIdMember() && notify.getIdNotification() == 1));
            }
            return expired;
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * Method used for check if already exist a club member with a specific CF
     * @param clubMember club member with the specific CF
     * @return returns true if the executive was successful.
     **/
    public boolean checkCFalreadyExist(ClubMember clubMember){
        updateList();
        for(var x:members){
            if(x.getCF().equals(clubMember.getCF()))
                return true;
        }
        return false;
    }


    /**
     * Used for get the clubMember by CF
     * @param clubMember Represents the clubMember with the spefific CF
     * @return if exist returns the club member with the CF passed otherwise return null
     **/
    public ClubMember getMemberByCF(ClubMember clubMember){
        updateList();
        for(var x:this.members){
            if(x.getCF().equals(clubMember.getCF()))
                return x;
        }
        return null;
    }

    /**
     * Used to send all Notification boat expired to all users.
     * @return returns true if the executive was successful.
     **/
    public boolean sendNotificationMembershipExpired(){
        try {
            List<ClubMember> memberExpired = new ArrayList<ClubMember>();
            memberExpired = clubMemberExpired();
            boolean check = false;
            if (!(memberExpired.size() == 0) && memberExpired != null) {
                for (var x : memberExpired) {
                    NotificationsRegister notificationsRegister = new NotificationsRegister();
                    notificationsRegister.setIdNotification(1);
                    notificationsRegister.setIdMember(x.getId());
                    notificationsRegister.setInfo("");
                    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    notificationsRegister.setDateSender(sqlDate);

                    check = NotificationsRegisterManager.getInstance().addNewNotificationRegister(notificationsRegister);
                }
                if (check)
                    return true;
                else
                    return false;
            } else
                return false;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
    }



    /**
     * Used to send all Notification boat expired to all users.
     * @return returns true if the executive was successful.
     **/
    public boolean sendNotificationBoatExpired() {
        try {
            updateList();
            List<NotificationsRegister> notificationsRegisters = new ArrayList<NotificationsRegister>();
            notificationsRegisters = NotificationsRegisterManager.getInstance().getRegisters();
            boolean checks=false;
            String idBoat = "";
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            for (var member : members) {
                checks=false;
                //check if the notification already exist, if doesn't exist send notification
                for(var notify:notificationsRegisters){
                    if(member.getId() == notify.getIdMember() && notify.getIdNotification()==2){
                        checks=true;
                    }
                }
                if(member.getBoatExpired().size()>0 && checks==false) {
                    for (var boat : member.getBoatExpired()) {
                        idBoat = idBoat + String.valueOf(boat.getId()) + " - ";

                    }
                    idBoat = idBoat.replaceFirst(".$","");
                    idBoat = idBoat.replaceFirst(".$","");
                    idBoat = idBoat.replaceFirst(".$","");
                    NotificationsRegister notificationsRegister = new NotificationsRegister();
                    notificationsRegister.setIdNotification(2);
                    notificationsRegister.setIdMember(member.getId());
                    notificationsRegister.setInfo(idBoat);
                    notificationsRegister.setDateSender(sqlDate);
                    NotificationsRegisterManager.getInstance().addNewNotificationRegister(notificationsRegister);
                    idBoat="";
                }

            }
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

}