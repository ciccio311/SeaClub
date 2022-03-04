package com.seaclub.client;

import com.seaclub.Communication.Message;
import com.seaclub.Model.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.List;

/**
 * Class used to manage the request from the client to the server
 */
public class Client {

    private static final int SERVER_PORT = 8080;
    private static final String SERVER_HOST = "localhost";

    /*
     * The instance is static so it is shared among all instances of the class. It is also private
     * so it is accessible only within the class.
     */
    private static Client instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private Client() {

    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static Client getInstance() {
        if (instance == null)
            instance = new Client();
        return instance;
    }

    /**
     * Used add new Partner to database.
     * @param member Represents the new member to add.
     * @return returns true if the executive was successful.
     **/
    public boolean addMember(ClubMember member) {
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionNewUserRegistration());
                request.setValue(member);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;


                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();

                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Used to remove specific boat.
     * @param boat Represents the boat to delete.
     * @return returns true if the executive was successful.
     **/
    public boolean deleteBoat(Boat boat) {
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionRemoveBoat());
                request.setValue(boat);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }

    /**
     * Used add new Storage register to database.
     * @param storageRegister Represents the new storage register to add.
     * @return returns true if the executive was successful.
     **/
    public boolean addStorageRegister(StorageRegister storageRegister) {
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_ADD_STORAGE_REGISTER());
                request.setValue(storageRegister);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Used to send all Notification boat expired to all users.
     * @return returns true if the executive was successful.
     **/
    public boolean SendNotificationBoatExpired() {
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_SEND_NOTIFICATIONS_BOAT_EXPIRED());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Used to send all Notification about membership expired to all users.
     * @return returns true if the executive was successful.
     **/
    public boolean SendNotificationMembershipExpired() {
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_SEND_NOTIFICATIONS_MEMBERSHIP_EXPIRED());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Used add new Notification register to database.
     * @param notificationsRegister Represents the new Notification register to add.
     * @return returns true if the executive was successful.
     **/
    public boolean addNotificationRegister(NotificationsRegister notificationsRegister) {
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_ADD_NOTIFICATION_REGISTER());
                request.setValue(notificationsRegister);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }

    /**
     * Used for login
     * @param cm Represents the new clubMember that try to login
     * @return returns clubmember logged if the credentials are correct otherwise return null
     **/
    public ClubMember login(ClubMember cm){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionLogin());
                request.setValue(cm);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        ClubMember memberLogged = (ClubMember) response.getValue();
                        return memberLogged;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used for get the clubMember by CF
     * @param cm Represents the clubMember with the spefific CF
     * @return if exist returns the clubmember with the CF passed otherwise return null
     **/
    public ClubMember getMemberByCF(ClubMember cm){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_CLUBMEMBER_BY_CF());
                request.setValue(cm);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        ClubMember member = (ClubMember) response.getValue();
                        return member;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used for getting the club member's notification
     * @param id Represents clubMember's id
     * @return if exist returns the notification of specifi clubMember otherwise return null
     **/
    public String getNotification(int id){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_NOTIFICATION());
                request.setValue(id);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        String notify = (String) response.getValue();
                        return notify;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }


    /**
     * Used for getting the last storage register of a specific boat
     * @param boat Represents the boat that we want discover the last storage register
     * @return if exist returns the last storage register of a specific boat otherwise return null
     **/
    public StorageRegister getLastStorageRegister(Boat boat){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_LAST_STORAGE_REGISTER());
                request.setValue(boat);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        StorageRegister storageRegister = (StorageRegister) response.getValue();
                        return storageRegister;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method used for getting the list of member with membership quote expired
     * @return if exist the list of member with membership quote expired otherwise return null
     */
    public List<ClubMember> getMemberExpired(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_CLUBMEMBER_EXPIRED());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        List<ClubMember> membersExpired = (List<ClubMember>) response.getValue();
                        return membersExpired;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method used for getting a club member with the information updated
     * @param cm the club member with old information
     * @return the club member with the information updated
     */
    public ClubMember updateClubMember(ClubMember cm){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_UPDATE_USER_INFO());
                request.setValue(cm);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        ClubMember memberLogged = (ClubMember) response.getValue();
                        return memberLogged;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method used for getting the list of all competition
     * @return the list of all competition
     */
    public List<Competition> getAllCompetition(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionRaces());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        List<Competition> competitions = (List<Competition>) response.getValue();
                        return competitions;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used for getting the list of all storage register
     * @return the list of all storage register
     */
    public List<StorageRegister> getAllStorageRegisterQuote(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_STORAGE_REGISTER_QUOTE());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        List<StorageRegister> registers = (List<StorageRegister>) response.getValue();
                        return registers;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method used for getting the list of all competition register
     * @return the list of all competition register
     */
    public List<CompetitionRegister> getAllCompetitionRegister(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_COMPETITION_REGISTER());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        List<CompetitionRegister> registers = (List<CompetitionRegister>) response.getValue();
                        return registers;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used for getting All Membership's Register
     * @return All Membership's Register
     */
    public List<MembershipRegister> getAllMembershipQuoteRegister(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionGetMembershipFee());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        List<MembershipRegister> registers = (List<MembershipRegister>) response.getValue();
                        return registers;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used for getting the list of competition register with specific competition's id
     * @param id the specific competition that I want to discover the subscribers
     * @return The list of competition register with specific competition's id
     */
    public List<CompetitionRegister> getCompetitionRegisterByIdComp(int id){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_COMPETITION_REGISTER_BY_ID_COMP());
                request.setValue(id);
                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        List<CompetitionRegister> registers = (List<CompetitionRegister>) response.getValue();
                        return registers;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used for getting the competition available of a specific club member
     * @param AllCompetition all competition register by specific member's id
     * @return Competition available
     */
    public List<Competition> getCompetitionAvailable(List<String> AllCompetition){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GE_COMPETITION_AVAILABLE());
                request.setValue(AllCompetition);
                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if(response.getValue()!=null){
                        List<Competition> registers = (List<Competition>) response.getValue();
                        return registers;
                    }else
                        return null;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method used for adding new boat
     * @param boat the new boat
     * @return true if the operation was successfully otherwise false
     */
    public boolean addNewBoat(Boat boat){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionAddBoat());
                request.setValue(boat);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Method used for adding new competition
     * @param competition the new competition
     * @return true if the operation was successfully otherwise false
     */
    public boolean addNewCompetition(Competition competition){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionAddRace());
                request.setValue(competition);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue())
                        return true;
                    else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Method used for register a boat to competition
     * @param compRegister the subscription
     * @return true if the operation was successfully otherwise false
     */
    public boolean addBoatToCompetition(CompetitionRegister compRegister){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionAddBoatToCompetition());
                request.setValue(compRegister);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Method used for getting all subscriptions at competitions of a specific club member
     * @param id the specific club member
     * @return list of all subscriptions at competitions of a specific club member
     */
    public List<String> getCompetitionRegisterByMemberId(int id){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionCompetitionRegisterByMemberId());
                request.setValue(id);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    return (List<String>) response.getValue();
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return null;
            }

            e.printStackTrace();
        }

        return null;
    }


    /**
     * Method used for updating the information about a specific club member (Password and Address)
     * @param cm the specific club member
     * @return true if the operation was successfully otherwise false
     */
    public boolean updateMemberInfo(ClubMember cm){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_UPDATE_MEMBER());
                request.setValue(cm);

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }

                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }

        } catch (IOException | ClassNotFoundException e) {

            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }


    /**
     * Method used for register a payment about membership quote
     * @param mr the register of payment
     * @return true if the operation was successfully otherwise false
     */
    public boolean addMembershipRegisterQuote(MembershipRegister mr){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_ADD_MEMBERSHIP_REGISTER_QUOTE());
                request.setValue(mr);
                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();
                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }
                Object o = is.readObject();
                if(o instanceof Message) {
                    Message response = (Message) o;
                    System.out.println(" and received response: " + response.getAction() + " action from Server");

                    client.close();
                    if((Boolean) response.getValue()){
                        return true;
                    }else
                        return false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Method used for updating notification if a club member pays an expired storage quote or
     * remove a boat with storage quote expired
     * @param clubMember the club member that pays the expired storage quote or remove the boat with expired quote
     * @return true if the operation was successfully otherwise false
     */
    public boolean updateNotificationStorage(ClubMember clubMember){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_UPDATE_NOTIFICATION_STORAGE());
                request.setValue(clubMember);
                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }
                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if((Boolean)response.getValue())
                        return true;
                    else
                        return false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Method used for deleting the notification of membership quote expired of specific club member
     * @param clubMember the specific club member
     * @return true if the operation was successfully otherwise false
     */
    public boolean deleteNotificationMembership(ClubMember clubMember){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_DELETE_NOTIFICATION_MEMBERSHIP());
                request.setValue(clubMember);
                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }
                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();
                    if((Boolean)response.getValue())
                        return true;
                    else
                        return false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
                return false;
            }
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Method used for getting the list of all club member
     * @return the list of all club member
     */
    public List<ClubMember> getAllClubMember(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getActionGetAllPartners());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }
                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();

                    return (List<ClubMember>) response.getValue();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
            }
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Method used for getting the list of all boat
     * @return the list of all boat
     */
    public List<Boat> getAllBoats(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_ALL_BOATS());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }
                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();

                    return (List<Boat>) response.getValue();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
            }
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Method used for getting the list of all competition
     * @return the list of all competition
     */
    public List<Competition> getAllCompetitions(){
        try {
            Socket client = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = null;

            while(true) {
                Message request = new Message();
                request.setAction(request.getACTION_GET_ALL_COMPETITIONS());

                System.out.println("Client sends: " + request.getAction()  + " action to Server");

                os.writeObject(request);
                os.flush();

                if(is == null) {
                    is= new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                }
                Object o = is.readObject();

                if(o instanceof Message) {
                    Message response = (Message) o;

                    System.out.println(" and received response: " + response.getAction() + " action from Server");
                    client.close();

                    return (List<Competition>) response.getValue();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof ConnectException) {
                System.out.println("Server is in down! Please retry...");
            }
            e.printStackTrace();
            return null;
        }
    }
}