package com.seaclub.Manager;

import com.seaclub.Model.CompetitionRegister;
import com.seaclub.Model.MembershipQuote;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a model for managing a list of membership quote
 */
public class MembershipQuoteManager {
    private List<MembershipQuote> membershipQuoteList;

    private static MembershipQuoteManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private MembershipQuoteManager(){
        membershipQuoteList = new ArrayList<MembershipQuote>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static MembershipQuoteManager getInstance() {
        if (instance == null)
            instance = new MembershipQuoteManager();
        return instance;
    }

    /**
     * Method used to return the list of membership quote
     * @return the list of membership quote
     */
    public List<MembershipQuote> getMembershipQuoteList() {
        return membershipQuoteList;
    }

    /**
     * Method used to set the list of membership quote
     * @param membershipQuoteList is the list to set
     */
    public void setMembershipQuoteList(List<MembershipQuote> membershipQuoteList) {
        this.membershipQuoteList = membershipQuoteList;
    }

}
