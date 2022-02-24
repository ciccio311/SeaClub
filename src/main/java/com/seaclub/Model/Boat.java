package com.seaclub.Model;

import java.io.Serializable;

/**
 *
 * The class {@code Boat} provides an implementation of a
 * Member's boat.
 *
 **/
public class Boat implements Serializable {
    private int id;
    private String name;
    private float width;
    private int idClubMember;


    /**
     * First class constructor.
     *
     **/
    public Boat(){}


    /**
     * @param id the boat's id.
     * @param name the boat's name.
     * @param width the boat's width.
     * @param idClubMember the boat's Clubmember.
     */
    public Boat(int id, String name, float width, int idClubMember){
        this.id = id;
        this.name = name;
        this.width = width;
        this.idClubMember = idClubMember;
    }

    /**
     * Gets the boat's width.
     *
     * @return the width.
     *
     **/
    public float getWidth() {
        return width;
    }


    /**
     * Gets the boat's id.
     *
     * @return the id.
     *
     **/
    public int getId() {
        return id;
    }


    /**
     * Gets the boat's ClubMember's id.
     *
     * @return the id of ClubMember.
     *
     **/
    public int getIdClubMember() {
        return idClubMember;
    }


    /**
     * Gets the boat's name.
     *
     * @return the name.
     *
     **/
    public String getName() {
        return name;
    }


    /**
     * Sets the boat's id.
     *
     * @param id the id.
     *
     **/
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Sets the clubMember's id.
     *
     * @param idClubMember the id of clubMember.
     *
     **/
    public void setIdClubMember(int idClubMember) {
        this.idClubMember = idClubMember;
    }


    /**
     * Sets the boat's name.
     *
     * @param name the name.
     *
     **/
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Sets the boat's width.
     *
     * @param width the width.
     *
     **/
    public void setWidth(float width) {
        this.width = width;
    }

}
