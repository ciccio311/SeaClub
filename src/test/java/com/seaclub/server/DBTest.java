package com.seaclub.server;

import com.seaclub.Model.Boat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBTest {

    /**
     * Test if get all boats in DB
     */
    @Test
    void getBoats() {
        assertTrue(DB.getInstance().getBoats());
    }


    /**
     * Test if get notification with specific id
     */
    @Test
    void getNotification() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        int n = (int) (Math.random() * 2);
        assertTrue(DB.getInstance().getNotification(ids.get(n)));
        System.out.println(ids.get(n));
    }

    /**
     * Test if get all notification register
     */
    @Test
    void getNotificatioinRegister() {
        assertTrue(DB.getInstance().getNotificatioinRegister());
    }

    /**
     * Test if get all storage quote
     */
    @Test
    void getBoatStorageQuote() {
        assertTrue(DB.getInstance().getBoatStorageQuote());
    }

    /**
     * Test if get last storage quote of a specific boat
     */
    @Test
    void getLastBoatStorageQuote(){
        Boat boat = new Boat();
        boat.setId(4);
        assertTrue(DB.getInstance().getLastBoatStorageQuote(boat));
    }


    /**
     * Test if get all competition register
     */
    @Test
    void getCompetitionRegister() {
        assertTrue(DB.getInstance().getCompetitionRegister());
    }


    /**
     * Test if get all membership quote register
     */
    @Test
    void getMembershipQuoteRegister() {
        assertTrue(DB.getInstance().getMembershipQuoteRegister());
    }

    /**
     * Test if get all competition
     */
    @Test
    void getCompetition() {
        assertTrue(DB.getInstance().getCompetition());
    }

    /**
     * Test if get all club member
     */
    @Test
    void getClubMembers() {
        assertTrue(DB.getInstance().getClubMembers());
    }

    /**
     * Test if get all competition register of a specific club member
     */
    @Test
    void getCompetitionRegisterByMemberId() {
        int idMemberTest = 18;
        assertTrue(DB.getInstance().getCompetitionRegisterByMemberId(idMemberTest));
    }

}