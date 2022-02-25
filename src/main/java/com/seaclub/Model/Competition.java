package com.seaclub.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<Boat> boats;

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
        this.boats = new ArrayList<Boat>();
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

    /**
     * Sets the list of the boats in the competition.
     *
     * @param boats the list of the boats.
     *
     **/
    public void setBoats(List<Boat> boats) { this.boats = boats; }

    /**
     * Gets the list of the boats in the competition.
     *
     * @return the lis of the boats.
     *
     **/
    public List<Boat> getBoats() { return boats; }
}
