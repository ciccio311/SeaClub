package com.seaclub.Manager;

import com.seaclub.Model.Boat;
import com.seaclub.Model.Competition;
import com.seaclub.Model.CompetitionRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

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

    public List<Competition> getCompetitionList() {
        return competitionList;
    }

    public void setCompetitionList(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }

    public void setcRegister(List<String> cRegister) {
        this.cRegister = cRegister;
    }

    public List<String> getcRegister() {
        return cRegister;
    }

    public void updateList(){
        DB.getInstance().getCompetition();
    }

    public void addCompetition(Competition competition){
        DB.getInstance().addNewCompetition(competition);
        updateList();
    }

    public void updateBoatList(CompetitionRegister c) {
        Competition comp = getCompetitionById(c.getIdCompetition());
        competitionList.remove(comp);
        Boat boat = BoatManager.getInstance().getBoatById(c.getIdBoat());
        comp.getBoats().add(boat);
        competitionList.add(comp);
    }

    public void addBoatToCompetition(CompetitionRegister competitionR){
        DB.getInstance().addNewBoatInCompetition(competitionR);
        updateBoatList(competitionR);
    }

    public Competition getCompetitionById(int id){
        for(var x: this.competitionList){
            if(x.getId() == id){
                return x;
            }
        }
        return null;
    }
}
