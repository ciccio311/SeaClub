package com.seaclub.Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * The class {@code MembershipRegister} provides a register of the registration.
 *
 **/
public class MembershipRegister implements Serializable {
    private int id;
    private int idQuote;
    private int idClubMember;
    private String paymentMethod;
    private Date datePayment;

    /**
     * First class constructor
     */
    public MembershipRegister(){}

    /**
     * @param id the membership's id.
     * @param idQuote the MembershipQuote's id.
     * @param idClubMember the ClubMember's id.
     * @param paymentMethod the payment method used.
     * @param datePayment the date of the payment.
     */
    public MembershipRegister(int id, int idQuote, int idClubMember, String paymentMethod, Date datePayment){
        this.id = id;
        this.idQuote = idQuote;
        this.idClubMember = idClubMember;
        this.paymentMethod = paymentMethod;
        this.datePayment = datePayment;
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
    public void setIDQuote(int idQuote) { this.idQuote = idQuote; }

    /**
     * Gets the quote's ID.
     *
     * @return the id of the quote.
     *
     **/
    public int getIDQuote() { return idQuote; }

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
}
