package com.seaclub.server;

import com.seaclub.Communication.Message;
import com.seaclub.Manager.BoatManager;
import com.seaclub.Manager.ClubMemberManager;
import com.seaclub.Manager.CompetitionManager;
import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import com.seaclub.Model.Competition;

public class MessageProcessing {

    /*
     * The instance is static so it is shared among all instances of the class. It is also private
     * so it is accessible only within the class.
     */
    private static MessageProcessing instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private MessageProcessing() {

    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static MessageProcessing getInstance() {
        if (instance == null)
            instance = new MessageProcessing();
        return instance;
    }

    public Message getActionFromRequest(Message mex){
        try{
            if(mex.getAction().equals(mex.getActionNewUserRegistration()))
                return addMember(mex);
            if(mex.getAction().equals(mex.getActionLogin()))
                return login(mex);
            if(mex.getAction().equals(mex.getActionAddBoat()))
                return addNewBoat(mex);
            if(mex.getAction().equals(mex.getActionRaces()))
                return getCompetitions(mex);
            if(mex.getAction().equals(mex.getActionAddRace()))
                return addNewCompetition(mex);
            return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Message addMember(Message mex){
        ClubMember member = (ClubMember) mex.getValue();

        ClubMemberManager.getInstance().addMember(member);

        return new Message("Aggiunto new member", null);
    }

    public Message login(Message mex){
        ClubMember member = (ClubMember) mex.getValue();
        ClubMember loggedMember = ClubMemberManager.getInstance().login(member);
        if(loggedMember != null)
            return new Message("User logged", loggedMember);
        else
            return new Message("User doesn t exist", null);
    }

    public Message addNewBoat(Message mex){
        if(mex.getValue() instanceof Boat == false)
            return null;

        Boat boat = (Boat) mex.getValue();
        BoatManager.getInstance().addBoat(boat);
        return new Message("New boat added!",null);
    }

    public Message getCompetitions(Message mex){
        CompetitionManager.getInstance().updateList();
        return new Message("Get all competitions.",CompetitionManager.getInstance().getCompetitionList());
    }

    public Message addNewCompetition(Message mex){
        if(mex.getValue() instanceof Competition == false)
            return null;

        Competition cp = (Competition) mex.getValue();
        CompetitionManager.getInstance().addCompetition(cp);
        return new Message("New competition added!",null);
    }
}
