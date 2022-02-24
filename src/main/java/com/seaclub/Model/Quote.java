package com.seaclub.Model;

import java.io.Serializable;

/**
 *
 * The class {@code Quote} provides an implementation of a
 * general quote.
 *
 **/
public class Quote implements Serializable {
    private int id;
    private String name;
    private String duration;


    /**
     * First class constructor
     */
    public Quote(){}

    /**
     * @param id the quote's id.
     * @param name the quote's name;
     * @param duration the quote's duration.
     */
    public Quote(int id, String name,String duration){

        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    /**
     * Gets the quote's name.
     *
     * @return the name.
     *
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the quote's name.
     *
     * @param name the name.
     *
     **/
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Sets the quote's identifier.
     *
     * @param id the identifier.
     *
     **/
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the quote's identifier.
     *
     * @return the identifier.
     *
     **/
    public int getId() {
        return id;
    }


    /**
     * Gets the quote's duration.
     *
     * @return the duration.
     *
     **/
    public String getDuration() {
        return duration;
    }


    /**
     * Sets the quote's duration.
     *
     * @param duration the duration.
     *
     **/
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
