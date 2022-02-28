package com.seaclub.Communication;

import java.io.Serializable;

public class Message implements Serializable {
    /**
     *  Represents the String of the action login. Used for communicating the type of action to be performed.
     **/
    private String ACTION_LOGIN = "login";
    /**
     *  Represents the String of the action check the existence of the username. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_THIS_USERNAME_EXISTS = "thisUsernameExist";
    /**
     *  Represents the String of the action add partner. Used for communicating the type of action to be performed.
     **/
    private String ACTION_NEW_USER_REGISTRATION = "newUserRegistration";
    /**
     *  Represents the String of the action get user logged information. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_USER_INFORMATION = "userInformation";
    /**
     *  Represents the String of the action get boats of partner. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_PARTNER_BOATS = "partnerBoats";
    /**
     *  Represents the String of the action add boat. Used for communicating the type of action to be performed.
     **/
    private String ACTION_ADD_BOAT = "addBoat";
    /**
     *  Represents the String of the action remove boat. Used for communicating the type of action to be performed.
     **/
    private String ACTION_REMOVE_BOAT = "removeBoat";
    /**
     *  Represents the String of the action get all races. Used for communicating the type of action to be performed.
     **/
    private String ACTION_RACES = "races";
    /**
     *  Represents the String of the action add registration of boat to a race. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_ADD_REGISTRATION = "addRegistrationToRace";
    /**
     *  Represents the String of the action logout. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_LOGOUT = "logout";
    /**
     *  Represents the String of the action payment. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_PAYMENT = "payment";
    /**
     *  Represents the String of the action get all partners. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_GET_ALL_PARTNERS = "getAllPartners";
    /**
     *  Represents the String of the action get all subscribers of race. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_GET_ALL_SUBSCRIBERS_OF_RACE = "getAllSubscribersOfRace";
    /**
     *  Represents the String of the action delete race. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_DELETE_RACE = "deleteRace";
    /**
     *  Represents the String of the action get all to pay to send. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_GET_TO_PAY = "getToPay";
    /**
     *  Represents the String of the action send notification to pay to partner. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_SEND_NOTIFICATION_PAYMENT = "sendNotificationPayment";
    /**
     *  Represents the String of the action get all notifications of partner. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_GET_NOTIFICATIONS = "getNotifications";
    /**
     *  Represents the String of the action get to pay of partner. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_GET_TO_PAY_PARTNER = "getToPayPartner";
    /**
     *  Represents the String of the action delete to pay after the payment by partner. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_DELETE_TO_PAY = "deleteToPay";
    /**
     *  Represents the String of the action get membership fee of partner. Used for communicating the type of action to be performed.
     **/
    private String ACTION_GET_MEMBERSHIP_FEE = "getMembershipFee";
    /**
     *  Represents the String of the action get subscription fee of partner. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_GET_SUBSCRIPTION_FEE = "getSubscriptionFee";
    /**
     *  Represents the String of the action get storage fee of partner. Used for communicating the type of action to be performed.
     **/
    private static final String ACTION_GET_STORAGE_FEE = "getStorageFee";
    /**
     *  Represents the String of the action add race. Used for communicating the type of action to be performed.
     **/
    private String ACTION_ADD_RACE = "addRace";
    /**
     *  Represents the String of the action add boat to competition. Used for communicating the type of action to be performed.
     **/
    private String ACTION_ADD_BOAT_TO_COMPETITION = "AddBoatToCompetition";
    /**
     *  Represents the String of the action add boat to competition. Used for communicating the type of action to be performed.
     **/
    private String ACTION_COMPETITION_REGISTER_BY_MEMBER_ID = "GetCompetitionRegisterByMemberId";

    private String ACTION_GET_COMPETITION_REGISTER = "GetCompetitionRegister";

    private String ACTION_UPDATE_MEMBER = "UpdateMember";

    private String ACTION_ADD_MEMBERSHIP_REGISTER_QUOTE = "AddMembershipRegisterQuote";

    private String ACTION_UPDATE_USER_INFO ="UpdateUserInfo";

    private String ACTION_GET_STORAGE_REGISTER_QUOTE = "GetStorageRegisterQuote";

    private String ACTION_GET_LAST_STORAGE_REGISTER = "GetLastStorageRegister";

    private String ACTION_ADD_STORAGE_REGISTER = "AddStorageRegister";

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

    public static String getActionAddRegistration() {
        return ACTION_ADD_REGISTRATION;
    }

    public static String getActionDeleteRace() {
        return ACTION_DELETE_RACE;
    }

    public static String getActionDeleteToPay() {
        return ACTION_DELETE_TO_PAY;
    }

    public static String getActionGetAllPartners() {
        return ACTION_GET_ALL_PARTNERS;
    }

    public static String getActionGetAllSubscribersOfRace() {
        return ACTION_GET_ALL_SUBSCRIBERS_OF_RACE;
    }

    public String getActionGetMembershipFee() {
        return ACTION_GET_MEMBERSHIP_FEE;
    }

    public static String getActionGetNotifications() {
        return ACTION_GET_NOTIFICATIONS;
    }

    public static String getActionGetStorageFee() {
        return ACTION_GET_STORAGE_FEE;
    }

    public static String getActionGetSubscriptionFee() {
        return ACTION_GET_SUBSCRIPTION_FEE;
    }

    public static String getActionGetToPay() {
        return ACTION_GET_TO_PAY;
    }

    public static String getActionGetToPayPartner() {
        return ACTION_GET_TO_PAY_PARTNER;
    }

    public String getActionLogin() {
        return ACTION_LOGIN;
    }

    public static String getActionLogout() {
        return ACTION_LOGOUT;
    }

    public String getActionNewUserRegistration() {
        return ACTION_NEW_USER_REGISTRATION;
    }

    public static String getActionPartnerBoats() {
        return ACTION_PARTNER_BOATS;
    }

    public static String getActionPayment() {
        return ACTION_PAYMENT;
    }

    public String getActionRaces() {
        return ACTION_RACES;
    }

    public String getActionRemoveBoat() {
        return ACTION_REMOVE_BOAT;
    }

    public static String getActionSendNotificationPayment() {
        return ACTION_SEND_NOTIFICATION_PAYMENT;
    }

    public static String getActionThisUsernameExists() {
        return ACTION_THIS_USERNAME_EXISTS;
    }

    public static String getActionUserInformation() {
        return ACTION_USER_INFORMATION;
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

    public String getACTION_ADD_MEMBERSHIP_REGISTER_QUOTE() {
        return ACTION_ADD_MEMBERSHIP_REGISTER_QUOTE;
    }
}
