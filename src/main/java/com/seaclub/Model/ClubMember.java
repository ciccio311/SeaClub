package com.seaclub.Model;

/**
 *
 * The class {@code ClubMember} provides an implementation of a
 * Member of secific Club.
 *
 **/
public class ClubMember {
    private String CF;
    private String name;
    private String surname;
    private String address;
    private String password;


    /**
     * First class constructor.
     *
     **/
    public ClubMember(){}

    /**
     * Second class constructor.
     * @param cf the member's id.
     * @param name the member's name.
     * @param surname the member's surname.
     * @param addres the member's address.
     * @param pswd the member's password.
     **/
    public  ClubMember(String cf, String name, String surname, String addres, String pswd){
        this.CF = cf;
        this.name = name;
        this.surname = surname;
        this.address = addres;
        this.password = pswd;
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

}