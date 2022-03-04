package com.seaclub.server;

import com.seaclub.Communication.Message;

import java.io.*;
import java.net.Socket;

/**
 * Class used to handle requests to the server on separate threads.
 */
public class ServerThread implements Runnable {
    private static final long SLEEPTIME = 200;

    private Socket socket;
    private String id;

    /**
     * @param c is the socket.
     */
    public ServerThread(final Socket c) {
        this.socket = c;
        this.id = String.valueOf(this.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        ObjectInputStream is = null;
        ObjectOutput os = null;

        try {

            is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while(true) {

            try {

                Object i = is.readObject();

                if(i instanceof Message) {
                    Message request = (Message) i;

                    //here process request in manager
                    Message response = MessageProcessing.getInstance().getActionFromRequest(request);

                    System.out.println("Thread " + id + " receives: " + request.getAction() + " action from its client.");
                    Thread.sleep(SLEEPTIME);

                    if(os == null) {
                        os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                    }

                    System.out.println("Thread " + id + " replies to " + response.getAction() + " action");

                    os.writeObject(response);
                    os.flush();

                    socket.close();
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

    }
}
