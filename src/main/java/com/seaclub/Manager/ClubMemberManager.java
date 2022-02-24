package com.seaclub.Manager;

import com.seaclub.Model.ClubMember;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

public class ClubMemberManager {

    private List<ClubMember> members;

    private static ClubMemberManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private ClubMemberManager(){
        members = new ArrayList<ClubMember>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static ClubMemberManager getInstance() {
        if (instance == null)
            instance = new ClubMemberManager();
        return instance;
    }

    /**
     * @return the members
     */
    public List<ClubMember> getMembers() {
        return this.members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(List<ClubMember> members) {
        this.members = members;
    }

    public void updateList(){
        DB.getInstance().getClubMembers();
    }

    public void addMember(ClubMember member){
        try{
            DB.getInstance().addNewMember(member);
            updateList();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

