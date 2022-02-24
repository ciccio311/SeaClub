package com.seaclub.Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * The class {@code Competition} provides an implementation of a
 * boat's competition.
 *
 **/
public class Competition implements Serializable {
    private int id;
    private float price;
    private Date date;

    /**
     * First class constructor
     */
    public Competition(){}

    /**
     * @param id the competition's id.
     * @param price the competition's price.
     * @param date the competition's date.
     */
    public Competition(int id, float price, Date date){
        this.id= id;
        this.price = price;
        this.date = date;
    }


    /**
     * Sets the competition's price.
     *
     * @param price the price.
     *
     **/
    public void setPrice(float price) {
        this.price = price;
    }


    /**
     * Gets the competition's price.
     *
     * @return the price.
     *
     **/
    public float getPrice() {
        return price;
    }


    /**
     * Gets the competition's id.
     *
     * @return the id.
     *
     **/
    public int getId() {
        return id;
    }


    /**
     * Sets the competition's id.
     *
     * @param id the id.
     *
     **/
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the competition's date.
     *
     * @return the date.
     *
     **/
    public Date getDate() {
        return date;
    }


    /**
     * Sets the competition's date.
     *
     * @param date the date.
     *
     **/
    public void setDate(Date date) {
        this.date = date;
    }

}
