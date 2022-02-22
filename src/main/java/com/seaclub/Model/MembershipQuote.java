package com.seaclub.Model;

/**
 *
 * The class {@code MembershipQuote} provides an implementation of a
 * quote that club's member has to pay.
 *
 **/

public class MembershipQuote extends Quote{
    private  float price;


    /**
     * First class constructor.
     *
     **/
    public MembershipQuote(){}


    /**
     * Second class constructor.
     * @param prezzo the quote's last price.
     **/

    public MembershipQuote(int id, String name, String duration,float prezzo){
        super(id,name,duration);
        this.price = prezzo;
    }



    /**
     * Gets the quote's price.
     *
     * @return the price.
     *
     **/
    public float getPrice() {
        return price;
    }


    /**
     * Sets the quote's price.
     *
     * @param price the price.
     *
     **/
    public void setPrice(float price) {
        this.price = price;
    }

}
