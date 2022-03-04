package com.seaclub.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the server
 */
public class Server {
    private static final int COREPOOL = 5;
    private static final int MAXPOOL = 10;
    private static final long IDLETIME = 5000;

    private static int SERVER_PORT = 8080;
    private ServerSocket socket;
    private ThreadPoolExecutor pool;

    /**
     * Public constructor.
     * @throws IOException Signals that an I/O exception of some sort has occurred. Thisclass is the general class of exceptions produced by failed orinterrupted I/O operations.
     **/
    public Server() throws IOException {
        this.socket = new ServerSocket(SERVER_PORT);
    }

    private void run() {
        System.out.println("Server is online!");

        this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME
                , TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        while(true) {
            try {
                Socket s = this.socket.accept();

                this.pool.execute(new ServerThread(s));
            } catch(Exception e) {
                break;
            }
        }

        this.pool.shutdown();
    }


    /**
     * This method is used to close socket connection.
     **/
    public void close() {
        try {
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the pool
     */
    public ThreadPoolExecutor getPool() {
        return pool;
    }


    /**
     * Main function that run Server.
     * @param args
     * @throws ParseException Signals that an error has been reached unexpectedlywhile parsing.
     * @throws IOException Signals that an I/O exception of some sort has occurred. Thisclass is the general class of exceptions produced by failed orinterrupted I/O operations.
     **/
    public static void main(final String[] args) throws IOException, ParseException {

        new Server().run();
    }
}
