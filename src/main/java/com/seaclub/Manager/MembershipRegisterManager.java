package com.seaclub.Manager;

import com.seaclub.Model.MembershipQuote;
import com.seaclub.Model.MembershipRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

public class MembershipRegisterManager {

    private List<MembershipRegister> membershipRegisters;

    private static MembershipRegisterManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private MembershipRegisterManager(){
        membershipRegisters = new ArrayList<MembershipRegister>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static MembershipRegisterManager getInstance() {
        if (instance == null)
            instance = new MembershipRegisterManager();
        return instance;
    }

    public List<MembershipRegister> getMembershipRegisters() {
        return membershipRegisters;
    }

    public void setMembershipRegisters(List<MembershipRegister> membershipRegisters) {
        this.membershipRegisters = membershipRegisters;
    }

    public void updateList(){
        DB.getInstance().getMembershipQuoteRegister();
    }
}
