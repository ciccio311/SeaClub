package com.seaclub.Model;

import java.io.Serializable;

/**
 *
 * The class {@code Notification} provides an implementation of a notification.
 *
 **/
public class Notification implements Serializable {
    private int id;
    private String name;

    /**
     * Constructor
     * @param id is the id of the notification
     * @param nome is the name of the notification
     */
    public Notification(int id, String nome){
        this.id=id;
        this.name=nome;
    }

    /**
     * Gets the notification's id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the notification's id
     * @param id is the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the notification's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the notification name
     * @param name is the name
     */
    public void setName(String name) {
        this.name = name;
    }

}
