package com.seaclub.server;

import com.seaclub.Communication.Message;
import com.seaclub.Manager.*;
import com.seaclub.Model.*;

public class MessageProcessing {

    /*
     * The instance is static so it is shared among all instances of the class. It is also private
     * so it is accessible only within the class.
     */
    private static MessageProcessing instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private MessageProcessing() {

    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static MessageProcessing getInstance() {
        if (instance == null)
            instance = new MessageProcessing();
        return instance;
    }

    public Message getActionFromRequest(Message mex){
        try{
            if(mex.getAction().equals(mex.getActionNewUserRegistration()))
                return addMember(mex);
            if(mex.getAction().equals(mex.getActionLogin()))
                return login(mex);
            if(mex.getAction().equals(mex.getActionAddBoat()))
                return addNewBoat(mex);
            if(mex.getAction().equals(mex.getActionRaces()))
                return getCompetitions(mex);
            if(mex.getAction().equals(mex.getActionAddRace()))
                return addNewCompetition(mex);
            if(mex.getAction().equals(mex.getActionAddBoatToCompetition()))
                return addBoatToCompetition(mex);
            if(mex.getAction().equals(mex.getActionCompetitionRegisterByMemberId()))
                return getActionCompetitionRegisterByMemberId(mex);
            if(mex.getAction().equals(mex.getACTION_GET_COMPETITION_REGISTER()))
                return getCompetitionRegister(mex);
            if(mex.getAction().equals(mex.getACTION_UPDATE_MEMBER()))
                return updateMemberInfo(mex);
            if(mex.getAction().equals(mex.getActionGetMembershipFee()))
                return getMembershipQuoteRegister(mex);
            if(mex.getAction().equals(mex.getACTION_ADD_MEMBERSHIP_REGISTER_QUOTE()))
                return addMembershipRegisterQuote(mex);
            if(mex.getAction().equals(mex.getACTION_UPDATE_USER_INFO()))
                return updateUserInfo(mex);
            if(mex.getAction().equals(mex.getACTION_GET_STORAGE_REGISTER_QUOTE()))
                return getStorageRegisterQuote(mex);
            if(mex.getAction().equals(mex.getACTION_GET_LAST_STORAGE_REGISTER()))
                return getLastStorageRegister(mex);
            if(mex.getAction().equals(mex.getACTION_ADD_STORAGE_REGISTER()))
                return addNewStorageRegister(mex);
            if(mex.getAction().equals(mex.getActionRemoveBoat()))
                return deleteBoat(mex);
            if(mex.getAction().equals(mex.getACTION_GET_NOTIFICATION_REGISTER()))
                return getNotificationRegister(mex);
            if(mex.getAction().equals(mex.getACTION_GET_NOTIFICATION()))
                return getNotification(mex);
            if(mex.getAction().equals(mex.getACTION_GET_CLUBMEMBER_EXPIRED()))
                return getMemberExpired(mex);
            if(mex.getAction().equals(mex.getACTION_ADD_NOTIFICATION_REGISTER()))
                return addNewNotificationRegister(mex);
            if(mex.getAction().equals(mex.getACTION_GET_CLUBMEMBER_BY_CF()))
                return getMemberByCF(mex);
            if(mex.getAction().equals(mex.getACTION_SEND_NOTIFICATIONS_BOAT_EXPIRED()))
                return sendNotificationBoatExpired(mex);
            if(mex.getAction().equals(mex.getACTION_UPDATE_NOTIFICATION_STORAGE()))
                return updateNotificationStorage(mex);
            if(mex.getAction().equals(mex.getACTION_DELETE_NOTIFICATION_MEMBERSHIP()))
                return deleteNotificationMembership(mex);
            return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Message addMember(Message mex){
        ClubMember member = (ClubMember) mex.getValue();

        if(ClubMemberManager.getInstance().addMember(member))
            return new Message("Aggiunto new member", true);
        else
            return new Message("member doesn' t added!", false);

    }

    public Message login(Message mex){
        ClubMember member = (ClubMember) mex.getValue();
        ClubMember loggedMember = ClubMemberManager.getInstance().login(member);
        if(loggedMember != null)
            return new Message("User logged", loggedMember);
        else
            return new Message("User doesn t exist", null);
    }

    public Message addNewBoat(Message mex){
        if(mex.getValue() instanceof Boat == false)
            return null;

        Boat boat = (Boat) mex.getValue();
        BoatManager.getInstance().addBoat(boat);
        return new Message("New boat added!",null);
    }

    public Message getCompetitions(Message mex){
        CompetitionManager.getInstance().updateList();
        return new Message("Get all competitions.",CompetitionManager.getInstance().getCompetitionList());
    }

    public Message addNewCompetition(Message mex){
        if(mex.getValue() instanceof Competition == false)
            return null;

        Competition cp = (Competition) mex.getValue();
        CompetitionManager.getInstance().addCompetition(cp);
        return new Message("New competition added!",null);
    }

    public Message addBoatToCompetition(Message mex){
        if(mex.getValue() instanceof CompetitionRegister == false)
            return null;

        CompetitionRegister cp = (CompetitionRegister) mex.getValue();
        CompetitionManager.getInstance().addBoatToCompetition(cp);
        return new Message("New boat to competition added!",null);
    }

    public Message getActionCompetitionRegisterByMemberId(Message mex){

        DB.getInstance().getCompetitionRegisterByMemberId((int)mex.getValue());
        return new Message("Get competition register by user's id -> "+mex.getValue(),CompetitionManager.getInstance().getcRegister());
    }

    public Message getCompetitionRegister(Message mex){
        CompetitionRegisterManager.getInstance().updateList();
        return new Message("Get all competition register",CompetitionRegisterManager.getInstance().getCompetitionRegisters());
    }

    public Message updateMemberInfo(Message mex){
        if(mex.getValue() instanceof ClubMember == false)
            return null;
        DB.getInstance().updateUser((ClubMember) mex.getValue());
        return new Message("User modified", null);
    }

    public Message getMembershipQuoteRegister(Message mex){
        MembershipRegisterManager.getInstance().updateList();
        return new Message("Get all MembershipQuoteRegister", MembershipRegisterManager.getInstance().getMembershipRegisters());
    }

    public Message addMembershipRegisterQuote(Message mex){
        if(mex.getValue() instanceof MembershipRegister == false)
            return null;
        MembershipRegisterManager.getInstance().addMembershipRegisterQuote((MembershipRegister) mex.getValue());
        return new Message("New membership quote added", null);
    }

    public Message updateUserInfo(Message mex){
        if(mex.getValue() instanceof ClubMember == false)
            return null;
        return new Message("User info updated!",ClubMemberManager.getInstance().updateClubMember((ClubMember) mex.getValue()));
    }

    public Message getStorageRegisterQuote(Message mex){
        StorageRegisterManager.getInstance().updateList();
        return new Message("Get all Storage Register Quote!",StorageRegisterManager.getInstance().getRegisters());
    }

    public Message getLastStorageRegister(Message mex){
        if(mex.getValue() instanceof Boat == false)
            return null;
        return new Message("Get last storage register!",StorageRegisterManager.getInstance().getLastStorageRegisterBoat((Boat) mex.getValue()));
    }

    public Message addNewStorageRegister(Message mex){
        if(mex.getValue() instanceof StorageRegister == false)
            return null;
        StorageRegisterManager.getInstance().addNewStorageRegister((StorageRegister) mex.getValue());
        return new Message("New storage register added!",null);
    }

    public Message deleteBoat(Message mex){
        if(mex.getValue()instanceof Boat==false)
            return null;
        BoatManager.getInstance().removeBoat((Boat) mex.getValue());
        return new Message("Boat removed!",null);
    }

    public Message getNotificationRegister(Message mex){
        NotificationsRegisterManager.getInstance().updateList();
        return new Message("Get all Notification Register!",NotificationsRegisterManager.getInstance().getRegisters());
    }

    public Message getNotification(Message mex){
        if(mex.getValue()instanceof Integer == false)
            return null;
        return new Message("Get notification to client!",NotificationsRegisterManager.getInstance().getNotificationById((Integer)mex.getValue()));
    }

    public Message getMemberExpired(Message mex){
        return new Message("Get member expired!",ClubMemberManager.getInstance().clubMemberExpired());
    }

    public Message addNewNotificationRegister(Message mex){
        if(mex.getValue() instanceof NotificationsRegister == false)
            return null;
        NotificationsRegisterManager.getInstance().addNewNotificationRegister((NotificationsRegister) mex.getValue());
        return new Message("New notification register added!",null);
    }

    public Message getMemberByCF(Message mex){
        if(mex.getValue()instanceof ClubMember == false)
            return null;
        return new Message("Get member by CF!",ClubMemberManager.getInstance().getMemberByCF((ClubMember) mex.getValue()));
    }

    public Message sendNotificationBoatExpired(Message mex){
        ClubMemberManager.getInstance().sendNotificationBoatExpired();
        return new Message("Notifications Send!",null);
    }

    public Message updateNotificationStorage(Message mex){
        return new Message("Update notification storage", NotificationsRegisterManager.getInstance().updateNotificationStorage((ClubMember) mex.getValue()));
    }

    public Message deleteNotificationMembership(Message mex){
        return new Message("Delete notification membership", NotificationsRegisterManager.getInstance().deleteNotificationMembership((ClubMember) mex.getValue()));
    }

}
