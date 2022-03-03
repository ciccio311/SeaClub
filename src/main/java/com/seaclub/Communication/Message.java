package com.seaclub.Communication;

import java.io.Serializable;

public class Message implements Serializable {

    private String ACTION_LOGIN = "login";

    private String ACTION_NEW_USER_REGISTRATION = "newUserRegistration";

    private String ACTION_ADD_BOAT = "addBoat";

    private String ACTION_REMOVE_BOAT = "removeBoat";

    private String ACTION_RACES = "races";

    private final String ACTION_GET_ALL_PARTNERS = "getAllPartners";

    private String ACTION_GET_MEMBERSHIP_FEE = "getMembershipFee";

    private String ACTION_ADD_RACE = "addRace";

    private String ACTION_ADD_BOAT_TO_COMPETITION = "AddBoatToCompetition";

    private String ACTION_COMPETITION_REGISTER_BY_MEMBER_ID = "GetCompetitionRegisterByMemberId";

    private String ACTION_GET_COMPETITION_REGISTER = "GetCompetitionRegister";

    private String ACTION_UPDATE_MEMBER = "UpdateMember";

    private String ACTION_ADD_MEMBERSHIP_REGISTER_QUOTE = "AddMembershipRegisterQuote";

    protected final String ACTION_UPDATE_USER_INFO ="UpdateUserInfo";

    private String ACTION_GET_STORAGE_REGISTER_QUOTE = "GetStorageRegisterQuote";

    private String ACTION_GET_LAST_STORAGE_REGISTER = "GetLastStorageRegister";

    private String ACTION_ADD_STORAGE_REGISTER = "AddStorageRegister";

    private String ACTION_GET_NOTIFICATION_REGISTER = "ActionGetNotificationRegister";

    private String ACTION_ADD_NOTIFICATION_REGISTER = "ActionAddNotificationRegister";

    private String ACTION_GET_NOTIFICATION = "GetNotification";

    private String ACTION_GET_CLUBMEMBER_EXPIRED = "GetMemberExpired";

    private String ACTION_GET_CLUBMEMBER_BY_CF = "GetMemberByCF";

    private String ACTION_SEND_NOTIFICATIONS_BOAT_EXPIRED = "GetNotificationBoatExpired";

    private String ACTION_UPDATE_NOTIFICATION_STORAGE = "getUpdateNotificationStorage";

    private String ACTION_DELETE_NOTIFICATION_MEMBERSHIP = "getDeleteNotificationMembership";

    private String ACTION_GET_ALL_BOATS = "getAllBoats";

    private String ACTION_GET_ALL_COMPETITIONS = "getAllCompetitions";

    private String ACTION_COMPETITION_REGISTER_BY_ID_COMP = "getCompetitionRegisterByIdComp";

    private String ACTION_GE_COMPETITION_AVAILABLE = "getCompetitionAvilableByIdMember";

    protected final String ACTION_SEND_NOTIFICATIONS_MEMBERSHIP_EXPIRED ="SendNotificationsMembershipExpired";

    private static final long serialVersionUID = 1L;


    /**
     * Represents the action to be performed.
     */
    private String action;


    /**
     * Represents the value to used in the action.
     */
    private Object value;

    /**
     * Default constructor.
     */
    public Message() {}

    /**
     * @param action is the action to be performed.
     * @param value is the value to used in the action.
     */
    public Message(String action, Object value) {
        super();
        this.action = action;
        this.value = value;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getActionAddBoat() {
        return ACTION_ADD_BOAT;
    }

    public String getActionCompetitionRegisterByMemberId() {
        return ACTION_COMPETITION_REGISTER_BY_MEMBER_ID;
    }

    public String getActionAddBoatToCompetition() { return ACTION_ADD_BOAT_TO_COMPETITION; }

    public String getActionAddRace() {
        return ACTION_ADD_RACE;
    }

    public String getACTION_UPDATE_MEMBER() {
        return ACTION_UPDATE_MEMBER;
    }

    public String getActionGetAllPartners() {
        return ACTION_GET_ALL_PARTNERS;
    }

    public String getActionGetMembershipFee() {
        return ACTION_GET_MEMBERSHIP_FEE;
    }

    public String getActionLogin() {
        return ACTION_LOGIN;
    }

    public String getActionNewUserRegistration() {
        return ACTION_NEW_USER_REGISTRATION;
    }

    public String getActionRaces() {
        return ACTION_RACES;
    }

    public String getActionRemoveBoat() {
        return ACTION_REMOVE_BOAT;
    }

    public String getACTION_GET_COMPETITION_REGISTER() {
        return ACTION_GET_COMPETITION_REGISTER;
    }

    public String getACTION_UPDATE_USER_INFO() {
        return ACTION_UPDATE_USER_INFO;
    }

    public String getACTION_GET_STORAGE_REGISTER_QUOTE() {
        return ACTION_GET_STORAGE_REGISTER_QUOTE;
    }

    public String getACTION_GET_LAST_STORAGE_REGISTER() {
        return ACTION_GET_LAST_STORAGE_REGISTER;
    }

    public String getACTION_ADD_STORAGE_REGISTER() {
        return ACTION_ADD_STORAGE_REGISTER;
    }

    public String getACTION_GET_NOTIFICATION_REGISTER() {
        return ACTION_GET_NOTIFICATION_REGISTER;
    }

    public String getACTION_ADD_NOTIFICATION_REGISTER() {
        return ACTION_ADD_NOTIFICATION_REGISTER;
    }

    public String getACTION_GET_NOTIFICATION() {
        return ACTION_GET_NOTIFICATION;
    }

    public String getACTION_GET_CLUBMEMBER_EXPIRED() {
        return ACTION_GET_CLUBMEMBER_EXPIRED;
    }

    public String getACTION_GET_CLUBMEMBER_BY_CF() {
        return ACTION_GET_CLUBMEMBER_BY_CF;
    }

    public String getACTION_SEND_NOTIFICATIONS_BOAT_EXPIRED() {
        return ACTION_SEND_NOTIFICATIONS_BOAT_EXPIRED;
    }

    public String getACTION_ADD_MEMBERSHIP_REGISTER_QUOTE() {
        return ACTION_ADD_MEMBERSHIP_REGISTER_QUOTE;
    }

    public String getACTION_DELETE_NOTIFICATION_MEMBERSHIP() {
        return ACTION_DELETE_NOTIFICATION_MEMBERSHIP;
    }

    public String getACTION_UPDATE_NOTIFICATION_STORAGE() {
        return ACTION_UPDATE_NOTIFICATION_STORAGE;
    }

    public String getACTION_GET_ALL_BOATS() {
        return ACTION_GET_ALL_BOATS;
    }

    public String getACTION_GET_ALL_COMPETITIONS() {
        return ACTION_GET_ALL_COMPETITIONS;
    }

    public String getACTION_GE_COMPETITION_AVAILABLE() {
        return ACTION_GE_COMPETITION_AVAILABLE;
    }

    public String getACTION_SEND_NOTIFICATIONS_MEMBERSHIP_EXPIRED() {
        return ACTION_SEND_NOTIFICATIONS_MEMBERSHIP_EXPIRED;
    }

    public String getACTION_COMPETITION_REGISTER_BY_ID_COMP() {
        return ACTION_COMPETITION_REGISTER_BY_ID_COMP;
    }
}