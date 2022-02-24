package com.seaclub.Model;

import java.util.Date;

/**
 *
 * The class {@code CompetitionRegister} provides a register of the competition.
 *
 **/
public class CompetitionRegister {
    private int id;
    private int idCompetition;
    private int idBoat;
    private int idClubMember;
    private String paymentMethod;

    /**
     * First class constructor
     */
    public CompetitionRegister(){}

    /**
     * @param id the register competition's id.
     * @param idCompetition the competition's id.
     * @param idBoat the boat's id.
     * @param idClubMember the ClubMember's id.
     * @param paymentMethod the payment method used.
     */
    public CompetitionRegister(int id, int idCompetition, int idBoat, int idClubMember, String paymentMethod){
        this.id = id;
        this.idCompetition = idCompetition;
        this.idBoat = idBoat;
        this.idClubMember = idClubMember;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Sets the Register's ID.
     *
     * @param id the ID of the competition register.
     *
     **/
    public void setID(int id) { this.id = id; }

    /**
     * Gets the register's ID.
     *
     * @return the ID.
     *
     **/
    public int getID() { return id; }

    /**
     * Sets the competition's ID.
     *
     * @param idCompetition the ID of the competition.
     *
     **/
    public void setIdCompetition(int idCompetition) { this.idCompetition = idCompetition; }

    /**
     * Gets the competition's ID.
     *
     * @return the id of the competition.
     *
     **/
    public int getIdCompetition() { return idCompetition; }

    /**
     * Sets the boat's ID.
     *
     * @param idBoat the ID of the boat.
     *
     **/
    public void setIdBoat(int idBoat) { this.idBoat = idBoat; }

    /**
     * Gets the boat's ID.
     *
     * @return the id of the boat.
     *
     **/
    public int getIdBoat() { return idBoat; }

    /**
     * Sets the club member's ID.
     *
     * @param idClubMember the ID of the club member.
     *
     **/
    public void setIdClubMember(int idClubMember) { this.idClubMember = idClubMember; }

    /**
     * Gets the club member's ID.
     *
     * @return the id of the club member.
     *
     **/
    public int getIdClubMember() { return idClubMember; }

    /**
     * Sets the payment method.
     *
     * @param method the method of the payment.
     *
     **/
    public void setPaymentMethod(String method) { this.paymentMethod = method; }

    /**
     * Gets the payment method.
     *
     * @return the payment Method.
     *
     **/
    public String getPaymentMethod() { return paymentMethod; }
}
