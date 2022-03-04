package com.seaclub.Manager;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a model for managing a list of boats
 */
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

    /**
     * Method used to return the boats list of a specific club member
     * @param clubMember is the specific club member
     * @return the boats list
     */
    public List<Boat> getMemberBoatsFromIDMember(ClubMember clubMember){
        updateList();
        List<Boat> membersBoat = new ArrayList<Boat>();
        for(var x:this.boats){
            if(x.getIdClubMember() == clubMember.getId()){
                membersBoat.add(x);
            }
        }
        return membersBoat;
    }

    /**
     * Method used to return the list of all boat
     * @return the entire list of boats
     */
    public List<Boat> getBoats() {
        return boats;
    }

    /**
     * Method used to set the list of all boats
     * @param boats is the list of all the boats
     */
    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    /**
     * Method used to update the list of boats
     */
    public void updateList(){
        try {
            DB.getInstance().getBoats();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Method used for adding new boat
     * @param boat the new boat
     * @return true if the operation was successfully otherwise false
     */
    public boolean addBoat(Boat boat){
        try{
            if(DB.getInstance().addNewBoat(boat)) {
                updateList();
                return true;
            }
            else
                return false;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }

    }

    /**
     * Method used to find a boat with a specific ID
     * @param id is the specific id of the boat
     * @return the boat with boat.getID()=id
     */
    public Boat getBoatById(int id){
        updateList();
        for(var x: this.boats){
            if(x.getId() == id){
                return x;
            }
        }
        return null;
    }

    /**
     * Used to remove specific boat.
     * @param boat Represents the boat to delete.
     * @return returns true if the executive was successful.
     **/
    public boolean removeBoat(Boat boat){
        try {
            if(DB.getInstance().deleteBoat(boat)) {
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
