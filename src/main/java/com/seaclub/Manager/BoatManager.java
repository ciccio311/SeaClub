package com.seaclub.Manager;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;

import java.util.ArrayList;
import java.util.List;

public class BoatManager {
    private List<Boat> boats;

    private static BoatManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private BoatManager(){
        boats = new ArrayList<Boat>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static BoatManager getInstance() {
        if (instance == null)
            instance = new BoatManager();
        return instance;
    }

    public List<Boat> getMemberBoatsFromIDMember(ClubMember clubMember){
        List<Boat> membersBoat = new ArrayList<Boat>();
        for(var x:this.boats){
            if(x.getIdClubMember().equals(clubMember.getCF())){
                membersBoat.add(x);
            }
        }
        return membersBoat;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }
}
