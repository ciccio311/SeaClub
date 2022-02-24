package com.seaclub.Model;

import java.util.Date;

/**
 *
 * The class {@code StorageRegister} provides a register of the storage.
 *
 **/
public class StorageRegister {
    private int id;
    private int idQuote;
    private int idBoat;
    private int idClubMember;
    private String paymentMethod;
    private Date datePayment;
    private double price;

    /**
     * First class constructor
     */
    public StorageRegister(){}

    /**
     * @param id the storage's id.
     * @param idQuote the MembershipQuote's id.
     * @param idBoat the boat's id.
     * @param idClubMember the ClubMember's id.
     * @param paymentMethod the payment method used.
     * @param datePayment the date of the payment.
     * @param price the price of the storage
     */
    public StorageRegister(int id, int idQuote, int idBoat, int idClubMember, String paymentMethod, Date datePayment, double price){
        this.id = id;
        this.idQuote = idQuote;
        this.idBoat = idBoat;
        this.idClubMember = idClubMember;
        this.paymentMethod = paymentMethod;
        this.datePayment = datePayment;
        this.price = price;
    }

    /**
     * Sets the Register's ID.
     *
     * @param id the ID of the membership.
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
     * Sets the quote's ID.
     *
     * @param idQuote the ID of the membership quote.
     *
     **/
    public void setIdQuote(int idQuote) { this.idQuote = idQuote; }

    /**
     * Gets the quote's ID.
     *
     * @return the id of the quote.
     *
     **/
    public int getIdQuote() { return idQuote; }

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

    /**
     * Sets the date of the payment.
     *
     * @param date the date of the payment.
     *
     **/
    public void setDatePayment(Date date) { this.datePayment = date; }

    /**
     * Gets the date of the payment.
     *
     * @return the date of the payment.
     *
     **/
    public Date getDatePayment() { return datePayment; }

    /**
     * Sets the price of the storage.
     *
     * @param price the price of the storage.
     *
     **/
    public void setPrice(double price) { this.price = price; }

    /**
     * Gets the price of the storage.
     *
     * @return the price of the storage.
     *
     **/
    public double getPrice() { return price; }
}
