package com.seaclub.Manager;

import com.seaclub.Model.ClubMember;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class used for testing the principal method of Club member manager class
 */
class ClubMemberManagerTest {

    /**
     * Test if return a club member with specific id and information updated
     */
    @Test
    void updateClubMember() {
        ClubMember testClubMember = new ClubMember();
        testClubMember.setId(5);
        assertInstanceOf(ClubMember.class,ClubMemberManager.getInstance().updateClubMember(testClubMember));
    }

    /**
     * Test if you get list of member expired
     */
    @Test
    void clubMemberExpired() {
        List<ClubMember> testMemberExpired = new ArrayList<ClubMember>();
        assertInstanceOf(testMemberExpired.getClass(),ClubMemberManager.getInstance().clubMemberExpired());
    }

    /**
     * Test if a club member with a specific CF already exist
     */
    @Test
    void checkCFalreadyExist() {
        ClubMember testClubMember = new ClubMember();
        testClubMember.setCF("n");
        assertTrue(ClubMemberManager.getInstance().checkCFalreadyExist(testClubMember));
    }

    /**
     * Test if you get a club member with specific CF
     */
    @Test
    void getMemberByCF() {
        ClubMember testClubMember = new ClubMember();
        testClubMember.setCF("n");
        assertInstanceOf(testClubMember.getClass(), ClubMemberManager.getInstance().getMemberByCF(testClubMember));
    }

    /**
     * Test if sends notifications and if there aren't new notification to send return true anyway
     */
    @Test
    void sendNotificationMembershipExpired() {
        assertTrue(ClubMemberManager.getInstance().sendNotificationMembershipExpired());
    }


    /**
     * Test if sends notifications and if there aren't new notification to send return true anyway
     */
    @Test
    void sendNotificationBoatExpired() {
        assertTrue(ClubMemberManager.getInstance().sendNotificationBoatExpired());
    }
}