package com.seaclub.Manager;

import com.seaclub.Model.MembershipQuote;
import com.seaclub.Model.MembershipRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a model for managing list of membership register
 */
public class MembershipRegisterManager {

    private List<MembershipRegister> membershipRegisters;

    private static MembershipRegisterManager instance = null;

    /**
     * The constructor is private so it is accessible only within the class.
     **/
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

    /**
     * @return the list of membership register
     */
    public List<MembershipRegister> getMembershipRegisters() {
        return membershipRegisters;
    }


    /**
     * @param membershipRegisters set the list of membership register
     */
    public void setMembershipRegisters(List<MembershipRegister> membershipRegisters) {
        this.membershipRegisters = membershipRegisters;
    }

    /**
     * Method used for updating information about all membership register in the list
     */
    public void updateList(){
        DB.getInstance().getMembershipQuoteRegister();
    }


    /**
     * Method used for register a payment about membership quote
     * @param mr the register of payment
     * @return true if the operation was successfully otherwise false
     */
    public boolean addMembershipRegisterQuote(MembershipRegister mr) {
        try {
            if(DB.getInstance().addMembershipRegisterQuote(mr)){
                updateList();
                return true;
            }else
                return false;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}