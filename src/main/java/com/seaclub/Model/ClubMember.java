package com.seaclub.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * The class {@code ClubMember} provides an implementation of a
 * Member of secific Club.
 *
 **/
public class ClubMember implements Serializable {
    private int id;
    private String CF;
    private int dipendente;
    private String name;
    private String surname;
    private String address;
    private String password;
    private List<Boat> boats;


    /**
     * First class constructor.
     *
     **/
    public ClubMember(){
        this.boats = new ArrayList<Boat>();
    }

    /**
     * Second class constructor.
     * @param cf the member's id.
     * @param dipendente 0 if he is only a club member, 1 if he is an employee
     * @param name the member's name.
     * @param surname the member's surname.
     * @param addres the member's address.
     * @param pswd the member's password.
     **/
    public  ClubMember(int id,String cf, int dipendente, String name, String surname, String addres, String pswd){
        this.id = id;
        this.CF = cf;
        this.dipendente = dipendente;
        this.name = name;
        this.surname = surname;
        this.address = addres;
        this.password = pswd;
        this.boats = new ArrayList<Boat>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /**
     * Sets the member's name.
     *
     * @param name the name.
     *
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the member's name.
     *
     * @return the name.
     *
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the type of the club member.
     *
     * @param dip 0 or 1.
     *
     **/
    public void setDipendente(int dip) {
        this.dipendente = dip;
    }

    /**
     * Gets the type of the club member.
     *
     * @return the 0 or 1.
     *
     **/
    public int getDipendente() {
        return dipendente;
    }

    /**
     * Gets the member's address.
     *
     * @return the address.
     *
     **/
    public String getAddress() {
        return address;
    }


    /**
     * Gets the member's id.
     *
     * @return the id.
     *
     **/
    public String getCF() {
        return CF;
    }


    /**
     * Gets the member's password.
     *
     * @return the password.
     *
     **/
    public String getPassword() {
        return password;
    }


    /**
     * Gets the member's surname.
     *
     * @return the surname.
     *
     **/
    public String getSurname() {
        return surname;
    }


    /**
     * Sets the member's address.
     *
     * @param address the address.
     *
     **/
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Sets the member's id.
     *
     * @param CF the id.
     *
     **/
    public void setCF(String CF) {
        this.CF = CF;
    }


    /**
     * Sets the member's password.
     *
     * @param password the password.
     *
     **/
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Sets the member's surname.
     *
     * @param surname the surname.
     *
     **/
    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * Gets the member's boats.
     *
     * @return the boats.
     *
     **/
    public List<Boat> getBoats() {
        return boats;
    }

    /**
     * Sets the member's boats.
     *
     * @param boats the boats.
     *
     **/
    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }
}
