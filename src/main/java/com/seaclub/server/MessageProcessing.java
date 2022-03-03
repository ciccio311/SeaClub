package com.seaclub.server;

import com.seaclub.Communication.Message;
import com.seaclub.Manager.*;
import com.seaclub.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for processing request from the client, and create the relative response for each request
 */
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

    /**
     * Method used to processing the request
     * @param mex is the request from the client
     * @return the correct function
     */
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
            if(mex.getAction().equals(mex.getActionGetAllPartners()))
                return getAllPartners(mex);
            if(mex.getAction().equals(mex.getACTION_GET_ALL_BOATS()))
                return getAllBoats(mex);
            if(mex.getAction().equals(mex.getACTION_GET_ALL_COMPETITIONS()))
                return getCompetitions(mex);
            if(mex.getAction().equals(mex.getACTION_COMPETITION_REGISTER_BY_ID_COMP()))
                return getCompetitionRegisterByIdComp(mex);
            if(mex.getAction().equals(mex.getACTION_GE_COMPETITION_AVAILABLE()))
                return getCompetitionAvailableByMemberId(mex);
            if(mex.getAction().equals(mex.getACTION_SEND_NOTIFICATIONS_MEMBERSHIP_EXPIRED()))
                return sendNotificationsMembershipExpired(mex);
            return null;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * @param mex is the request from the client for adding a new member
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message addMember(Message mex){
        if(!(mex.getValue() instanceof ClubMember))
            return new Message("Member doesn' t added!", false);

        ClubMember member = (ClubMember) mex.getValue();
        if(ClubMemberManager.getInstance().addMember(member))
            return new Message("Aggiunto new member", true);
        else
            return new Message("Member doesn' t added!", false);

    }

    /**
     * @param mex is the request from the client for the login
     * @return the correct message with the value of the operation (LOGGEDMEMBER->successfully; NULL->not successfully)
     */
    public Message login(Message mex){
        if(!(mex.getValue() instanceof ClubMember))
            return new Message("User doesn t exist", null);

        ClubMember member = (ClubMember) mex.getValue();
        ClubMember loggedMember = ClubMemberManager.getInstance().login(member);
        if(loggedMember != null)
            return new Message("User logged", loggedMember);
        else
            return new Message("User doesn t exist", null);
    }

    /**
     * @param mex is the request from the client for adding a new boat
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message addNewBoat(Message mex){
        if(!(mex.getValue() instanceof Boat))
            return new Message("New boat NOT added!",false);

        Boat boat = (Boat) mex.getValue();
        if(BoatManager.getInstance().addBoat(boat))
            return new Message("New boat added!",true);
        else
            return new Message("New boat NOT added!",false);
    }

    /**
     * @param mex is the request from the client to return all the competitions
     * @return the correct message with the value of the operation (competitionList)
     */
    public Message getCompetitions(Message mex){
        CompetitionManager.getInstance().updateList();
        return new Message("Get all competitions.",CompetitionManager.getInstance().getCompetitionList());
    }

    /**
     * @param mex is the request from the client for adding a new competition
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message addNewCompetition(Message mex){
        if(!(mex.getValue() instanceof Competition))
            return new Message("New competition NOT added!",false);

        Competition cp = (Competition) mex.getValue();
        if(CompetitionManager.getInstance().addCompetition(cp))
            return new Message("New competition added!",true);
        else
            return new Message("New competition NOT added!",false);

    }

    /**
     * @param mex is the request from the client for adding a new boat in a competition
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message addBoatToCompetition(Message mex){
        if(!(mex.getValue() instanceof CompetitionRegister))
            return new Message("New boat to competition NOT added!", false);

        CompetitionRegister cp = (CompetitionRegister) mex.getValue();
        if(CompetitionManager.getInstance().addBoatToCompetition(cp))
            return new Message("New boat to competition added!",true);
        else
            return new Message("New boat to competition NOT added!", false);
    }

    /**
     * @param mex is the request from the client to return competition register for a specific club member
     * @return the correct message with the value of the operation (CompetitionManager.getInstance().getcRegister()->successfully;
     *                                                              NULL->not successfully)
     */
    public Message getActionCompetitionRegisterByMemberId(Message mex){
        if(!(mex.getValue() instanceof Integer))
            return new Message("Impossible to get competition register by user's id -> "+mex.getValue(), null);

        if(DB.getInstance().getCompetitionRegisterByMemberId((int)mex.getValue()))
            return new Message("Get competition register by user's id -> "+mex.getValue(),CompetitionManager.getInstance().getcRegister());
        else
            return new Message("Impossible to get competition register by user's id -> "+mex.getValue(), null);
    }

    /**
     * @param mex is the request from the client to return all competition register
     * @return the correct message with the value of the operation (CompetitionRegisterManager.getInstance().getCompetitionRegisters())
     */
    public Message getCompetitionRegister(Message mex){
        CompetitionRegisterManager.getInstance().updateList();
        return new Message("Get all competition register",CompetitionRegisterManager.getInstance().getCompetitionRegisters());
    }

    /**
     * @param mex is the request from the client to update the user informations in the DB
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message updateMemberInfo(Message mex){
        if(!(mex.getValue() instanceof ClubMember))
            return new Message("User NOT modified!", false);

        if(DB.getInstance().updateUser((ClubMember) mex.getValue()))
            return new Message("User modified", true);
        else
            return new Message("User NOT modified!", false);
    }

    /**
     * @param mex is the request from the client to return the membership quote register
     * @return the correct message with the value of the operation (MembershipRegisterManager.getInstance().getMembershipRegisters())
     */
    public Message getMembershipQuoteRegister(Message mex){
        MembershipRegisterManager.getInstance().updateList();
        return new Message("Get all MembershipQuoteRegister", MembershipRegisterManager.getInstance().getMembershipRegisters());
    }

    /**
     * @param mex is the request from the client for adding membership quote register
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message addMembershipRegisterQuote(Message mex){
        if(!(mex.getValue() instanceof MembershipRegister))
            return new Message("New membership quote NOT added", false);

        if(MembershipRegisterManager.getInstance().addMembershipRegisterQuote((MembershipRegister) mex.getValue()))
            return new Message("New membership quote added", true);
        else
            return new Message("New membership quote NOT added", false);
    }

    /**
     * @param mex is the request from the client to return the club member with information updated
     * @return the correct message with the value of the operation (ClubMemberManager.getInstance().updateClubMember((ClubMember) mex.getValue())
     */
    public Message updateUserInfo(Message mex){
        if(!(mex.getValue() instanceof ClubMember))
            return new Message("User info NOT updated!",null);

        return new Message("User info updated!",ClubMemberManager.getInstance().updateClubMember((ClubMember) mex.getValue()));
    }

    /**
     * @param mex is the request from the client to return storage register quote
     * @return the correct message with the value of the operation (StorageRegisterManager.getInstance().getRegisters())
     */
    public Message getStorageRegisterQuote(Message mex){
        StorageRegisterManager.getInstance().updateList();
        return new Message("Get all Storage Register Quote!",StorageRegisterManager.getInstance().getRegisters());
    }

    /**
     * @param mex is the request from the client to return the last payment of storage quote from storage register
     * @return the correct message with the value of the operation (StorageRegisterManager.getInstance().getLastStorageRegisterBoat((Boat) mex.getValue()))
     */
    public Message getLastStorageRegister(Message mex){
        if(!(mex.getValue() instanceof Boat))
            return new Message("IMPOSSIBLE TO get last storage register!",null);

        return new Message("Get last storage register!",StorageRegisterManager.getInstance().getLastStorageRegisterBoat((Boat) mex.getValue()));
    }

    /**
     * @param mex is the request from the client for adding storage quote register
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message addNewStorageRegister(Message mex){
        if(!(mex.getValue() instanceof StorageRegister))
            return new Message("New storage register NOT added!", false);

        if(StorageRegisterManager.getInstance().addNewStorageRegister((StorageRegister) mex.getValue()))
            return new Message("New storage register added!",true);
        else
            return new Message("New storage register NOT added!", false);
    }

    /**
     * @param mex is the request from the client to delete a boat
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message deleteBoat(Message mex){
        if(!(mex.getValue() instanceof Boat))
            return new Message("Boat NOT removed!",false);

        if(BoatManager.getInstance().removeBoat((Boat) mex.getValue()))
            return new Message("Boat removed!",true);
        else
            return new Message("Boat NOT removed!",false);
    }

    /**
     * @param mex is the request from the client to return the notification register
     * @return the correct message with the value of the operation (NotificationsRegisterManager.getInstance().getRegisters())
     */
    public Message getNotificationRegister(Message mex){
        NotificationsRegisterManager.getInstance().updateList();
        return new Message("Get all Notification Register!",NotificationsRegisterManager.getInstance().getRegisters());
    }

    /**
     * @param mex is the request from the client to return the a notification with a specific id
     * @return the correct message with the value of the operation (NotificationsRegisterManager.getInstance().getNotificationById((Integer)mex.getValue())
     */
    public Message getNotification(Message mex){
        if(!(mex.getValue() instanceof Integer))
            return new Message("IMPOSSIBLE TO get notification to client!",null);

        return new Message("Get notification to client!",NotificationsRegisterManager.getInstance().getNotificationById((Integer)mex.getValue()));
    }

    /**
     * @param mex is the request from the client to return the club member expired
     * @return the correct message with the value of the operation (ClubMemberManager.getInstance().clubMemberExpired())
     */
    public Message getMemberExpired(Message mex){
        return new Message("Get member expired!",ClubMemberManager.getInstance().clubMemberExpired());
    }

    /**
     * @param mex is the request from the client for adding a new notification register
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message addNewNotificationRegister(Message mex){
        if(!(mex.getValue() instanceof NotificationsRegister))
            return new Message("New notification register NOT added!",false);

        if(NotificationsRegisterManager.getInstance().addNewNotificationRegister((NotificationsRegister) mex.getValue()))
            return new Message("New notification register added!",true);
        else
            return new Message("New notification register NOT added!",false);
    }

    /**
     * @param mex is the request from the client to return a specific club member with a specifi ID
     * @return the correct message with the value of the operation (ClubMemberManager.getInstance().getMemberByCF((ClubMember) mex.getValue()))
     */
    public Message getMemberByCF(Message mex){
        if(!(mex.getValue() instanceof ClubMember))
            return new Message("IMPOSSILE TO get member by CF!",null);

        return new Message("Get member by CF!",ClubMemberManager.getInstance().getMemberByCF((ClubMember) mex.getValue()));
    }

    /**
     * @param mex is the request from the client for sending a new notification boat expired
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message sendNotificationBoatExpired(Message mex){
        if(ClubMemberManager.getInstance().sendNotificationBoatExpired())
            return new Message("Notifications Send!",true);
        else
            return new Message("Notifications NOT Send!",false);
    }

    /**
     * @param mex is the request from the client for updating the notifications storage
     * @return the correct message with the value of the operation (NotificationsRegisterManager.getInstance().updateNotificationStorage((ClubMember) mex.getValue()))
     */
    public Message updateNotificationStorage(Message mex){
        if(!(mex.getValue() instanceof ClubMember))
            return new Message("IMPOSSILE TO Update notification storage!",false);
        boolean check=NotificationsRegisterManager.getInstance().updateNotificationStorage((ClubMember) mex.getValue());
        if(check)
            return new Message("Update notification storage",check);
        else
            return new Message("NOT Updated notification storage",check);
    }

    /**
     * @param mex is the request from the client to delete a notification membership
     * @return the correct message with the value of the operation (NotificationsRegisterManager.getInstance().deleteNotificationMembership((ClubMember) mex.getValue()))
     */
    public Message deleteNotificationMembership(Message mex){
        if(!(mex.getValue() instanceof ClubMember))
            return new Message("IMPOSSILE TO delete notification storage!",null);

        return new Message("Delete notification membership", NotificationsRegisterManager.getInstance().deleteNotificationMembership((ClubMember) mex.getValue()));
    }

    /**
     * @param mex is the request from the client to return all club members
     * @return the correct message with the value of the operation (ClubMemberManager.getInstance().getMembers())
     */
    public Message getAllPartners(Message mex){
        ClubMemberManager.getInstance().updateList();
        return new Message("Club members list update", ClubMemberManager.getInstance().getMembers());
    }

    /**
     * @param mex is the request from the client to return all boats
     * @return the correct message with the value of the operation (BoatManager.getInstance().getBoats())
     */
    public Message getAllBoats(Message mex){
        BoatManager.getInstance().updateList();
        return new Message("Boats list update", BoatManager.getInstance().getBoats());
    }

    /**
     * @param mex is the request from the client to return all competitions
     * @return the correct message with the value of the operation (CompetitionManager.getInstance().getCompetitionList())
     */
    public Message getAllCompetitions(Message mex){
        CompetitionManager.getInstance().updateList();
        return new Message("Boats list update", CompetitionManager.getInstance().getCompetitionList());
    }

    /**
     * @param mex is the request from the client to return the competition register by a specific ID of competition
     * @return the correct message with the value of the operation (CompetitionRegisterManager.getInstance().getListByCompetitionId((Integer)mex.getValue()))
     */
    public Message getCompetitionRegisterByIdComp(Message mex){
        if(!(mex.getValue() instanceof Integer))
            return new Message("IMPOSSIBLE TO get competition registers by id competition!",null);

        return new Message("Get competition registers by id competition!",CompetitionRegisterManager.getInstance().getListByCompetitionId((Integer)mex.getValue()));
    }

    /**
     * @param mex is the request from the client to return the competition available for a specific club member
     * @return the correct message with the value of the operation (COMPETITIONS->successfully; NULL->not successfully)
     */
    public Message getCompetitionAvailableByMemberId(Message mex){
        List<Competition> competitions = new ArrayList<Competition>();
        competitions = CompetitionManager.getInstance().getCompetitionAvailable((List<String>) mex.getValue());
        if(competitions!=null)
            return new Message("Get competition available!",competitions);
        else
            return new Message("Impossible get competition available!",null);
    }

    /**
     * @param mex is the request from the client for sending a new notification membership expired
     * @return the correct message with the value of the operation (TRUE->successfully; FALSE->not successfully)
     */
    public Message sendNotificationsMembershipExpired(Message mex){
        if(ClubMemberManager.getInstance().sendNotificationMembershipExpired())
            return new Message("Notifications send!",true);
        else
            return new Message("Notifications NOT send!",false);
    }

}