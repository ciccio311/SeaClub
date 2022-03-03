package com.seaclub.Manager;

import com.seaclub.Model.Boat;
import com.seaclub.Model.Competition;
import com.seaclub.Model.CompetitionRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a model for managing a list of competition
 */
public class CompetitionManager {
    private List<Competition> competitionList;
    private List<String> cRegister;

    private static CompetitionManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private CompetitionManager(){
        competitionList = new ArrayList<Competition>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static CompetitionManager getInstance() {
        if (instance == null)
            instance = new CompetitionManager();
        return instance;
    }

    /**
     * Method used to return the list of competition
     * @return the list of competition
     */
    public List<Competition> getCompetitionList() {
        return competitionList;
    }

    /**
     * Method used to set the competition lists
     * @param competitionList is the list of competition
     */
    public void setCompetitionList(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }

    /**
     * Method used to set the competition register
     * @param cRegister is the list of competition register
     */
    public void setcRegister(List<String> cRegister) {
        this.cRegister = cRegister;
    }

    /**
     * Method used to return the competition register
     * @return the list of competition register
     */
    public List<String> getcRegister() {
        return cRegister;
    }

    /**
     * Method used to update the list of competition
     */
    public void updateList(){
        try {
            DB.getInstance().getCompetition();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Method used for adding new competition
     * @param competition the new competition
     * @return true if the operation was successfully otherwise false
     */
    public boolean addCompetition(Competition competition){
        try {
            if (DB.getInstance().addNewCompetition(competition)) {
                updateList();
                return true;
            } else
                return false;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Method used for register a boat to competition
     * @param competitionR the subscription
     * @return true if the operation was successfully otherwise false
     */
    public boolean addBoatToCompetition(CompetitionRegister competitionR){
        try {
            if(DB.getInstance().addNewBoatInCompetition(competitionR)) {
                Competition comp = getCompetitionById(competitionR.getIdCompetition());
                competitionList.remove(comp);
                Boat boat = BoatManager.getInstance().getBoatById(competitionR.getIdBoat());
                comp.getBoats().add(boat);
                competitionList.add(comp);
                return true;
            }else
                return false;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Method used to return the competition with a specific ID
     * @param id is the specific id of the competition
     * @return the competition
     */
    public Competition getCompetitionById(int id){
        for(var x: this.competitionList){
            if(x.getId() == id){
                return x;
            }
        }
        return null;
    }

    /**
     * Method used to find which competition are available
     * @param registers is the list of all competition register by member's id
     * @return the list of all competition AVAILABLE
     */
    public List<Competition> getCompetitionAvailable(List<String> registers){
        try {
            updateList();
            List<Competition> competitionAvailable = new ArrayList<Competition>();
            competitionAvailable = getCompetitionList();
            List<Integer> ids = new ArrayList<Integer>();
            for (var x : registers) {
                String word[] = x.split(", ");
                ids.add(Integer.valueOf(word[0]));
            }

            for (var id : ids) {
                competitionAvailable.removeIf(n -> n.getId() == id);
            }

            return competitionAvailable;
        }catch(Exception e){
            System.out.println("Error: "+e.toString());
            return null;
        }
    }
}
