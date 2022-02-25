package com.seaclub.client;

import com.seaclub.Communication.Message;
import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

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
                    return true;
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
                    return true;
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
}
