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
}

