package com.seaclub.Manager;

import com.seaclub.Model.Boat;
import com.seaclub.Model.ClubMember;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class used for testing the principal method of Boat manager class
 */
class BoatManagerTest {
    private ClubMember testcm = new ClubMember();

    /**
     * Test if you get a list of boats with specific Club member
     */
    @Test
    void getMemberBoatsFromIDMember() {
        List<Boat> bt = new ArrayList<Boat>();
        testcm.setId(1);
        assertInstanceOf(bt.getClass(), BoatManager.getInstance().getMemberBoatsFromIDMember(testcm));
    }

    /**
     * Test if you get a boat with a specific ID
     */
    @Test
    void getBoatById() {
        assertInstanceOf(Boat.class, BoatManager.getInstance().getBoatById(11));
    }
}