package com.seaclub.Manager;

import com.seaclub.Model.CompetitionRegister;
import com.seaclub.Model.MembershipQuote;

import java.util.ArrayList;
import java.util.List;

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

    public List<MembershipQuote> getMembershipQuoteList() {
        return membershipQuoteList;
    }

    public void setMembershipQuoteList(List<MembershipQuote> membershipQuoteList) {
        this.membershipQuoteList = membershipQuoteList;
    }


}
