package com.seaclub.Manager;

import com.seaclub.Model.Competition;
import com.seaclub.Model.CompetitionRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

public class CompetitionRegisterManager {

    private List<CompetitionRegister> competitionRegisters;

    private static CompetitionRegisterManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private CompetitionRegisterManager(){
        competitionRegisters = new ArrayList<CompetitionRegister>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static CompetitionRegisterManager getInstance() {
        if (instance == null)
            instance = new CompetitionRegisterManager();
        return instance;
    }

    public void setCompetitionRegisters(List<CompetitionRegister> competitionRegisters) {
        this.competitionRegisters = competitionRegisters;
    }

    public List<CompetitionRegister> getCompetitionRegisters() {
        return competitionRegisters;
    }

    public void updateList(){
        DB.getInstance().getCompetitionRegister();
    }

    /**
     * @param id the id of competition that I want discover the subscribers
     * @return the subscribers register of the specific competition
     */
    public List<CompetitionRegister> getListByCompetitionId(int id){
        try {
            List<CompetitionRegister> memberOfCompetition = new ArrayList<CompetitionRegister>();
            updateList();
            for (var x : competitionRegisters) {
                if (x.getIdCompetition() == id) {
                    memberOfCompetition.add(x);
                }
            }
            return memberOfCompetition;
        }catch(Exception e){

            System.out.println("Error: "+e.toString());
            return null;

        }
    }
}

